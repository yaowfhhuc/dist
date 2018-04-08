package orders.plugin;

public interface IDataReceive {
	public void onDataReceived(byte[] msg);
	public void onDataReceived(byte[] cmd, byte[] msg);
}
