  
package jk.dao;

import java.util.Map;

import jk.domain.PackingList;

/**
 * @Description: TODO
 * @Author:	tyler
 * @Company:	
 * @CreateDate:	2017年2月22日
 */
public interface PackingListDao extends BaseDao<PackingList>{
	public abstract void updateState(Map paramMap);
}
