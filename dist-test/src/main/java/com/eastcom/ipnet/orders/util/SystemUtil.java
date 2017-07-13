package com.eastcom.ipnet.orders.util;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.DecimalFormat;

import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.ProcUtil;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import com.eastcom.ipnet.orders.message.ProcessInfo;

public class SystemUtil {
	
	static {
		addLibrary("../lib/sigar/");
	}
	
	private static long getPid() { 
		//获取进程的PID
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
        String name = runtime.getName(); // format: "pid@hostname"  
        try {  
            return Long.parseLong(name.substring(0, name.indexOf('@')));  
        } catch (Exception e) {  
            return -1;  
        }  
    }  
	
	

	private static DecimalFormat df = new DecimalFormat("#.00");


	public static void addLibrary(String libPath) {
		if (libPath == null) {
			return;
		}

		String path = new File(libPath).getAbsolutePath();
		String libs = System.getProperty("java.library.path");
		if (!libs.contains(path)) {
			libs += File.pathSeparator + path;
		}

		System.setProperty("java.library.path", libs);
	}

	public static double getCpuInfo() {
		Sigar sigar = new Sigar();
		try {
			double cpuUsage = Double.parseDouble(df.format(sigar.getCpuPerc()
					.getCombined() * 100));
			return cpuUsage;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sigar.close();
		}
		return -1;
	}

	public static double getMemInfo() {
		Sigar sigar = new Sigar();
		try {
			double memoryUsage = Double.parseDouble(df.format(sigar.getMem()
					.getUsedPercent()));
			return memoryUsage;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sigar.close();
		}
		return -1;
		
	}
	
	public static ProcessInfo getProcessInfo() {
		Sigar sigar = new Sigar();
		try {
			
			long pid = getPid();
				ProcessInfo processInfo = new ProcessInfo();
				processInfo.setPid((int) pid);
				try{
			      ProcMem mem = sigar.getProcMem(pid);
			      processInfo.setMemUsed(mem.getResident()/1024l);
			    } catch (SigarException e) {
			    	e.printStackTrace();
			    }
			    try{
		    		ProcCpu cpu = sigar.getProcCpu(pid);
		    		processInfo.setCpuUsage(Double.parseDouble(df.format(cpu.getPercent()*100)));
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
			    try {
					ProcState state=sigar.getProcState(pid);
					processInfo.setProcessState(getProcessState(state.getState()));
					processInfo.setProcessName(state.getName());
					String processDesc = ProcUtil.getDescription(sigar,pid);
					processInfo.setProcessDesc(processDesc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			    return processInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sigar.close();
		}
		return null;
	}
	
	/**
	 * @author wt
	 * @Description: 进程状态枚举
	 * @param state
	 * @return
	 * @date 2013-10-18 上午09:57:54 
	 */
	public static int getProcessState(char state) {
		switch (state) {
		case 'S':
			return 1;
		case 'R':
			return 2;
		case 'T':
			return 3;
		case 'Z':
			return 4;
		case 'D':
			return 5;
		default:
			return 2;
		}
		
	}

}
