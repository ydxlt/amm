package cn.jxust.khpj.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.base.model.Department;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.enums.ConDisClassEnum;
import cn.jxust.enums.SysDataType;
import cn.jxust.khpj.model.CDReport;
import cn.jxust.khpj.model.SysData;
import cn.jxust.khpj.service.CDReportService;
import cn.jxust.khpj.service.MachineAccountService;
import cn.jxust.khpj.service.SysDataService;
import cn.jxust.utils.ConfigUtils;
import cn.jxust.utils.DateUtils;

@Controller
@RequestMapping("/khpj/sysData")
public class SysDataAction extends BaseDwzAction
{
	@Resource
	private SysDataService sysDataService;
	@Resource
	private CDReportService cDReportService;
	@Resource
	private MachineAccountService machineAccountService;
//	@Resource
//	private AppraisalReportService appraisalReportService;
//	@Resource
//	private BPReportService bPReportService;
//	@Resource
//	private RegisterReportService registerReportService;

	/**
	 * 罗列某个月某个部门的提交数据
	 * 
	 * @param pageNumber
	 * @param entity
	 * @param month
	 * @return
	 */
	@RequestMapping("/list.php")
	public ModelAndView list(String month)
	{
		ModelAndView mv = new ModelAndView();
		String[] submitDay = DateUtils.getDaysForSubmit();
		if(null == month || "".equals(month.trim()))
		{
			month = submitDay[0];
		}
		// 获得截至提交时间剩下的天数
		mv.addObject("submitDay", submitDay);
		
		//查询月份，默认为当前月份
		mv.addObject("queryMonth", month);
		
		List<SysData> pageData = null;
		Department dept = getCurrentDepartment();
		pageData = sysDataService.getListByDept(dept.getDepartmentId(), month);
		mv.addObject("pageData", pageData);

		mv.addObject("user", getCurrentUser());

		// 是否显示固定指标
		mv.addObject("fixedquota", dept.getTransFixedQuota());

		// 是否显示专门工作
		mv.addObject("speciallyWork", dept.getSpeciallyWork());

		// 得到排名
		mv.addObject("totalScore", departmentService.getTotalScoreByDept(dept));
		// 得到排名指数
		mv.addObject("totalScoreRank", departmentService.getTotalScoreRankByDept(dept));

		// 单位文件type
		mv.addObject("departmentType", SysDataType.DEPARTMENTFILE.getIndex());
		mv.addObject("acitveType", SysDataType.ACTIVEPIC.getIndex());
		mv.addObject("materialType", SysDataType.SYSDATAMATERIAL.getIndex());
		// 固定指标
		mv.addObject("fixquotaType", SysDataType.FIXEDQUOTADATA.getIndex());
		//专门小组工作
		mv.addObject("plan", SysDataType.PLAN.getIndex());
		mv.addObject("develop", SysDataType.DEVELOP.getIndex());
		mv.addObject("effect", SysDataType.EFFECT.getIndex());
		
		//查询数据库中已有的年份
		mv.addObject("years", sysDataService.getYearsByDeptId(dept.getDepartmentId()));
		
		//设置政绩档案填写月份
		String reportMonth = ConfigUtils.getInstance().getValues().get("reportMonth");
		if(null == reportMonth)
		{
			reportMonth = "12";
		}
		mv.addObject("reportMonth", reportMonth);

		return mv;
	}

	/**
	 * 管理员打分时，显示某个部门、某个月份的提交数据
	 * 
	 * @param departmentID
	 * @param month
	 * @return
	 */
	@RequestMapping("/assess_list.php")
	public ModelAndView list(String dept, String month)
	{
		ModelAndView mv = new ModelAndView();

		mv.addObject("sysDataType", SysDataType.toMap());

		mv.addObject("sysDataDeptFile", sysDataService.getByDeptMonth(dept, month, SysDataType.DEPARTMENTFILE.getIndex()));
		mv.addObject("sysDataActive", sysDataService.getByDeptMonth(dept, month, SysDataType.ACTIVEPIC.getIndex()));
		mv.addObject("sysDataMaterial", sysDataService.getByDeptMonth(dept, month, SysDataType.SYSDATAMATERIAL.getIndex()));
		
		mv.addObject("cdreports",cDReportService.getMyReportList(dept, month));
		mv.addObject("types", ConDisClassEnum.toMap());
		mv.addObject("machineAccount", machineAccountService.getReport(dept, month));

		mv.setViewName("/khpj/sysData/assess_list");

		return mv;
	}
	
