package jk.dao;

import java.io.Serializable;
import java.util.*;


import jk.pagination.Page;

public interface BaseDao<T> {

	public List<T> findPage(Page page);
	public List<T> find(Map map);
	public T get(Serializable id);
	public void insert(T entity);
	void update(T entity);
	void deleteById(Serializable id);
	void delete(Serializable[] ids);
}
