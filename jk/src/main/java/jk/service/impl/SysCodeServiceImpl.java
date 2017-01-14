package jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jk.dao.ContractDao;
import jk.dao.SysCodeDao;
import jk.domain.Contract;
import jk.domain.SysCode;
import jk.pagination.Page;
import jk.service.ContractService;
import jk.service.SysCodeService;

/**
 * @author zhangtai
 *
 */
@Service
public class SysCodeServiceImpl  implements SysCodeService {

	@Resource
	SysCodeDao sysCodeDao;
	
	public List<SysCode> find(Map map) {
		// TODO Auto-generated method stub
		return this.sysCodeDao.find(map);
	}


}
