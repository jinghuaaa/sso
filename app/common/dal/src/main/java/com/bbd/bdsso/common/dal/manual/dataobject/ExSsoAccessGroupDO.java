/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.manual.dataobject;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * A data object class directly models database table <tt>sso_auth</tt>.
 *
 * This file is generated by <tt>bdsso-bbdalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>bdsso</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/bbdalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/sso_auth.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>bdsso-bbdalgen</tt> 
 * to generate this file.
 *
 * @author Byron Zhang
 * @version $Id: description-java.vm,v 1.1 2016/05/01 07:34:20 byron Exp $
 */
public class ExSsoAccessGroupDO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = -2038940005588868045L;

    /** 用户名 */
    private String            userName;

    /** 应用名 */
    private String            appName;

    /**
     * Getter method for property <tt>userName</tt>.
     * 
     * @return property value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for property <tt>userName</tt>.
     * 
     * @param userName value to be assigned to property userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method for property <tt>appName</tt>.
     * 
     * @return property value of appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Setter method for property <tt>appName</tt>.
     * 
     * @param appName value to be assigned to property appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}