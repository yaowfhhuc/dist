package com.eastcom.ipnet.orders.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import com.eastcom.ipnet.orders.entry.AccountEntry;
import com.eastcom.ipnet.orders.entry.AccountEntryRmi;
import com.eastcom.ipnet.orders.entry.CorbaDeviceEntry;
import com.eastcom.ipnet.orders.entry.DbDeviceEntry;
import com.eastcom.ipnet.orders.entry.DeviceEntry;
import com.eastcom.ipnet.orders.entry.DeviceEntryRmi;
import com.eastcom.ipnet.orders.entry.ScriptEntry;
import com.eastcom.ipnet.orders.webbean.TreeNode;

public class EntryRowMapper {
	private static Logger logger = Logger.getLogger(EntryRowMapper.class);

	//SELECT  c.NAME as \"accname\",c.PASSWORD  as \"password\",c.TYPE  as \"acctype\",d.IP  as \"ip\",
	//d.NAME  as \"devname\",f.LOGIN_COMMAND  as \"loginScript\",f.PROMPT  as \"prompt\
	public static RowMapper getAccountEntryMapper(){
        return new RowMapper()
       {
           @Override
           public Object mapRow(ResultSet ret, int arg1) throws SQLException
           {
               DeviceEntryRmi d=new DeviceEntryRmi();
               AccountEntryRmi ac=new AccountEntryRmi();
               String accName=ret.getString("accname");
               String devname=ret.getString("devname");
               String passwd=ret.getString("password");
               try
             {
                passwd=PasswordCryptUtil.getInstance().DECODE(devname,accName,passwd);
             }
             catch (Exception e)
             {
              logger.error("解密出错"+ e.getMessage());
             }//解密
               ac.setName(accName);
               ac.setPassword(passwd);
               ac.setAccounType(ret.getString("acctype"));
               ac.setLoginScript(ret.getString("loginScript"));
               ac.setSuccessWaitfor(ret.getString("prompt"));
               d.setDeviceName(devname);
               d.setLoginIp(ret.getString("ip"));
               d.setAccountEntity(ac);
               return d;
           }
       };
   }
	
	
	
	
	public static RowMapper getScriptEntryMapper(){
	     return new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet ret, int arg1) throws SQLException
            {
                ScriptEntry scriptEntry=new ScriptEntry();
                scriptEntry.setCommand(ret.getString("command"));
                scriptEntry.setScriptTxt(ret.getString("scriptTxt"));
                scriptEntry.setDeviceName(ret.getString("name"));
                scriptEntry.setDeviceId(ret.getString("deviceId"));
                scriptEntry.setCity(ret.getString("city"));
                scriptEntry.setFactory(ret.getString("factory"));
                return scriptEntry;
            }
        };
	}
	
	
	
	
	
	public static RowMapper getDeviceEntryMapper() {
	    
		return new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				DeviceEntry entry = new DeviceEntry();
				entry.setDeviceIP(rs.getString("deviceIp"));
				entry.setDeviceName(rs.getString("deviceName"));
				entry.setSessionCount(rs.getInt("sessionCount"));
				entry.setProxyIp(rs.getString("proxyIp"));
				entry.setUserName(rs.getString("userName"));
				if (!StringUtils.isEmpty(entry.getUserName()))
					try {
						entry.setPassword(PasswordCryptUtil.getInstance()
								.DECODE(entry.getDeviceName(),
										entry.getUserName(),
										rs.getString("password")));
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				entry.setTelnetType(rs.getInt("telnetType"));
				entry.setPort(rs.getInt("port"));
				entry.setLoginCmd(rs.getString("loginCmd"));
				entry.setPrompt(rs.getString("prompt"));
				entry.setSwipDeviceId(rs.getString("swipeDeviceId"));
				entry.setCommandPrompt(rs.getString("commandPrompt"));
				entry.setEndMask(rs.getString("endMask"));
				entry.setDeviceCode(rs.getString("deviceCode"));
				entry.setBeforeCommand(rs.getString("beforeCommand"));
				entry.setAfterCommand(rs.getString("afterCommand"));
				entry.setDeviceAlias(rs.getString("alias"));
				
				return entry;
			}
		};
	}

	public static RowMapper getTreeNodeRowMapper(final String type,
			final boolean leaf) {
		return new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TreeNode node = new TreeNode();
				node.setId(rs.getString("id"));
				node.setText(rs.getString("name"));
				node.setType(type);
				node.setLeaf(leaf);
				return node;
			}
		};
	}
	
	
