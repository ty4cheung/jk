
package jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jk.dao.ExportDao;
import jk.domain.Contract;
import jk.domain.Export;
import jk.pagination.Page;
import jk.service.ExportService;

/**
 * @Description: TODO
 * @Author:	tyler
 * @Company:	
 * @CreateDate:	2017年2月9日
 */
public class ExportServiceImpl implements ExportService {

	@Resource
	ExportDao exportDao;
	
	public List<Export> findPage(Page paramPage) {
		// TODO Auto-generated method stub
		return exportDao.findPage(paramPage);
	}

	public List<Export> find(Map paramMap) {
		// TODO Auto-generated method stub
		return exportDao.find(paramMap);
	}

	public Export get(Serializable id) {
		// TODO Auto-generated method stub
		return exportDao.get(id);
	}

	public void insert(Export export) {
		// TODO Auto-generated method stub
		exportDao.insert(export);
	}

	public void update(Export export) {
		// TODO Auto-generated method stub
		exportDao.update(export);
	}

	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		exportDao.deleteById(id);

	}

	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub

	}

	public void submit(Serializable[] ids) {
		// TODO Auto-generated method stub

	}

	public void cancel(Serializable[] ids) {
		// TODO Auto-generated method stub

	}

}
