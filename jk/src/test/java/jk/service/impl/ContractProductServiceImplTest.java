package jk.service.impl;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jk.dao.ContractDao;
import jk.dao.ContractProductDao;
import jk.dao.ExtCproductDao;

/**
 * @author zhangtai
 *
 */
public class ContractProductServiceImplTest {

	private ApplicationContext context;
	private ContractProductDao contractProductDao;
	private	ExtCproductDao extCproductDao;
	static{  
        PropertyConfigurator.configure("classPath:config/log4j.properties");  
} 
	@Before
	public void setUp() throws Exception {
	
		context = new ClassPathXmlApplicationContext(
				new String[]{
						"spring/applicationContext-dao.xml",
						"spring/applicationContext.xml",
						"spring/applicationContext-service.xml"
						}
			);
		contractProductDao = (ContractProductDao) context.getBean("contractProductDao");
		extCproductDao = (ExtCproductDao) context.getBean("extCproductDao");
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeleteById() {
		String id = "c6fb8627-f1e9-4d53-96f9-c174181fd2b2";
		String[] ids = {id};
		extCproductDao.deleteByContractProductById(ids);		//删除当前这些货物下的所有附件
		contractProductDao.deleteById(id);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
