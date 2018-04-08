package orders.webbean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ListRange implements Serializable {
	private Object[] data;

	private int totalSize;
	
	
	public ListRange(int totalSize,Object[] data){
		this.totalSize=totalSize;
		this.data=data;
	}
	public ListRange() {
		totalSize = 0;
		data = new Object[]{};
	}
	
	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}