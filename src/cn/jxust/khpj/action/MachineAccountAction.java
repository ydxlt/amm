package cn.jxust.khpj.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.base.service.DepartmentService;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.khpj.model.MachineAccount;
import cn.jxust.khpj.service.MachineAccountService;
import cn.jxust.orm.PageData;
import cn.jxust.utils.DateUtils;

@Controller
@RequestMapping("/khpj/reports/machine_account_report")
public class MachineAccountAction extends BaseDwzAction
{
	@Resource
	private MachineAccountService machineAccountService;
	
	@Resource
	private DepartmentService departmentService;

	/**
	 * 单位用户查询自已提交的台账
	 * @param pageNum
	 * @param month
	 * @return
	 */
	@RequestMapping(value = "/my.php")
	public ModelAndView my(@RequestParam(defaultValue = "1") int pageNum, String month)
	{
		if(null == month)
		{
			month = DateUtils.getDaysForSubmit()[0];
		}
		
		PageData<MachineAccount> pageData = new PageData<MachineAccount>(pageNum);
		String deptId = getCurrentDepartment().getDepartmentId();
		PageData<MachineAccount> datas = machineAccountService.getReport(pageData, deptId, month);
		ModelAndView mv = new ModelAndView();
		mv.addObject("datas", datas);
		mv.addObject("years", machineAccountService.getYears(deptId));
		
		//判断是否具有删除功能
		String submitMonth = DateUtils.getDaysForSubmit()[0];
		if(submitMonth.equals(month))
		{
			mv.addObject("delete", "true");
		}
		mv.addObject("queryMonth", month);
		
		mv.setViewName("/khpj/reports/machine_account_report/my");
		return mv;
	}

	/**
	 * 管理员查看台账数据
	 * @param pageNum
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum, MachineAccount entity, String deptId, String month)
	{
		PageData<MachineAccount> pageData = new PageData<MachineAccount>(pageNum);
		if(null == month)
		{
			month = DateUtils.getDaysForSubmit()[0];
		}
		PageData<MachineAccount> datas = machineAccountService.getReport(pageData, entity, deptId, month);
		ModelAndView mv = new ModelAndView();
		mv.addObject("datas", datas);
		mv.addObject("years", machineAccountService.getYears());
		mv.addObject("queryMonth", month);
		mv.addObject("depts", departmentService.getAll());
		mv.addObject("deptId", deptId);
		mv.addObject("totlePage", datas.getPagination().getTotalPage());
		mv.setViewName("/khpj/reports/machine_account_report/list");
		return mv;
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public String save(MachineAccount machineAccount, Model model)
	{
		machineAccount.setDepartment(getCurrentDepartment());
		if (machineAccount.getMachineAccountId() == null || "".equals(machineAccount.getMachineAccountId().trim()))
		{
			machineAccount.setReportDate(DateUtils.getDaysForSubmit()[0]);
			machineAccountService.save(machineAccount);
		}
		else
		{
			machineAccount.setReportDate(DateUtils.getDaysForSubmit()[0]);
			machineAccountService.update(machineAccount);
		}
		model.addAttribute("tips", SUCCESS);
		return "redirect:/khpj/reports/machine_account_report/my.php";
	}
	
	@RequestMapping(value = "/delete/{id}.php")
	public String delete(@PathVariable("id") String id)
	{
		machineAccountService.delete(id);
		return "redirect:/khpj/reports/machine_account_report/my.php";
	}
}
