package com.dabizi.point.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dabizi.point.model.PointInfo;
import com.dabizi.point.service.PointInfoService;

import cn.jxust.common.action.BaseDwzAction;

@Controller
@RequestMapping("/index")
public class PointInfoAction extends BaseDwzAction {
	
	@Resource
	private PointInfoService pointInfoService;
	
	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public void save(PointInfo info)
	{
		pointInfoService.save(info);
	}
	
	/**
	 * 从前台ajax传来的数据
	 * @param missionId
	 * @param model
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/PointInfo/savePointInfo.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> savePointInfo(String numId, String longitude, String latitude){
		PointInfo p = new PointInfo();
		p.setNumId(numId);
		p.setLongitude(longitude);
		p.setLatitude(latitude);
		save(p);
		return success("成功");
	}
}
