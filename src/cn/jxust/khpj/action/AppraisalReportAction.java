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
import cn.jxust.khpj.model.AppraisalReport;
import cn.jxust.khpj.service.AppraisalReportService;
import cn.jxust.orm.PageData;
import cn.jxust.utils.DateUtils;

@Controller
@RequestMapping("/khpj/reports/appraisal_report")
public class AppraisalReportAction extends BaseDwzAction
{
	@Resource
	private AppraisalReportService appraisalReportService;
	
	@RequestMapping(value = "/my.php")
	public ModelAndView my(@RequestParam(defaultValue = "1") int pageNum)
	{
		PageData<AppraisalReport> pageData = new PageData<AppraisalReport>(pageNum);
		String deptId = getCurrentDepartment().getDepartmentId();
		PageData<AppraisalReport> datas = appraisalReportService.getReport(pageData, deptId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("datas", datas);
		mv.addObject("dept", getCurrentDepartment().getDepartmentName());
		mv.addObject("years", DateUtils.getYearList(5));
		mv.addObject("currentYear", DateUtils.getCurrentYear());
		mv.setViewName("/khpj/reports/appraisal_report/my");
		return mv;
	}
	
	@RequestMapping(value = "/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum, AppraisalReport entity){
		PageData<AppraisalReport> pageData = new PageData<AppraisalReport>(pageNum);
		pageData = appraisalReportService.findPage(pageData, entity);
		ModelAndView mv = new ModelAndView();
		mv.addObject(pageData);
		mv.setViewName("/khpj/reports/appraisal_report/list");
		return mv;
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public String save(AppraisalReport appraisalReport, Model model)
	{
		appraisalReport.setDepartment(getCurrentDepartment());
		if (appraisalReport.getAppraisalReportId() == null || "".equals(appraisalReport.getAppraisalReportId().trim()))
		{
			appraisalReportService.save(appraisalReport);
		} else
		{
			appraisalReportService.update(appraisalReport);
		}
		model.addAttribute("tips", SUCCESS);
		return "/khpj/reports/appraisal_report/my";
	}
	
	@RequestMapping(value = "/edit/{id}.php")
	public String edit(@PathVariable("id") String id, Model model)
	{
		model.addAttribute("data", appraisalReportService.find(id));
		return "/khpj/reports/appraisal_report/my";
	}
	
	@RequestMapping(value = "/view/{id}.php")
	public String view(@PathVariable("id") String id, Model model)
	{
		model.addAttribute("data", appraisalReportService.find(id));
		return "/khpj/reports/appraisal_report/view";
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(@PathVariable("id") String[] id)
	{
		appraisalReportService.delete(id);
		return refresh("list");
	}
}
