package orders.plugin.db.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import orders.message.Constants;
import orders.message.SqlMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eastcom.ipnet.orders.message.Constants;
import com.eastcom.ipnet.orders.message.SqlMessage;
import com.eastcom.ipnet.orders.message.SqlMessage.Mode;
import com.eastcom.ipnet.orders.message.SqlMessage.SqlParameter;
import com.eastcom.ipnet.orders.util.ObjectSeriUtil;

/**
 * 做连接SNMP设备操作
 * 
 * @author Administrator
 * 
 */

public class DbWrapper {
	private static Log logger = LogFactory.getLog(DbWrapper.class);
    private String driverClassName;
    private String dbUrl;
    private String dbUsername; 
    private String dbPassword;
    private Connection conn;
    

	public void connect(HashMap<String, Object> param) throws Exception {
		logger.info(param);
		driverClassName = (String) param.get(Constants.ATTR_DB_DRIVER_CLASS_NAME);
		dbUrl = (String) param.get(Constants.ATTR_DB_URL);
		dbUsername = (String) param.get(Constants.ATTR_DB_USERNAME);
		dbPassword = (String) param.get(Constants.ATTR_DB_PASSWORD);
		startOpenSession();
	}

	private void startOpenSession() throws Exception {
		Class.forName(driverClassName).newInstance();
	    conn= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
	    conn.setAutoCommit(true);
		logger.debug("DbWrapper: connect ok. db_url=" +  dbUrl);
	}

	public void disconnect() throws Exception {
		if (conn != null)
			conn.close();
		logger.debug("DbWrapper: disconnect db_url=" + dbUrl );
	}

	private void setAutoCommit(String sql) throws SQLException{
		sql = sql.replaceAll("\\s+", " ");
		if(sql.equals("set autocommit on")){
			conn.setAutoCommit(true);
		}else if(sql.equals("set autocommit off")){
			conn.setAutoCommit(false);
		}
	}
	
	private void commit() throws SQLException{
		conn.commit();
	}
	
	private void rollback() throws SQLException{
		conn.rollback();
	}

	private void autoConnect() throws Exception{
		if (conn.isClosed()) {
			startOpenSession();
		}
	}
	
	private List<Map<String, Object>> executeQuery(String sql) throws Exception {
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try{
			ps = conn.prepareStatement(sql);
			resultSet = ps.executeQuery();
			return buildResultSet(resultSet);
		} finally {
			if(resultSet!=null){
				resultSet.close();
			}
			if(ps!=null){
				ps.close();
			}
		}
	}
	private List<Map<String, Object>> executeQuery(String sql, LinkedHashMap<String, SqlMessage.SqlParameter> params) throws Exception {
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try{
			ps = conn.prepareStatement(sql);
			if(params!=null)
				setParameter(ps, params.values());
			resultSet = ps.executeQuery();
			return buildResultSet(resultSet);
		} finally {
			if(resultSet!=null){
				resultSet.close();
			}
			if(ps!=null){
				ps.close();
			}
		}
	}

