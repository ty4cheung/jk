package jk.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jk.dao.FactoryDao;
import jk.domain.Factory;
import jk.pagination.Page;
import jk.service.FactoryService;
@Service
public class FactoryServiceImpl implements FactoryService{

	@Resource
	FactoryDao factoryDao;
	
	
	public List<Factory> findPage(Page paramPage) {
		// TODO Auto-generated method stub
		return this.factoryDao.findPage(paramPage);
	}


	public List<Factory> find(Map paramMap) {
		// TODO Auto-generated method stub
		return this.factoryDao.find(paramMap);
	}


	public Factory get(Serializable paramSerializable) {
		// TODO Auto-generated method stub
		return this.factoryDao.get(paramSerializable);
	}

	
	public void insert(Factory factory) {
		// TODO Auto-generated method stub
		factory.setId(UUID.randomUUID().toString());
	    factory.setState("1");
	    this.factoryDao.insert(factory);
		
	}

	public void update(Factory factory) {
		// TODO Auto-generated method stub
		this.factoryDao.update(factory);
		
	}


	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		 this.factoryDao.deleteById(id);
	}


	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		  this.factoryDao.delete(ids);
	}

	public void start(Serializable[] ids) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();
	    map.put("state", Integer.valueOf(1));
	    map.put("ids", ids);
	    this.factoryDao.updateState(map);
	}

	
	public void stop(Serializable[] ids) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("state", Integer.valueOf(0));
	    map.put("ids", ids);
		this.factoryDao.updateState(map);
	}
	
	public List<Factory> getFactoryList()
	  {
	    Map<String, Integer> paraMap = new HashMap<String, Integer>();
	    paraMap.put("state", Integer.valueOf(1));
	    return this.factoryDao.find(paraMap);
	  }

}
