
package jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jk.domain.Contract;
import jk.domain.Export;
import jk.pagination.Page;
import jk.vo.ContractVO;

/**
 * @Description: TODO
 * @Author:	tyler
 * @Company:	
 * @CreateDate:	2017年2月9日
 */
public abstract interface ExportService {
	public abstract List<Export> findPage(Page paramPage);

	  public abstract List<Export> find(Map paramMap);

	  public abstract Export get(Serializable id);

	  public void insert(String[] contractIds);

	  public abstract void update(Export export);

	  public abstract void deleteById(Serializable id);

	  public abstract void delete(Serializable[] ids);

	  public abstract void submit(Serializable[] ids);

	  public abstract void cancel(Serializable[] ids);

	  public abstract List<Contract> getContract();
	
}
