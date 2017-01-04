package jk.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import jk.dao.FactoryDao;
import jk.domain.Factory;

@Repository
public class FactoryDaoImpl extends BaseDaoImap<Factory> implements FactoryDao{

	@Override
	public void setNs(String ns) {
		// TODO Auto-generated method stub
		super.setNs("jk.dao.mapper.FactoryMapper");
	}
	
	public void updateState(Map map) {
	    super.getSqlSession().update(super.getNs() + ".updateState", map);
	 }

}
