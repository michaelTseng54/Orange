package com.ifbk.project.manager;

import java.util.List;

public interface InsertService<T> {
	
	public void txInsertList(List<T> t);
	public void insertList(List<T> t);
	
	public void txSoUEntity(T t);
	
	public void txInsertEntity(T t);
	public void insertEntity(T t);
	
	public void txStoreProcedure(String str);
	public void txTestStoreProcedure(String str);
}