	/**
	 * 查看专门工作、某个月份的提交数据
	 * 
	 * @param departmentID
	 * @param month
	 * @return
	 */
	@RequestMapping("/work_list.php")
	public ModelAndView workList(String month)
	{
		if(null == month)
		{
			month = DateUtils.getCurrentMonth();
		}
		
		ModelAndView mv = new ModelAndView();

		mv.addObject("sysDataType", SysDataType.toMap());

		mv.addObject("sysDataPlan", sysDataService.getByDeptMonthWork(month, SysDataType.PLAN.getIndex()));
		mv.addObject("sysDataDevelop", sysDataService.getByDeptMonthWork(month, SysDataType.DEVELOP.getIndex()));
		mv.addObject("sysDataEffect", sysDataService.getByDeptMonthWork(month, SysDataType.EFFECT.getIndex()));
		
		mv.addObject("month", month);
		mv.addObject("years", DateUtils.getYearMonth(DateUtils.getCurrentYear()));

		mv.setViewName("/khpj/sysData/work_list");

		return mv;
	}

	/**
	 * 查看某一条提交的数据
	 * 
	 * @param pageNumber
	 * @param entity
	 * @return
	 */
	@RequestMapping("/view/{id}.php")
	public ModelAndView view(@PathVariable("id") String id)
	{
		SysData sysData = sysDataService.find(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys_data", sysData);
		mv.addObject("sysDataType", SysDataType.toMap());
		mv.setViewName("/khpj/sysData/view");
		return mv;
	}

	/**
	 * 查看固定指标
	 * 
	 * @param month
	 * @param deptId
	 * @return
	 */
	@RequestMapping("/view.php")
	public ModelAndView view(String deptId, String month, String type)
	{
		if(null == month)
		{
			month = DateUtils.getDaysForSubmit()[0];
		}
		
		SysData sysData = sysDataService.getByDeptMonthType(deptId, month, type);
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys_data", sysData);
		mv.setViewName("/khpj/sysData/view");
		
		mv.addObject("deptId", deptId);
		mv.addObject("month", month);
		mv.addObject("type", type);
		mv.addObject("years", DateUtils.getYearMonth(DateUtils.getCurrentYear()));
		
		return mv;
	}
	
	/**
	 * 查看固定指标
	 * 
	 * @param month
	 * @param deptId
	 * @return
	 */
	@RequestMapping("/viewFixedQuota.php")
	public ModelAndView viewFixedQuota(String month)
	{
		if(null == month)
		{
			month = DateUtils.getDaysForSubmit()[0];
		}
		
		List<SysData> sysDatas = sysDataService.getFixedQuotaByMonth(month);
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys_datas", sysDatas);
		mv.setViewName("/khpj/sysData/viewFixedQuota");
		
		mv.addObject("month", month);
		mv.addObject("years", DateUtils.getYearMonth(DateUtils.getCurrentYear()));
		
		return mv;
	}

	/**
	 * 新建系统数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new.php")
	public String addForm(HttpServletRequest request, Model model)
	{
		Department dept = getCurrentDepartment();
		model.addAttribute("department", dept);
		
		//是否添加历史数据
		String showRank = ConfigUtils.getInstance().getValues().get("history");
		if("是".equals(showRank) || "yes".equals(showRank) || "y".equals(showRank.toLowerCase()))
		{
			model.addAttribute("showHistory", DateUtils.getPerMonthByCuttentYear());
		}

		//设置当月 固定指标和专门工作数据
		String month = DateUtils.getDaysForSubmit()[0];
		model.addAttribute("fix_quota", sysDataService.getByDeptMonthType(dept.getDepartmentId(), month, SysDataType.FIXEDQUOTADATA.getIndex()));
		model.addAttribute("fix_quota_template", dept.getTransFixedQuotaTemplate());
		model.addAttribute("work_plan", sysDataService.getByDeptMonthType(dept.getDepartmentId(), month, SysDataType.PLAN.getIndex()));
		model.addAttribute("work_develop", sysDataService.getByDeptMonthType(dept.getDepartmentId(), month, SysDataType.DEVELOP.getIndex()));
		model.addAttribute("work_effect", sysDataService.getByDeptMonthType(dept.getDepartmentId(), month, SysDataType.EFFECT.getIndex()));
		
		// 单位文件type
		model.addAttribute("departmentType", SysDataType.DEPARTMENTFILE.getIndex());
		model.addAttribute("acitveType", SysDataType.ACTIVEPIC.getIndex());
		model.addAttribute("materialType", SysDataType.SYSDATAMATERIAL.getIndex());
		model.addAttribute("fixquotaType", SysDataType.FIXEDQUOTADATA.getIndex());
		model.addAttribute("plan", SysDataType.PLAN.getIndex());
		model.addAttribute("develop", SysDataType.DEVELOP.getIndex());
		model.addAttribute("effect", SysDataType.EFFECT.getIndex());
		
		/**
		 * 2014/4/4 把突出矛盾纠纷报表移植到新增本月考核数据中
		 */
		String reportDate = DateUtils.getDaysForSubmit()[0];
		String deptId = getCurrentDepartment().getDepartmentId();
		Map<Integer, CDReport> data = cDReportService.getMyReportMap(deptId, reportDate);
		model.addAttribute("data", data);
		model.addAttribute("types", ConDisClassEnum.toMap());
		
		if(null != request.getParameter("tips"))
		{
			model.addAttribute("tips", SUCCESS);
		}

		return "/khpj/sysData/input";
	}
	
