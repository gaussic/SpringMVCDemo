/**
 * Copyright (C) 2015 Infinite Automation Software. All rights reserved.
 * @author Terry Packer
 */
package com.gaussic.util.modbus4j;

//import com.serotonin.modbus4j.serial.SerialPortWrapper;
import com.serotonin.modbus4j.serial.SerialPortWrapper;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * This class is not finished
 * 
 * @author Terry Packer
 *
 */
public class RtuSerialPortWrapper implements SerialPortWrapper {
//public class RtuSerialPortWrapper {

	private SerialPort serialPort;
	private String commPortId;
	private int baudRate;
	private int flowControlIn;
	private int flowControlOut;
	private int dataBits;
	private int stopBits;
	private int parity;

	public RtuSerialPortWrapper() {
		super();
	}

	public RtuSerialPortWrapper(String commPortId, int baudRate, int flowControlIn, int flowControlOut, int dataBits,
                                int stopBits, int parity) {
		this.commPortId = commPortId;
		this.baudRate = baudRate;
		this.flowControlIn = flowControlIn;
		this.flowControlOut = flowControlOut;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
		this.parity = parity;
	}

	public String getCommPortId() {
		return commPortId;
	}

	public void setCommPortId(String commPortId) {
		this.commPortId = commPortId;
	}

	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}

	public void setFlowControlIn(int flowControlIn) {
		this.flowControlIn = flowControlIn;
	}

	public void setFlowControlOut(int flowControlOut) {
		this.flowControlOut = flowControlOut;
	}

	public void setDataBits(int dataBits) {
		this.dataBits = dataBits;
	}

	public void setStopBits(int stopBits) {
		this.stopBits = stopBits;
	}

	public void setParity(int parity) {
		this.parity = parity;
	}

	@Override
	public void close() throws Exception {
		if (serialPort != null) {
			serialPort.close();
			serialPort = null;
		}
	}

	@Override
	public void open() throws Exception {
		System.out.println(commPortId);
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(commPortId);
		// 打开端口，并给端口名字和一个timeout（打开操作的超时时间）
		System.out.println(commPortId);
		CommPort commPort = portIdentifier.open(commPortId, 2000);
		// 判断是不是串口
		if (commPort instanceof SerialPort) {
			serialPort = (SerialPort) commPort;
			try {
				// 设置一下串口的波特率等参数
				// serialPort.setSerialPortParams(baudRate,
				// SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
				// SerialPort.PARITY_NONE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// System.out.println("Open " + portName + " sucessfully !");
			// return serialPort;
		} else {
			// 不是串口
			System.out.println("不是串口");
		}

	}
	@Override
	public InputStream getInputStream() {
		InputStream in = null;
		try {
			in = serialPort.getInputStream();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return in;
	}
	@Override
	public OutputStream getOutputStream() {
		OutputStream out = null;
		try {
			out = serialPort.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return out;
	}
	@Override
	public int getBaudRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStopBits() {
		// TODO Auto-generated method stub
		return this.stopBits;
	}

	@Override
	public int getParity() {
		// TODO Auto-generated method stub
		return this.parity;
	}

	@Override
	public int getFlowControlIn() {
		// TODO Auto-generated method stub
		return this.flowControlIn;
	}
	@Override
	public int getFlowControlOut() {
		// TODO Auto-generated method stub
		return this.flowControlOut;
	}
	@Override
	public int getDataBits() {
		// TODO Auto-generated method stub
		return this.dataBits;
	}


}
