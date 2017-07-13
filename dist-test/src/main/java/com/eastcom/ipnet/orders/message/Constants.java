package com.eastcom.ipnet.orders.message;

/**
 * Title: Followinds 广东巡检项目改造组 <br>
 * Description:共用常量定义类 <br>
 * 
 * @author xiazy<br>
 * @version 1.0 <br>
 * @creatdate 2012-3-19 下午02:32:01 <br>
 * 
 */
public class Constants {

	final public static String loginSuccessHead = "LOG_ID=ipnet.SimTermRequestSuccess|module=ipnet";

	final public static String loginFailHead = "LOG_ID=ipnet.SimTermRequestFail|module=ipnet";

	final public static String logoutHead = "LOG_ID=ipnet.SimTermLogout|module=ipnet";

	final public static String executeHead = "LOG_ID=ipnet.SimTermExecute|module=ipnet";

	// message type definition

	/**
	 * 请求建立控制连接 （ proxy server --> dispatch server)
	 */
	final public static int CONTROL_SESSION_REQUEST = 1;

	/**
	 * 控制连接是否建立的通知消息（ dispatch server --> proxy server)
	 */
	final public static int CONTROL_SESSION_ACK = 2;

	/**
	 * 请求建立数据连接 （ proxy server --> dispatch server)
	 */
	final public static int DATA_SESSION_REQUEST = 3;

	/**
	 * 数据连接是否允许建立的通知消息（ dispatch server --> proxy server)
	 */
	final public static int DATA_SESSION_ACK = 4;

	/**
	 * client 请求建立数据连接 ( proxy client --> dispatch server)
	 */
	final public static int SIMTERM_CONNECT_REQUEST = 5;

	/**
	 * dispatch server 向proxy server 请求建立数据通道（dispatch server --> proxy server )
	 */
	final public static int SIMTERM_REAL_CONNECT_REQUEST = 6;

	/**
	 * dispatch server 通知 proxy client 通道是否建立（dispatch server --> proxy client)
	 */
	final public static int SIMTERM_CHANNEL_ACK = 7;

	/**
	 * 仿真终端指令执行请求 (proxy client --> dispatch server 或 dispatch server --> proxy
	 * server)
	 */
	final public static int SIMTREM_RAW_REQUEST = 8;

	/**
	 * 仿真终端指令执行结果 （dispatch server --> proxy client 或 proxy server --> dispatch
	 * server)
	 */
	final public static int SIMTREM_RAW_RESPONSE = 9;

	/**
	 * 仿真终端指令执行请求 (proxy client --> dispatch server 或 dispatch server --> proxy
	 * server)
	 */
	final public static int SIMTREM_SCRIPT_REQUEST = 10;
	
	final public static int SIMTREM_SCTIPT_OFFLINE=16;

	/**
	 * 仿真终端指令执行结果 （dispatch server --> proxy client 或 proxy server --> dispatch
	 * server)
	 */
	final public static int SIMTREM_SCRIPT_RESPONSE = 11;

	/**
	 * 通知 批量指令执行 client的确认消息
	 */
	public static final int CLIENT_NOTIFY_OR_ACK = 13;

	/**
	 * 仿真终端命令退出请求(client --> dispatcher 或则 dispatcher --> proxy)
	 */
	public static final int DATA_CHANNEL_QUIT = 14;

	/**
	 * 通知仿真终端client channel 建立异常
	 */
	public static final int D2S_DATA_CHANNEL_SETUP_BROKEN = 15;

	// add by nedved,for session manager panel
	public static final int SESSION_MANAGER_REQUEST = 16;

	public static final int SESSION_FORCE_CLOSE = 17;

	public static final int SESSION_MANAGER_REQUEST_ACK = 18;

	public static final int SESSION_MANAGER_RESPONSE = 19;

	// public static final int SESSION_MANAGER_VIEW = 20;

	// operation
	public static final int LOCAL_COMMAND_REQUEST = 22;

	public static final int LOCAL_COMMAND_REQUEST_ACK = 23;

	public static final int LOCAL_COMMAND_STOP_SERVER = 24;

