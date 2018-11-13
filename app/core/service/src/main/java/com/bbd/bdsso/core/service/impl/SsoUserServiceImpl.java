/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.common.dal.daointerface.SsoAccessDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoAccountValidDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoRoleDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoUserDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoUserRoleDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAccessDO;
import com.bbd.bdsso.common.dal.dataobject.SsoAccountValidDO;
import com.bbd.bdsso.common.dal.dataobject.SsoUserDO;
import com.bbd.bdsso.common.dal.dataobject.SsoUserRoleDO;
import com.bbd.bdsso.common.util.DateUtils;
import com.bbd.bdsso.common.util.EncryptUtils;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.enums.IsEnableEnum;
import com.bbd.bdsso.common.util.enums.RoleEnum;
import com.bbd.bdsso.common.util.enums.VerifyCodeTypeEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.service.SsoEmailService;
import com.bbd.bdsso.core.service.SsoUserService;
import com.bbd.commons.lang.util.AssertUtils;

/**
 * SSO用户服务实现
 * 
 * @author byron
 * @version $Id: SsoUserServiceImpl.java, v 0.1 Sep 21, 2017 3:29:15 PM byron Exp $
 */
public class SsoUserServiceImpl implements SsoUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SsoUserServiceImpl.class);

    /** SSO用户中心DAO */
    @Autowired
    private SsoUserDAO          ssoUserDAO;

    /** SSO权限DAO */
    @Autowired
    private SsoAccessDAO        ssoAccessDAO;

    /** SSO账户验证DAO */
    @Autowired
    private SsoAccountValidDAO  ssoAccountValidDAO;

    /** 发送邮件服务 */
    @Autowired
    private SsoEmailService     ssoEmailService;

    /**SSO用户角色DAO */
    @Autowired
    private SsoUserRoleDAO      ssoUserRoleDAO;

    /** SSO角色DAO */
    @Autowired
    private SsoRoleDAO          ssoRoleDAO;

    /** 
     * @see com.bbd.bdsso.core.service.SsoUserService#loginByUserName(java.lang.String, java.lang.String)
     */
    @Override
    public String loginByUserName(String userName, String userPassword) {

        // 密码解密
        String deEncryptPwd = EncryptUtils.encryptPassword(userPassword);

        SsoUserDO ssoUserDO = ssoUserDAO.loginByUserName(userName, deEncryptPwd);
        // 用户不存在
        if (null == ssoUserDO) {
            throw new BdssoBaseException(BdssoResultEnum.USER_NOT_EXIST);
        }

        // 用户存在，更新token
        LOGGER.info("updateToken by loginByUserName().  userId={}, userName={}", ssoUserDO.getId(), ssoUserDO.getUserName());
        return this.updateToken(ssoUserDO.getId(), ssoUserDO.getUserName());
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoUserService#loginByEmail(java.lang.String, java.lang.String)
     */
    @Override
    public String loginByEmail(String email, String userPassword) {

        // 密码解密
        String deEncryptPwd = EncryptUtils.encryptPassword(userPassword);

        SsoUserDO ssoUserDO = ssoUserDAO.loginByEmail(email, deEncryptPwd);

        // 用户不存在
        if (null == ssoUserDO) {
            throw new BdssoBaseException(BdssoResultEnum.USER_NOT_EXIST);
        }

        LOGGER.info("updateToken by loginByEmail().  userId={}, userName={}", ssoUserDO.getId(), ssoUserDO.getUserName());
        return this.updateToken(ssoUserDO.getId(), ssoUserDO.getUserName());
    }

    /**
     * 更新token
     * 
     * @param userId
     * @param userName
     */
    private String updateToken(Integer userId, String userName) {

        // 生成新的登录Token
        String newToken = EncryptUtils.generateLoginToken(userName);

        // 清除token
        SsoAccessDO oldDO = ssoAccessDAO.queryByUserId(userId);
        ssoAccessDAO.deleteByUserId(userId);
        if (oldDO != null) {
            LOGGER.warn("delete old ssoAccessDO. id={}, userId={}, userName={}, token={}",
                    new Object[] { oldDO.getId(), userId, userName, oldDO.getToken() });
        }

        // 构建实体
        SsoAccessDO newDO = new SsoAccessDO();
        newDO.setDescription(SsoConstant.INIT);
        newDO.setToken(newToken);
        newDO.setUserId(userId);
        newDO.setValidDate(DateUtils.plusDuration(SsoConstant.TOKEN_VALID_DURATION));
        newDO.setGmtCreate(new Date());
        newDO.setGmtModified(new Date());
        ssoAccessDAO.insert(newDO);
        LOGGER.warn("insert new ssoAccessDO. id={}, userId={}, userName={}, token={}", new Object[] { newDO.getId(), userId, userName, newToken });
        return newToken;
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoUserService#sendVerifyCode(java.lang.String)
     */
    @Override
    public String sendVerifyCode(String email) {
        return ssoEmailService.sendVerifyCodeEmail(email);
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoUserService#regist(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String regist(String userName, String email, String password, String verifyCode) {

        // 校验验证码是否合法
        AssertUtils.assertNotNull(ssoAccountValidDAO.queryByEmailAndVerifyCode(email, verifyCode, Integer.parseInt(VerifyCodeTypeEnum.REGIST.getCode())), SsoConstant.ERROR_VERIFY_CODE);
        AssertUtils.assertTrue(checkVerifyCode(email), SsoConstant.INVALID_VERIFY_CODE);

        // 构建实体
        SsoUserDO ssoUserDO = new SsoUserDO();
        ssoUserDO.setDescription(SsoConstant.REGIST);
        ssoUserDO.setEmail(email);
        ssoUserDO.setIsEnable(Integer.parseInt(IsEnableEnum.VALID.getCode()));
        ssoUserDO.setLastModifier(userName);
        ssoUserDO.setUserName(userName);
        ssoUserDO.setUserPassword(EncryptUtils.encryptPassword(password));
        ssoUserDAO.insert(ssoUserDO);

        // 获取用户id
        String userId = ssoUserDAO.queryByEmail(email).getId().toString();

        // 设置为普通用户角色
        SsoUserRoleDO ssoUserRoleDO = new SsoUserRoleDO();
        ssoUserRoleDO.setDescription(SsoConstant.REGIST);
        ssoUserRoleDO.setLastModifier(ssoUserDO.getUserName());
        ssoUserRoleDO.setRoleId(ssoRoleDAO.queryByRoleName(RoleEnum.BDSSO_COMMON_USER_R.getCode()).getId());
        ssoUserRoleDO.setUserId(Integer.parseInt(userId));
        ssoUserRoleDAO.insert(ssoUserRoleDO);

        // 返回用户id
        return userId;
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoUserService#resetPsw(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String resetPsw(String email, String password, String verifyCode) {
        // 首先校验验证码是否合法
        AssertUtils.assertNotNull(ssoAccountValidDAO.queryByEmailAndVerifyCode(email, verifyCode, Integer.parseInt(VerifyCodeTypeEnum.FIND_PASSOWRD.getCode())), SsoConstant.ERROR_VERIFY_CODE);
        AssertUtils.assertTrue(checkVerifyCode(email), SsoConstant.INVALID_VERIFY_CODE);

        // 更新密码
        ssoUserDAO.updateByEmail(EncryptUtils.encryptPassword(password), new Date(), email);

        // 返回 用户ID
        return ssoUserDAO.queryByEmail(email).getId().toString();
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoUserService#checkVerifyCode(java.lang.String)
     */
    @Override
    public boolean checkVerifyCode(String email) {
        SsoAccountValidDO ssoAccountValidDO = ssoAccountValidDAO.queryByEmail(email);
        if (ssoAccountValidDO == null || ssoAccountValidDO.getValidDate() == null) {
            return false;
        }
        return new Date().before(ssoAccountValidDO.getValidDate());
    }

}
