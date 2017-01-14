package jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jk.domain.Contract;
import jk.domain.SysCode;
import jk.pagination.Page;

public abstract interface SysCodeService {

	  public abstract List<SysCode> find(Map map);

}
