package fm5.datatask.idc;

public class SftpSource {

	private String username;
	private String password;
	private String remotePath;
	private String url;
	private int port;
	private String seperatekey;
	private String localPath;
	
	
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public String getSeperatekey() {
		return seperatekey;
	}
	public void setSeperatekey(String seperatekey) {
		this.seperatekey = seperatekey;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRemotePath() {
		return remotePath;
	}
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
}
