package jk.dao.impl;

import jk.dao.ContractProductDao;
import jk.domain.ContractProduct;

/**
 * @author zhangtai
 *
 */
public class ContractProductDaoImpl extends BaseDaoImap<ContractProduct> implements ContractProductDao {

	@Override
	public void setNs(String ns) {
		// TODO Auto-generated method stub
		super.setNs("jk.dao.mapper.ContractProductMapper");
	}
}
