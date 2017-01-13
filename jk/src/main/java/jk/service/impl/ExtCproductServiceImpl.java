package jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jk.dao.ExtCproductDao;
import jk.domain.ExtCproduct;
import jk.pagination.Page;
import jk.service.ExtCproductService;
import jk.util.UtilFuns;

/**
 * @author zhangtai
 *
 */
@Service
public class ExtCproductServiceImpl implements ExtCproductService {

	@Resource
	ExtCproductDao extCproductDao;

	public List<ExtCproduct> findPage(Page page) {
		// TODO Auto-generated method stub
		return this.extCproductDao.findPage(page);
	}

	public List<ExtCproduct> find(Map paraMap) {
		// TODO Auto-generated method stub
		return this.extCproductDao.find(paraMap);
	}

	public ExtCproduct get(Serializable id) {
		// TODO Auto-generated method stub
		return this.extCproductDao.get(id);
	}

	public void insert(ExtCproduct extCproduct) {
		// TODO Auto-generated method stub
		extCproduct.setId(UUID.randomUUID().toString());
		//自动计算总金额=数量*单价		...修改，删除；同步合同总金额
		if(UtilFuns.isNotEmpty(extCproduct.getCnumber()) && UtilFuns.isNotEmpty(extCproduct.getPrice())){
			extCproduct.setAmount(extCproduct.getCnumber()*extCproduct.getPrice());
		}
		
		this.extCproductDao.insert(extCproduct);
	}

	public void update(ExtCproduct extCproduct) {
		// TODO Auto-generated method stub
		this.extCproductDao.update(extCproduct);
	}

	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		this.extCproductDao.deleteById(id);
	}

	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
		this.extCproductDao.delete(ids);
	}
	
}
