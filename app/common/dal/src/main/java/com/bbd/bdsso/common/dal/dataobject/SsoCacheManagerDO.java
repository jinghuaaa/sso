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
 * A data object class directly models database table <tt>sso_cache_manager</tt>.
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
public class SsoCacheManagerDO implements Serializable {

	/** serialId */
    private static final long serialVersionUID = 741231858441822688L;

    //========== properties ==========

	/**
	 * This property corresponds to db column <tt>id</tt>.
	 */
	private Integer id;

	/**
	 * This property corresponds to db column <tt>cache_name</tt>.
	 */
	private String cacheName;

	/**
	 * This property corresponds to db column <tt>server_host</tt>.
	 */
	private String serverHost;

	/**
	 * This property corresponds to db column <tt>server_ip</tt>.
	 */
	private String serverIp;

	/**
	 * This property corresponds to db column <tt>status</tt>.
	 */
	private String status;

	/**
	 * This property corresponds to db column <tt>description</tt>.
	 */
	private String description;

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
     * Getter method for property <tt>cacheName</tt>.
     *
     * @return property value of cacheName
     */
	public String getCacheName() {
		return cacheName;
	}
	
	/**
	 * Setter method for property <tt>cacheName</tt>.
	 * 
	 * @param cacheName value to be assigned to property cacheName
     */
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

    /**
     * Getter method for property <tt>serverHost</tt>.
     *
     * @return property value of serverHost
     */
	public String getServerHost() {
		return serverHost;
	}
	
	/**
	 * Setter method for property <tt>serverHost</tt>.
	 * 
	 * @param serverHost value to be assigned to property serverHost
     */
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

    /**
     * Getter method for property <tt>serverIp</tt>.
     *
     * @return property value of serverIp
     */
	public String getServerIp() {
		return serverIp;
	}
	
	/**
	 * Setter method for property <tt>serverIp</tt>.
	 * 
	 * @param serverIp value to be assigned to property serverIp
     */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

    /**
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Setter method for property <tt>status</tt>.
	 * 
	 * @param status value to be assigned to property status
     */
	public void setStatus(String status) {
		this.status = status;
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
