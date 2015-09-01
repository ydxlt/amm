package com.dabizi.amm.mina.util;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import cn.jxust.device.model.Message;
import cn.jxust.sms.model.SMS;

import com.dabizi.amm.receiver.ControlStatus;
import com.dabizi.amm.receiver.ControlValue;
import com.dabizi.amm.receiver.DeviceStatus;
import com.dabizi.amm.receiver.GeneralFun;

/**
 * @author lt 自定义解码器，解决断包，粘包问题
 */
public class MyDecoder extends CumulativeProtocolDecoder {

	public static Logger log = Logger.getLogger(MyDecoder.class);

	/**
	 * 
	 * 这个方法的返回值是重点：
	 * 
	 * 1、当内容刚好时，返回false，告知父类接收下一批内容
	 * 
	 * 2、内容不够时需要下一批发过来的内容，此时返回false，这样父类 CumulativeProtocolDecoder
	 * 
	 * 会将内容放进IoSession中，等下次来数据后就自动拼装再交给本类的doDecode
	 * 
	 * 3、当内容多时，返回true，因为需要再将本批数据进行读取，父类会将剩余的数据再次推送本
	 * 
	 * 类的doDecode
	 */

	public boolean doDecode(IoSession session, IoBuffer in,

	ProtocolDecoderOutput out) throws Exception {

		log.info("in.remaining : " + in.remaining());

		if (in.remaining() > 0) {// 有数据时，读取前8字节判断消息长度
			if (session.getReadBytes()<=24) {// 如果消息内容还没结束，则重置，相当于不读取
				return false;// 父类接收新数据，以拼凑成完整数据
			} else {
				byte[] msg = new byte[in.limit()]; // 固定2000
				in.get(msg);
				// 把字节转换为Java对象的工具类
				Message mi = DeCoderToJavaObject(msg);
				if(mi!= null){
					out.write(mi);
				}else{
					out.write("error");
				}
				if (in.remaining() > 0) {// 如果读取内容后还粘了包，就让父类再重读 一次，进行下一次解析
					return true;
				}
			}
		}
		return false;// 处理成功，让父类进行接收下个包
	}
	
	public String byteToHex(byte[] src, long size) throws Exception {
		String ret = "";
		if (src == null || size <= 0) {
			return null;
		}
		for (int i = 0; i < size; i++) {
			String hex1 = Integer.toHexString(src[i] & 0xFF);

			if (hex1.length() < 2) {
				hex1 = "0" + hex1;
			}
			hex1 += " ";
			ret += hex1;
		}
		return ret.toUpperCase();
	}

