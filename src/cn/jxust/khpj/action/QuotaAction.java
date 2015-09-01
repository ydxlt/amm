package cn.jxust.khpj.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.enums.QuotaType;
import cn.jxust.khpj.model.Quota;
import cn.jxust.khpj.service.QuotaService;
import cn.jxust.orm.PageData;

@Controller
@RequestMapping("/khpj/quota")
public class QuotaAction extends BaseDwzAction
{
	@Resource
	private QuotaService quotaService;

	@RequestMapping("/list/{type}.php")
	public ModelAndView list(@PathVariable("type") String type, @RequestParam(defaultValue = "1") int pageNum, Quota entity)
	{
		PageData<Quota> pageData = new PageData<Quota>(pageNum);
		entity.setQuotaType(type);
		pageData = quotaService.findPage(pageData, entity);
		ModelAndView mv = new ModelAndView();
		mv.addObject(pageData);
		mv.addObject(type);
		mv.addObject("qtypes", QuotaType.toMap());
		mv.setViewName("/khpj/quota/list");
		return mv;
	}

	@RequestMapping(value = "/new/{type}.php")
	public String addForm(@PathVariable("type") String type, Model model)
	{
		model.addAttribute(type);
		return "/khpj/quota/input";
	}

	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") String id, Model model)
	{
		Quota quota = quotaService.find(id);
		model.addAttribute(quota);
		return "/khpj/quota/input";
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(Quota quota)
	{
		if (quota.getQuotaId() == null || "".equals(quota.getQuotaId().trim()))
		{
			quotaService.save(quota);
		} else
		{
			quotaService.update(quota);
		}

		return closeCurrentAndRefresh("quotaList" + quota.getQuotaType());
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(@PathVariable("id") String[] id)
	{
		quotaService.delete(id);
		return refresh("quotaList");
	}
}
