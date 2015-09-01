package com.dabizi.amm.receiver;

import java.util.List;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import cn.jxust.device.model.Device;
import cn.jxust.device.model.Message;
import cn.jxust.device.service.DeviceService;
import cn.jxust.device.service.MessageService;


/**
 * 负责处理连接上来的客户机，即消息处理器
 */
public class AlarmMessageHandler extends IoHandlerAdapter {
	
	private MessageService messageService;
	private DeviceService deviceService;
	
	/**
	 * 当消息到达的时候调用，这里接收的message已经被累加过滤器封装成一个对象了
	 * 这里只要处理业务逻辑
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if(message.toString().equals("error")){ // 解码出现异常
			System.out.println("解码出错");
		}else{
			System.out.println("累加过滤器过滤啦");
			Message mi = (Message)message;
			// 将报文信息存入数据库
			String timeLaber = mi.getTimeLaber();
			// 转换格式 150722083151 
			StringBuffer sb = new StringBuffer();
			sb.append(timeLaber.substring(0, 2)+"年");
			sb.append(timeLaber.substring(2, 4)+"月");
			sb.append(timeLaber.substring(4, 6)+"日");
			sb.append(timeLaber.substring(6, 8)+"时");
			sb.append(timeLaber.substring(8, 10)+"分");
			sb.append(timeLaber.substring(10, 12)+"秒");
			mi.setTimeLaber(sb.toString());
			
			messageService.save(mi);
			
			// 保存设备信息
			List<Device> devices = deviceService.findBySQL("select * from device where deviceId = "+mi.getInfoID());
			if(devices == null||devices.size()==0){ // 设备在数据库中还没有保存
				Device device = new Device();
				device.setDeviceId(mi.getInfoID());
				deviceService.save(device);
			}
			
			// 得到mi的信息，判断是否报警
			System.out.println("Handler里:"+mi.getConcentration());
			System.out.println("Handler里:"+mi.getUnitStatus());
			// 根据浓度判断是否发短信
			
//			CallUtils callUtils = new CallUtils();
//			callUtils.sendMsg("2015年8月1日10时34分0秒某站点0号探测器报警。江西理工大学。【东震】","15779001007");
			// .................
			
			session.write("发送成功");
		}
	}
	
	// 一个客户端关闭时
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("one Client Disconnect");
	}

	// 一个客户端接入时
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("one Client Connection");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("接收信息出错");
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
}