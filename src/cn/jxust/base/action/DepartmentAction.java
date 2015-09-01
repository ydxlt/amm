package cn.jxust.base.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxust.base.model.Department;
import cn.jxust.base.model.User;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.khpj.service.ScoreService;
import cn.jxust.utils.WebUtils;

@Controller
@RequestMapping("/base/department")
public class DepartmentAction extends BaseDwzAction
{
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private ScoreService scoreService;

	@RequestMapping(value="/list.php")
	public ModelMap list(@RequestParam(defaultValue = "1") int pageNum, Department entity)
	{
		return new ModelMap(departmentService.getAllByPage(pageNum, entity));
	}
	
	@RequestMapping(value = "/new.php")
	public String addForm(Model model)
	{
		return "/base/department/input";
	}

	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") String id, Model model)
	{
		Department department = departmentService.find(id);
		model.addAttribute(department);
		return "/base/department/input";
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(Department department)
	{
		if (department.getDepartmentId() == null || "".equals(department.getDepartmentId().trim()))
		{
			departmentService.save(department);
		} else
		{
			departmentService.update(department);
		}

		return closeCurrentAndRefresh("departmentList");
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(String[] items)
	{
		departmentService.delete(items);
		return refresh("departmentList");
	}
	
	@RequestMapping(value = "/viewDetails.php", produces="text/plain;charset=UTF-8")
	public @ResponseBody String viewDetails(String deptId, HttpServletResponse response)
	{
		response.setContentType("text.php;charset=utf-8");
		return departmentService.viewDetails(deptId);
	}
	
	@RequestMapping(value = "/details.php")
	public String detailsForm(Model model)
	{
		User user = getCurrentUser();
		model.addAttribute("username", user.getUserName());
		model.addAttribute("type", user.getUserType());
		
		Map<String, Object> details = departmentService.convenDescToMap(getCurrentDepartment().getDepartmentDesc());
		model.addAttribute("detail", details);
		
		return "/base/department/details";
	}
	
	@RequestMapping(value = "/updateDetails.php")
	public String details(Model model, HttpServletRequest request)
	{
		User user = getCurrentUser();
		model.addAttribute("username", user.getUserName());
		model.addAttribute("type", user.getUserType());
		
		Map<String, Object> details = WebUtils.getParametersStartingWith(request, "details");
		departmentService.updateDetails(getCurrentDepartment().getDepartmentId(), details);
		
		model.addAttribute("tips", SUCCESS);
		
		details = departmentService.convenDescToMap(getCurrentDepartment().getDepartmentDesc());
		model.addAttribute("detail", details);
		
		return "/base/department/details";
	}
	
	/**
	 * 查看单位排名
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rank.php")
	public String tableRank(Model model, String year)
	{
		List<String> years = scoreService.getExistYear();
		if(null == year && years.size() > 0)
		{
			year = years.get(0);
		}
		model.addAttribute("year", year);
		model.addAttribute("years", years);
		model.addAttribute("Atable", departmentService.getTotalScore("A"));
		model.addAttribute("Btable", departmentService.getTotalScore("B"));
		model.addAttribute("Ctable", departmentService.getTotalScore("C"));
		return "/base/department/rank";
	}
	
	/**
	 * 打印单位排名
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rank_print.php")
	public String tableRankPrint(Model model, String year)
	{
		List<String> years = scoreService.getExistYear();
		if(null == year && years.size() > 0)
		{
			year = years.get(0);
			model.addAttribute("Atable", departmentService.getTotalScore("A"));
			model.addAttribute("Btable", departmentService.getTotalScore("B"));
			model.addAttribute("Ctable", departmentService.getTotalScore("C"));
		}
		else
		{
//			model.addAttribute("At", scoreService.getTotalScore(year, "A"));
//			model.addAttribute("Bt", scoreService.getTotalScore(year, "B"));
//			model.addAttribute("Ct", scoreService.getTotalScore(year, "C"));
			
			model.addAttribute("At", scoreService.getTotalScoreFromSQL(year, "A"));
			model.addAttribute("Bt", scoreService.getTotalScoreFromSQL(year, "B"));
			model.addAttribute("Ct", scoreService.getTotalScoreFromSQL(year, "C"));
		}
		model.addAttribute("year", year);
		return "/base/department/rank_print";
	}
}
