package jk.dao.impl;

import java.io.Serializable;

import jk.dao.ExtEproductDao;
import jk.domain.ExtEproduct;

/**
 * @author zhangtai
 *
 */
public class ExtEproductDaoImpl extends BaseDaoImpl<ExtEproduct> implements ExtEproductDao {

	public ExtEproductDaoImpl(){
		super.setNs("jk.dao.mapper.ExtEproductMapper");
	}
	
	public void deleteByContractProductById(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByContractProductById", ids);
	}
	
	public void deleteByContractId(Serializable[] contractIds){
		super.getSqlSession().delete(super.getNs()+".deleteByContractId", contractIds);
	}

}
