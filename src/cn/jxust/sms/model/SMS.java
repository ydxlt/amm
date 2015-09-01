package cn.jxust.sms.model;

import java.io.Serializable;

import cn.jxust.device.model.Device;
import cn.jxust.device.model.Message;

/**
 * 短信
 * @author lt
 */
public class SMS implements Serializable {
	private Integer id;
	private Message message; // 协议信息
	private Device device; // 设备
	private String phonoNumbers; // 短信绑定的电话号码，多个电话号码之间用，隔开
	private String content; // 短信内容
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhonoNumbers() {
		return phonoNumbers;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public void setPhonoNumbers(String phonoNumbers) {
		this.phonoNumbers = phonoNumbers;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
}
