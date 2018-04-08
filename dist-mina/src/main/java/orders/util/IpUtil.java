/*
 * Created on 2006-1-4
 */
package orders.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Title: Followinds 广东指令平台改造组 <br>
 * Description: <br>
 * @author xiazy<br>
 * @version 1.0 <br>
 * @creatdate 2012-3-21 下午02:38:05 <br>
 *
 */
public class IpUtil {
	@SuppressWarnings("unused")
	private static final String validLetters = "abcdefghijklmnopqrstuvwxyz0123456789";
	@SuppressWarnings("unused")
	private static final Random rand = new Random();

	/**
	 * 该整数是否是合法的ip地址段（0～255）
	 * 
	 * @param i
	 * @return
	 */
	public static boolean validIpSection(int i) {
		return i < 0 || i > 255;
	}

	/**
	 * 该字符串是否是合法的ip地址段（0～255）
	 * 
	 * @param s
	 * @return
	 */
	public static boolean validIpSection(String s) {
		if (s == null || s.trim().equals(""))
			return false;
		try {
			int i = Integer.parseInt(s);
			if (i < 0 || i > 255)
				return false;
			else
				return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	/**
	 * 某个段转换为字符串如：128转换为"10000000"
	 * @param i
	 * @return
	 */
	private static String ipSection2Binary(int ipsec) {
		String binary = Integer.toBinaryString(ipsec);
		while (binary.length() < 8) {
			binary = "0" + binary;
		}
		return binary;
	}
	/**
	 * 将十进制ip地址串转换为二进制ip地址串，如1.1.1.1转换为："00000001.00000001.00000001.00000001"
	 * @param ipaddr
	 * @return
	 */
	public static String ip2BinaryString(String ipaddr) {
		StringTokenizer st = new StringTokenizer(ipaddr, ".");
		String result = null;
		while (st.hasMoreTokens()) {
			if (result== null) {
				result = "";
			} else {
				result += ".";
			}
			result += ipSection2Binary(Integer.parseInt(st.nextToken()));
		}
		return result;
	}

	/**
	 * 把ip地址转换为长整型
	 * 
	 * @param ip
	 *            String
	 * @return long
	 */
	public static long ip2Long(String ip) {
		StringTokenizer st = new StringTokenizer(ip, ".");
		long returnIP = 0;
		if (st.countTokens() != 4) {
			return -1;
		}
		while (st.hasMoreTokens()) {
			returnIP = (returnIP << 8) + Integer.parseInt(st.nextToken());
		}
		return returnIP;
	}

	/**
	 * 根据起始终止地址获取  整个连续地址段
	 * @param ipStart
	 * @param ipEnd
	 * @return
	 */
	public static List<String> getIPList(String ipStart,String ipEnd){
		List<String> list=new ArrayList<String>();
		long start=ip2Long(ipStart);
		long end=ip2Long(ipEnd);
		for(long i=start;i<=end;i++){
			list.add(long2Ip(i));
		}
		return list;
	}

	/**
	 * 把长整型转换为ip地址
	 * 
	 * @param ip
	 *            long
	 * @return String
	 */
	public static String long2Ip(long ip) {
		String partString = "";
		long ipTemp = ip;
		long ipPart = -1;
		while (true) {
			ipPart = ipTemp % 256;
			ipTemp = ipTemp >> 8;
		partString = String.valueOf(ipPart) + "." + partString;
		if (ipTemp < 1) {
			break;
		}
		}
		return partString.substring(0, partString.length() - 1);
	}

	/**
	 * 提供一个一般的地址（如192.168.1.250），以及掩码的长度（如24），得到网络地址（如192.168.1.0）
	 * 
	 * @param len
	 * @return
	 */
	public static long getNetworkAddr(long generalAddr, int maskLength) {
		return generalAddr & (long)(Math.pow(2, 32) - 1 - (Math.pow(2, 32 - maskLength) - 1));
	}


	/**
	 * 根据起始地址,终止地址,掩码长度,获取可使用的网段地址
	 * @param startIp
	 * @param endIp
	 * @param maskLength
	 * @return
	 */
	public static long[] getAvailableNetwork(String startIp, String endIp, int maskLength) {
		long startNetwork = getNetworkAddr(ip2Long(startIp), maskLength);
		long endNetwork = getNetworkAddr(ip2Long(endIp), maskLength);
		long step = (long)Math.pow(2, 32 - maskLength);
		int count = (int)((endNetwork - startNetwork) / step);
		long[] result = new long[count + 1];
		for(int i = 0; i <= count ; i++) {
			result[i] = startNetwork;
			startNetwork += step;
		}
		return result;
	}


	/**
	 * 根据网段地址，和所需要的子网掩码长度，获得可使用的子网地址
	 * @param networkAddr 网段地址
	 * @param networkMaskLength 网段掩码长度
	 * @param subnetMaskLength 子网掩码长度
	 * @return
	 */
	public static long[] getAvailableSubnet(String networkAddr, int networkMaskLength, int subnetMaskLength) {
		int count = (int)Math.pow(2, subnetMaskLength - networkMaskLength);
		long[] result = new long[count];
		for (int i = 0; i < count; i++) {
			result[i] = ip2Long(networkAddr) + (long)(i * Math.pow(2, 32 - subnetMaskLength));
		}
		return result;
	}

	/**
	 * 根据子网地址和掩码，获得所有的其下的ip地址
	 * @param subnetAddr
	 * @param maskLength
	 * @return
	 */
	public static long[] getAvailableIpAddr(String subnetAddr, int maskLength) {
		int count = (int)Math.pow(2, 32 - maskLength);
		long[] result = new long[count];
		for (int i = 0; i < count; i++) {
			result[i] = ip2Long(subnetAddr) + i;
		}
		return result;
	}

	/**
	 * 根据网段的起始ip地址和掩码长度，获得终止ip地址
	 * @param ip String
	 * @param maskLength int
	 * @return String
	 */
	public static String getDestinationIp(String startIp, int maskLength) {
		long startIpLong = ip2Long(startIp);
		startIpLong >>>= (32 - maskLength);
		startIpLong <<= (32 - maskLength);
		startIpLong += Math.pow(2, 32 - maskLength) - 1;
		return long2Ip(startIpLong);
	}

	/**
	 * 根据ip地址,获得最小的网络掩码长度
	 * 原则:按此此ip地址归属的最小网络计算
	 * 
	 * @param segAddr
	 * @return
	 */
	public static int getMinMaskLength(String segAddr) {
		long addr = ip2Long(segAddr);
		int i = 32;
		for (; i > 8; i--) {
			int remain = 32 - i;
			long weight = (long)Math.pow(2, remain + 1);
			if (addr % weight != 0) {
				break;
			}
		}
		return i;
	}


	public static void displayMessage(HashMap message) {
		Set keySet = message.keySet();
		Iterator itor = keySet.iterator();
		while (itor.hasNext()) {
			Object key = itor.next();
			@SuppressWarnings("unused")
			Object value = message.get(key);
		}
	}

	/**
	 * 生成UUID
	 * @param len
	 * @return
	 */
	public static String generateUUID(int len) {
		String result = "";
		//			UUIDHexGenerator gen = new UUIDHexGenerator();
		//			result = (String) gen.generate();
		//			for (int i = 0; i < len; i++) {
		//				int idx = rand.nextInt(validLetters.length());
		//				result += validLetters.charAt(idx);
		//			}
		return result;
	}
	/**
	 * 比较两个Ip的大小 
	 * @param startIp
	 * @param endIp
	 * @return 
	 */
	public static int compareIp(String startIp,String endIp){
		int flag=0;
		String startips[]=startIp.split("\\."); 
		String endIps[]=endIp.split("\\."); 
		for(int i=0;i<startips.length;i++){ 
			if(Integer.parseInt(endIps[i])>Integer.parseInt(startips[i])){ 
				flag=1; 
				break; 
			}else{ 
				if(Integer.parseInt(endIps[i])==Integer.parseInt(startips[i])){ 
					continue;
				}else{ 
					flag=-1; 
					break;
				} 
			}
		}
		return flag;
	}

		/**
		 * 字符串比较
		 * @param oldString
		 * @param newString
		 * @return
		 */
		public static String compare(String oldString, String newString) {
			if (oldString != null && oldString.trim().equals("")) {
				oldString = null;
			}
			if (newString != null && newString.trim().equals("")) {
				newString = null;
			}
			if (oldString == null && newString == null) {
				return null;
			} else if (oldString == null) {
				return "null -> " + newString;
			} else if (newString == null) {
				return oldString + " -> null";
			} else if (oldString.equals(newString)) {
				return null;
			} else {
				return oldString + " -> " + newString;
			}
		}

		/**
		 * 整数比较
		 * @param oldInt
		 * @param newInt
		 * @return
		 */
		public static String compare(int oldInt, int newInt) {
			if (oldInt == newInt) {
				return null;
			} else {
				return oldInt + " -> " + newInt;
			}
		}

		/**
		 * 日期比较
		 * @param oldDate
		 * @param newDate
		 * @return
		 */
		public static String compare(Date oldDate, Date newDate) {
			if (oldDate == null && newDate == null) {
				return null;
			} else if (oldDate == null) {
				return "null -> " + new Timestamp(newDate.getTime());
			} else if (newDate == null) {
				return new Timestamp(oldDate.getTime()) + " -> null";
			} else if (oldDate.equals(newDate)) {
				return null;
			} else {
				return new Timestamp(oldDate.getTime()) + " -> " + new Timestamp(newDate.getTime());
			}
		}

		/**
		 * 比较日志
		 * @param compareLog
		 * @param title
		 * @param newCompareLog
		 * @return
		 */
		public static String appendCompare(String compareLog, String title, String newCompareLog) {
			if (newCompareLog == null) {
				return compareLog;
			} else if (compareLog == null) {
				return newCompareLog;
			} else {
				return compareLog + "；" + title + "（" + newCompareLog + "）";
			}
		}

		/**
		 * 
		 * @param startIp
		 * @param segAddr
		 * @return
		 * 开始ip、结束ip、掩码长度 
		 */
		public long[] getLongSection(String startIp, String maskLen) {
			long startLong = IpUtil.ip2Long(startIp);		
			String di = IpUtil.getDestinationIp(startIp, Integer.valueOf(maskLen));
			long endLong = IpUtil.ip2Long(di);

			return new long[]{startLong, endLong, Integer.valueOf(maskLen)};
		}

		public static void main(String[] args) {
	        System.out.println(compareIp("1.1.10.1", "1.1.1.3"));

		}

	}