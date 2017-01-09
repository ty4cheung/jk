package jk;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jk.dao.ContractDao;
import jk.domain.Contract;

/**
 * @author zhangtai
 *
 */
public class ContractTest {

	
	
	@Test
	public void insertContractTest(){
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{
						"spring/applicationContext-dao.xml",
						"spring/applicationContext.xml",
						"spring/applicationContext-service.xml"
						}
			);
		ContractDao dao = (ContractDao) context.getBean("contracDao");
		Contract c = new Contract();
		c.setContractNo("1");
		c.setCheckBy("1");
		c.setCreateBy("abc");
		c.setCreateDept("a");
		c.setCreateTime(new Date());
		c.setCrequest("aaaa");
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
}
