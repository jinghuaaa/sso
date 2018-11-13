/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoUserDO;
import com.bbd.bdsso.common.service.facade.vo.SsoUserVO;

/**
 * SSO用户访问模型转换器
 * 
 * @author byron
 * @version $Id: SsoUserConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoUserConvertor {

    /**
     * Do2Vo
     * 
     * @param ssoUserDO
     * @return
     */
    public static SsoUserVO convertDo2Vo(SsoUserDO ssoUserDO) {
        if (null == ssoUserDO) {
            return null;
        }
        SsoUserVO ssoUserVO = new SsoUserVO();

        ssoUserVO.setId(ssoUserDO.getId().toString());
        ssoUserVO.setUserName(ssoUserDO.getUserName());
        ssoUserVO.setEmail(ssoUserDO.getEmail());

        // fix: set empty password field to response data. 2018-07-06 update by tianyuliang
        // ssoUserVO.setUserPassword(ssoUserDO.getUserPassword());
        ssoUserVO.setUserPassword(null);

        ssoUserVO.setIsEnable(ssoUserDO.getIsEnable());
        ssoUserVO.setDescription(ssoUserDO.getDescription());
        ssoUserVO.setLastModifier(ssoUserDO.getLastModifier());
        ssoUserVO.setGmtCreate(ssoUserDO.getGmtCreate());
        ssoUserVO.setGmtModified(ssoUserDO.getGmtModified());

        return ssoUserVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoUserVO
     * @return
     */
    public static SsoUserDO convertVo2Do(SsoUserVO ssoUserVO) {
        if (null == ssoUserVO) {
            return null;
        }
        SsoUserDO ssoUserDO = new SsoUserDO();
        ssoUserDO.setUserName(ssoUserVO.getUserName());
        ssoUserDO.setUserPassword(ssoUserVO.getUserPassword());
        ssoUserDO.setEmail(ssoUserVO.getEmail());
        ssoUserDO.setIsEnable(ssoUserVO.getIsEnable());
        ssoUserDO.setDescription(ssoUserVO.getDescription());
        ssoUserDO.setLastModifier(ssoUserVO.getLastModifier());
        ssoUserDO.setGmtCreate(ssoUserVO.getGmtCreate());
        ssoUserDO.setGmtModified(ssoUserVO.getGmtModified());
        ssoUserDO.setId(StringUtils.isBlank(ssoUserVO.getId()) ? 0 : Integer.parseInt(ssoUserVO.getId()));

        return ssoUserDO;
    }

    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoUserVO> convertDos2Vos(ArrayList<SsoUserDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoUserVO> result = new ArrayList<SsoUserVO>();
        for (SsoUserDO ssoUserDO : list) {
            result.add(convertDo2Vo(ssoUserDO));
        }

        return result;
    }

}
