package cn.jxust.base.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxust.base.model.Role;
import cn.jxust.base.model.User;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.base.service.RoleService;
import cn.jxust.base.service.UserService;
import cn.jxust.common.action.BaseDwzAction;

@Controller
@RequestMapping("/base/user")
public class UserAction extends BaseDwzAction
{
	@Resource
	private UserService userService;
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private RoleService roleService;

	@RequestMapping("/list.php")
	public ModelMap list(@RequestParam(defaultValue = "1", required=false) int pageNum, User entity)
	{
		return new ModelMap(userService.getAllByPage(pageNum, entity));
	}

	@RequestMapping(value = "/new.php")
	public String addForm(Model model)
	{
		model.addAttribute("departments", departmentService.getAll());
		model.addAttribute("roles", roleService.findAll());
		return "/base/user/input";
	}

	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") Integer id, Model model)
	{
		User user = userService.find(id);
		model.addAttribute(user);
		for(Role r : user.getUserRole())
		{
			if(null != r)
			{
				model.addAttribute("userRole", r);
				break;
			}
		}
		model.addAttribute("departments", departmentService.getAll());
		model.addAttribute("roles", roleService.findAll());
		return "/base/user/input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(User user)
	{
		/**
		 * 添加用户前先判断用户是否存在！
		 * 要初始化用户角色和部门
		 */
		if (user.getUserId() == null)
		{
			userService.save(user);
		} else
		{
			userService.update(user);
		}
		return closeCurrentAndRefresh("userList");
	}

	@RequestMapping(value = "/delete.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> delete(Integer[] items)
	{
		userService.delete(items);
		return refresh("userList");
	}
	
	@RequestMapping(value = "/changePw.php")
	public String changepwForm(Model model)
	{
		model.addAttribute("username", getCurrentUser().getUserName());
		return "/base/user/changePw";
	}
	
	@RequestMapping(value = "/updatePw.php")
	public String changepw(Model model, String newPassword, String oldPassword)
	{
		User currentUser = getCurrentUser();
		boolean flag = userService.changePassword(currentUser, newPassword, oldPassword);
		model.addAttribute("username", currentUser.getUserName());
		if(flag)
		{
			model.addAttribute("tips", SUCCESS);
		}
		else
		{
			model.addAttribute("tips", "修改失败，原密码错误");
		}
		return "/base/user/changePw";
	}
	
	/**
	 * 初始化密码
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/initPw/{id}.php")
	public @ResponseBody Map<String, String> initPassword(@PathVariable("id") int userId){
		userService.initPassword(userId);
		return refresh("userList");
	}
}
