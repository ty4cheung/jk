package jk.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jk.dao.ContractDao;
import jk.dao.FactoryDao;
import jk.domain.Factory;

/**
 * @author zhangtai
 *
 */
public class FactoryServiceTest {
	private ApplicationContext context;
	private FactoryDao factoryDao;
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[]{
						"spring/applicationContext-dao.xml",
						"spring/applicationContext.xml",
						"spring/applicationContext-service.xml"
						}
			);
		factoryDao = (FactoryDao) context.getBean("factoryDao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", 1);
		
		List<Factory> find = factoryDao.find(map);
		System.out.println(find);
		
		
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		
		Factory f = new Factory();
		f.setCreateBy("张泰");
		f.setFullName("同志厂");
		f.setId("1");
		factoryDao.insert(f);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testStop() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFactoryList() {
		fail("Not yet implemented");
	}

}
