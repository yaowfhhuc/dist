package org.dist.hadoop.hdfs;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.ipc.WritableRpcEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class HDFSTest 
    extends TestCase
{
	public static void main(String[] args) {
		try {
			FileSystem fs= FileSystem.get(URI.create("hdfs://192.168.190.128:9000"),new Configuration());
			FileStatus[] fileStatus = fs.listStatus(new Path("/"));
			for (FileStatus fileStatus2 :fileStatus){
				System.out.println(fileStatus2.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
