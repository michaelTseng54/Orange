package com.ifbk.project.manager.impl;

import java.util.List;

import com.ifbk.project.manager.QueryService;

public class QueryServiceImpl<T> extends BasicServiceImpl<T> implements QueryService<T> {

	public T selectById(String sql, String... val) {
		return projectDao.selectById(sql, val);
	}
	public int selectByIdReturnCount(String sql, String... val) {
		return projectDao.selectByIdReturnCount(sql, val);
	}

	public List<T> selectAll(String tableName) {
		return projectDao.selectAll(tableName);
	}
}