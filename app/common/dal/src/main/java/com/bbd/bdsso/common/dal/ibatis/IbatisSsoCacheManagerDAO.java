/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bbd.bdsso.common.dal.daointerface.SsoCacheManagerDAO;

// auto generated imports
import com.bbd.bdsso.common.dal.dataobject.SsoCacheManagerDO;
import org.springframework.dao.DataAccessException;
import java.util.Map;
import java.util.HashMap;

/**
 * An ibatis based implementation of dao interface <tt>com.bbd.bdsso.common.dal.daointerface.SsoCacheManagerDAO</tt>.
 *
 * This file is generated by <tt>bdsso-bbdalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>bdsso</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/bbdalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/sso_cache_manager.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>bdsso-bbdalgen</tt> 
 * to generate this file.
 *
 * @author Byron Zhang
 * @version $Id: description-java.vm,v 1.1 2016/05/01 07:34:20 byron Exp $
 */
public class IbatisSsoCacheManagerDAO extends SqlMapClientDaoSupport implements SsoCacheManagerDAO {
	/**
	 *  Insert one <tt>SsoCacheManagerDO</tt> object to DB table <tt>sso_cache_manager</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into sso_cache_manager(cache_name,server_host,server_ip,status,description,gmt_create,gmt_modified) values (?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param ssoCacheManager
	 *	@return Integer
	 *	@throws DataAccessException
	 */	 
    public Integer insert(SsoCacheManagerDO ssoCacheManager) throws DataAccessException {
    	if (ssoCacheManager == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-SSO-CACHE-MANAGER-INSERT", ssoCacheManager);

        return ssoCacheManager.getId();
    }

	/**
	 *  Query DB table <tt>sso_cache_manager</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, cache_name, server_host, server_ip, status, description, gmt_create, gmt_modified from sso_cache_manager where ((cache_name = ?) AND (server_host = ?) AND (server_ip = ?))</tt>
	 *
	 *	@param cacheName
	 *	@param serverHost
	 *	@param serverIp
	 *	@return SsoCacheManagerDO
	 *	@throws DataAccessException
	 */	 
    public SsoCacheManagerDO queryByCacheName(String cacheName, String serverHost, String serverIp) throws DataAccessException {
        Map param = new HashMap();

        param.put("cacheName", cacheName);
        param.put("serverHost", serverHost);
        param.put("serverIp", serverIp);

        return (SsoCacheManagerDO) getSqlMapClientTemplate().queryForObject("MS-SSO-CACHE-MANAGER-QUERY-BY-CACHE-NAME", param);

    }

	/**
	 *  Query DB table <tt>sso_cache_manager</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, cache_name, server_host, server_ip, status, description, gmt_create, gmt_modified from sso_cache_manager where ((status = ?) AND (server_host = ?) AND (server_ip = ?))</tt>
	 *
	 *	@param status
	 *	@param serverHost
	 *	@param serverIp
	 *	@return SsoCacheManagerDO
	 *	@throws DataAccessException
	 */	 
    public SsoCacheManagerDO queryByStatus(String status, String serverHost, String serverIp) throws DataAccessException {
        Map param = new HashMap();

        param.put("status", status);
        param.put("serverHost", serverHost);
        param.put("serverIp", serverIp);

        return (SsoCacheManagerDO) getSqlMapClientTemplate().queryForObject("MS-SSO-CACHE-MANAGER-QUERY-BY-STATUS", param);

    }

	/**
	 *  Update DB table <tt>sso_cache_manager</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update sso_cache_manager set status=? where (id = ?)</tt>
	 *
	 *	@param status
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateById(String status, Integer id) throws DataAccessException {
        Map param = new HashMap();

        param.put("status", status);
        param.put("id", id);

        return getSqlMapClientTemplate().update("MS-SSO-CACHE-MANAGER-UPDATE-BY-ID", param);
    }

	/**
	 *  Update DB table <tt>sso_cache_manager</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update sso_cache_manager set status=? where (cache_name = ?)</tt>
	 *
	 *	@param status
	 *	@param cacheName
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateStatusByName(String status, String cacheName) throws DataAccessException {
        Map param = new HashMap();

        param.put("status", status);
        param.put("cacheName", cacheName);

        return getSqlMapClientTemplate().update("MS-SSO-CACHE-MANAGER-UPDATE-STATUS-BY-NAME", param);
    }

	/**
	 *  Delete records from DB table <tt>sso_cache_manager</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_cache_manager where (description LIKE ?)</tt>
	 *
	 *	@param description
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByDesc(String description) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-CACHE-MANAGER-DELETE-BY-DESC", description);
    }

}