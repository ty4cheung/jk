package jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jk.domain.ContractProduct;
import jk.pagination.Page;

/**
 * @author zhangtai
 *
 */
public abstract interface ContractProductService {

	  public abstract List<ContractProduct> findPage(Page paramPage);

	  public abstract List<ContractProduct> find(Map paramMap);

	  public abstract ContractProduct get(Serializable paramSerializable);

	  public abstract void insert(ContractProduct paramContractProduct);

	  public abstract void update(ContractProduct paramContractProduct);

	  public abstract void deleteById(Serializable paramSerializable);

	  public abstract void delete(Serializable[] paramArrayOfSerializable);

}
