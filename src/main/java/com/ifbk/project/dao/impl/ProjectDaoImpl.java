package com.ifbk.project.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ifbk.project.dao.ProjectDao;
import com.ifbk.project.model.AdminBean;
import com.ifbk.project.model.TfIj;
import com.ifbk.project.utils.Utils;

public class ProjectDaoImpl<T> extends HibernateDaoSupport implements ProjectDao<T> {

	private static final Logger logger = Logger.getLogger(ProjectDaoImpl.class);
	private static final Utils utils = new Utils();
	public HibernateTemplate hibernateTemplate ;


	public T selectBySQL(String sql) {
		List<T> temp = getHibernateTemplate().find(utils.getSqlProperties(sql));
		if (temp != null) {
			return temp.get(0);
		} else {
			return null;
		}
	}
	
	public T selectBySQL(String sql, String... val) {
		List<T> temp = getHibernateTemplate().find(utils.getSqlProperties(sql), val);
		if (temp != null) {
			return temp.get(0);
		} else {
			return null;
		}
	}
	
	public List<T> selectAllBySQL(String sql) {
		return getHibernateTemplate().find(utils.getSqlProperties(sql));
	}
	
	public List<T> selectAllBySQL(String sql, String... val) {
		return getHibernateTemplate().find(utils.getSqlProperties(sql), val);
	}

	public String login(String sql, String... val) {
		String account = val[0];
		List<AdminBean> result = getHibernateTemplate().find(utils.getSqlProperties(sql), account);
		if (result.size() > 0) {
			if (Arrays.equals(result.get(0).getPassword(), utils.encrypt(val[1]))) {
				return result.get(0).getPrivilege();
			} else {
				return "-1";
			}
		} else {
			return "-1";
		}
	}

	public T selectById(String sql, String... val) {
		List<T> tt = getHibernateTemplate().find(utils.getSqlProperties(sql), val);
		if (tt.size() > 0) {
			return (T) tt.get(0);
		} else {
			return null;
		}
	}
	
	public int selectByIdReturnCount(String sql, String... val) {
		return getHibernateTemplate().find(utils.getSqlProperties(sql), val).size();
	}
	
	public List<T> selectAll(String tableName) {
		return getHibernateTemplate().find("from " + tableName);
	}
	
	public void update(T t) {
		getHibernateTemplate().update(t);
		getHibernateTemplate().flush();
	}
	
	public void txUpdate(T t) {
		getHibernateTemplate().setFlushMode(hibernateTemplate.FLUSH_EAGER);
		getHibernateTemplate().update(t);
	}
	
	public void txUpdate(final String sql, final String... var) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session sess)
					throws HibernateException, SQLException {
				Query query = sess.createSQLQuery(sql);
				for (int i = 0; i < var.length; i ++) {
					System.out.println("var[" + i + "] : " + var[i]);
				}
				for (int i = 0; i < var.length; i ++) {
					query.setParameter(i, var[i]);
				}
				return query;
			}
		});
//		getHibernateTemplate().find(utils.getSqlProperties(sql), var);
	}
	
	public void txInsert(T t) {
		try {
			getHibernateTemplate().setFlushMode(hibernateTemplate.FLUSH_EAGER);
			Serializable i = getHibernateTemplate().save(t);
			System.out.println("#from dao : " + i);
			getHibernateTemplate().evict(t);		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void txSoU(T t) {
		try {
			getHibernateTemplate().setFlushMode(hibernateTemplate.FLUSH_EAGER);
			getHibernateTemplate().saveOrUpdate(t);
			getHibernateTemplate().evict(t);		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insert(T t) {
		getHibernateTemplate().setFlushMode(hibernateTemplate.FLUSH_EAGER);
		getHibernateTemplate().save(t);
		getHibernateTemplate().evict(t);
	}
	
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}
	
	public void txDelete(T t) {
		getHibernateTemplate().delete(t);
	}
	
	public void txStoreProcedure(final String str) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session sess2)
					throws HibernateException, SQLException {
				try {
					Session sess = getHibernateTemplate().getSessionFactory().openSession();
					Transaction trx = sess.beginTransaction();
					Query query = sess.createQuery("EXEC KC_UpDate_TF_IJ :p1, :p2, :p3, :p4, :p5, :p6, :p7, :p8, :p9");
					query.setString("p1", "+");
					query.setString("p2", "IJ");
					query.setString("p3", str);
					System.out.println("#------------------------------- str : " + str);
					query.setString("p4", "F");
					query.setString("p5", "F");
					query.setInteger("p6", 0);
					query.setString("p7", "F");
					query.setString("p8", "F");
					query.setInteger("p9", 2);
					query.executeUpdate();
					trx.commit();
					return query;
				} catch (Exception e) {
					System.out.println("from dao exception : " + e.getMessage());
				}
				return null;
			}
		});
	}
	public void txTestStoreProcedure(String sqlTmp) {
		final String sql = sqlTmp;
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session sess)
					throws HibernateException, SQLException {
				try {
					Query query = sess.createSQLQuery("EXEC KC_UpDate_TF_IJ :p1, :p2, :p3, :p4, :p5, :p6, :p7, :p8, :p9").addEntity(TfIj.class);
					query.setString("p1", "+");
					query.setString("p2", "IJ");
					query.setString("p3", "IJ10310020963");	//TODO
					query.setString("p4", "F");
					query.setString("p5", "F");
					query.setInteger("p6", 0);
					query.setString("p7", "F");
					query.setString("p8", "F");
					query.setInteger("p9", 2);
					int x = query.executeUpdate();
					System.out.println("txStoreProcedure result code : " + x); 
					return query;
				} catch (Exception e) {
					System.out.println("Exception : " + e.getMessage());
				}
				return null;
			}
		});
	}
}
