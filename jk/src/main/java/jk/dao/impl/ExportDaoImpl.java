package jk.dao.impl;

import java.util.Map;

import jk.dao.ExportDao;
import jk.domain.Export;

/**
 * @author zhangtai
 *
 */
public class ExportDaoImpl extends BaseDaoImpl<Export> implements ExportDao{


	public ExportDaoImpl() {
		super();
	}

	@Override
	public void setNs(String ns) {
		// TODO Auto-generated method stub
		super.setNs("jk.dao.mapper.ExportMapper");
	}
	
	public void updateState(Map map) {
		// TODO Auto-generated method stub
		  super.getSqlSession().update(super.getNs() + ".updateState", map);
	}

	

}
