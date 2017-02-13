package jk.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jk.dao.ExtCproductDao;
import jk.domain.ExtCproduct;
import jk.pagination.Page;

/**
 * @author zhangtai
 *
 */
public class ExtEproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao {

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
