package jk.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import jk.dao.FactoryDao;
import jk.domain.Factory;


public class FactoryDaoImpl extends BaseDaoImap<Factory> implements FactoryDao{

	
	public void updateState(Map map) {
	    super.getSqlSession().update(super.getNs() + ".updateState", map);
	 }

}
