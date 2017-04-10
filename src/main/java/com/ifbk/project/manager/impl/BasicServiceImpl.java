package com.ifbk.project.manager.impl;

import org.springframework.transaction.annotation.Transactional;

import com.ifbk.project.dao.ProjectDao;

@Transactional
public class BasicServiceImpl<T> {
	
	public ProjectDao<T> projectDao = null;
	
	public ProjectDao<T> getProjectDao() {
		return projectDao;
	}
	
	public void setProjectDao(ProjectDao<T> projectDao) {
		this.projectDao = projectDao;
	}
	
}
