
package jk.dao.impl;

import org.springframework.stereotype.Repository;

import jk.dao.ExportProductDao;
import jk.domain.ExportProduct;

/**
 * @Description: TODO
 * @Author:	tyler
 * @Company:	
 * @CreateDate:	2017年2月13日
 */
@Repository
public class ExportProductDaoImpl extends BaseDaoImpl<ExportProduct> implements ExportProductDao{
	public ExportProductDaoImpl() {
		//设置命名空间
		super.setNs("jk.dao.mapper.ExportProductMapper");
	}
	
}