	public static final int LOCAL_COMMAND_STATUS_REQUEST = 25;

	public static final int LOCAL_COMMAND_STATUS_RESPONSE = 26;

	// log maintance

	// public static final int LOG_INFO_REQUEST = 27;

	// public static final int LOG_INFO_DELETE = 28;

	public static final int LOG_OPERATION_RESPONSE = 29;

	public static final int HEART_BEAT = 99;
	
	public static final int PROCESS_STATUS = 199;

	// 消息返回码定义

	final public static int D2S_CONTROL_CHANNEL_SETUP_OK = 10001;// 控制会话建立成功

	final public static int D2S_CONTROL_CHANNEL_SETUP_FAIL = 10002;// 控制会话建立失败

	final public static int D2S_DATA_CHANNEL_SETUP_OK = 10003;

	final public static int D2S_DATA_CHANNEL_SETUP_FAIL = 10004;

	final public static int D2C_SIMTERM_REQUEST_OK = 10005;

	final public static int D2C_SIMTERM_REQUEST_FAIL = 10006;
	
	final public static String D2C_SIMTERM_RWQUEST_MSG="request_msg";

	public static final int D2C_SESSION_MANAGER_REQUEST_OK = 10009;

	public static final int D2C_SESSION_MANAGER_REQUEST_FAIL = 10010;

	public static final int LOG_OPERATION_OK = 10020;

	public static final int LOG_OPERATION_FAIL = 10020;

	/**
	 * 仿真终端client
	 */
	public static final int CLIENT_SIM_TERMINAL = 1;// sim类型的client

	public static final int CLIENT_INTERNAL = 2;// internal类型的client

	public static final int CLIENT_SESSION_MANAGER = 3;

	public static final int CLIENT_LOCAL_COMMAND = 4;

	/**
	 * 访问协议 字符常量
	 */
	public static final String PROTOCOL_SSH = "SSH";

	public static final String PROTOCOL_SSH2 = "SSH2";

	public static final String PROTOCOL_TELNET = "TELNET";

	/**
	 * 访问协议 整数常量
	 */

	public static final int PROTOCOL_SSH_NUMBER = 1;// 表示SSH1

	public static final int PROTOCOL_SSH2_NUMBER = 2;

	public static final int PROTOCOL_TELNET_NUMBER = 3;

	public static final int PROTOCOL_SWIPE_NUMBER = 4;
	
	public static final int PROTOCOL_SNMP_NUMBER = 5;// 表示SNMP登录方式
	
	public static final int PROTOCOL_DB_NUMBER = 6; //DB登陆
	
	public static final int PROTOCOL_CORBA_NUMBER = 7; //corba登陆

	public static final int PROTOCOL_TELNET_DB_NUMBER = 8; //通过telnet登陆设备，在执行sql时，再加上指令（登陆DB信息）前后缀

	/**
	 * 绑定返回LOG文件内容的属性定义
	 */
	final public static String ATTR_LOG_RESPONSE = "ATTR_LOG_RESPONSE";

	public static final String ATTR_SESSION_ID = "sessionid";

	public static final String ATTR_OLD_SESSION_ID = "oldsessionid";

	public static final String ATTR_CLIENT_TYPE = "clientType";

	public static final String ATTR_DEV_USER = "devUser";

	public static final String ATTR_FACTORY = "factory";

	public static final String ATTR_CITY = "city";

	public static final String ATTR_ACCOUNT_AUTHORITY = "accAuthority";

	public static final String ATTR_USER_ACCOUNT = "userAccount";

	public static final String ATTR_DEV_PASSWORD = "devPass";

	public static final String ATTR_DEV_IPADDRESS = "devIPAddr";

	public static final String ATTR_DEVICE_NAME = "deviceName";
	
	public static final String ATTR_SESSION_TYPE = "session_type";

	public static final String ATTR_ACCESS_PORT = "accessPort";

	public static final String ATTR_TRANSPORT = "transport";

	public static final String ATTR_STATUS_RESPONSE = "DISPATCH_SERVER_STATUS";

	public static final String ATTR_PROXY_ID = "ProxyID";

