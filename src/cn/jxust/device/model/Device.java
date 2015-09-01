package cn.jxust.device.model;

import cn.jxust.sms.model.SMS;


/**
 * 设备
 * @author lt
 */
public class Device {
	private Integer id; // 数据库表中的id
	private String deviceId; // 设备Id
	private String deviceName; //设备名称
	private SMS sms;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public SMS getSms() {
		return sms;
	}
	public void setSms(SMS sms) {
		this.sms = sms;
	}
}
