package com.ifbk.project.manager.impl;

import com.ifbk.project.manager.DeleteService;

public class DeleteServiceImpl<T> extends BasicServiceImpl<T> implements DeleteService<T> {
	
	public void delete(T t) {
		projectDao.delete(t);
	}

	public void txDelete(T t) {
		projectDao.txDelete(t);
	}
}