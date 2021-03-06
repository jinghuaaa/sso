/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.daointerface;

// auto generated imports
import com.bbd.bdsso.common.dal.dataobject.SsoAccessDO;
import org.springframework.dao.DataAccessException;
import java.util.Date;
import java.util.List;

/**
 * A dao interface provides methods to access database table <tt>sso_access</tt>.
 *
 * This file is generated by <tt>bdsso-bbdalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>bdsso</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/bbdalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/sso_access.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>bdsso-bbdalgen</tt> 
 * to generate this file.
 *
 * @author Byron Zhang
 * @version $Id: description-java.vm,v 1.1 2016/05/01 07:34:20 byron Exp $
 */
public interface SsoAccessDAO {
	/**
	 *  Insert one <tt>SsoAccessDO</tt> object to DB table <tt>sso_access</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into sso_access(user_id,token,valid_date,description,gmt_create,gmt_modified) values (?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param ssoAccess
	 *	@return Integer
	 *	@throws DataAccessException
	 */	 
    public Integer insert(SsoAccessDO ssoAccess) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>sso_access</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_access where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(Integer id) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>sso_access</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_access where (user_id = ?)</tt>
	 *
	 *	@param userId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByUserId(Integer userId) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>sso_access</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_access where ((user_id = ?) AND (token = ?))</tt>
	 *
	 *	@param userId
	 *	@param token
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByUserIdAndToken(Integer userId, String token) throws DataAccessException;

	/**
	 *  Update DB table <tt>sso_access</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update sso_access set user_id=?, token=?, valid_date=?, description=?, gmt_modified=? where (id = ?)</tt>
	 *
	 *	@param ssoAccess
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(SsoAccessDO ssoAccess) throws DataAccessException;

	/**
	 *  Update DB table <tt>sso_access</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update sso_access set valid_date=?, gmt_modified=? where (user_id = ?)</tt>
	 *
	 *	@param validDate
	 *	@param gmtModified
	 *	@param userId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateValidDate(Date validDate, Date gmtModified, Integer userId) throws DataAccessException;

	/**
	 *  Query DB table <tt>sso_access</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_id, token, valid_date, description, gmt_create, gmt_modified from sso_access where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return SsoAccessDO
	 *	@throws DataAccessException
	 */	 
    public SsoAccessDO query(Integer id) throws DataAccessException;

	/**
	 *  Query DB table <tt>sso_access</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_id, token, valid_date, description, gmt_create, gmt_modified from sso_access</tt>
	 *
	 *	@return List<SsoAccessDO>
	 *	@throws DataAccessException
	 */	 
    public List<SsoAccessDO> queryAll() throws DataAccessException;

	/**
	 *  Query DB table <tt>sso_access</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_id, token, valid_date, description, gmt_create, gmt_modified from sso_access where (user_id = ?)</tt>
	 *
	 *	@param userId
	 *	@return SsoAccessDO
	 *	@throws DataAccessException
	 */	 
    public SsoAccessDO queryByUserId(Integer userId) throws DataAccessException;

	/**
	 *  Query DB table <tt>sso_access</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_id, token, valid_date, description, gmt_create, gmt_modified from sso_access where (token = ?)</tt>
	 *
	 *	@param token
	 *	@return SsoAccessDO
	 *	@throws DataAccessException
	 */	 
    public SsoAccessDO queryByToken(String token) throws DataAccessException;

	/**
	 *  Query DB table <tt>sso_access</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_id, token, valid_date, description, gmt_create, gmt_modified from sso_access where ((user_id = ?) AND (token = ?))</tt>
	 *
	 *	@param userId
	 *	@param token
	 *	@return SsoAccessDO
	 *	@throws DataAccessException
	 */	 
    public SsoAccessDO queryByUserIdAndToken(Integer userId, String token) throws DataAccessException;

	/**
	 *  Query DB table <tt>sso_access</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, user_id, token, valid_date, description, gmt_create, gmt_modified from sso_access where (valid_date = ?)</tt>
	 *
	 *	@param validDate
	 *	@return List<SsoAccessDO>
	 *	@throws DataAccessException
	 */	 
    public List<SsoAccessDO> queryByDate(Date validDate) throws DataAccessException;

	/**
	 *  Query DB table <tt>sso_access</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select a.id id, a.user_id user_id, a.token token, a.description description, a.valid_date valid_date, a.gmt_create gmt_create, a.gmt_modified gmt_modified from sso_access a, sso_user b where ((a.user_id = b.id) AND (b.is_enable = 1))</tt>
	 *
	 *	@param userId
	 *	@param token
	 *	@return SsoAccessDO
	 *	@throws DataAccessException
	 */	 
    public SsoAccessDO checkValid(String userId, String token) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>sso_access</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from sso_access where (description LIKE ?)</tt>
	 *
	 *	@param description
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByDesc(String description) throws DataAccessException;

}