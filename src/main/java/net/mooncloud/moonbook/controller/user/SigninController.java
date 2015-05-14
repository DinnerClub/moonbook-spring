package net.mooncloud.moonbook.controller.user;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.mooncloud.moonbook.controller.index.HomeController;
import net.mooncloud.moonbook.entity.user.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/views")
public class SigninController
{
	private static final Logger logger = LoggerFactory.getLogger(SigninController.class);
	
	@RequestMapping(value = "/pages/signin", method = RequestMethod.GET)
	public String signin(Locale locale, Model model)
	{
		logger.info("Welcome signin! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "/views/pages/signin";
	}

	@RequestMapping(value = "/pages/signin", method = RequestMethod.POST)
	public String signin(@ModelAttribute("user") User user, HttpSession session,
			HttpServletRequest req, Model model)
	{
		String error = null;

		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");

		if (UnknownAccountException.class.getName().equals(exceptionClassName))
		{
			error = "用户名/密码错误";
		}
		else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName))
		{
			error = "用户名/密码错误";
		}
		else if (DisabledAccountException.class.getName().equals(exceptionClassName))
		{
			error = "用户已经被禁用，请联系平台工作人员！";
		}
		else if (LockedAccountException.class.getName().equals(exceptionClassName))
		{
			error = "用户已经被锁定，请联系平台工作人员！";
		}
		else if (AuthenticationException.class.getName().equals(exceptionClassName))
		{
			error = "账号认证错误";
		}
		else if (exceptionClassName != null)
		{
			error = "账号错误";
		}
		model.addAttribute("error", error);
		return "index";
	}
}
