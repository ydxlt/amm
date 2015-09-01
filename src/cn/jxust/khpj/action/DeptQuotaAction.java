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
import cn.jxust.khpj.model.DeptQuota;
import cn.jxust.khpj.service.DeptQuotaService;
import cn.jxust.khpj.service.QuotaService;
import cn.jxust.orm.PageData;

@Controller
@RequestMapping("/khpj/deptQuota")
public class DeptQuotaAction extends BaseDwzAction
{
	@Resource
	private DeptQuotaService deptQuotaService;
	
	@Resource
	private QuotaService quotaService;

	@RequestMapping(value="/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum, DeptQuota entity)
	{
		PageData<DeptQuota> pageData = new PageData<DeptQuota>(pageNum);
		pageData = deptQuotaService.findPage(pageData);
		ModelAndView mv = new ModelAndView();
		mv.addObject(pageData);
		mv.addObject("qtypes", QuotaType.toMap());
		mv.setViewName("/khpj/deptQuota/list");
		return mv;
	}
	
	@RequestMapping(value = "/new.php")
	public String addForm(Model model)
	{
		model.addAttribute("quotas", quotaService.getAll());
		return "/khpj/deptQuota/input";
	}

	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") String id, Model model)
	{
		DeptQuota deptQuota = deptQuotaService.find(id);
		model.addAttribute(deptQuota);
		model.addAttribute("quotas", quotaService.getAll());
		return "/khpj/deptQuota/input";
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(DeptQuota deptQuota, String quotaId)
	{
		if (deptQuota.getDeptQuotaId() == null || "".equals(deptQuota.getDeptQuotaId().trim()))
		{
			deptQuotaService.save(deptQuota, quotaService.find(quotaId));
		} else
		{
			deptQuotaService.update(deptQuota, quotaService.find(quotaId));
		}

		return closeCurrentAndRefresh("deptQuotaList");
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(String[] items)
	{
		deptQuotaService.delete(items);
		return refresh("deptQuotaList");
	}
	
	/**
	 * 启用
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/enable/{id}.php")
	public @ResponseBody Map<String, String> enable(@PathVariable("id") String id)
	{
		deptQuotaService.enable(id);
		return refresh("deptQuotaList");
	}
	
	/**
	 * 禁用
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/disable/{id}.php")
	public @ResponseBody Map<String, String> disable(@PathVariable("id") String id)
	{
		deptQuotaService.disable(id);
		return refresh("deptQuotaList");
	}
}
