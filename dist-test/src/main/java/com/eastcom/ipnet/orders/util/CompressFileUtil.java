package com.eastcom.ipnet.orders.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CompressFileUtil {

	static Log logger = LogFactory.getLog(CompressFileUtil.class);

	public static void compressSingleFile(String inputFileName,
			String outputZipFileName) throws Exception {

		File f = new File(inputFileName);
		if (!f.isDirectory()) {
			if (!outputZipFileName.endsWith(".zip"))
				outputZipFileName.concat(".zip");
			zipFile(outputZipFileName, new File(inputFileName));
		} else
			logger.error("[" + inputFileName + "] is not a single file.");
	}

	public static void compressDirectory(String inputDirName,
			String outputZipFileName) throws Exception {
		String outPutFileName = null;
		if (!outputZipFileName.endsWith(".zip")) {
			outPutFileName = outputZipFileName.concat(".zip");
		} else
			outPutFileName = outputZipFileName;

		File f = new File(inputDirName);
		if (f.isDirectory())
			zipFile(outPutFileName, f);
		else {
			logger.error("[" + inputDirName + "] is not a directory");
		}
	}

	private static void zipFile(String zipFileName, File inputFile)
			throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		zip(out, inputFile, "");
		out.close();
	}

	private static void zip(ZipOutputStream out, File f, String base)
			throws Exception {
		if (f.isDirectory()) {
			File[] fileList = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fileList.length; i++) {
				zip(out, fileList[i], base + fileList[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

}
