/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bbd.bdsso.common.dal.daointerface.SsoUserDAO;

// auto generated imports
import com.bbd.bdsso.common.dal.dataobject.SsoUserDO;
import org.springframework.dao.DataAccessException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.bbd.bdsso.common.dal.dataobject.SsoUserDO;

/**
 * An ibatis based implementation of dao interface <tt>com.bbd.bdsso.common.dal.daointerface.SsoUserDAO</tt>.
 *
 * This file is generated by <tt>bdsso-bbdalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>bdsso</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/bbdalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/sso_user.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>bdsso-bbdalgen</tt> 
 * to generate this file.
 *
 * @author Byron Zhang
 * @version $Id: description-java.vm,v 1.1 2016/05/01 07:34:20 byron Exp $
 */
public class IbatisSsoUserDAO extends SqlMapClientDaoSupport implements SsoUserDAO {
	/**
	 *  Insert one <tt>SsoUserDO</tt> object to DB table <tt>sso_user</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into sso_user(user_name,user_password,email,is_enable,description,last_modifier,gmt_create,gmt_modified) values (?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param ssoUser
	 *	@return Integer
	 *	@throws DataAccessException
	 */	 
    public Integer insert(SsoUserDO ssoUser) throws DataAccessException {
    	if (ssoUser == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-SSO-USER-INSERT", ssoUser);

        return ssoUser.getId();
    }

	/**
	 *  Delete records from DB table <tt>sso_user</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_user where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(Integer id) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-USER-DELETE", id);
    }

	/**
	 *  Update DB table <tt>sso_user</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update sso_user set is_enable=?, description=?, last_modifier=? where (id = ?)</tt>
	 *
	 *	@param isEnable
	 *	@param description
	 *	@param lastModifier
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(Integer isEnable, String description, String lastModifier, Integer id) throws DataAccessException {
        Map param = new HashMap();

        param.put("isEnable", isEnable);
        param.put("description", description);
        param.put("lastModifier", lastModifier);
        param.put("id", id);

        return getSqlMapClientTemplate().update("MS-SSO-USER-UPDATE", param);
    }

	/**
	 *  Update DB table <tt>sso_user</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update sso_user set user_password=? where (id = ?)</tt>
	 *
	 *	@param userPassword
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateByPsw(String userPassword, Integer id) throws DataAccessException {
        Map param = new HashMap();

        param.put("userPassword", userPassword);
        param.put("id", id);

        return getSqlMapClientTemplate().update("MS-SSO-USER-UPDATE-BY-PSW", param);
    }

	/**
	 *  Update DB table <tt>sso_user</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update sso_user set user_password=?, gmt_modified=? where (email = ?)</tt>
	 *
	 *	@param userPassword
	 *	@param gmtModified
	 *	@param email
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateByEmail(String userPassword, Date gmtModified, String email) throws DataAccessException {
        Map param = new HashMap();

        param.put("userPassword", userPassword);
        param.put("gmtModified", gmtModified);
        param.put("email", email);

        return getSqlMapClientTemplate().update("MS-SSO-USER-UPDATE-BY-EMAIL", param);
    }

	/**
	 *  Query DB table <tt>sso_user</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_name, user_password, email, is_enable, description, last_modifier, gmt_create, gmt_modified from sso_user where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return SsoUserDO
	 *	@throws DataAccessException
	 */	 
    public SsoUserDO query(Integer id) throws DataAccessException {

        return (SsoUserDO) getSqlMapClientTemplate().queryForObject("MS-SSO-USER-QUERY", id);

    }

	/**
	 *  Query DB table <tt>sso_user</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_name, user_password, email, is_enable, description, last_modifier, gmt_create, gmt_modified from sso_user</tt>
	 *
	 *	@return List<SsoUserDO>
	 *	@throws DataAccessException
	 */	 
    public List<SsoUserDO> queryAll() throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-SSO-USER-QUERY-ALL", null);

    }

	/**
	 *  Query DB table <tt>sso_user</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_name, user_password, email, is_enable, description, last_modifier, gmt_create, gmt_modified from sso_user where (user_name = ?)</tt>
	 *
	 *	@param userName
	 *	@return SsoUserDO
	 *	@throws DataAccessException
	 */	 
    public SsoUserDO queryByName(String userName) throws DataAccessException {

        return (SsoUserDO) getSqlMapClientTemplate().queryForObject("MS-SSO-USER-QUERY-BY-NAME", userName);

    }

	/**
	 *  Query DB table <tt>sso_user</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_name, user_password, email, is_enable, description, last_modifier, gmt_create, gmt_modified from sso_user where (email = ?)</tt>
	 *
	 *	@param email
	 *	@return SsoUserDO
	 *	@throws DataAccessException
	 */	 
    public SsoUserDO queryByEmail(String email) throws DataAccessException {

        return (SsoUserDO) getSqlMapClientTemplate().queryForObject("MS-SSO-USER-QUERY-BY-EMAIL", email);

    }

	/**
	 *  Query DB table <tt>sso_user</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_name, user_password, email, is_enable, description, last_modifier, gmt_create, gmt_modified from sso_user where ((user_name = ?) AND (user_password = ?))</tt>
	 *
	 *	@param userName
	 *	@param userPassword
	 *	@return SsoUserDO
	 *	@throws DataAccessException
	 */	 
    public SsoUserDO loginByUserName(String userName, String userPassword) throws DataAccessException {
        Map param = new HashMap();

        param.put("userName", userName);
        param.put("userPassword", userPassword);

        return (SsoUserDO) getSqlMapClientTemplate().queryForObject("MS-SSO-USER-LOGIN-BY-USER-NAME", param);

    }

	/**
	 *  Query DB table <tt>sso_user</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_name, user_password, email, is_enable, description, last_modifier, gmt_create, gmt_modified from sso_user where ((email = ?) AND (user_password = ?))</tt>
	 *
	 *	@param email
	 *	@param userPassword
	 *	@return SsoUserDO
	 *	@throws DataAccessException
	 */	 
    public SsoUserDO loginByEmail(String email, String userPassword) throws DataAccessException {
        Map param = new HashMap();

        param.put("email", email);
        param.put("userPassword", userPassword);

        return (SsoUserDO) getSqlMapClientTemplate().queryForObject("MS-SSO-USER-LOGIN-BY-EMAIL", param);

    }

	/**
	 *  Query DB table <tt>sso_user</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select COUNT(*) from sso_user where (is_enable = 1)</tt>
	 *
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long queryTotalUser() throws DataAccessException {

	    Long retObj = (Long) getSqlMapClientTemplate().queryForObject("MS-SSO-USER-QUERY-TOTAL-USER", null);

		if (retObj == null) {
		    return 0;
		} else {
		    return retObj.longValue();
		}

    }

	/**
	 *  Delete records from DB table <tt>sso_user</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_user where (description LIKE ?)</tt>
	 *
	 *	@param description
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByDesc(String description) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-SSO-USER-DELETE-BY-DESC", description);
    }

}