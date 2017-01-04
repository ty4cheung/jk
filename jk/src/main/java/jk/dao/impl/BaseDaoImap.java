package jk.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import jk.dao.BaseDao;
import jk.pagination.Page;

public abstract class BaseDaoImap<T> extends SqlSessionDaoSupport implements BaseDao<T> {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	private String ns;

	public String getNs() {
		return ns;
	}

	public void setNs(String ns) {
		this.ns = ns;
	}

	public List<T> findPage(Page page) {
		// TODO Auto-generated method stub
		List<T> oList = this.getSqlSession().selectList(this.ns+".findPage",page);
		return oList;
	}

	public List<T> find(Map map) {
		// TODO Auto-generated method stub
		List<T> oList = this.getSqlSession().selectList(this.ns+".findPage",map);
		return oList;
	}

	public T get(Serializable id) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(ns+".get",id);
	}

	public void insert(T entity) {
		// TODO Auto-generated method stub
		this.getSqlSession().insert(this.ns+".insert",entity);
	}

	public void update(T entity) {
		// TODO Auto-generated method stub
		this.getSqlSession().update(ns+".update",entity);
	}

	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		this.getSqlSession().delete(ns+".deleteById",id);
	}

	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		getSqlSession().delete(this.ns + ".delete", ids);
		
	}
	
		
}
