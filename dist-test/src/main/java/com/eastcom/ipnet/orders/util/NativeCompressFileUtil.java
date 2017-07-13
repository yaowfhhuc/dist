package com.eastcom.ipnet.orders.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class NativeCompressFileUtil {
	private static final int MAX_ZIP_SIZE = 65535;
	private static Log logger = LogFactory.getLog(NativeCompressFileUtil.class);

	public static void compressDirectory(String inputDirName,
			String outputZipFileName) throws Exception {
		File dir = new File(inputDirName);
		if (!dir.exists() || !dir.isDirectory()) return;
		
		String outPutFileName = null;
		if (!outputZipFileName.endsWith(".zip")) {
			outPutFileName = outputZipFileName.concat(".zip");
		} else
			outPutFileName = outputZipFileName;
		
		File zipfile = new File(outPutFileName);			
		FileOutputStream fout = new FileOutputStream(zipfile);
		ZipOutputStream zout = new ZipOutputStream(fout);
		int count = 0;
		
		try{	
				for (File f:dir.listFiles()){	
					try{
						ZipEntry en = new ZipEntry(dir.getName() + "/" + f.getName());			
						zout.putNextEntry(en);
							
						FileInputStream in = new FileInputStream(f);	
						try{
							write(in, zout);
						} finally {
							in.close();
							zout.closeEntry();
						}
						count++;
						if (count >= MAX_ZIP_SIZE){
							logger.warn(zipfile + "的ZIP文件数量达到最大");							
						}
					} catch(Exception e){
						logger.error(f + ", " + e.getMessage(), e);
					}
				}				
			
		}  finally {
			if (count > 0){
				zout.close();
			} else {
				logger.info(dir + "下目录为空，不创建备份zip");
				fout.close();				
			}
		}
	}
	
	private static void write(InputStream in, OutputStream out) throws IOException {
		long size = 0;
		while (in.available() > 0) {
			byte[] buffer = new byte[16 * 1024];
			int c = in.read(buffer);			
			if (c > 0) {
				out.write(buffer, 0, c);
				out.flush();
				size += c;
			}
		}
	}
}
