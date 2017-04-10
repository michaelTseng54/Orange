package com.ifbk.project.manager;

import java.util.List;

public interface GeneralService<T> {
	
	public String login(String sql, String...val);

	public List<T> selectAll(String tableName);

}