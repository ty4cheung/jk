package jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jk.domain.Contract;
import jk.pagination.Page;
import jk.vo.ContractVO;

public abstract interface ContractService {
	  public abstract List<Contract> findPage(Page paramPage);

	  public abstract List<Contract> find(Map paramMap);

	  public abstract Contract get(Serializable paramSerializable);

	  public abstract void insert(Contract paramContract);

	  public abstract void update(Contract paramContract);

	  public abstract void deleteById(Serializable paramSerializable);

	  public abstract void delete(Serializable[] paramArrayOfSerializable);

	  public abstract void submit(Serializable[] paramArrayOfSerializable);

	  public abstract void cancel(Serializable[] paramArrayOfSerializable);

	  public abstract ContractVO view(String id);

}
