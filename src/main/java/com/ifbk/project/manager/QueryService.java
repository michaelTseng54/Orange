package com.ifbk.project.manager;

import java.util.List;

public interface QueryService<T> {
	
	public T selectById(String sql, String... val);
	public int selectByIdReturnCount(String sql, String... val);
	public List<T> selectAll(String tableName);
}