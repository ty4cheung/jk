package jk.pagination;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.mysql.jdbc.Connection;



public class PageInterceptor implements Interceptor {

	private String databaseType;
	 
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handel = (RoutingStatementHandler) invocation.getTarget();
		StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(handel, "delegate");
		BoundSql boundSql = delegate.getBoundSql();
		 Object obj = boundSql.getParameterObject();
		if(obj instanceof Page){
			Page page = (Page) obj;
			MappedStatement mappedStatement =  (MappedStatement) ReflectUtil.getFieldValue(obj, "mappedStatement");
			Connection connection = (Connection) invocation.getArgs()[0];
			String sql = boundSql.getSql();
			setTotalRecord(page, mappedStatement, connection);
			 String pageSql = getPageSql(page, sql);
			 ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
		}
		return null;
	}

	
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		this.databaseType = properties.getProperty("databaseType");
	}
	
	private void setTotalRecord(Page<?> page, MappedStatement mappedStatement, Connection connection)
	  {
	    BoundSql boundSql = mappedStatement.getBoundSql(page);

	    String sql = boundSql.getSql();

	    String countSql = getCountSql(sql);

	    List parameterMappings = boundSql.getParameterMappings();

	    BoundSql countBoundSql = new BoundSql(
	      mappedStatement.getConfiguration(), countSql, 
	      parameterMappings, page);

	    ParameterHandler parameterHandler = new DefaultParameterHandler(
	      mappedStatement, page, countBoundSql);

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	      pstmt = connection.prepareStatement(countSql);

	      parameterHandler.setParameters(pstmt);

	      rs = pstmt.executeQuery();
	      if (rs.next()) {
	        int totalRecord = rs.getInt(1);

	        page.setTotalRecord(totalRecord);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	      try
	      {
	        if (rs != null)
	          rs.close();
	        if (pstmt != null)
	          pstmt.close();
	      } catch (SQLException e1) {
	        e1.printStackTrace();
	      }
	    }
	    finally
	    {
	      try
	      {
	        if (rs != null)
	          rs.close();
	        if (pstmt != null)
	          pstmt.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	  }
	
	private String getCountSql(String sql)
	  {
	    int index = sql.indexOf("from");
	    return "select count(*) " + sql.substring(index);
	  }

	
	private String getPageSql(Page<?> page, String sql)
	  {
	    StringBuffer sqlBuffer = new StringBuffer(sql);
	    if ("mysql".equalsIgnoreCase(this.databaseType))
	      return getMysqlPageSql(page, sqlBuffer);
	    if ("oracle".equalsIgnoreCase(this.databaseType)) {
	      return getOraclePageSql(page, sqlBuffer);
	    }
	    return sqlBuffer.toString();
	  }
	
	private String getMysqlPageSql(Page<?> page, StringBuffer sqlBuffer)
	  {
	    int offset = (page.getPageNo() - 1) * page.getPageSize();
	    sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());
	    return sqlBuffer.toString();
	  }

	  private String getOraclePageSql(Page<?> page, StringBuffer sqlBuffer)
	  {
	    int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
	    sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getPageSize());
	    sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);

	    return sqlBuffer.toString();
	  }
	
	private static class ReflectUtil{
		
		public static Object getFieldValue(Object obj, String fieldName){
			 Object result = null;
			 Field field = getField(obj, fieldName);
			 if(field !=null){
				 field.setAccessible(true);
			 }
			 try {
				result = field.get(obj);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return result;
		}
		
		private static Field getField(Object obj, String fieldName){
			Field field = null;
			for(Class clazz = obj.getClass();clazz !=Object.class;){
				
				 try {
					field = clazz.getDeclaredField(fieldName);
				} catch (NoSuchFieldException e) {
					clazz = clazz.getSuperclass();
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 return field;
		}
		
		private static void setFieldValue(Object obj,String fieldName, String fieldValue){
			Field field = getField(obj, fieldName);
			if (field != null)
		        try {
		          field.setAccessible(true);
		          field.set(obj, fieldValue);
		        } catch (IllegalArgumentException e) {
		          e.printStackTrace();
		        } catch (IllegalAccessException e) {
		          e.printStackTrace();
		        }
		}
		
	}

}
