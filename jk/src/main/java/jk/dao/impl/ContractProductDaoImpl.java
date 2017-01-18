package jk.dao.impl;

import java.io.Serializable;

import jk.dao.ContractProductDao;
import jk.domain.ContractProduct;

/**
 * @author zhangtai
 *
 */
public class ContractProductDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductDao {

	public void deleteByContractId(Serializable[] contractIds){
		super.getSqlSession().delete(super.getNs()+".deleteByContractId", contractIds);
	}
}
