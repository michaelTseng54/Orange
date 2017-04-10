package com.ifbk.project.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.ifbk.project.manager.DeleteService;
import com.ifbk.project.manager.InsertService;
import com.ifbk.project.manager.ModifyService;
import com.ifbk.project.manager.QueryService;
import com.ifbk.project.utils.Utils;

public class BasicController<T> extends HttpServlet{
	
	private static final long serialVersionUID = 6461473608328071844L;
	
	@Autowired
	protected QueryService<T> queryService;
	
	@Autowired
	protected InsertService<T> insertService;
	
	@Autowired
	protected DeleteService<T> deleteService;
	
	@Autowired
	protected ModifyService<T> modifyService;
	
	public final static Utils utils = new Utils();
	
	public BasicController() {}

//	public void init() {
//		
//		ServletContext application = this.getServletContext();
//		WebApplicationContext webAppCtx = WebApplicationContextUtils
//				.getWebApplicationContext(application);
//
//		facultyDeptService = (FacultyDeptService) webAppCtx.getBean("facultyDeptService");
//		queryService = (QueryService) webAppCtx.getBean("queryService");
//		insertService = (InsertService) webAppCtx.getBean("insertService");	
//		deleteService = (DeleteService) webAppCtx.getBean("deleteService");
//		modifyService = (ModifyService) webAppCtx.getBean("modifyService");
//	}
	
}
