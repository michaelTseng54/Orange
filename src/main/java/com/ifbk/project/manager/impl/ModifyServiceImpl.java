package com.ifbk.project.manager.impl;

import com.ifbk.project.manager.ModifyService;

public class ModifyServiceImpl<T> extends BasicServiceImpl<T> implements ModifyService<T> {
	
	public void update(T t) {
		projectDao.update(t);
	}

	public void txUpdate(T t) {
		projectDao.txUpdate(t);
	}

	public void txUpdate(String sql, String... var) {
		projectDao.txUpdate(sql, var);
	}

}