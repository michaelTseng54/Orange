//package com.ifbk.project.controller;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.log4j.Logger;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ifbk.project.manager.ScoreService;
//import com.ifbk.project.model.Pmt001;
//import com.ifbk.project.model.ScoreBean;
//import com.ifbk.project.utils.JqGridData;
//import com.ifbk.project.utils.Utils;
//
//@Controller
//@RequestMapping("/FUNC005")
//public class TemplateController {
//
//	@Autowired
//	protected ScoreService<ScoreBean> scoreService;
//
//	private static final Logger logger = Logger.getLogger(TemplateController.class);
//
//	public final static Utils utils = new Utils();
//
//	public TemplateController() {}
//
//	@RequestMapping(method = RequestMethod.GET)
//	public String getPage(HttpSession session) {
//		return utils.userLoginStatus(session, this.getClass().getName());
//	}
//
//	@SuppressWarnings("unchecked")
//	@RequestMapping(method = RequestMethod.GET, value = "/query")
//	public void query(HttpSession session, HttpServletResponse response,
//			@RequestParam(value = "page", required = false) String pageString,
//			@RequestParam(value = "rows", required = false) String rowsString) {
//
//		JSONArray joArray = new JSONArray();
//
//		int rows = Integer.parseInt(rowsString); // 單頁資料列數
//		int page = Integer.parseInt(pageString); // 目前頁數
//
//		List<ScoreBean> beanList = null;
//
//		if (session.getAttribute("scoreSession") == null) {
//			beanList = scoreService.selectAll("ScoreBean");
//			session.setAttribute("scoreSession", beanList);
//		} else {
//			beanList = (List<ScoreBean>) session.getAttribute("scoreSession");
//		}
//
//		for (int i = 0; i < beanList.size(); i++) {
//			JSONObject jo = new JSONObject();
//			jo.put("xxxx", "xxx");
//			joArray.add(jo);
//		}
//
//		int totalPage = (int) Math.ceil((double) beanList.size() / rows); // 總頁數
//		int totalRecords = beanList.size(); // 總資料數
//		JqGridData<Pmt001> gridData = new JqGridData<Pmt001>(totalPage, page,
//				totalRecords, joArray);
//		try {
//			logger.info(new Date() + " / " + session.getAttribute("userId") + " / query");
//			response.getWriter().write(gridData.getJsonString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@RequestMapping(method = RequestMethod.POST, value = "/update")
//	public ModelAndView updateDataPost(
//			HttpSession session,
//			@RequestParam(value = "sId", required = false) String sId,
//			@RequestParam(value = "year", required = false) String year,
//			@RequestParam(value = "sName", required = false) String sName,
//			@RequestParam(value = "classId", required = false) String classId,
//			@RequestParam(value = "dept", required = false) String dept,
//			@RequestParam(value = "fileName", required = false) String fileName,
//			@RequestParam(value = "page", required = false) String page) {
//		
//		return new ModelAndView("/front/Function005");
//	}
//	
//	@RequestMapping(method = RequestMethod.POST, value = "/add")
//	public ModelAndView addDataPost(
//			HttpSession session,
//			@RequestParam(value = "sId", required = false) String sId,
//			@RequestParam(value = "year", required = false) String year,
//			@RequestParam(value = "sName", required = false) String sName,
//			@RequestParam(value = "classId", required = false) String classId,
//			@RequestParam(value = "dept", required = false) String dept,
//			@RequestParam(value = "fileName", required = false) String fileName,
//			@RequestParam(value = "page", required = false) String page) {
//		
//		return new ModelAndView("/front/Function005");
//	}
//	
//	@RequestMapping(method = RequestMethod.POST, value = "/delete")
//	public ModelAndView deleteDataPost(HttpSession session,
//			@RequestParam(value = "sId", required = false) String sId) {
//		
//		return new ModelAndView("/front/Function005");
//	}
//}