package com.business.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.client.SqlMapExecutor;

@Component
public class BaseDao {
	//由spring 注入sqlMapClientTemplate
	//配置后用到此对象,spring会实例化该对象，不用我们每次去拿对象
	
	@Resource
	protected SqlMapClientTemplate sqlMapClientTemplate;
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	 /**
    * 删除记录,参数为SQL语句的名字及SQL参数。返回为更新的记录数量�?
    * 
    * @param sqlName
    * @param parameter
    * @return
    */
	public int delete(String sqlName, Object parameter)throws Exception {
		if(parameter!=null){
			return sqlMapClientTemplate.delete(sqlName, parameter);
		}else{
			return sqlMapClientTemplate.delete(sqlName);
		}
		
	}

	/**
    * 数据读取，返回为查询到的对象列表
    * 
    * @param sqlName
    * @return
    */
	public List getMultiObjects(String sqlName, Object parameters)throws SQLException {
		if(parameters!=null){
			return sqlMapClientTemplate.queryForList(sqlName, parameters);
		}else{
			return sqlMapClientTemplate.queryForList(sqlName);
		}
	}

	
	public List getMultiObjects(String sqlName, Object parameters,
			int skipResults, int maxResults)  throws SQLException{
		if(parameters!=null){
			return sqlMapClientTemplate.queryForList(sqlName, parameters, skipResults, maxResults);
		}else{
			return sqlMapClientTemplate.queryForList(sqlName, skipResults, maxResults);
		}
	}

	
	public Object getObject(String sqlName, Object parameters)throws SQLException {
		if(parameters!=null){
			return sqlMapClientTemplate.queryForObject(sqlName, parameters);
		}else{
			return sqlMapClientTemplate.queryForObject(sqlName);
		}
	}

	
	public Object insert(String sqlName, Object parameter)throws SQLException {
		if(parameter!=null){
			return sqlMapClientTemplate.insert(sqlName, parameter);
		}else{
			return sqlMapClientTemplate.insert(sqlName);
		}
	}

	
	public int update(String sqlName, Object parameter) throws SQLException{
		if(parameter!=null){
			return sqlMapClientTemplate.update(sqlName, parameter);
		}else{
			return sqlMapClientTemplate.update(sqlName);
		}
	}

	 /**
    * 数据读取，返回为Map
    * @param sqlName
    * @param parameters
    * @param keyProperty
    * @param valueProperty
    * @return
    */
	public Map getMultiObjectsForMap(String sqlName, Object parameters,
			String keyProperty, String valueProperty) {
		if(valueProperty!=null&&!"".equals(valueProperty)){
			return sqlMapClientTemplate.queryForMap(sqlName, parameters, keyProperty, valueProperty);
		}else{
			return sqlMapClientTemplate.queryForMap(sqlName,parameters, keyProperty);
		}
		
	}
	/**
	 * 批量新增
	 * @param sqlName
	 * @param parameters
	 * @return
	 */
	public void batchInsert(final String sqlName,final List parameters)throws SQLException{
		 sqlMapClientTemplate.execute(new SqlMapClientCallback(){

			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Iterator iterator = parameters.iterator(); iterator
						.hasNext();) {
					Object object = (Object) iterator.next();
					executor.insert(sqlName,object);
				}
				executor.executeBatch();
				return null;
			}});
	}
	
	/**
    * 批量修改
   * @param sqlName
   * @param parameters
   */
	public void batchUpdate(final String sqlName , final List parameters)throws SQLException{
		sqlMapClientTemplate.execute(new SqlMapClientCallback(){

			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Iterator iterator = parameters.iterator(); iterator
						.hasNext();) {
					Object object = (Object) iterator.next();
					executor.update(sqlName,object);
				}
				executor.executeBatch();
				return null;
			}});	
		return;
	}

	/**
    * 批量删除
   * @param sqlName
   * @param parameters
   */
	public void batchdelete(final String sqlName, final List parameters)
			throws SQLException {
		sqlMapClientTemplate.execute(new SqlMapClientCallback(){

			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Iterator iterator = parameters.iterator(); iterator
						.hasNext();) {
					Object object = (Object) iterator.next();
					executor.delete(sqlName,object);
				}
				executor.executeBatch();
				return null;
			}});	
		return;
		
	}
}
