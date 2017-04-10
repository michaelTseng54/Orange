package com.ifbk.project.manager.impl;

import java.util.List;

import com.ifbk.project.manager.GeneralService;

public class GeneralServiceImpl<T> extends BasicServiceImpl<T> implements GeneralService<T> {

	public String login(String sql, String...val) {
		return projectDao.login(sql, val);
	}

	public List<T> selectAll(String tableName) {
		return projectDao.selectAll(tableName);
	}
}