/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service.impl;

import com.bbd.bdsso.common.dal.daointerface.SsoAccessDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoAccountValidDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoAuthDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAccessDO;
import com.bbd.bdsso.common.dal.dataobject.SsoAuthDO;
import com.bbd.bdsso.common.util.DateUtils;
import com.bbd.bdsso.common.util.EncryptUtils;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.enums.IsVerifiedEnum;
import com.bbd.bdsso.common.util.enums.VerifyCodeTypeEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.service.SsoAccessService;
import com.bbd.commons.lang.util.AssertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * SSO访问Token验证服务实现
 * 
 * @author byron
 * @version $Id: SsoAccessServiceImpl.java, v 0.1 Sep 25, 2017 1:25:46 PM byron Exp $
 */
public class SsoAccessServiceImpl implements SsoAccessService {

    @Autowired
    private SsoAccessDAO       ssoAccessDAO;

    @Autowired
    private SsoAuthDAO         ssoAuthDAO;

    @Autowired
    private SsoAccountValidDAO ssoAccountValidDAO;

    /**
     * 创建新的token
     * @see com.bbd.bdsso.core.service.SsoAccessService#generateToken(java.lang.String, java.lang.String, java.lang.String, com.bbd.bdsso.common.util.enums.VerifyCodeTypeEnum)
     */
    @Override
    public String generateToken(String userId, String userName, String email, VerifyCodeTypeEnum type) {

        // 清除掉之前的记录
        ssoAccessDAO.deleteByUserId(Integer.parseInt(userId));

        // 新的token
        String token = EncryptUtils.generateLoginToken(userName);
        SsoAccessDO ssoAccessDO = new SsoAccessDO();
        ssoAccessDO.setUserId(Integer.parseInt(userId));
        ssoAccessDO.setToken(token);
        ssoAccessDO.setValidDate(DateUtils.plusDuration(SsoConstant.TOKEN_VALID_DURATION));
        ssoAccessDO.setDescription(SsoConstant.INIT);
        ssoAccessDAO.insert(ssoAccessDO);

        // 将验证码设置为失效
        ssoAccountValidDAO.updateByEmailAndType(Integer.parseInt(IsVerifiedEnum.YES.getCode()), new Date(), email, Integer.parseInt(type.getCode()));

        return token;
    }

    /**
     * 删除当前用户userId关联的所有token
     * @see com.bbd.bdsso.core.service.SsoAccessService#deleteToken(java.lang.String)
     */
    @Override
    public boolean deleteToken(String userId) {
        return ssoAccessDAO.deleteByUserId(Integer.parseInt(userId)) > 0 ? true : false;
    }

    /**
     * 使用“当前时间”更新token有效期
     * @see com.bbd.bdsso.core.service.SsoAccessService#updateValidDateForToken(java.lang.String)
     */
    public boolean updateValidDateForToken(String userId) {
        Date validDate = DateUtils.plusDuration(SsoConstant.TOKEN_VALID_DURATION);
        return ssoAccessDAO.updateValidDate(validDate, new Date(), Integer.parseInt(userId)) > 0 ? true : false;
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoAccessService#checkValid(java.lang.String, java.lang.String)
     */
    @Override
    public boolean checkValid(String userId, String token) {
        // 非空校验
        AssertUtils.assertStringNotBlank(userId, SsoConstant.INVALID_TICKET);
        AssertUtils.assertStringNotBlank(token, SsoConstant.INVALID_TICKET);
        SsoAccessDO ssoAccessDO = ssoAccessDAO.checkValid(userId, token);
        if (null == ssoAccessDO) {
            return false;
        } else {
            updateValidDateForToken(userId);
            return true;
        }
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoAccessService#getAuthCodeList(java.lang.String, java.lang.Boolean)
     */
    @Override
    public HashMap<String, Boolean> getAuthCodeList(String userId, String appName) {
        // 结果
        HashMap<String, Boolean> result = new HashMap<String, Boolean>();

        List<SsoAuthDO> list = ssoAuthDAO.queryByAppIdAndUserName(userId, appName);

        if (list.size() == 0) {
            throw new BdssoBaseException(BdssoResultEnum.AUTH_CODE_ERROR);
        }

        for (SsoAuthDO ssoAuthDO : list) {
            result.put(ssoAuthDO.getAuthCode(), true);
        }

        return result;
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoAccessService#getAuthCodeList(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public HashMap<String, Boolean> getAuthCodeList(String userId, String appName, String authCode) {
        // 结果
        HashMap<String, Boolean> result = new HashMap<String, Boolean>();

        List<SsoAuthDO> list = ssoAuthDAO.queryByAppIdAndUserName(userId, appName);

        if (list.size() == 0) {
            throw new BdssoBaseException(BdssoResultEnum.AUTH_CODE_ERROR);
        }

        for (SsoAuthDO ssoAuthDO : list) {
            if (StringUtils.equals(ssoAuthDO.getAuthCode(), authCode)) {
                result.put(ssoAuthDO.getAuthCode(), true);
            }
        }

        return result;
    }
}
