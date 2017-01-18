package jk.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jk.dao.ContractDao;
import jk.domain.Contract;
import jk.pagination.Page;

/**
 * @author zhangtai
 *
 */
public class ContractServiceTest {

	private ApplicationContext context;
	private ContractDao dao;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[]{
						"spring/applicationContext-dao.xml",
						"spring/applicationContext.xml",
						"spring/applicationContext-service.xml"
						}
			);
		dao = (ContractDao) context.getBean("contracDao");
	
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testFindPage() {
		Page<Contract> page = new Page<Contract>();
		page.setPageNo(0);
		page.setPageSize(1);
		List<Contract> findPage = dao.findPage(page);
		System.out.println(findPage.size()+"");
	}

	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		Contract c = new Contract();
		
		c.setContractNo("s");
		c.setCheckBy("2");
		c.setCreateBy("张无谁");
		c.setCreateDept("呵呵呵大");
		c.setCreateTime(new Date());
		c.setCrequest("aaaaa a a");
		c.setDeliveryPeriod(new Date());

		c.setImportNum(11);
		c.setInputBy("1");
		c.setOldState(1);
		c.setOfferor("1");
		c.setOutState(1);
		c.setPrintStyle("1");
		c.setShipTime(new Date());
		c.setSigningDate(new Date());
		try {

			dao.insert(c);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDeleteById(){
		
	}

}
