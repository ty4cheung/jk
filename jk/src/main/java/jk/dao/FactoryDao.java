package jk.dao;

import java.util.Map;

import jk.domain.Factory;

public abstract interface FactoryDao extends BaseDao<Factory>{
	 public abstract void updateState(Map paramMap);
}
