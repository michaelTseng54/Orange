package com.ifbk.project.dao;

import java.util.List;

public interface ProjectDao<T> {
	
	public String login(String sql, String... val);
	
	public T selectBySQL(String sql);
	public T selectBySQL(String sql, String... val);
	public List<T> selectAllBySQL(String sql);
	public List<T> selectAllBySQL(String sql, String... val);

	public T selectById(String sql, String... val);
	public int selectByIdReturnCount(String sql, String... val);
	public List<T> selectAll(String tableName);
	
	public void txSoU(T t);	//saveOrUpdate
	
	public void txInsert(T t);
	public void insert(T t);
	
	public void txUpdate(T t);
	public void txUpdate(String sql, String... var);
	public void update(T t);
	public void txStoreProcedure(String str);
	public void txTestStoreProcedure(String str);
	
	public void delete(T t);
	public void txDelete(T t);
}
