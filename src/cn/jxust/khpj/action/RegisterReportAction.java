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
import cn.jxust.khpj.model.RegisterReport;
import cn.jxust.khpj.service.RegisterReportService;
import cn.jxust.orm.PageData;
import cn.jxust.utils.DateUtils;

@Controller
@RequestMapping("/khpj/reports/register_report")
public class RegisterReportAction extends BaseDwzAction
{
	@Resource
	private RegisterReportService registerReportService;

	@RequestMapping(value = "/my.php")
	public ModelAndView my(@RequestParam(defaultValue = "1") int pageNum)
	{
		PageData<RegisterReport> pageData = new PageData<RegisterReport>(pageNum);
		String deptId = getCurrentDepartment().getDepartmentId();
		PageData<RegisterReport> datas = registerReportService.getReport(pageData, deptId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("datas", datas);
		mv.addObject("dept", getCurrentDepartment().getDepartmentName());
		mv.addObject("years", DateUtils.getYearList(5));
		mv.addObject("currentYear", DateUtils.getCurrentYear());
		mv.setViewName("/khpj/reports/register_report/my");
		return mv;
	}

	@RequestMapping(value = "/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum, RegisterReport entity)
	{
		PageData<RegisterReport> pageData = new PageData<RegisterReport>(pageNum);
		pageData = registerReportService.findPage(pageData, entity);
		ModelAndView mv = new ModelAndView();
		mv.addObject(pageData);
		mv.setViewName("/khpj/reports/register_report/list");
		return mv;
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public String save(RegisterReport registerReport, Model model)
	{
		registerReport.setDepartment(getCurrentDepartment());
		if (registerReport.getRegisterReportId() == null || "".equals(registerReport.getRegisterReportId().trim()))
		{
			registerReportService.save(registerReport);
		}
		else
		{
			registerReportService.update(registerReport);
		}
		model.addAttribute("tips", SUCCESS);
		return "/khpj/reports/register_report/my";
	}
	
	@RequestMapping(value = "/view/{id}.php")
	public String view(@PathVariable("id") String id, Model model)
	{
		model.addAttribute("data", registerReportService.find(id));
		return "/khpj/reports/register_report/view";
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody
	Map<String, String> delete(@PathVariable("id") Integer[] id)
	{
		registerReportService.delete(id);
		return refresh("list");
	}
}
