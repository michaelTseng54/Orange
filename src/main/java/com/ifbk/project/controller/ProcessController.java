package com.ifbk.project.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProcessController extends BasicController {

	private static final Logger logger = Logger.getLogger(ProcessController.class);

	public ProcessController() {}

	@RequestMapping(method = RequestMethod.GET, value="/login")
	public String getPage() {
		return "index";
	}
	
	/**
	 * 登入系統，METHOD POST
	 * 正常狀況下使用POST方法傳值，不過另外有設置GET方法對應使用者使用模式。
	 * @param userId String
	 * @param passwd String
	 * @param session HttpSession
	 * @return ModelAndView
	 */	
	@RequestMapping(method = RequestMethod.POST, value="/Main")
	public ModelAndView loginCheck(@RequestParam("userId") String userId
			, @RequestParam("password") String passwd, HttpSession session) {
		
		ModelAndView model = new ModelAndView();
		
		if (utils.cNul(userId) && utils.cNul(passwd)) {
			String result = "1111111111"; 
					//generalService.login("login", userId, passwd);
			if (result.equals("-1")) {							//帳密錯誤
				model.addObject("error", utils.getZh_TWProperties("ACC_ERROR"));
				model.setViewName("index");
			} else {											
				if (result.charAt(0) == '0') {					//停止使用
					model.addObject("error", utils.getZh_TWProperties("ACC_STOP_USE"));
					model.setViewName("index");
				} else {										//正常登入
//					session.setAttribute("manager", result.charAt(1) == '0' ? "false" : "true");
//					session.setAttribute("print", result.charAt(2) == '0' ? "false" : "true");
//					session.setAttribute("query", result.charAt(3) == '0' ? "false" : "true");
					session.setAttribute("userId", userId);
					model.setViewName("/front/Main");
				}
			}
			return model;
		} else {
			model.addObject("error", utils.getZh_TWProperties("ACC_INPUT_ERROR"));
			model.setViewName("index");
			return model;
		}
	}
	
	/**
	 * 登入系統, METHOD GET
	 * 正常狀況下使用POST方法傳值，不過另外有設置GET方法對應使用者使用模式。
	 * @param userId String, not require
	 * @param passwd String, not require
	 * @param session HttpSession
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET, value="/Main")
	public ModelAndView loginCheckForGet(@RequestParam(value = "userId", required = false) String userId
			, @RequestParam(value = "password", required = false) String passwd, HttpSession session) {
		Map<String, String> model = new HashMap<String, String>();
		if (session.getAttribute("userId") != null) {
			model.put("account", session.getAttribute("userId") + "");
			return new ModelAndView("/front/Main", model);
		} else {
			model.put("error", utils.getZh_TWProperties("ACC_NOT_LOGIN"));
			return new ModelAndView("index", model);
		}
	}
	
	/**
	 * 登出系統
	 * @param session HttpSession
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET, value="/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:index.jsp"); 
	}
	
	/**
	 * Redirect 404 Error
	 * @param session HttpSession
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET, value="/404")
	public ModelAndView pageNotFound(HttpSession session, HttpServletRequest request) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("userId", session.getAttribute("userId") + "");
		model.put("time", new Date() + "");
		model.put("status", "404");
		return new ModelAndView("/error/uncaughtException", model);
	}
	
	/**
	 * Redirect 500 Error
	 * @param session HttpSession
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET, value="/500")
	public ModelAndView pageException(HttpSession session, HttpServletRequest request) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("userId", session.getAttribute("userId") + "");
		model.put("time", new Date() + "");
		model.put("status", "500");
		return new ModelAndView("/error/uncaughtException", model);
	}
}