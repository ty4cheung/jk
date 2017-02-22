
package jk.dao.impl;

import java.io.Serializable;
import java.util.Map;

import jk.dao.PackingListDao;
import jk.domain.PackingList;

/**
 * @Description: TODO
 * @Author:	tyler
 * @Company:	
 * @CreateDate:	2017年2月22日
 */
public class PackingListDaoImpl extends BaseDaoImpl<PackingList> implements PackingListDao{

	@Override
	public void setNs(String ns) {
		// TODO Auto-generated method stub
		super.setNs("jk.dao.mapper.PackingListMapper");
		
	}
	
	@Override
	public void updateState(Map paramMap) {
		// TODO Auto-generated method stub
		
	}


}
