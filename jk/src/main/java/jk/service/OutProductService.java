package jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jk.domain.Contract;
import jk.pagination.Page;
import jk.vo.ContractVO;
import jk.vo.OutProductVO;

public abstract interface OutProductService {
	
	  public abstract List<OutProductVO> find(String inputDate);

}
