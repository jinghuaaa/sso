/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.dal.daointerface.SsoUserDAO;
import com.bbd.bdsso.common.service.facade.SsoUserFacade;
import com.bbd.bdsso.test.BasicTest;

/**
 * SSO用户服务测试用例
 * 
 * @author byron
 * @version $Id: SsoUserFacadeImpl.java, v 0.1 Sep 20, 2017 5:39:43 PM byron Exp $
 */
public class SsoUserFacadeImplTest extends BasicTest {

    /** SSO用户中心DAO */
    @Autowired
    private SsoUserDAO    ssoUserDAO;

    @Autowired
    private SsoUserFacade ssoUserFacade;

    @Test
    private void registTest() {
        ssoUserFacade.regist("byron", "zhangwei@bbdservice.com", "nihaoma", "27E98");
    }

    @Test
    private void loginTest() {
        ssoUserFacade.login("byron", "abc123");
    }

    @Test
    private void logoutTest() {
        ssoUserFacade.logout("999999", "fsdfasdfasdfasd");
    }

    @Test
    private void checkUserNameTest() {
        ssoUserFacade.checkUserName("zhangwei");
    }

    @Test
    private void checkEmailTest() {
        ssoUserFacade.checkEmail("zhangwei@bbdservice.com");
    }

    @Test
    private void sendVerifyCodeTest() {
        ssoUserFacade.sendVerifyCode("zhangwei@bbdservice.com");
    }

    @Test
    private void forgetPswTest() {
        ssoUserFacade.forgetPsw("zhangwei@bbdservice.com");
    }

    @Test
    private void fuzzyQueryForPage() {
        ssoUserFacade.fuzzyQueryForPage("1", "33a90dcf0ef4845e1e57b26033e8f0cf", 0, 1, "2017");
    }

    @Test
    private void resetPswTest() {
        ssoUserFacade.resetPsw("1", "33a90dcf0ef4845e1e57b26033e8f0cf", "nihaoma", "nihao");
    }

    @Test
    private void queryByTicketTest() {
        String ticket = "4kLnVVI22Uvz8aNiHq/mRuFh0EHP/1+T8QjczxmigOwPTTkyV+wXpueSN2yMXZF8";
        System.out.println(ssoUserFacade.queryByTicket(ticket));
    }

    @BeforeClass
    private void beforeProcess() {
        ssoUserDAO.deleteByDesc("byronzoz");
    }

    @AfterClass
    private void postProcess() {
        ssoUserDAO.deleteByDesc("byronzoz");
    }

}
