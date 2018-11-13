/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.dataobject;

import java.io.Serializable;
// auto generated imports
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * A data object class directly models database table <tt>sso_user_role</tt>.
 *
 * This file is generated by <tt>bdsso-bbdalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>bdsso</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/bbdalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/sso_user_role.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>bdsso-bbdalgen</tt> 
 * to generate this file.
 *
 * @author Byron Zhang
 * @version $Id: description-java.vm,v 1.1 2016/05/01 07:34:20 byron Exp $
 */
public class SsoUserRoleDO implements Serializable {

	/** serialId */
    private static final long serialVersionUID = 741231858441822688L;

    //========== properties ==========

	/**
	 * This property corresponds to db column <tt>id</tt>.
	 */
	private Integer id;

	/**
	 * This property corresponds to db column <tt>user_id</tt>.
	 */
	private Integer userId;

	/**
	 * This property corresponds to db column <tt>role_id</tt>.
	 */
	private Integer roleId;

	/**
	 * This property corresponds to db column <tt>description</tt>.
	 */
	private String description;

	/**
	 * This property corresponds to db column <tt>last_modifier</tt>.
	 */
	private String lastModifier;

	/**
	 * This property corresponds to db column <tt>gmt_create</tt>.
	 */
	private Date gmtCreate;

	/**
	 * This property corresponds to db column <tt>gmt_modified</tt>.
	 */
	private Date gmtModified;

    //========== getters and setters ==========

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Setter method for property <tt>id</tt>.
	 * 
	 * @param id value to be assigned to property id
     */
	public void setId(Integer id) {
		this.id = id;
	}

    /**
     * Getter method for property <tt>userId</tt>.
     *
     * @return property value of userId
     */
	public Integer getUserId() {
		return userId;
	}
	
	/**
	 * Setter method for property <tt>userId</tt>.
	 * 
	 * @param userId value to be assigned to property userId
     */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

    /**
     * Getter method for property <tt>roleId</tt>.
     *
     * @return property value of roleId
     */
	public Integer getRoleId() {
		return roleId;
	}
	
	/**
	 * Setter method for property <tt>roleId</tt>.
	 * 
	 * @param roleId value to be assigned to property roleId
     */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

    /**
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter method for property <tt>description</tt>.
	 * 
	 * @param description value to be assigned to property description
     */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Getter method for property <tt>lastModifier</tt>.
     *
     * @return property value of lastModifier
     */
	public String getLastModifier() {
		return lastModifier;
	}
	
	/**
	 * Setter method for property <tt>lastModifier</tt>.
	 * 
	 * @param lastModifier value to be assigned to property lastModifier
     */
	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}

    /**
     * Getter method for property <tt>gmtCreate</tt>.
     *
     * @return property value of gmtCreate
     */
	public Date getGmtCreate() {
		return gmtCreate;
	}
	
	/**
	 * Setter method for property <tt>gmtCreate</tt>.
	 * 
	 * @param gmtCreate value to be assigned to property gmtCreate
     */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

    /**
     * Getter method for property <tt>gmtModified</tt>.
     *
     * @return property value of gmtModified
     */
	public Date getGmtModified() {
		return gmtModified;
	}
	
	/**
	 * Setter method for property <tt>gmtModified</tt>.
	 * 
	 * @param gmtModified value to be assigned to property gmtModified
     */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
