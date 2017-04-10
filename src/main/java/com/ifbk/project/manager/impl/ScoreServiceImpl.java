package com.ifbk.project.manager.impl;

import java.util.List;

import com.ifbk.project.manager.ScoreService;

public class ScoreServiceImpl<T> extends BasicServiceImpl<T> implements ScoreService<T> {

	public T selectBySQL(String sql) {
		return projectDao.selectBySQL(sql);
	}
	
	public T selectBySQL(String sql, String... val) {
		return projectDao.selectBySQL(sql, val);
	}
	
	public List<T> selectAllBySQL(String sql) {
		return projectDao.selectAllBySQL(sql);
	}
	
	public List<T> selectAllBySQL(String sql, String... val) {
		return projectDao.selectAllBySQL(sql, val);
	}

	public T selectById(String sql, String... val) {
		return projectDao.selectById(sql, val);
	} 

	public List<T> selectAll(String tableName) {
		return projectDao.selectAll(tableName);
	}
	
	public void update(T t) {
		projectDao.update(t);
	}

	public void txUpdate(T t) {
		projectDao.txUpdate(t);
	}
	
	public void insertList(List<T> t) {
		for (int i = 0; i < t.size(); i++) {
			projectDao.insert((T)t.get(i));
		}
	}

	public void txInsertList(List<T> t) {
		for (int i = 0; i < t.size(); i++) {
			projectDao.txInsert((T)t.get(i));
		}
	}
	
	public void insertEntity(T t) {
		projectDao.insert(t);
	}

	public void txInsertEntity(T t) {
		projectDao.txInsert(t);
	}
	
	public void delete(T t) {
		projectDao.delete(t);
	}

	public void txDelete(T t) {
		projectDao.txDelete(t);
	}

}