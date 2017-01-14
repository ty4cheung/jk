package jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jk.dao.ContractProductDao;
import jk.dao.ExtCproductDao;
import jk.domain.ContractProduct;
import jk.pagination.Page;
import jk.service.ContractProductService;

/**
 * @author zhangtai
 *
 */
@Service
public class ContractProductServiceImpl implements ContractProductService {

	@Resource
	ContractProductDao contractProductDao;

	@Resource
	ExtCproductDao extCproductDao;

	
	public List<ContractProduct> findPage(Page map) {
		// TODO Auto-generated method stub
		
		return contractProductDao.findPage(map);
	}

	public List<ContractProduct> find(Map paramMap) {
		// TODO Auto-generated method stub
		return contractProductDao.find(paramMap);
	}

	public ContractProduct get(Serializable paramSerializable) {
		// TODO Auto-generated method stub
		return contractProductDao.get(paramSerializable);
	}

	public void insert(ContractProduct contractProduct) {
		// TODO Auto-generated method stub
		contractProduct.setId(UUID.randomUUID().toString());
		//自动计算总金额=数量*单价		...修改，删除；同步合同总金额
		contractProduct.setAmount(contractProduct.getCnumber()*contractProduct.getPrice());
		this.contractProductDao.insert(contractProduct);
	}

	public void update(ContractProduct paramContractProduct) {
		// TODO Auto-generated method stub
		contractProductDao.update(paramContractProduct);
	}

	public void deleteById(Serializable paramSerializable) {
		// TODO Auto-generated method stub
		Serializable[] ids = {paramSerializable};
		extCproductDao.deleteByContractProductById(ids);		//删除当前这些货物下的所有附件
		contractProductDao.deleteById(paramSerializable);
		
	}

	public void delete(Serializable[] paramArrayOfSerializable) {
		// TODO Auto-generated method stub
		contractProductDao.delete(paramArrayOfSerializable);

	}

}
