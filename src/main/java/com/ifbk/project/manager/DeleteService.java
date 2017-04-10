package com.ifbk.project.manager;

public interface DeleteService<T> {
	
	public void delete(T t);
	public void txDelete(T t);

}