	/**
	 * 固定指标数据 、 专门工作数据修改
	 * @param deptId
	 * @param month
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit.php")
	public String editForm(String deptId, String month, String type, Model model)
	{
		SysData sysData = sysDataService.getByDeptMonthType(deptId, month, type);
		
		if(null!=sysData)
		{
			model.addAttribute(sysData);
		}
		
		model.addAttribute("sysDataType", SysDataType.toMap());
		
		return "/khpj/sysData/edit";
	}

	/**
	 * 一般数据修改
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") String id, Model model)
	{
		SysData sysData = sysDataService.find(id);
		model.addAttribute(sysData);
		
		model.addAttribute("sysDataType", SysDataType.toMap());
		
		return "/khpj/sysData/edit";
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public String save(SysData sysData, Model model)
	{
		// 获得当前用户
		sysData.setUser(getCurrentUser());
		sysData.setDepartment(getCurrentDepartment());

		if (sysData.getSysDataId() == null || "".equals(sysData.getSysDataId()))
		{
			sysDataService.save(sysData);
		}
		else
		{
			sysDataService.update(sysData);
		}

		model.addAttribute("tips", SUCCESS);
		
		model.addAttribute("sysDataType", SysDataType.toMap());
		
		Department dept = getCurrentDepartment();
		model.addAttribute("department", dept);
		model.addAttribute("fix_quota", sysDataService.getByDeptMonthType(dept.getDepartmentId(), sysData.getScoreMonth(), SysDataType.FIXEDQUOTADATA.getIndex()));
		model.addAttribute("fix_quota_template", getCurrentDepartment().getTransFixedQuotaTemplate());
		model.addAttribute("work_plan", sysDataService.getByDeptMonthType(dept.getDepartmentId(), sysData.getScoreMonth(), SysDataType.PLAN.getIndex()));
		model.addAttribute("work_develop", sysDataService.getByDeptMonthType(dept.getDepartmentId(), sysData.getScoreMonth(), SysDataType.DEVELOP.getIndex()));
		model.addAttribute("work_effect", sysDataService.getByDeptMonthType(dept.getDepartmentId(), sysData.getScoreMonth(), SysDataType.EFFECT.getIndex()));
		
		// 单位文件type
		model.addAttribute("departmentType", SysDataType.DEPARTMENTFILE.getIndex());
		model.addAttribute("acitveType", SysDataType.ACTIVEPIC.getIndex());
		model.addAttribute("materialType", SysDataType.SYSDATAMATERIAL.getIndex());
		model.addAttribute("fixquotaType", SysDataType.FIXEDQUOTADATA.getIndex());
		model.addAttribute("plan", SysDataType.PLAN.getIndex());
		model.addAttribute("develop", SysDataType.DEVELOP.getIndex());
		model.addAttribute("effect", SysDataType.EFFECT.getIndex());
		
		//是否添加历史数据
		String showRank = ConfigUtils.getInstance().getValues().get("history");
		if("是".equals(showRank) || "yes".equals(showRank) || "y".equals(showRank.toLowerCase()))
		{
			model.addAttribute("showHistory", DateUtils.getPerMonthByCuttentYear());
			model.addAttribute("showHistoryMonth", sysData.getScoreMonth());
		}
		
		return "/khpj/sysData/input";
	}

	@RequestMapping(value = "/delete/{id}.php")
	public String delete(@PathVariable("id") String id)
	{
		sysDataService.delete(id);
		return "redirect:/khpj/sysData/list.php";
	}

	@RequestMapping(value = "/upload.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> upload(MultipartFile imgFile, HttpServletRequest request)
	{
		String path = request.getSession().getServletContext().getRealPath(".php");
		String savePath = path + "\\uploadImg\\" + DateUtils.getCurrentMonth();
		String saveuri = request.getContextPath() + ".php/uploadImg/" + DateUtils.getCurrentMonth() +"/";
		File saveDir = new File(savePath);
		if(!saveDir.exists())
		{
			saveDir.mkdirs();
		}
		String uploadFileName = imgFile.getOriginalFilename();
		String saveFileName = System.currentTimeMillis() + uploadFileName.substring(uploadFileName.lastIndexOf(".")).toLowerCase();
		saveuri += saveFileName;
		File newFile = new File(savePath + "\\" + saveFileName);

		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
//			System.out.println(imgFile.getSize());
//			if(imgFile.getSize() > 500000l)
//			{
//				result.put("error", 1);
//			    result.put("message", "上传失败！ 图片过大，请修改成500KB以下！");
//			    return result;
//			}
			
			//对上传文件设置成宽度为600的缩略图
			Thumbnails.of(imgFile.getInputStream()).width(600).toFile(newFile);
//			imgFile.transferTo(newFile);
			
			result.put("error", 0);
		    result.put("url", saveuri);
		}
		catch (Exception e)
		{
			result.put("error", 1);
		    result.put("message", "上传失败！");
		    result.put("url", saveuri);
		}                  
	    return result;
	}
	
	/**
	 * 将审核页面的报表功能移至月数据审核界面
	 */
	@RequestMapping(value = "/view_dept_rep.php")
	public ModelAndView viewDepartmentReport(String departmentId, String month){
		if(null == month)
		{
			month = DateUtils.getDaysForSubmit()[0];
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("cdreports",cDReportService.getMyReportList(departmentId, month));
		mv.addObject("types", ConDisClassEnum.toMap());
		mv.addObject("machineAccount", machineAccountService.getReport(departmentId, month));
//		mv.addObject("appraisalReport", appraisalReportService.getByDeptAndYear(departmentId, month.substring(0, 4)));
//		mv.addObject("bPReport", bPReportService.getBydeptAndYear(departmentId, month.substring(0, 4)));
//		mv.addObject("registerReport", registerReportService.getBydeptAndYear(departmentId, month.substring(0, 4)));
		
		mv.setViewName("/khpj/sysData/viewReports");
		return mv;
	}
}
