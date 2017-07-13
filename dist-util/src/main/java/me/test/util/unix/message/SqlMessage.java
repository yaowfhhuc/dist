package me.test.util.unix.message;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class SqlMessage implements Serializable {

	public enum Mode {
		IN, OUT, BOTH
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String exeSql;

	private LinkedHashMap<String, SqlParameter> params = new LinkedHashMap<String, SqlParameter>();

	public SqlMessage(){
		
	}
	public String getExeSql() {
		return exeSql;
	}

	public void setExeSql(String exeSql) {
		this.exeSql = exeSql;
	}

	
	public LinkedHashMap<String, SqlParameter> getParams() {
		return params;
	}

	public void setParams(LinkedHashMap<String, SqlParameter> params) {
		this.params = params;
	}

	public void addSqlParameter(SqlParameter param) {
		this.params.put(param.getName(), param);
	}

	public void removeSqlParameter(String parameterName) {
		this.params.remove(parameterName);
	}

	public void getParameter(String parameterName) {
		this.params.get(parameterName);
	}
	
	public SqlParameter createSqlParameter() {
		return new SqlParameter();
	}

	public String toString(){
		String str = "exeSql: "+exeSql +", [";
		for(SqlParameter p : params.values()){
			str = str + " Parameters: (" + p.toString() + "),";
		}
		str =str+" ]";
		return str;
	}
	
	public class SqlParameter implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		private Object value;
		private Mode mode;
		private int sqlType;

		public SqlParameter(){
			
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public Mode getMode() {
			return mode;
		}

		public void setMode(Mode mode) {
			this.mode = mode;
		}

		public int getSqlType() {
			return sqlType;
		}

		public void setSqlType(int sqlType) {
			this.sqlType = sqlType;
		}
		
		public String toString(){
			return "name=" + name + ", value=" + value +", mode="  + mode + ", sqlType=" + sqlType;
		}

	}
}
