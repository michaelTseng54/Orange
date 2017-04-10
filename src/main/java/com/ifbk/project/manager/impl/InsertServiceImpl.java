package com.ifbk.project.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.ifbk.project.manager.InsertService;

public class InsertServiceImpl<T> extends BasicServiceImpl<T> implements InsertService<T> {

	private static final Logger logger = Logger.getLogger(InsertServiceImpl.class);

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

	public void txSoUEntity(T t) {
		projectDao.txSoU(t);
	}
	
	public void txInsertEntity(T t) {
		projectDao.txInsert(t);
	}

	public void txStoreProcedure(String str) {
		projectDao.txStoreProcedure(str);
	}
	
	public void txTestStoreProcedure(String str) {
		projectDao.txTestStoreProcedure(str);
	}
	
}