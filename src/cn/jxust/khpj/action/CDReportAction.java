package cn.jxust.khpj.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.base.model.Department;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.enums.ConDisClassEnum;
import cn.jxust.khpj.model.CDReport;
import cn.jxust.khpj.service.CDReportService;
import cn.jxust.utils.DateUtils;

@Controller
@RequestMapping("/khpj/reports/con_disputes_report")
public class CDReportAction extends BaseDwzAction
{
	@Resource
	private CDReportService cDReportService;
	
	@RequestMapping(value = "/new.php")
	public String addForm(Model model)
	{
		String reportDate = DateUtils.getDaysForSubmit()[0];
		String deptId = getCurrentDepartment().getDepartmentId();
		Map<Integer, CDReport> data = cDReportService.getMyReportMap(deptId, reportDate);
		model.addAttribute("data", data);
		model.addAttribute("types", ConDisClassEnum.toMap());
		return "/khpj/reports/con_disputes_report/input";
	}
	
	@RequestMapping(value = "/list.php")
	public ModelAndView list(String reportDate)
	{
		if(null == reportDate || "".equals(reportDate.trim()))
		{
			reportDate = DateUtils.getDaysForSubmit()[0];
		}
		List<Object> years = cDReportService.getCdReportsYears();
		Map<Integer, CDReport> datas = cDReportService.getReport(reportDate);
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("queryMonth", reportDate);
		
		mv.addObject("types", ConDisClassEnum.toMap());
		
		mv.addObject("years", years);
		mv.addObject("datas", datas);
		
		mv.setViewName("/khpj/reports/con_disputes_report/list");
		return mv;
	}
	
	@RequestMapping(value = "/view.php")
	public ModelAndView view(String reportDate, String deptId)
	{
		if(null == reportDate)
		{
			reportDate = DateUtils.getDaysForSubmit()[0];
		}
		List<CDReport> datas = cDReportService.getMyReportList(deptId, reportDate);
		ModelAndView mv = new ModelAndView();
		mv.addObject("datas", datas);
		
		mv.addObject("types", ConDisClassEnum.toMap());
		
		mv.setViewName("/khpj/reports/con_disputes_report/view");
		return mv;
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public String save(HttpServletRequest request, Model model)
	{
		Map<Integer, String> types = ConDisClassEnum.toMap();
		List<CDReport> cDReports = new ArrayList<CDReport>();
		CDReport cDReport = null;
		Department dept = getCurrentDepartment();
		String reportDate = DateUtils.getDaysForSubmit()[0];
		String informant = getCurrentUsername();
		String currentTime = DateUtils.getCurrentTime();
		
		for(Integer type : types.keySet())
		{
			cDReport = new CDReport();
			String id = request.getParameter(type + "_id");
			if(null != id && !"".equals(id.trim()))
			{
				cDReport.setcDReportId(id);
			}
			
			cDReport.setDepartment(dept);
			cDReport.setReportDate(reportDate);
			cDReport.setConDisClass(type);
			
			String countyCount = (null == request.getParameter(type + "_countyCount") || "".equals(request.getParameter(type + "_countyCount").trim()))? "0" : request.getParameter(type + "_countyCount");
			String townCount = (null == request.getParameter(type + "_townCount") || "".equals(request.getParameter(type + "_townCount").trim()))? "0" : request.getParameter(type + "_townCount");
			String villageCount = (null == request.getParameter(type + "_villageCount") || "".equals(request.getParameter(type + "_villageCount").trim()))? "0" : request.getParameter(type + "_villageCount");
			String couDisCount = (null == request.getParameter(type + "_couDisCount") || "".equals(request.getParameter(type + "_couDisCount").trim()))? "0" : request.getParameter(type + "_couDisCount");
			String townDisCount = (null == request.getParameter(type + "_townDisCount") || "".equals(request.getParameter(type + "_townDisCount").trim()))? "0" : request.getParameter(type + "_townDisCount");
			String villageDisCount = (null == request.getParameter(type + "_villageDisCount") || "".equals(request.getParameter(type + "_villageDisCount").trim()))? "0" : request.getParameter(type + "_villageDisCount");
			
			cDReport.setCountyCount(Integer.parseInt(countyCount));
			cDReport.setTownCount(Integer.parseInt(townCount));
			cDReport.setVillageCount(Integer.parseInt(villageCount));
			cDReport.setCouDisCount(Integer.parseInt(couDisCount));
			cDReport.setTownDisCount(Integer.parseInt(townDisCount));
			cDReport.setVilDisCount(Integer.parseInt(villageDisCount));
			
			cDReport.setInformant(informant);
			cDReport.setTimeFillReport(currentTime);
			
			cDReports.add(cDReport);
		}
		
		cDReportService.saveOrUpdate(cDReports);
		
		model.addAttribute("tips", SUCCESS);
		if(null != request.getParameter("from"))
		{
			return "redirect:/khpj/sysData/new.php";
		}
		return addForm(model);
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(@PathVariable("id") String[] id)
	{
		cDReportService.delete(id);
		return refresh("list");
	}
}
