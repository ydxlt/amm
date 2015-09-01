package cn.jxust.common.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.jxust.base.model.Department;
import cn.jxust.base.model.User;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.base.service.UserService;
import cn.jxust.utils.ConfigUtils;
import cn.jxust.utils.DateUtils;

/**
 * index
 * 
 * @author chenle 2013-5-5 4:48:19
 */

@Controller
@RequestMapping("/")
public class IndexController
{
	@Resource
	private UserService userService;
	
	@Resource
	private DepartmentService departmentService;
	
	public String getWebName()
	{
		return ConfigUtils.getInstance().getValues().get("web");
	}
	
	public Cookie setCookieByCurrentUser()
	{
		//设置Cookie
		try
		{
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = userDetails.getUsername();
			username = URLEncoder.encode(username,"utf-8");
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(60*60*24*7*2);
			return cookie;
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Cookie setCookieByCurrentUserType(String type)
	{
		//设置Cookie
		Cookie cookie = new Cookie("type", type);
		cookie.setMaxAge(60*60*24*7*2);
		return cookie;
	}

	/**
	 * 默认首页
	 * 设置Cookie
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model, @CookieValue(value="username", defaultValue="") String username, @CookieValue(value="type", defaultValue="") String type)
	{
		try
		{
			username = URLDecoder.decode(username,"utf-8");
			model.addAttribute("department", username);
			
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return "login";
	}
	
	/**
	 * 根据类型获得部门
	 */
	@RequestMapping(value = "/dept/{type}.php", produces="text/json;charset=UTF-8")
	public @ResponseBody String getDepartmentByType(@PathVariable("type") String type, HttpServletResponse response)
	{
		//设置用户类型
		response.addCookie(setCookieByCurrentUserType(type));
		
		List<Department> depts = departmentService.getByType(type);
		JSONArray json=JSONArray.fromObject(depts);
		return json.toString();
	}
	
	/**
	 * 后台用户登录
	 * @param model
	 * @return
	 */
	@RequestMapping("/login.php")
	public String loginPage(Model model)
	{
		model.addAttribute("webName", getWebName());
		return "login";
	}

	/**
	 * 后台加载页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/loading.php")
	public String loadingPage(Model model, HttpServletResponse response)
	{
		response.addCookie(setCookieByCurrentUser());
		
		model.addAttribute("webName", getWebName());
		return "loading";
	}

	/**
	 * 后台首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/index.php")
	public String indexPage(Model model, HttpServletResponse response)
	{
		response.addCookie(setCookieByCurrentUser());
		
		model.addAttribute("webName", getWebName());
		model.addAttribute("overview", ConfigUtils.getInstance().getValues().get("overview"));
		//String position = "赣州";
		//model.addAttribute("position", position);
		
		//String ip = "61.180.78.43";
		//String[] location = new BMapLocation().getIPXY(ip);
		//String longitude = location[0];
		//String latitude = location[1];
		
		//model.addAttribute("longitude", longitude);
		//model.addAttribute("latitude", latitude);
		
		model.addAttribute("now", DateUtils.getCurrentTime());
		return "index";
	}
	
	/**
	 * 返回定位信息，这里用城市定位,ajax调用
	 * @param model
	 * @return
	 */
	@RequestMapping("/getPosition.php")
	public @ResponseBody Map<String,String> postion(int id)
	{
		Map<String,String> result = new HashMap<String, String>();
		String position = "江西理工大学";
		result.put("position", position);
		return result;
	}
	
	
	@RequestMapping(value = "/changePw.php")
	public String changepwForm(Model model)
	{
		//获得当前用户和单位
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		return "/changePw";
	}
	
	@RequestMapping(value = "/updatePw.php")
	public @ResponseBody Map<String, String> changepw(Model model, String newPassword, String oldPassword)
	{
		//获得当前用户和单位
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.getUserByName(userDetails.getUsername());
		boolean flag = userService.changePassword(currentUser, newPassword, oldPassword);
		Map<String, String> result = new HashMap<String, String>();
		if(flag)
		{
			result.put("statusCode", "200");
	        result.put("message", "修改成功");
		}
		else
		{
			result.put("statusCode", "300");
	        result.put("message", "修改失败，原密码错误");
		}
		result.put("navTabId", "changepwd");
		result.put("callbackType", "closeCurrent");
        return result;
	}
}
