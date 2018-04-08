package orders.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSeriUtil {

	public static byte[] objectToByte(Object object) throws IOException {
		if (object == null)
			return null;
		ObjectOutputStream oos = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 构造一个字节输出流
			oos = new ObjectOutputStream(baos); // 构造一个类输出流
			oos.writeObject(object); // 写这个对象
			oos.flush();
			return baos.toByteArray();
		} finally {
			if (oos != null)
				oos.close();
		}
	}

	public static Object byteToObject(byte[] buf) throws Exception {
		if (buf == null || buf.length == 0)
			return null;
		ObjectInputStream ois = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(buf);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} finally {
			if (ois != null)
				ois.close();
		}
	}

}
