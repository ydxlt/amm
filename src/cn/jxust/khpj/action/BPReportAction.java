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
import cn.jxust.khpj.model.BPReport;
import cn.jxust.khpj.service.BPReportService;
import cn.jxust.orm.PageData;
import cn.jxust.utils.DateUtils;

@Controller
@RequestMapping("/khpj/reports/bonus_penalty_report")
public class BPReportAction extends BaseDwzAction
{
	@Resource
	private BPReportService bPReportService;
	
	@RequestMapping(value = "/my.php")
	public ModelAndView my(@RequestParam(defaultValue = "1") int pageNum)
	{
		PageData<BPReport> pageData = new PageData<BPReport>(pageNum);
		String deptId = getCurrentDepartment().getDepartmentId();
		PageData<BPReport> datas = bPReportService.getReport(pageData, deptId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("datas", datas);
		mv.addObject("dept", getCurrentDepartment().getDepartmentName());
		mv.addObject("years", DateUtils.getYearList(5));
		mv.addObject("currentYear", DateUtils.getCurrentYear());
		mv.setViewName("/khpj/reports/bonus_penalty_report/my");
		return mv;
	}
	
	@RequestMapping(value = "/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum, BPReport entity){
		PageData<BPReport> pageData = new PageData<BPReport>(pageNum);
		pageData = bPReportService.findPage(pageData, entity);
		ModelAndView mv = new ModelAndView();
		mv.addObject(pageData);
		mv.setViewName("/khpj/reports/bonus_penalty_report/list");
		return mv;
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public String save(BPReport bpReport, Model model)
	{
		bpReport.setDepartment(getCurrentDepartment());
		if (bpReport.getbPReportId() == null || "".equals(bpReport.getbPReportId().trim()))
		{
			bPReportService.save(bpReport);
		} else
		{
			bPReportService.update(bpReport);
		}
		model.addAttribute("tips", SUCCESS);
		return "/khpj/reports/bonus_penalty_report/my";
	}
	
	@RequestMapping(value = "/view/{id}.php")
	public String view(@PathVariable("id") String id, Model model)
	{
		model.addAttribute("data", bPReportService.find(id));
		return "/khpj/reports/bonus_penalty_report/view";
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(@PathVariable("id") String[] id)
	{
		bPReportService.delete(id);
		return refresh("list");
	}
}
