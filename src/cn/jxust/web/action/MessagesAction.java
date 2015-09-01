package cn.jxust.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.orm.PageData;
import cn.jxust.web.model.Messages;
import cn.jxust.web.service.MessagesService;

@Controller
@RequestMapping("/web/messages")
public class MessagesAction extends BaseDwzAction
{
	@Resource
	private MessagesService messagesService;

	@RequestMapping("/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNumber)
	{
		ModelAndView mv = new ModelAndView();
		PageData<Messages> pageData = new PageData<Messages>(pageNumber);
		pageData = messagesService.getReplys(pageData);
		mv.addObject(pageData);
		
		mv.setViewName("/web/messages/list");
		return mv;
	}
	
	@RequestMapping("/replyList.php")
	public ModelAndView replyList(@RequestParam(defaultValue = "1") int pageNumber)
	{
		ModelAndView mv = new ModelAndView();
		PageData<Messages> pageData = new PageData<Messages>(pageNumber);
		pageData = messagesService.getAll(pageData);
		mv.addObject(pageData);
		
		mv.setViewName("/web/messages/replysList");
		return mv;
	}
	
	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") String id, Model model)
	{
		Messages message = messagesService.find(id);
		model.addAttribute("message", message);
		return "/web/messages/input";
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public ModelAndView save(Messages message, String validateCode, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String check = (String) request.getSession().getAttribute("check");
		if(null != validateCode && !"".equals(validateCode.trim()) && check.equals(validateCode))
		{
			messagesService.save(message);
			mv.addObject("success", "success");
		}
		else
		{
			mv.addObject("fail", "fail");
		}
		
		PageData<Messages> pageData = new PageData<Messages>(1);
		pageData = messagesService.getReplys(pageData);
		mv.addObject(pageData);
		
		mv.setViewName("/web/messages/list");
		return mv;
	}
	
	@RequestMapping(value = "/update.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> update(HttpServletRequest request, Messages message)
	{
		String username = getCurrentUsername(request);
		message.setReplyor(username);
		messagesService.update( message);
		return closeCurrentAndRefresh("replyList");
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(String[] items)
	{
		messagesService.delete(items);
		return refresh("replyList");
	}
	
	/**
	 * 以下代码为产生随机码
	 */
	
	private static final String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
	private static final int WIDTH = 150;
	private static final int HEIGHT = 50;

	@RequestMapping(value = "/rand.php")
	public void jpg(HttpServletResponse response, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		response.setContentType("image/jpeg");

		// 防止浏览器缓冲
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		char[] rands = getCode(4);
		drawBackground(g);
		drawRands(g, rands);
		g.dispose();
		try
		{
			ServletOutputStream out = response.getOutputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(image, "PNG", bos);
			byte[] buf = bos.toByteArray();
			response.setContentLength(buf.length);
			out.write(buf);
			bos.close();
			out.close();
			session.setAttribute("check", new String(rands).toLowerCase());
		}
		catch (Exception e)
		{
		}
	}

	/**
	 * 产生随机数
	 * 
	 * @return
	 */
	private char[] getCode(int length)
	{
		char[] rands = new char[length];
		for (int i = 0; i < length; i++)
		{
			int rand = (int) (Math.random() * chars.length());
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}

	/**
	 * 绘制背景
	 * 
	 * @param g
	 */
	private void drawBackground(Graphics g)
	{
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Random random = new Random();
		int len = 0;
		while (len <= 5)
		{
			len = random.nextInt(15);
		}
		for (int i = 0; i < len; i++)
		{
			int x = (int) (random.nextInt(WIDTH));
			int y = (int) (random.nextInt(HEIGHT));
			int red = (int) (255 - random.nextInt(200));
			int green = (int) (255 - random.nextInt(200));
			int blue = (int) (255 - random.nextInt(200));
			g.setColor(new Color(red, green, blue));
			// g.drawLine(x, y, random.nextInt(WIDTH)-x,
			// random.nextInt(HEIGHT)-y);
			g.drawOval(x, y, 2, 2);
		}
	}

	/**
	 * 绘制验证码
	 * 
	 * @param g
	 * @param rands
	 */
	private void drawRands(Graphics g, char[] rands)
	{
		Random random = new Random();

		g.setFont(new Font("黑体", Font.ITALIC | Font.BOLD, 45));
		for (int i = 0; i < rands.length; i++)
		{
			int red = (int) (random.nextInt(255));
			int green = (int) (random.nextInt(255));
			int blue = (int) (random.nextInt(255));
			g.setColor(new Color(red, green, blue));
			g.drawString("" + rands[i], i * 40, 40);
		}
	}
	
}
