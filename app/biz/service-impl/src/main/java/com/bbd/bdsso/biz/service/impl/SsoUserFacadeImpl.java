/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.daointerface.SsoAccessDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoRoleDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoUserDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoUserRoleDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoUserDO;
import com.bbd.bdsso.common.dal.dataobject.SsoUserRoleDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoUserDAO;
import com.bbd.bdsso.common.service.facade.SsoUserFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.result.BdssoUserInfoResult;
import com.bbd.bdsso.common.service.facade.result.BdssoUserResult;
import com.bbd.bdsso.common.service.facade.vo.SsoUserVO;
import com.bbd.bdsso.common.util.EncryptUtils;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.SsoStringUtils;
import com.bbd.bdsso.common.util.enums.*;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.model.EmailValidateModel;
import com.bbd.bdsso.core.model.convertor.SsoUserConvertor;
import com.bbd.bdsso.core.service.SsoAccessService;
import com.bbd.bdsso.core.service.SsoEmailService;
import com.bbd.bdsso.core.service.SsoEncryptService;
import com.bbd.bdsso.core.service.SsoUserService;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务接口实现
 * 
 * @author byron
 * @version $Id: SsoUserFacadeImpl.java, v 0.1 Sep 13, 2017 3:38:05 PM byron Exp $
 */
public class SsoUserFacadeImpl implements SsoUserFacade {

    /** 日志 */
    private final Logger       logger = LoggerFactory.getLogger(SsoUserFacadeImpl.class);

    /** 事务模板 */
    @Autowired
    private BdssoTemplate      bdssoTransactionTemplate;

    /** SSO用户管理服务 */
    @Autowired
    private SsoUserService     ssoUserService;

    /** SSO访问Token验证服务 */
    @Autowired
    private SsoAccessService   ssoAccessService;

    /** SSO用户访问DAO */
    @Autowired
    private SsoAccessDAO       ssoAccessDAO;

    /** SSO用户DAO */
    @Autowired
    private SsoUserDAO         ssoUserDAO;

    /** SSO手工用户DAO */
    @Autowired
    private ExtraSsoUserDAO    extraSsoUserDAO;

    /** SSO邮件服务 */
    @Autowired
    private SsoEmailService    ssoEmailService;

    /** SSO加解密服务 */
    @Autowired
    private SsoEncryptService  ssoEncryptService;

    /** 邮箱验证模型 */
    @Autowired
    private EmailValidateModel emailValidateModel;

    /** SSO用户角色DAO */
    @Autowired
    private SsoUserRoleDAO     ssoUserRoleDAO;

    /** SSO角色DAO */
    @Autowired
    private SsoRoleDAO         ssoRoleDAO;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#add(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoUserVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_ADMIN_A)
    public BdssoBaseResult add(String uid, String token, SsoUserVO ssoUserVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoUserVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoUserVO.getEmail()) <= SsoConstant.MAX_EMAIL_LENGH, "邮箱最长为64个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoUserVO.getUserPassword()) <= SsoConstant.MAX_PSW_LENGH, "密码最长为64个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoUserVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {

                // 该接口为手动添加用户，需更新描述
                SsoUserDO ssoUserDO = SsoUserConvertor.convertVo2Do(ssoUserVO);
                ssoUserDO.setDescription(SsoConstant.MANUAL_ADD_USER);
                ssoUserDO.setUserPassword(EncryptUtils.encryptPassword(ssoUserVO.getUserPassword()));
                int id = ssoUserDAO.insert(ssoUserDO);
                logger.info("新增用户, [userId={}, userName={}]", id, ssoUserVO.getUserName());

                // 设置为普通用户角色
                SsoUserRoleDO ssoUserRoleDO = new SsoUserRoleDO();
                ssoUserRoleDO.setDescription(SsoConstant.REGIST);
                ssoUserRoleDO.setLastModifier(ssoUserDO.getUserName());
                ssoUserRoleDO.setRoleId(ssoRoleDAO.queryByRoleName(RoleEnum.BDSSO_COMMON_USER_R.getCode()).getId());
                ssoUserRoleDO.setUserId(ssoUserDAO.queryByName(ssoUserVO.getUserName()).getId());
                ssoUserRoleDAO.insert(ssoUserRoleDO);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#login(java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoUserResult login(String userName, String password) {

        final BdssoUserResult result = new BdssoUserResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(userName, "用户名为空");
                AssertUtils.assertStringNotBlank(password, "密码为空");
            }

