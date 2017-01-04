package jk.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jk.domain.Contract;
import jk.pagination.Page;

/**
 * @author zhangtai
 *
 */
public interface ContractDao extends BaseDao<Contract> {

	public abstract void updateState(Map paramMap);
}
