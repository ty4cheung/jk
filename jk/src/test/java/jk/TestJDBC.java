package jk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
public class TestJDBC {

	@Test
	public void jdbc() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Object instance = Class.forName("com.mysql.jdbc.Driver").newInstance();

		String url = "jdbc:mysql://localhost:3306/XE?useUnicode=true&characterEncoding=utf-8";
		String user = "root";
		String pwd = "";

		Connection conn = DriverManager.getConnection(url, user, pwd);
		Statement stmt = conn.createStatement(1005, 1008);

		String sql = "select * from contract_c";
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			for (int j = 0; j < rsmd.getColumnCount(); j++) {
				System.out.print(rs.getString(j + 1) + "\t");
			}
			System.out.println("");
		}

		rs.close();
		stmt.close();
		conn.close();
	}
	
	@Test
	public void insertContract(){
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
		c.setId("1");
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
