package jk.dao.impl;

import java.util.Map;

import jk.dao.ContractDao;
import jk.dao.SysCodeDao;
import jk.domain.Contract;
import jk.domain.SysCode;

/**
 * @author zhangtai
 *
 */
public class SysCodeDaoImpl extends BaseDaoImpl<SysCode> implements SysCodeDao{


	public SysCodeDaoImpl() {
		super.setNs("jk.dao.mapper.SysCodeMapper");;
		// TODO Auto-generated constructor stub
	}


}
