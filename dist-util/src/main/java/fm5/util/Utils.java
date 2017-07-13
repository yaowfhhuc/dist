package fm5.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Utils {

	public static InetAddress getLocalHostAddress() throws SocketException,
			UnknownHostException {
		InetAddress localHost = InetAddress.getLocalHost();
		if (!localHost.isLoopbackAddress()) {
			return localHost;
		}
		Enumeration<NetworkInterface> nwifs = NetworkInterface
				.getNetworkInterfaces();
		while (nwifs.hasMoreElements()) {
			NetworkInterface nwif = nwifs.nextElement();
			if (!nwif.isUp() || nwif.isPointToPoint() || nwif.isVirtual()) {
				continue;
			}
			if (nwif.getName().startsWith("virbr")) {
				continue;
			}
			Enumeration<InetAddress> inetAddresses = nwif.getInetAddresses();
			while (inetAddresses.hasMoreElements()) {
				InetAddress address = inetAddresses.nextElement();
				if (address.isLoopbackAddress()) {
					continue;
				}
				if (address instanceof Inet4Address) {
					System.out.println(nwif.getDisplayName());
					System.out.println(nwif.getName());
					return address;
				}
			}
		}
		return localHost;
	}

}
