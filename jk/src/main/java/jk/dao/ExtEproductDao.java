package jk.dao;

import java.io.Serializable;

import jk.domain.ExtCproduct;
import jk.domain.ExtEproduct;

/**
 * @author zhangtai
 *
 */
public interface ExtEproductDao extends BaseDao<ExtEproduct> {

	void deleteByContractProductById(Serializable[] ids);

	void deleteByContractId(Serializable[] ids);
}