	/**
	 * @param message:为二进制数据
	 * 
	 * 将二进制数据解码得到java对象
	 * 
	 * @author lt 
	 * 
	 * @throws IOException
	 */
	private Message DeCoderToJavaObject(byte[] msg) {
		
		Message mi = null;
		
		String startChar = ""; // 启动符
		int swiftN = 0; // 流水号
		int version = 0; // 版本号
		String date = ""; // 日期
		String ID = ""; // 信息模块ID
		int dataLen = 0; // 数据单元的长度
		int order = 0; // 命令
		int checkSum = 0; // 校验和
		String dataUnit = ""; // 数据单元
		String endChar = ""; // 结束符
		String printStr = "";
		
		ArrayList<ControlStatus> status;
		ArrayList<ControlValue> value;
		ArrayList<DeviceStatus> deviceStatu;
		
		try {
			
			String str = byteToHex(msg, msg.length);
			str = str.replace(" ", "");
			System.out.println(":::" + str);

			int flg = -1;
			int amount = -1;
			if (GeneralFun.verifyFun(str.substring(4, 42)).equals(
					str.substring(42, 44))) {
				String returnStr = "6464" + str.substring(4, 8)
						+ str.substring(8, 12) + str.substring(12, 24)
						+ str.substring(24, 36) + "0000" + GeneralFun.toHex(3);
				returnStr += GeneralFun.verifyFun(returnStr.substring(4, 42))
						+ "3535";
				
				mi = new Message();
				
				swiftN = Integer.parseInt(str.substring(4, 8), 16);//流水号
				version = Integer.parseInt(str.substring(8, 12), 16);//版本号
				date = str.substring(12, 24);//日期
				ID = str.substring(24, 36);//信息模块ID
				dataLen = Integer.parseInt(str.substring(36, 40), 16);//数据长度
				order = Integer.parseInt(str.substring(40, 42), 16);//命令
				
				mi.setSerialNum(swiftN);
				mi.setVersionNum(version);
				mi.setTimeLaber(date);
				mi.setInfoID(ID);
				mi.setDataLength(dataLen);
				mi.setCommand(order);
				
				//--------以上至此为止控制单元解析并写入Message对象完毕---------
				
				printStr = "----------------------------------\n";
				printStr += "流水号：" + swiftN + "\n";
				printStr += "版本号：" + version + "\n";
				printStr += "时间：15" + date + "\n";
				printStr += "ID号：" + ID + "\n";
				printStr += "数据长度：" + dataLen + "\n";
				printStr += "命令：" + order + "\n";
				
				if (dataLen > 0 && order == 2) {
					flg = Integer.parseInt(str.substring(44, 46), 16);
					amount = Integer.parseInt(str.substring(46, 48), 16);

					printStr += "数据类型：" + flg + "\n";
					printStr += "数据个数： " + amount + "\n";
					
					switch (flg) {
					case 1:
						printStr += "控制器状态：\n";
						status = new ArrayList<ControlStatus>();
						int j = 0;
						for (int i = 0; i < amount; i++) {
							// statu.add(new
							// ControlStatus(Integer.parseInt(str.substring(48+j,
							// 50+j),16), str.substring(50+j, 52+j),
							// Integer.parseInt(str.substring(52+j,
							// 56+j),16)));
							printStr += "控制器"
									+ (i + 1)
									+ "："
									+ "类型："
									+ Integer.parseInt(str.substring(48 + j, 50 + j), 16)
									+ "，地址：" + str.substring(50 + j, 52 + j)
									+ "，状态:" + str.substring(52 + j, 56 + j);
							
							int high = Integer.parseInt(
									str.substring(52 + j, 54 + j), 16);
							int low = Integer.parseInt(
									str.substring(54 + j, 56 + j), 16);
							
							String sstr = "";
							
							for (int k = 0; k < 3; k++) {
								if ((high & (1 << k)) != 0) {
									switch (k) {
									case 0:
										sstr += "系统启动\t";
										break;
									case 2:
										sstr += "系统总线故障\t";
										break;
									}
								} else {
									switch (k) {
									case 0:
										sstr += "系统停止\t";
										break;
									}
								}
							}
							
							for (int l = 0; l < 8; l++) {
								if ((low & (1 << l)) != 0) {
									switch (l) {
									case 0:
										sstr += "正常\t";
										break;
									case 1:
										sstr += "火警\t";
										break;
									case 2:
										sstr += "故障\t";
										break;
									case 3:
										sstr += "屏蔽\t";
										break;
									case 6:
										sstr += "主电故障\t";
										break;
									case 7:
										sstr += "备电故障\t";
										break;
									}
								} else {
									if (l == 0) {
										sstr += "测试\t";
									}
								}
							}
							j += 8;
							printStr += "--" + sstr + "\n";
						}
						break;
					case 2:
						deviceStatu = new ArrayList<DeviceStatus>();
						int jd = 0;
						printStr += "探测器状态：\n";
						for (int i = 0; i < amount; i++) {
							/*
							 * deviceStatu.add(new DeviceStatus(Integer.parseInt
							 * (str.substring(48+jd, 50+jd),16) ,
							 * str.substring(50+jd, 52+jd) ,
							 * Integer.parseInt(str.substring(52+jd, 54+jd),16)
							 * , str.substring(54+jd, 62+jd) ,
							 * Integer.parseInt(str.substring(62+jd,
							 * 64+jd),16)));
							 */

							int high = Integer.parseInt(
									str.substring(62 + jd, 64 + jd), 16);
							
							int low = Integer.parseInt(
									str.substring(64 + jd, 66 + jd), 16);
							
							int type = Integer.parseInt(
									str.substring(52 + jd, 54 + jd), 16);
							
							String sstr = "";
							if (type == 11) {
								for (int k = 0; k < 3; k++) {
									if ((low & (1 << k)) != 0) {
										switch (k) {
										case 0:
											sstr += "屏蔽\t";
											break;
										case 1:
											sstr += "连线故障\t";
											break;
										case 2:
											sstr += "探测器故障\t";
											break;
										}
									}
								}
								printStr += "探测器"
										+ (i + 1)
										+ "："
										+ "系统类型："
										+ Integer.parseInt(str.substring(48 + jd, 50 + jd), 16)
										+ ",系统地址："
										+ str.substring(50 + jd, 52 + jd)
										+ ",部件类型："
										+ Integer.parseInt(str.substring(52 + jd, 54 + jd), 16)
										+ ",部件地址："
										+ str.substring(54 + jd, 62 + jd)
										+ ",状态："
										+ str.substring(62 + jd, 66 + jd)
										+ sstr + "\n";
							} else if (type == 86) {
								for (int k = 0; k < 4; k++) {
									if ((low & (1 << k)) != 0) {
										switch (k) {
										case 0:
											sstr += "屏蔽\t";
											break;
										case 1:
											sstr += "连线故障\t";
											break;
										case 2:
											sstr += "低开关量\t";
											break;
										case 3:
											sstr += "高开关量\t";
											break;
										}
									}
								}
								printStr += "输出模块"
										+ (i + 1)
										+ "："
										+ "系统类型："
										+ Integer.parseInt(str.substring(48 + jd, 50 + jd), 16)
										+ ",系统地址："
										+ str.substring(50 + jd, 52 + jd)
										+ ",部件类型："
										+ Integer.parseInt(str.substring(52 + jd, 54 + jd), 16)
										+ ",部件地址："
										+ str.substring(54 + jd, 62 + jd)
										+ ",状态："
										+ str.substring(62 + jd, 66 + jd)
										+ sstr + "\n";
							}
							jd += 18;
						}
						break;
					case 7:
						printStr += "探测器浓度值：\n";
						value = new ArrayList<ControlValue>();
						int jv = 0;
						for (int i = 0; i < amount; i++) {
							/*
							 * value.add(new ControlValue(Integer.parseInt(str
							 * .substring(48+jv, 50+jv),16) ,
							 * str.substring(50+jv, 52+jv) ,
							 * Integer.parseInt(str.substring(52+jv, 54+jv),16)
							 * , str.substring(54+jv, 62+jv) ,
							 * str.substring(62+jv,66+jv) , Integer.parseInt(
							 * str.substring(66+jv,68+jv),16)));
							 */

							int low = Integer.parseInt(str.substring(66 + jv, 68 + jv), 16);
							String sstr = "";
							int bit0 = 0, bit4 = 0, bit5 = 0, bit6 = 0;
							if (low == 238) {
								sstr += "连线故障\t";
							} else
								for (int k = 0; k < 7; k++) {
									System.out.println("++++if判断之前的k+++"+k);
									if ((low & (1 << k)) != 0) {
										System.out.println("++++if判断之后k+++"+k);
										switch (k) {
										case 0:
											bit0 = 1;
											//TODO
											sstr += "低端报警\t";
											break;
										case 1:
											sstr += "低端报警\t";
											break;
										case 2:
											sstr += "高端报警\t";
											break;
										case 3:
											sstr += "探测器故障\t";
											break;
										case 4:
											bit4 = 1;
											break;
										case 5:
											bit5 = 1;
											break;
										case 6:
											bit6 = 1;
											break;
										}
									} else {
										switch (k) {
										case 0:
											bit0 = 0;
											break;
										case 4:
											bit4 = 0;
											break;
										case 5:
											bit5 = 0;
											break;
										case 6:
											bit6 = 0;
											break;
										}
									}
								}
							String unit = "";
							int scale = 0;
							String sss = "" + bit4 + bit0;
							if (sss.equals("00"))
								scale = 1;
							else if (sss.equals("01"))
								scale = 10;
							else if (sss.equals("10"))
								scale = 100;
							else
								scale = 1000;

							sss = "" + bit6 + bit5;
							if (sss.equals("00"))
								unit = "LEL";
							else if (sss.equals("01"))
								unit = "PPM";
							else if (sss.equals("10"))
								unit = "VOL";
							else
								unit = "PPB";

							printStr += "探测器"
									+ (i + 1)
									+ "："
									+ "，系统类型："
									+ Integer.parseInt(str.substring(48 + jv,50 + jv), 16)
									+ "，系统地址："
									+ str.substring(50 + jv, 52 + jv)
									+ "，部件类型："
									+ Integer.parseInt(str.substring(52 + jv,54 + jv), 16)
									+ "，部件地址："
									+ str.substring(54 + jv, 62 + jv)
									+ "，部件浓度："
									//TODO
									+ Integer.parseInt(str.substring(62 + jv,66 + jv)) / scale + "%" + unit
									+ "，部件状态："
									+ str.substring(66 + jv, 68 + jv) + sstr
									+ "\n";
									mi.setConcentration(Integer.parseInt(str.substring(62 + jv,66 + jv)) / scale + "%" + unit);
									mi.setUnitStatus(sstr.replaceAll("\\t",""));
							jv += 20;
						}
						break;
					}
				}
				printStr += "-------------------------------------------------";
				System.out.println(printStr);
			}

		} catch (Exception e) {
			System.out.println("数据解码异常");
			e.printStackTrace();
			return null;
		}
		return mi;
	}
}