public static RowMapper getDownDeviceEntryMapper() {
        return new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                DeviceEntry entry = new DeviceEntry();
                entry.setDeviceIP(rs.getString("deviceIp"));
                entry.setDeviceName(rs.getString("deviceName"));
                entry.setUserName(rs.getString("userName"));
//                if (!StringUtil.isBlank(entry.getUserName()))
//                    try {
//                        entry.setPassword(PasswordCryptUtil.getInstance()
//                                .DECODE(entry.getDeviceName(),
//                                        entry.getUserName(),
//                                        rs.getString("password")));
//                    } catch (Exception e) {
//                        logger.error(e.getMessage(), e);
//                    }
                entry.setPort(rs.getInt("port"));
                entry.setLoginCmd(rs.getString("loginCmd"));
                entry.setPrompt(rs.getString("prompt"));
                return entry;
            }
        };
    }



public static RowMapper getAcconUserPasswdEntryMapper() {
return new RowMapper() {
    public Object mapRow(ResultSet rs, int arg1) throws SQLException {
        AccountEntry entry = new AccountEntry();
        entry.setUser(rs.getString("user"));
        entry.setPassword(rs.getString("password"));
        entry.setSuScript(rs.getString("suscript"));
        entry.setPrompt(rs.getString("prompt"));
        return entry;
    }
};

}

public static RowMapper getDbDeviceEntryMapper() {
    
    return new RowMapper() {
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            DbDeviceEntry entry = new DbDeviceEntry();
            entry.setDeviceIP(rs.getString("deviceIp"));
            entry.setDeviceName(rs.getString("deviceName"));
            entry.setSessionCount(rs.getInt("sessionCount"));
            entry.setProxyIp(rs.getString("proxyIp"));
            entry.setCommandPrompt(rs.getString("commandPrompt"));
            entry.setEndMask(rs.getString("endMask"));
            entry.setDeviceCode(rs.getString("deviceCode"));
            entry.setDbName(rs.getString("dbName"));
            entry.setDriverClassName(rs.getString("deviceClassName"));
            entry.setDbUrl(rs.getString("dbUrl"));
            entry.setDbUsername(rs.getString("dbUsername"));
            entry.setDbPassword(rs.getString("dbPassword"));
            return entry;
        }
    };
}


public static RowMapper getCorbaDeviceEntryMapper() {
    
    return new RowMapper() {
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            CorbaDeviceEntry entry = new CorbaDeviceEntry();
            entry.setDeviceIP(rs.getString("deviceIp"));
            entry.setDeviceName(rs.getString("deviceName"));
            entry.setSessionCount(rs.getInt("sessionCount"));
            entry.setProxyIp(rs.getString("proxyIp"));
            entry.setCommandPrompt(rs.getString("commandPrompt"));
            entry.setEndMask(rs.getString("endMask"));
            entry.setDeviceCode(rs.getString("deviceCode"));
            entry.setCorbaName(rs.getString("corbaName"));
            entry.setCorbaUsername(rs.getString("corbaUsername"));
            entry.setCorbaPassword(rs.getString("corbaPassword"));
            entry.setClassname(rs.getString("corbaClass"));
            entry.setCorbaUrl(rs.getString("corbaUrl"));
            entry.setVendor(rs.getString("corbaVendor"));
            entry.setEmsInstance(rs.getString("corbaEmsInstance"));
            entry.setVersion(rs.getString("corbaVersion"));
            entry.setEmsSessionFactory(rs.getString("corbaEmsSessionFactory"));
            return entry;
        }
    };
}


public static RowMapper getBusDeviceEntryMapper() {
    
    return new RowMapper() {
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("id", rs.getString("id"));
        	map.put("serviceSys", rs.getString("serviceSys"));
        	map.put("deviceIp", rs.getString("deviceIp"));
        	map.put("deviceName", rs.getString("deviceName"));
        	map.put("deviceType", rs.getString("deviceType"));
        	map.put("factory", rs.getString("factory"));
        	map.put("city", rs.getString("city"));
            return map;
        }
    };
}

public static RowMapper getCountEntryMapper() {
    
    return new RowMapper() {
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
        	Long count = rs.getLong("num");
        	return count;
        }
    };
}

}