            @Override
            public void service() {

                // 首先验证通过用户名登陆
                String token = ssoUserService.loginByUserName(userName, password);

                if (StringUtils.isBlank(token)) {
                    throw new BdssoBaseException(BdssoResultEnum.USER_NOT_EXIST);
                }

                String uid = ssoAccessDAO.queryByToken(token).getUserId().toString();

                if (StringUtils.isBlank(uid)) {
                    throw new BdssoBaseException(BdssoResultEnum.USER_NOT_EXIST);
                }

                // 检查用户是否被禁止访问
                if (ssoUserDAO.query(Integer.parseInt(uid)).getIsEnable() == Integer.parseInt(IsEnableEnum.INVAID.getCode())) {
                    throw new BdssoBaseException(BdssoResultEnum.FORBIDDEN_USER);
                }

                result.setToken(token);
                result.setUid(uid);

                logger.info("用户登陆, [user={}, token={}]", userName, token);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#logout(java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoBaseResult logout(String uid, String token) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertStringNotBlank(uid, SsoConstant.INVALID_TICKET);
                AssertUtils.assertStringNotBlank(token, SsoConstant.INVALID_TICKET);
            }

            @Override
            public void service() {
                ssoAccessDAO.deleteByUserIdAndToken(Integer.parseInt(uid), token);
                logger.info("用户注销登陆，[uid={}]", uid);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#checkUserName(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoBaseResult checkUserName(String userName) {

        final BdssoBaseResult result = new BdssoBaseResult();
        if (bdssoTransactionTemplate == null) {
            logger.error("bdssoTransactionTemplate is null.");
        }

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertStringNotBlank(userName, "用户名为空");
            }

            @Override
            public void service() {
                if (null != ssoUserDAO.queryByName(userName)) {
                    throw new BdssoBaseException(BdssoResultEnum.USERNAME_ALREADY_EXIST);
                }
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#checkEmail(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoBaseResult checkEmail(String email) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertStringNotBlank(email, "邮箱为空");
            }

            @Override
            public void service() {

                // 邮箱后缀不支持
                if (!SsoStringUtils.checkEmail(email, emailValidateModel.getEmailEegularExp())) {
                    throw new BdssoBaseException(BdssoResultEnum.ERROR_EMAIL_SUFFIX);
                }

                // 验证邮箱后缀是否在列表中
                if (null != ssoUserDAO.queryByEmail(email)) {
                    throw new BdssoBaseException(BdssoResultEnum.EMAIL_ALREADY_EXIST);
                }
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#sendVerifyCode(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoBaseResult sendVerifyCode(String email) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertStringNotBlank(email, "邮箱为空");
            }

            @Override
            public void service() {
                String verifyCode = ssoUserService.sendVerifyCode(email);
                logger.info("发送验证码完成. [verifyCode={}, email={}]", verifyCode, email);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#regist(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoUserResult regist(String userName, String email, String password, String verifyCode) {
        final BdssoUserResult result = new BdssoUserResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(userName, "用户名为空");
                AssertUtils.assertStringNotBlank(email, "邮箱为空");
                AssertUtils.assertStringNotBlank(password, "密码为空");
                AssertUtils.assertStringNotBlank(verifyCode, "验证码为空");

                // 检测邮箱和用户名是否被占用
                AssertUtils.assertTrue(ssoUserDAO.queryByEmail(email) == null, "邮箱已存在");
                AssertUtils.assertTrue(ssoUserDAO.queryByName(userName) == null, "用户名已存在");
            }

            @Override
            public void service() {
                logger.info("用户注册参数, [user={}, email={}]", userName, email);

                // 完成注册
                String userId = ssoUserService.regist(userName, email, password, verifyCode);

                // 生成token
                String token = ssoAccessService.generateToken(userId, userName, email, VerifyCodeTypeEnum.REGIST);

                // 设置结果
                result.setUid(userId);
                result.setToken(token);
                logger.info("用户注册完成, [user={}, email={}, userId={}, token={}]", new Object[] { userName, email, userId, token });
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#resetPwd(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoBaseResult forgetPsw(String email) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(email, "邮箱为空");

                // 检测邮箱和用户名是否被占用
                AssertUtils.assertTrue(ssoUserDAO.queryByEmail(email) != null, "邮箱不存在");
            }

            @Override
            public void service() {
                logger.info("用户重置密码，[email={}]", email);

                // 首先检查邮箱是否存在
                if (null == ssoUserDAO.queryByEmail(email)) {
                    throw new BdssoBaseException(BdssoResultEnum.EMAIL_NOT_EXIST);
                }

                // 清除token
                ssoAccessService.deleteToken(ssoUserDAO.queryByEmail(email).getId().toString());

                // 发送新密码
                ssoEmailService.sendResetPwdEmail(email);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoForPageResult<SsoUserVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final BdssoForPageResult<SsoUserVO> result = new BdssoForPageResult<>();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertPositiveIntegerNumber(pageSize, "每页条数应大于0且为整数");
                AssertUtils.assertIntegerNumberGeZero(pageNum, "页码应大于等于0");
            }

