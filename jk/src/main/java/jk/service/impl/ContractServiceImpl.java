package jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jk.dao.ContractDao;
import jk.dao.ContractProductDao;
import jk.dao.ExtCproductDao;
import jk.domain.Contract;
import jk.pagination.Page;
import jk.service.ContractService;

/**
 * @author zhangtai
 *
 */
@Service
public class ContractServiceImpl implements ContractService {

	@Resource
	ContractDao contracDao;

	@Resource
	ContractProductDao contractProductDao;

	@Resource
	ExtCproductDao extCproductDao;

	public List<Contract> findPage(Page paramPage) {
		// TODO Auto-generated method stub
		return this.contracDao.findPage(paramPage);
	}

	public List<Contract> find(Map paramMap) {
		// TODO Auto-generated method stub
		return this.contracDao.find(paramMap);
	}

	public Contract get(Serializable paramSerializable) {
		// TODO Auto-generated method stub
		return this.contracDao.get(paramSerializable);
	}

	public void insert(Contract paramContract) {
		// TODO Auto-generated method stub
		this.contracDao.insert(paramContract);
	}

	public void update(Contract paramContract) {
		// TODO Auto-generated method stub
		this.contracDao.update(paramContract);
	}

	public void deleteById(Serializable id) {
		this.contracDao.deleteById(id);
		Serializable[] ids = { id };
		// 删除当前合同下的附近信息
		this.extCproductDao.deleteByContractId(ids);
		// 删除当前合同下的货物信息
		this.contractProductDao.deleteByContractId(ids);
	}

	public void delete(Serializable[] ids) {

		this.contracDao.delete(ids);
		// 删除当前合同下的附近信息
		this.extCproductDao.deleteByContractId(ids);
		// 删除当前合同下的货物信息
		this.contractProductDao.deleteByContractId(ids);

	}

	public void submit(Serializable[] paramArrayOfSerializable) {
		// TODO Auto-generated method stub

	}

	public void cancel(Serializable[] paramArrayOfSerializable) {
		// TODO Auto-generated method stub

	}

}