	public static final String ATTR_CLIENT_AUTHENTICATED = "session_auth_status";

	public static final String ATTR_SESSION_PARAM = "session_login_parameter";

	public static final String ATTR_SETUP_TIME = "sessionCreateTime";

	public static final String ATTR_PEER_ADDRESS = "sessionPeerAddress";

	public static final String ATTR_LOGIN_USER = "sessionLoginUser";

	public static final String ATTR_LOGIN_CONFIG = "loginConfig";

	public static final String ATTR_LOGIN_COMMAND = "loginCmd";
	
	public static final String ATTR_END_MASK = "endMask";

	public static final String ATTR_COMMAND_PROMPT = "commandPrompt";

	public static final String ATTR_LOGIN_PROMPT = "loginPrompt";
	
	public static final String ATTR_BEFORE_COMMAND = "beforeCommand";
	
	public static final String ATTR_AFTER_COMMAND = "afterCommand";
	
	public static final String ATTR_LOGIN_SESSION_COUNT="sessionCount";

	public static final String ATTR_CALCELA_DEV_ACCOUNT="calcelDeviceAccount";
	
	public static final String ATTR_DEVICE_CODE="deviceCode";
	
	public static final String ATTR_CURRENT_LOGIN_USER="currentLoginUser";
	
	public static final String ATTR_CURRENT_USER_AUTHORITH="currentUserAuthority";
	
	public static final String ATTR_SU_USER_MAP="suUserMap";
	
	public static final String ATTR_DEVICE_TYPE_NAME="deviceTypeName";
	
	public static final String ATTR_MORE_TERM_QUERY="moreQuery";
	
	public static final String ATTR_OPER_COMMAND_TYPE = "operCommadType";
	

	public static final String ATTR_EXECUTE_RESULT = "EXECUTE_RESULT";
	
	// DB
	
	public static final String ATTR_DB_NAME = "dbName";
	
	public static final String ATTR_DB_DRIVER_CLASS_NAME = "dbDriverClassName";
	public static final String ATTR_DB_URL = "dbURL";
	public static final String ATTR_DB_USERNAME = ATTR_DEV_USER;//"dbUsername";
	public static final String ATTR_DB_PASSWORD = ATTR_DEV_PASSWORD;//"dbPassword";
	public static final String ATTR_DB_EXECUTE_RESULT = ATTR_EXECUTE_RESULT;
	
	//CORBA
	public static final String ATTR_CORBA_NAME = "corbaName";
	public static final String ATTR_CORBA_URL = "corbaURL";
	public static final String ATTR_CORBA_USERNAME = ATTR_DEV_USER;//"corbaUsername";
	public static final String ATTR_CORBA_PASSWORD = ATTR_DEV_PASSWORD;//"corbaPassword";
	public static final String ATTR_CORBA_CLASS = "corbaClass";
	public static final String ATTR_CORBA_VENDOR = "corbaVendor";
	public static final String ATTR_CORBA_EMS_INSTANCE = "corbaEmsInstance";
	public static final String ATTR_CORBA_VERSION = "corbaVersion";
	public static final String ATTR_CORBA_EMS_SESSION_FACTORY = "corbaEmsSessionFactory";
	
	
	/**
	 * 确认登录成功的等待时间
	 */
	public static final long LOGIN_CONFIRM_TIME = 2000;

	/**
	 * session日志目录存放时间，超过该时间将目录压缩打包放到backup目录
	 */
	public static final int LOG_KEEP_DAYS = 30;

	/**
	 * 是否启用解析BeanShell脚本常量标识
	 */
	public static final int ANALYTICAL_BEAN_SHELL_SCRIPT = 1;

	/**
	 * 是否启用解析Groovy脚本常量标识
	 */
	public static final int ANALYTICAL_GROOVY_SCRIPT = 2;

	/**
	 * 指令执行
	 */
	public static final int ANALYTICAL_COMMAND=0;
	
	/**
	 * 非交互，如修改指令平台数据库等操作
	 */
	public static final int NON_INTERACTIVE_COMMAND=-1;
	
	/**
	 * 在线脚本
	 */
	public static final int SCRIPT_ONLINE = 1;

