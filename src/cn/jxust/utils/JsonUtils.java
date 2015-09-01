package cn.jxust.utils;

import java.util.Map;

import net.sf.json.JSONObject;


public class JsonUtils
{
@SuppressWarnings("unchecked")
	public static Map<String, Object> string2map(String s)
	{
		return (Map<String, Object>)JSONObject.fromObject(s);
	}
	
}
