package cn.jxust.device.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.device.model.Message;
import cn.jxust.device.service.MessageService;
import cn.jxust.orm.PageData;
import cn.jxust.sms.service.SMSService;

@Controller
@RequestMapping("/device")
public class DeviceAction 
{
	@Resource
	private MessageService messageService;
	
	@RequestMapping("/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum)
	{
		ModelAndView mv = new ModelAndView();
		
		PageData<Message> pageData = new PageData<Message>(pageNum);
		pageData = messageService.getAll(pageData);
		mv.addObject(pageData);
		return mv;
	}
}
