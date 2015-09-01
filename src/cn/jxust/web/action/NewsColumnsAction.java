package cn.jxust.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.orm.PageData;
import cn.jxust.utils.ConfigUtils;
import cn.jxust.utils.HtmlUtils;
import cn.jxust.web.model.NewsColumns;
import cn.jxust.web.service.NewsColumnsService;

@Controller
@RequestMapping("/web/newsColumns")
public class NewsColumnsAction extends BaseDwzAction
{
	@Resource
	private NewsColumnsService newsColumnsService;

	@RequestMapping("/list")
	public ModelMap list(@RequestParam(defaultValue = "1") int pageNumber, NewsColumns entity)
	{
		PageData<NewsColumns> pageData = new PageData<NewsColumns>(pageNumber);
		pageData = newsColumnsService.findPage(pageData, entity);

		return new ModelMap(pageData);
	}
	
	@RequestMapping(value = "/getTree")
	public @ResponseBody List<Map<String, Object>> getQuotaTree(String id, String month)
	{
		return newsColumnsService.getTree();
	}

	@RequestMapping(value = "/new")
	public String addForm(Model model)
	{
		model.addAttribute("columns", newsColumnsService.findAll());
		return "/web/newsColumns/input";
	}
	
	@RequestMapping(value = "/getChilderColumns/{pid}")
	public @ResponseBody String getChilderColumns(@PathVariable("pid") Integer pid)
	{
		if(null == pid || 0 == pid)
		{
			return "[]";
		}
		return newsColumnsService.getChilderColumnsString(pid);
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model)
	{
		NewsColumns newsColumns = newsColumnsService.find(id);
		model.addAttribute(newsColumns);
		return "/web/newsColumns/input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(NewsColumns newsColumns, Integer newscolumnsId, HttpServletRequest request)
	{
		if (newsColumns.getId() == null)
		{
			newsColumnsService.save(newsColumns, newscolumnsId);
		} else
		{
			newsColumnsService.update(newsColumns, newscolumnsId);
		}
		//刷新common/head.jsp common/foot.jsp
		refreshHeadAndFoot(request);
		return closeCurrentAndRefresh("newsColumnsList");
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Map<String, String> delete(@PathVariable("id") Integer[] id)
	{
		newsColumnsService.delete(id);
		return refresh("newsColumnsList");
	}
	
	@RequestMapping(value = "/refresh")
	public @ResponseBody Map<String, String> refresh(HttpServletRequest request)
	{
		//刷新common/head.jsp common/foot.jsp
		refreshHeadAndFoot(request);
		return success();
	}
	
	/**
	 * 刷新common/head.jsp common/foot.jsp
	 * @param request
	 */
	public void refreshHeadAndFoot(HttpServletRequest request)
	{
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/pages/common/");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ctx", request.getContextPath());
		values.put("columns", newsColumnsService.findParentExceptIndex());
		values.put("footColumns", newsColumnsService.findPageForIndex());
		values.put("web", ConfigUtils.getInstance().getValues().get("web"));
		try
		{
			HtmlUtils.CreatePage("head_jsp.ftl", path + "/head.jsp", values);
			HtmlUtils.CreatePage("foot_jsp.ftl", path + "/foot.jsp", values);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
