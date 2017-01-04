package jk.dao.impl;

import java.util.Map;

import jk.dao.ContractDao;
import jk.domain.Contract;

/**
 * @author zhangtai
 *
 */
public class ContractDaoImpl extends BaseDaoImap<Contract> implements ContractDao{


	public ContractDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.setNs("aaa");
	}

	@Override
	public void setNs(String ns) {
		// TODO Auto-generated method stub
		super.setNs("jk.dao.mapper.ContractMapper");
	}
	
	public void updateState(Map map) {
		// TODO Auto-generated method stub
		  super.getSqlSession().update(super.getNs() + ".updateState", map);
	}

}
