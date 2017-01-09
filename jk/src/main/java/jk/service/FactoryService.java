package jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jk.domain.Factory;
import jk.pagination.Page;

public abstract interface FactoryService {
	  public abstract List<Factory> findPage(Page paramPage);

	  public abstract List<Factory> find(Map paramMap);

	  public abstract Factory get(Serializable paramSerializable);

	  public abstract void insert(Factory paramFactory);

	  public abstract void update(Factory paramFactory);

	  public abstract void deleteById(Serializable paramSerializable);

	  public abstract void delete(Serializable[] paramArrayOfSerializable);

	  public abstract void start(Serializable[] paramArrayOfSerializable);

	  public abstract void stop(Serializable[] paramArrayOfSerializable);

	  public abstract List<Factory> getFactoryList();

}
