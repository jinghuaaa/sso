/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bbd.bdsso.common.service.facade.vo.SsoAuthVO;

/**
 * 用户权限服务结果
 * 
 * @author byron
 * @version $Id: BdssoAuthResult.java, v 0.1 Sep 26, 2017 10:15:35 AM byron Exp $
 */
public class BdssoAuthResult extends BdssoBaseResult {

    /** 序列化id */
    private static final long serialVersionUID = 7257891780545232683L;

    /** 结果列表 */
    private List<SsoAuthVO>   resultList       = new ArrayList<SsoAuthVO>();

    /**
     * Getter method for property <tt>resultList</tt>.
     * 
     * @return property value of resultList
     */
    public List<SsoAuthVO> getResultList() {
        return resultList;
    }

    /**
     * Setter method for property <tt>resultList</tt>.
     * 
     * @param resultList value to be assigned to property resultList
     */
    public void setResultList(List<SsoAuthVO> resultList) {
        this.resultList = resultList;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}