            @Override
            public void service() {

                PageList pageList = extraSsoUserDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<SsoUserDO> listCopy = (ArrayList<SsoUserDO>) pageList.clone();

                // 设置结果
                result.setResultList(SsoUserConvertor.convertDos2Vos(listCopy));
                result.setPageNum(pageList.getPaginator().getPage());
                result.setPageSize(pageList.getPaginator().getItemsPerPage());
                result.setTotal(pageList.getPaginator().getItems());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#resetPsw(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoBaseResult resetPsw(String uid, String token, String oldPsw, String newPsw) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertStringNotBlank(oldPsw, "旧密码为空");
                AssertUtils.assertStringNotBlank(newPsw, "新密码为空");
            }

            @Override
            public void service() {
                SsoUserDO ssoUserDO = ssoUserDAO.query(Integer.parseInt(uid));
                AssertUtils.assertNotNull(ssoUserDO, "该用户不存在");

                if (!StringUtils.equals(ssoUserDO.getUserPassword(), EncryptUtils.encryptPassword(oldPsw))) {
                    throw new BdssoBaseException(BdssoResultEnum.WRONG_OLD_PASSWORD);
                }
                ssoUserDAO.updateByPsw(EncryptUtils.encryptPassword(newPsw), Integer.parseInt(uid));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#delete(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult delete(String uid, String token, String id) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertStringNotBlank(id, SsoConstant.INVALID_TICKET);
            }

            @Override
            public void service() {
                // 数据库对应的BDSSO系统本身的管理员名称，是“admin” 2018-07-05 Add by tianyuliang
                SsoUserDO ssoUserDO = ssoUserDAO.query(Integer.parseInt(id));
                if (ssoUserDO != null && SsoStringUtils.matchKey(ssoUserDO.getUserName(), SsoConstant.ADMIN)) {
                    throw new BdssoBaseException(BdssoResultEnum.NOT_ALLOWED);
                }

                ssoUserDAO.delete(Integer.parseInt(id));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#update(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoUserVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult update(String uid, String token, SsoUserVO ssoUserVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertNotNull(ssoUserVO, "实体为空");
            }

            @Override
            public void service() {
                ssoUserDAO.update(ssoUserVO.getIsEnable(), ssoUserVO.getDescription(), ssoUserVO.getLastModifier(), Integer.parseInt(ssoUserVO.getId()));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserFacade#queryByTicket(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoUserInfoResult queryByTicket(String ticket) {
        final BdssoUserInfoResult result = new BdssoUserInfoResult();

        bdssoTransactionTemplate.executeWithoutTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertNotNull(ticket, "ticket为空");
            }

            @Override
            public void service() {
                String uid = basicCheckForTicketAndReturnUid(ticket);
                fillUserInfo(result, ssoUserDAO.query(Integer.valueOf(uid)));
            }
        }, result);

        return result;
    }

    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoBaseResult logoutByTicket(String ticket) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertStringNotBlank(ticket, "用户凭据为空，请检查请求头中的ticket");
            }

            @Override
            public void service() {
                //对ticket解密
                HashMap<String, String> decryptRet = ssoEncryptService.decrypt(ticket);
                String uid = decryptRet.get(SsoConstant.UID);
                String token = decryptRet.get(SsoConstant.TOKEN);

                //如果uid或ticket有空，则说明ticket不合法，登出失败
                if (StringUtils.isBlank(uid) || StringUtils.isBlank(token)) {
                    throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
                }

                ssoAccessDAO.deleteByUserIdAndToken(Integer.parseInt(uid), token);

                logger.info("用户注销登陆，[ticket={}]", ticket);
            }

        }, result);

        return result;
    }

    private void fillUserInfo(BdssoUserInfoResult result, SsoUserDO userDO) {
        if (null == userDO) {
            throw new BdssoBaseException(BdssoResultEnum.USER_NOT_EXIST);
        }
        result.setId(String.valueOf(userDO.getId()));
        result.setUserName(userDO.getUserName());
        result.setEmail(userDO.getEmail());

    }

    private String basicCheckForTicketAndReturnUid(String ticket) {
        Map<String, String> userInfo = ssoEncryptService.decrypt(ticket);
        String uid = userInfo.get(SsoConstant.UID);
        String token = userInfo.get(SsoConstant.TOKEN);
        if (StringUtils.isBlank(uid)) {
            throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
        }
        if (StringUtils.isBlank(token)) {
            throw new BdssoBaseException(BdssoResultEnum.INVAID_TOKEN);
        }
        return uid;
    }
}
