/*
 *   @(#) $Id: AddMessage.java 355016 2005-12-08 07:00:30Z trustin $
 *
 *   Copyright 2004 The Apache Software Foundation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package com.eastcom.ipnet.orders.message;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 该类定义 dispatch server 发送到proxy server 的请求信息
 * 
 * @author localadmin
 * 
 */
public class D2SRequestMessage extends AbstractMessage {

	private static final long serialVersionUID = 8718639110399833152L;

	/**
	 * 请求参数集合
	 */
	HashMap<String, Object> paramMap = null;

	/**
	 * 指令序列
	 */
	private ArrayList<Instruction> instructions = null;

	/**
	 * 仿真终端的byte流，只在SIMTREM_RAW_REQUEST 中使用
	 */
	private byte[] rawBytes = null;

	public D2SRequestMessage() {

	}

	/**
	 * param.put("DeviceIPAddr","设备地址"); param.put("DeviceClass","设备类型");
	 * param.put("transport","连接方式 telnet/ssh1/ssh2");
	 * param.put("deviceFirstPass","一次登录口令");
	 * param.put("deviceFirstUser","一次登录用户");
	 * param.put("deviceSecondPass","二次登录口令");
	 * param.put("deviceSecondUser","二次登录用户");
	 * param.put("sessionid","用于匹配两端连接的session标识"); param.put("loginLevel",
	 * "登陆级别");
	 * 
	 * @param param
	 */
	public void setParamInfo(HashMap<String, Object> param) {

		paramMap = param;
	}

	public HashMap<String, Object> getParameter() {
		return paramMap;
	}

	/**
	 * @param instructions
	 *            The instructions to set.
	 */
	public void setInstructions(ArrayList<Instruction> instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return Returns the instructions.
	 */
	public ArrayList<Instruction> getInstructions() {
		return instructions;
	}

	/**
	 * @param rawBytes
	 *            the rawBytes to set
	 */
	public void setRawBytes(byte[] rawByteStream) {
		this.rawBytes = rawByteStream;
	}

	/**
	 * @return the rawBytes
	 */
	public byte[] getRawBytes() {
		return rawBytes;
	}

}
