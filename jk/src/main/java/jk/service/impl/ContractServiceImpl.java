package jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jk.dao.ContractDao;
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
	
	public List<Contract> findPage(Page paramPage) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contract> find(Map paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public Contract get(Serializable paramSerializable) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Contract paramContract) {
		// TODO Auto-generated method stub
		this.contracDao.insert(paramContract);
	}

	public void update(Contract paramContract) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Serializable paramSerializable) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Serializable[] paramArrayOfSerializable) {
		// TODO Auto-generated method stub
		
	}

	public void submit(Serializable[] paramArrayOfSerializable) {
		// TODO Auto-generated method stub
		
	}

	public void cancel(Serializable[] paramArrayOfSerializable) {
		// TODO Auto-generated method stub
		
	}

}
