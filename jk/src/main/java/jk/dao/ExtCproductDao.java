package jk.dao;

import java.io.Serializable;

import jk.domain.ExtCproduct;

/**
 * @author zhangtai
 *
 */
public interface ExtCproductDao extends BaseDao<ExtCproduct> {

	void deleteByContractProductById(Serializable[] ids);

	void deleteByContractId(Serializable[] ids);
}
