package cn.jxust.khpj.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.base.model.Department;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.enums.ScoreState;
import cn.jxust.enums.SysDataType;
import cn.jxust.khpj.model.Score;
import cn.jxust.khpj.service.DeptQuotaService;
import cn.jxust.khpj.service.ScoreService;
import cn.jxust.khpj.service.SysDataService;
import cn.jxust.utils.DateUtils;

@Controller
@RequestMapping("/khpj/score")
public class ScoreAction extends BaseDwzAction
{
	@Resource
	private DepartmentService departmentService;
	@Resource
	private ScoreService scoreService;
	@Resource
	private DeptQuotaService deptQuotaService;
	@Resource
	private SysDataService sysDataService;

	// 月份单位指标查看打分
	@RequestMapping("/assess.php")
	public ModelAndView assess(String month)
	{
		if(null == month)
		{
			month = DateUtils.getDaysForSubmit()[0];
		}
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userType = userService.getUserByName(userDetails.getUsername()).getUserType();

		ModelAndView mv = new ModelAndView();
		mv.addObject("type", userType);
		
		mv.addObject("queryMonth", month);

		mv.addObject("yearMonth", DateUtils.getYearMonth(null));

		mv.addObject("departments", departmentService.getByType(userType));
		mv.addObject("deptQuota1", deptQuotaService.findByType(userType, "1"));
		mv.addObject("deptQuota2", deptQuotaService.findByType(userType, "2"));

		// 获得截至提交时间剩下的天数
		mv.addObject("submitDay", DateUtils.getDaysForSubmit());

		// 获得固定指标单位
		mv.addObject("fixedQuotaDept", departmentService.getByFixedQuota());
		
		// 审核状态
		mv.addObject("scoreState", ScoreState.toMap());
		mv.addObject("pass", ScoreState.PASS.getIndex());
		mv.addObject("scores", scoreService.getScoreMapByTypeAndMonth(userType, month));
		
		//查询数据库中已有的年份
//		mv.addObject("years", scoreService.getYears(userType));
		mv.addObject("years", sysDataService.getYearsByDeptType(userType));
		
		//固定指标标示
		mv.addObject("fixedQuota", SysDataType.FIXEDQUOTADATA.getIndex());

		mv.setViewName("/khpj/score/assess");
		return mv;
	}