	private List<Map<String, Object>> executeUpdate(String sql) throws Exception {
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>> ();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put(Constants.ATTR_DB_EXECUTE_RESULT, i);
			list.add(map);
			return list;
		} finally {
			if(ps!=null){
				ps.close();
			}
		}
	}

	
	private LinkedHashMap<String, SqlMessage.SqlParameter> executeProcedure(String sql, LinkedHashMap<String, SqlMessage.SqlParameter> params) throws Exception {

		CallableStatement proc = null;
		try {
			proc = conn.prepareCall("{" + sql + "}");
			if(params!=null)
				setParameter(proc, params.values());
			proc.execute();

			int i = 1;
			if (params != null) {
				for (SqlMessage.SqlParameter param : params.values()) {
					if (param.getMode() == SqlMessage.Mode.IN) {
						continue;
					}
					Object obj = null;
					switch (param.getSqlType()) {
					case Types.VARCHAR:
						obj = proc.getString(i);
						break;
					case Types.INTEGER:
						obj = proc.getInt(i);
						break;
					case Types.NUMERIC:
					case Types.DECIMAL:
						obj = proc.getDouble(i);
						break;
					case Types.DATE:
						obj = proc.getDate(i);
						break;
					case Types.TIMESTAMP:
						obj = proc.getTimestamp(i);
						break;
					default:
						obj = proc.getObject(i);
						break;
					}

					if (obj instanceof ResultSet) {
						ResultSet rs = (ResultSet) obj;
						try{
							param.setValue(buildResultSet(rs));
						}finally{
							if(rs!=null)
								rs.close();
						}
					} else {
						param.setValue(obj);
					}
					i++;
				}
			}
		} finally {
			if(proc!=null){
				proc.close();
			}
		}
		return params;
	}
	
	
	private void setParameter(PreparedStatement proc, Collection<SqlMessage.SqlParameter> params ) throws SQLException{
		int i =1;
		for(SqlMessage.SqlParameter param : params){
			switch(param.getSqlType()){
				case Types.VARCHAR:
					if(param.getMode()== SqlMessage.Mode.IN || param.getMode()== SqlMessage.Mode.BOTH)
						proc.setString(i, (String)param.getValue());
					break;
				case Types.INTEGER:
					if(param.getMode()== SqlMessage.Mode.IN || param.getMode()== SqlMessage.Mode.BOTH)
						proc.setInt(i, (Integer)param.getValue());
					break;
				case Types.NUMERIC:
				case Types.DECIMAL:
					if(param.getMode()== SqlMessage.Mode.IN || param.getMode()== SqlMessage.Mode.BOTH)
						proc.setDouble(i, (Double) param.getValue());
					break;
				case Types.DATE:
					if(param.getMode()== SqlMessage.Mode.IN || param.getMode()== SqlMessage.Mode.BOTH)
						proc.setDate( i, (java.sql.Date)param.getValue() );
					break;
				case Types.TIMESTAMP:
					if(param.getMode()== SqlMessage.Mode.IN || param.getMode()== SqlMessage.Mode.BOTH)
						proc.setTimestamp(i, (Timestamp) param.getValue());
					break;
				default:
					if(param.getMode()== SqlMessage.Mode.IN || param.getMode()== SqlMessage.Mode.BOTH)
						proc.setObject(i, param.getValue());
					break;
			}
			if (proc instanceof CallableStatement &&
					(param.getMode()== SqlMessage.Mode.OUT || param.getMode()== SqlMessage.Mode.BOTH) )
				((CallableStatement)proc).registerOutParameter(i, param.getSqlType());
			
			i++;
		}
	}
	
	private List<Map<String, Object>> buildResultSet(ResultSet resultSet) throws SQLException{
		LinkedHashMap<String, String> colunmName = new LinkedHashMap<String, String>();
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
			colunmName.put(resultSet.getMetaData().getColumnName(i),
					resultSet.getMetaData().getColumnTypeName(i));
		
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>> ();
		while (resultSet.next()) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			for (String key : colunmName.keySet()) {
				map.put(key, resultSet.getObject(key));
			}
			logger.debug(map);
			list.add(map);
		}
		return list;
	}
	
	public byte[] sendCommand(byte[] byteStream) throws Exception {
		Object result = null;
		String sql = new String(byteStream);
		SqlMessage complex = null;
		try{
			complex = (SqlMessage)ObjectSeriUtil.byteToObject(byteStream);
			sql = complex.getExeSql();
		}catch(Exception e){
//			e.printStackTrace();
//			logger.debug("", e);
		}
		logger.info("db 接收到的sql: " + sql);
		String sql_bak = sql.toLowerCase().trim();
		autoConnect();
		if(complex==null){
			if(sql_bak.startsWith("select ")){
				result = executeQuery(sql); 
			}else if(sql_bak.equals("commit")){
				commit();
				result = buildNormalResult();
			}else if(sql_bak.equals("rollback")){
				rollback();
				result = buildNormalResult();
			}else if(sql_bak.startsWith("set ")){
				setAutoCommit(sql);
				result = buildNormalResult();
			}else{
				result = executeUpdate(sql);
			}
		}else{
			if(sql_bak.startsWith("call ")){
				result = executeProcedure(sql, complex.getParams());
			}else if(sql_bak.startsWith("select ")){
				result = executeQuery(sql, complex.getParams()); 
			}
		}
		return ObjectSeriUtil.objectToByte(result);
	}
	
	private ArrayList<Map<String, Object>>  buildNormalResult(){
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>> ();
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put(Constants.ATTR_DB_EXECUTE_RESULT, "OK");
		list.add(map);
		return list;
	}

	
	public static void  main(String[] args) throws Exception{
		DbWrapper db = new DbWrapper();
		
		HashMap param = new HashMap<String, Object>();
		param.put(Constants.ATTR_DB_DRIVER_CLASS_NAME, "oracle.jdbc.driver.OracleDriver");
		param.put(Constants.ATTR_DB_URL,"jdbc:oracle:thin:@172.16.100.67:1521:fm5");
		param.put(Constants.ATTR_DB_USERNAME,"nhm");
		param.put(Constants.ATTR_DB_PASSWORD,"nhm#1390");
		db.connect(param);
	//	System.out.println(db.executeQuery("select * from dual"));
		SqlMessage b = new SqlMessage();
		SqlMessage.SqlParameter p = b.createSqlParameter();
		p.setMode(SqlMessage.Mode.BOTH);
		p.setName("test");
		p.setSqlType(Types.VARCHAR);
		p.setValue("id1");
		
		p = b.createSqlParameter();
		p.setMode(SqlMessage.Mode.OUT);
		p.setName("test");
		p.setSqlType(Types.INTEGER);
		p.setValue(0);

		
		p = b.createSqlParameter();
		p.setMode(SqlMessage.Mode.OUT);
		p.setName("test");
		p.setSqlType(OracleTypes.CURSOR);
		p.setValue(0);
		
//		List<SqlParameter>  l = db.executeProcedure("call firstPro(?,?,?)", params);
//		for(SqlParameter sp : l ){
//			System.out.println(sp.getValue());
//		}
		
		b.setExeSql("call firstPro(?,?,?)");
		
		byte[] bs = ObjectSeriUtil.objectToByte(b);
		db.sendCommand(bs);
		
		b = new SqlMessage();
		p = b.createSqlParameter();
		p.setMode(SqlMessage.Mode.BOTH);
		p.setName("test");
		p.setSqlType(Types.VARCHAR);
		p.setValue("id1");
		b.setExeSql("select * from T where id=?");
		bs = ObjectSeriUtil.objectToByte(b);
		db.sendCommand(bs);
	}
	
}
