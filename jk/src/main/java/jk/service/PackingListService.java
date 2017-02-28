
package jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jk.domain.PackingList;
import jk.pagination.Page;

/**
 * @Description: TODO
 * @Author:	tyler
 * @Company:	
 * @CreateDate:	2017年2月22日
 */
public interface PackingListService {
		public abstract List<PackingList> findPage(Page paramPage);

	  public abstract List<PackingList> find(Map paramMap);

	  public abstract PackingList get(Serializable paramSerializable);

	  public abstract void insert(PackingList paramPackingList);

	  public abstract void update(PackingList paramPackingList);

	  public abstract void deleteById(Serializable paramSerializable);

	  public abstract void delete(Serializable[] paramArrayOfSerializable);

	public abstract Object getDivDataCreate(String[] id);

	public abstract Object getDivDataView(String[] split);

	public abstract String getDivDataUpdate(String[] split, String[] split2);
}
