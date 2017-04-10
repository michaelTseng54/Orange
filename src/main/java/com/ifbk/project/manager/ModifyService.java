package com.ifbk.project.manager;

public interface ModifyService<T> {
	
	public void update(T t);
	public void txUpdate(T t);
	public void txUpdate(String sql, String... var);

}