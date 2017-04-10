package com.ifbk.project.manager;

import java.util.List;

public interface UserService<T> {
	
	public T selectById(String sql, String... val);
	public List<T> selectAll(String tableName);
	
	public void update(T t);
	public void txUpdate(T t);

	public void txInsertList(List<T> t);
	public void insertList(List<T> t);
	
	public void txInsertEntity(T t);
	public void insertEntity(T t);

	public void delete(T t);
	public void txDelete(T t);

}