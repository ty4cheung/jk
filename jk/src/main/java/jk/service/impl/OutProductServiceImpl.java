package jk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jk.dao.OutProductDao;
import jk.service.OutProductService;
import jk.vo.OutProductVO;

@Service
public class OutProductServiceImpl  implements OutProductService{

	@Resource
	OutProductDao outProductDao;
	
	public List<OutProductVO> find(String inputDate) {
		Map<String, Object> hashmap = new HashMap<String,Object>();
		hashmap.put("inputDate", inputDate);
		return outProductDao.find(hashmap);
	}
	
	 

}
