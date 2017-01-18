package jk.dao;

import java.io.Serializable;

import jk.domain.ContractProduct;

/**
 * @author zhangtai
 *
 */
public interface ContractProductDao extends BaseDao<ContractProduct> {

	public void deleteByContractId(Serializable[] contractIds);
}
