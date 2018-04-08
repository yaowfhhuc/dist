package orders.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class SendExitUtil {
	private static final Logger log = Logger.getLogger(SendExitUtil.class);

	private LinkedList<String> exitcommands = new LinkedList<String>();

	private static SendExitUtil sendExitUtil;

	private void readFile() {
		BufferedReader r = null;
		try {
		    
		    String filePath = System.getProperty("workHome")
            + "/conf/sendexitcommand.txt";
   if (System.getProperty("os.name").toLowerCase().contains("windows"))
           filePath = System.getProperty("workHome")
              + "/WEB-INF/classes/sendexitcommand.txt";
			r = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			while (r.ready()) {
				String line = r.readLine();
				if (line != null && !line.trim().equals(""))
					exitcommands.add(line.trim());
			}

		} catch (Exception e) {
			log.error("读取exit文件出错" + e.getMessage(), e);
		} finally {
			//IOUtils.closeQuietly(r);
		    try
            {
                r.close();
            }
            catch (IOException e)
            {
              log.error(e.getMessage());
            }
		}
	}

	private SendExitUtil() {
		readFile();
	}

	public List<String> getSendExitCommand() {
		return exitcommands;
	}

	public static SendExitUtil getInstance() {
		synchronized (SendExitUtil.class) {
			if (sendExitUtil == null) {
				sendExitUtil = new SendExitUtil();
			}
			return sendExitUtil;
		}
	}

	public static String getCmd(String cmd) {
		String ss[] = cmd.split("#");
		if ("1".equals(ss[1])) {
			return ss[0] + "\n";
		}
		return ss[0];
	}

}