	/**
	 * 离线脚本
	 */
	public static final int SCRIPT_OFFINELINE = 2;

	/**
	 * 放入脚本解析器中的key常量 ("ret", true);//执行脚本是否异常 ("descr", "");//描述
	 * ("prompt");//结束符 ("currentuserid");//切换用户时用到 ("terminal",
	 * terminal);//client端核心对象 ("islog", true);//是否记录日志功能 ("log","")//日志信息
	 */

	public static final String SCRIPT_RET = "_ret";

	public static final String SCRIPT_SUMMARY = "_summary";// 总结描述

	public static final String SCRIPT_DESCR = "_descr";

	public static final String SCRIPT_PROMPT = "_prompt";

	public static final String SCRIPT_CURRENTTUSERID = "currentuserid";

	public static final String SCRIPT_TERMINAL_CLIENT = "terminal";

	public static final String SCRIPT_LOGIN_PLUGIN = "iPlugin";

	public static final String SCRIPT_DATAHANDLER_CLIENT = "dh";

	public static final String SCRIPT_ISLOG = "_islog";

	public static final String SCRIPT_LOG = "_log";
	public static final String SCRIPT_COMMAND_LOG = "_command_log";
	
	public static final String SCRIPT_BUFFER_LOG = "_buffer_log";

	public static final String SCRIPT_ERROR = "__ERROR";

	public static final String SCRIPT_EXECUTE_SUCCESS = "success";

	public static final String SCRIPT_COMMAND_RESULT = "_rr";

	public static final String SCRIPT_USER_LOGIN_ERROR = "_userError";

	public static final String SCRIPT_USER_NAME_PASSWD_OBJECT = "_userPasswdObject";

	public static final String MORE_WAITFOR_COMMAND = "commPromtttt";

	public static final String CLIENT_DEVICE_INFO = "client_device_info";
	
	public static final String SCRIPT_DEVICE_OBJECT="device";//巡检专用的

	/**
	 * 账号权限
	 */
	public static final Integer ACCOUNT_AUTHORITY_METHOD_R = 1;// 只读

	public static final Integer ACCOUNT_AUTHORITY_METHOD_AUTO = 2;// 可配置的

	/**
	 * Groovy脚本基础类
	 */
	public static final String GROOVY_EXTRA_BASE = "baseExtra";

	/**
	 * 脚本执行过程中的一些状态码success
	 */
	public static final String SCRIPT_SUCCESS = "0";// 成功

	public static final String LOAD_CLIENT_XML_EX = "1001";// 加载clinet配置文件出错

	public static final String CONN_DISPATCHER_EX = "1002";// 与dispatcher建连接出错

	public static final String CONN_DISPATCHER_CHANNEL_EX = "1003";// 从dispatcher中得到数据通道出错

	public static final String SCRIPT_INIT_EX = "1004";// 脚本初始化环境失败

	public static final String SCTIPT_EXECUTE_EX = "1005";// 脚本或命令执行出错
	
	public static final String SCTIPT_EXECUTE_EX_LOGIN="1111";//登录设备失败
	
	public static final String WAIT_FOR_RESULT_TIMEOUT="1007";//获取结果日志超时
	
	public static final String ORDERS_NOT_FOUND_DEVICE="1006";//查询设备失败
	
	//db设备登录是否成功检测脚本
	public static final String SIMULATE_LOGIN_SCRIPT="try{ charOnScreen = waitfor(\"OK\", 20000); success = 1;}catch(Exception e){}";

	//外部系统调用指令平台一些操作
	public static final String ATTR_SYSTEM_OP="system.op";
	public static final String ATTR_SYSTEM_OP_STATUS="system.op.status";
	public static final int ATTR_SYSTEM_OP_ADD=1;
	public static final int ATTR_SYSTEM_OP_MODIFY=2;
	public static final int ATTR_SYSTEM_OP_DELETE=3;
	public static final int ATTR_SYSTEM_OP_CHANGE_PASSWD=4;
	public static final String ATTR_DEVICE_NAME_OLD="deviceName.old";
	
	
	
	
}