	// 月份单位指标审核
	@RequestMapping("/audit.php")
	public ModelAndView audit(@RequestParam(defaultValue = "A") String type, String month)
	{
		if(null == month)
		{
			month = DateUtils.getDaysForSubmit()[0];
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("type", type);

		mv.addObject("queryMonth", month);
		
		mv.addObject("yearMonth", DateUtils.getYearMonth(null));

		mv.addObject("departments", departmentService.getByType(type));
		mv.addObject("deptQuota1", deptQuotaService.findByType(type, "1"));
		mv.addObject("deptQuota2", deptQuotaService.findByType(type, "2"));

		// 获得截至提交时间剩下的天数
		mv.addObject("submitDay", DateUtils.getDaysForSubmit());

		// 获得固定指标单位
		mv.addObject("fixedQuotaDept", departmentService.getByFixedQuota());

		// 当前单位得分
		mv.addObject("scores", scoreService.getScoreMapByTypeAndMonth(type, month));

		// 审核状态
		mv.addObject("scoreState", ScoreState.toMap());

		// 审核未通过代码
		mv.addObject("unaudited", ScoreState.UNAUDITED.getIndex());
		
		//查询数据库中已有的年份
		mv.addObject("years", scoreService.getYearsByType(type));
		
		//固定指标标示
		mv.addObject("fixedQuota", SysDataType.FIXEDQUOTADATA.getIndex());

		mv.setViewName("/khpj/score/audit");
		return mv;
	}

	// 月份单位指标查看
	@RequestMapping("/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "A") String type, String month)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("type", type);

		mv.addObject("yearMonth", DateUtils.getYearMonth(month));

		mv.addObject("departments", departmentService.getByType(type));
		mv.addObject("deptQuota1", deptQuotaService.findByType(type, "1"));
		mv.addObject("deptQuota2", deptQuotaService.findByType(type, "2"));

		// 获得截至提交时间剩下的天数
		mv.addObject("submitDay", DateUtils.getDaysForSubmit());

		// 获得固定指标单位
		mv.addObject("fixedQuotaDept", departmentService.getByFixedQuota());

		// 当前单位得分
		mv.addObject("scores", scoreService.getScoreMapByTypeAndMonth(type, month));

		// 审核状态
		mv.addObject("scoreState", ScoreState.toMap());

		// 审核未通过代码
		mv.addObject("unaudited", ScoreState.UNAUDITED.getIndex());

		mv.setViewName("/khpj/score/list");
		return mv;
	}

	/**
	 * 审核通过
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pass/{id}.php")
	public @ResponseBody
	String editPass(@PathVariable("id") String id)
	{
		scoreService.auditTheScore(id, ScoreState.PASS.getIndex(), null);
		return "ok";
	}

	/**
	 * 审核不通过
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/refuse/{id}.php")
	public @ResponseBody
	String editRefuse(@PathVariable("id") String id, String refuseInfo)
	{
		scoreService.auditTheScore(id, ScoreState.REFUSE.getIndex(), refuseInfo);
		return "ok";
	}
	
	@RequestMapping(value = "/pass_batch.php")
	public String auditScoreBatch(String deptType, String month)
	{
		scoreService.auditScoreBatch(deptType, month);
		return "redirect: audit.php?type="+deptType+"&month="+month;
	}

	/**
	 * 添加某个部门的指标及对应指标分值
	 * 
	 * @param departmentId
	 * @param quotaId
	 * @param month
	 * @param scores
	 * @return
	 */
	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public ModelAndView save(String departmentId, String[] quotaIds, String month, HttpServletRequest request)
	{
		if (null != quotaIds && quotaIds.length > 0)
		{
			Double[] scores = new Double[quotaIds.length];
			for (int i = 0; i < quotaIds.length; i++)
			{
				String score = request.getParameter(quotaIds[i] + "score");
				if (null != score && !"".equals(score.trim()))
				{
					scores[i] = Double.valueOf(score);
				}
				else
				{
					scores[i] = 0d;
				}
			}
			scoreService.save(departmentId, quotaIds, month, scores);
			request.setAttribute("tips", SUCCESS);
			request.setAttribute("deptId", departmentId);
		}

		// 返回assess控制器
		return assess(month);
	}
	
	@RequestMapping(value = "/publish.php")
	public @ResponseBody String publishScore(String month)
	{
		scoreService.publishScore(month);
		return "ok";
	}
	
	
	// 查看单个单位指标
	@RequestMapping("/view/{id}.php")
	public ModelAndView view(@PathVariable("id") String id, String month)
	{
		if(null == month)
		{
			month = DateUtils.getDaysForSubmit()[0];
		}
		Department dept = departmentService.getById(id);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("dept", dept);
		mv.addObject("queryMonth", month);
		
		//固定指标
		mv.addObject("deptQuota1", deptQuotaService.findByType(dept.getDepartmentType(), "1"));
		//动态指标
		mv.addObject("deptQuota2", deptQuotaService.findByType(dept.getDepartmentType(), "2"));

		// 获得固定指标单位
		mv.addObject("fixedQuotaDept", departmentService.getByFixedQuota());

		// 当前单位得分
		Map<String, Score> val = scoreService.getScoreValueByDeptAndMonth(dept.getDepartmentId(), month);
		mv.addObject("scores", val);

		// 审核状态
		mv.addObject("scoreState", ScoreState.toMap());

		// 审核未通过代码
		mv.addObject("unaudited", ScoreState.UNAUDITED.getIndex());
			
		//查询数据库中已有的年份
		mv.addObject("years", scoreService.getYearsById(dept.getDepartmentId()));
			
		//固定指标标示
		mv.addObject("fixedQuota", SysDataType.FIXEDQUOTADATA.getIndex());
		mv.setViewName("/khpj/score/view");
		return mv;
	}
}
