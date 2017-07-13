package me.test.util.unix.entry;

public class CorbaDeviceEntry extends DeviceEntry {
 	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String corbaName;
    private String corbaUrl;
    private String corbaUsername; 
    private String corbaPassword;
    private String classname;
    private String vendor;
    private String emsInstance;
    private String version;
    private String emsSessionFactory;
    
	public String getCorbaName() {
		return corbaName;
	}
	public void setCorbaName(String name) {
		this.corbaName = name;
	}
	public String getCorbaUrl() {
		return corbaUrl;
	}
	public void setCorbaUrl(String corbaUrl) {
		this.corbaUrl = corbaUrl;
	}
	public String getCorbaUsername() {
		return corbaUsername;
	}
	public void setCorbaUsername(String corbaUsername) {
		this.corbaUsername = corbaUsername;
	}
	public String getCorbaPassword() {
		return corbaPassword;
	}
	public void setCorbaPassword(String corbaPassword) {
		this.corbaPassword = corbaPassword;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getEmsInstance() {
		return emsInstance;
	}
	public void setEmsInstance(String emsInstance) {
		this.emsInstance = emsInstance;
	}
	public String getEmsSessionFactory() {
		return emsSessionFactory;
	}
	public void setEmsSessionFactory(String emsSessionFactory) {
		this.emsSessionFactory = emsSessionFactory;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
    
	
}
