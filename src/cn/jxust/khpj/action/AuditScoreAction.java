package cn.jxust.khpj.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.enums.AuditScoreState;
import cn.jxust.khpj.service.AuditScoreService;
import cn.jxust.utils.DateUtils;

@Controller
@RequestMapping("/khpj/auditScore")
public class AuditScoreAction extends BaseDwzAction
{
	@Resource
	private AuditScoreService auditScoreService;

	//单位指标查看
	@RequestMapping("/list/{scoreType}.php")
	public ModelAndView list(@PathVariable("scoreType") String scoreType, String year)
	{
		ModelAndView mv = new ModelAndView();
		
		if(null == year)year = DateUtils.getCurrentYear();
		mv.addObject("year", year);
		mv.addObject("years", DateUtils.getYearsStart2013());
		
		String userType = getCurrentUser().getUserType();
		
		mv.addObject("type", userType);
		
		mv.addObject("departments", departmentService.getByType(userType));
		
		//获得已添加AuditScore
		mv.addObject("scores", auditScoreService.getMapByYearScoreType(year, userType, scoreType));
		mv.addObject("scoreType", scoreType);
		
		//审核状态
		mv.addObject("auditScoreState", AuditScoreState.toMap());
		mv.addObject("vpass", AuditScoreState.VICE_PASS.getIndex());
		mv.addObject("pass", AuditScoreState.PASS.getIndex());
		
		mv.setViewName("/khpj/auditScore/list");
		return mv;
	}
	
	//半年单位指标查看打分
	@RequestMapping("/audit/{scoreType}.php")
	public ModelAndView audit(@RequestParam(defaultValue = "A") String type, @PathVariable("scoreType") String scoreType, String year)
	{
		ModelAndView mv = new ModelAndView();
		
		if(null == year)year = DateUtils.getCurrentYear();
		mv.addObject("year", year);
		mv.addObject("years", DateUtils.getYearsStart2013());
		
		mv.addObject("type", type);
		
		mv.addObject("departments", departmentService.getByType(type));
		
		//获得已添加AuditScore
		mv.addObject("scores", auditScoreService.getMapByYearScoreType(year, type, scoreType));
		mv.addObject("scoreType", scoreType);
		
		//审核状态
		mv.addObject("auditScoreState", AuditScoreState.toMap());
		mv.addObject("vpass", AuditScoreState.VICE_PASS.getIndex());
		mv.addObject("pass", AuditScoreState.PASS.getIndex());
		
		mv.addObject("unaudited", AuditScoreState.UNAUDITED.getIndex());
		
		//副书记审核状态
		mv.addObject("viceAuditState", AuditScoreState.VICE_PASS.getIndex());
		
		//书记审核状态
		mv.addObject("auditState", AuditScoreState.PASS.getIndex());
		
		mv.setViewName("/khpj/auditScore/audit");
		return mv;
	}
	

	/**
	 * 添加部门的半年及全年审核分值
	 * @param departmentId
	 * @param scoreType
	 * @param scoreValue
	 * @return
	 */
	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public String save(String[] departmentIds, String year, String deptType, String scoreType, HttpServletRequest request)
	{
		if(null != departmentIds && departmentIds.length > 0)
		{
			Double[] scores = new Double[departmentIds.length];
			for(int i = 0; i < scores.length; i++)
			{
				String score = request.getParameter(departmentIds[i]+"score");
				if(null != score && !"".equals(score.trim()))
				{
					scores[i] = Double.valueOf(score);
				}
				else
				{
					scores[i] = 0d;
				}
			}
			auditScoreService.save(departmentIds, year, deptType, scoreType, scores);
			request.setAttribute("tips", SUCCESS);
		}
		
		return "redirect:/khpj/auditScore/list/"+ scoreType +".php";
	}
	
	/**
	 * 副审核通过
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/vicepass/{id}.php")
	public @ResponseBody String editVicePass(@PathVariable("id") String id)
	{
		auditScoreService.auditTheScore(id, AuditScoreState.VICE_PASS.getIndex(), null);
		return "ok";
	}
	
	/**
	 * 副审核不通过
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/vicerefuse/{id}.php")
	public @ResponseBody String editViceRefuse(@PathVariable("id") String id, String refuseInfo)
	{
		auditScoreService.auditTheScore(id, AuditScoreState.VICE_REFUSE.getIndex(), refuseInfo);
		return "ok";
	}
	
	/**
	 * 审核通过
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pass/{id}.php")
	public @ResponseBody String editPass(@PathVariable("id") String id)
	{
		auditScoreService.auditTheScore(id, AuditScoreState.PASS.getIndex(), null);
		return "ok";
	}
	
	/**
	 * 审核不通过
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/refuse/{id}.php")
	public @ResponseBody String editRefuse(@PathVariable("id") String id, String refuseInfo)
	{
		auditScoreService.auditTheScore(id, AuditScoreState.REFUSE.getIndex(), refuseInfo);
		return "ok";
	}
	
	/**
	 * 发布分值
	 * @param scoreType
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/publish/{scoreType}.php")
	public @ResponseBody String publish(@PathVariable("scoreType") String scoreType, String year)
	{
		auditScoreService.publishAuditScore(scoreType, year);
		return "ok";
	}
	
	/**
	 * 副书记批量审核通过
	 * @param type
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/vice_pass_batch.php")
	public String viceAuditScoreBatch(String deptType, String scoreType, String year)
	{
		auditScoreService.viceAuditScoreBatch(deptType, scoreType, year);
		return "redirect:audit/"+scoreType+".php?type="+deptType+"&year="+year;
	}
	
	/**
	 * 书记批量审核通过
	 * @param type
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/pass_batch.php")
	public String auditScoreBatch(String deptType, String scoreType, String year)
	{
		auditScoreService.auditScoreBatch(deptType, scoreType, year);
		return "redirect:audit/"+scoreType+".php?type="+deptType+"&year="+year;
	}
}
