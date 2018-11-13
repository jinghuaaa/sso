/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAuthResourceDAO;
import com.bbd.bdsso.test.BasicTest;

/**
 * SSO权限测试用例
 * 
 * @author byron
 * @version $Id: SsoAuthResourceDAOTest.java, v 0.1 Sep 19, 2017 4:20:55 PM byron Exp $
 */
public class SsoAuthResourceDAOTest extends BasicTest {

    /** SSO手工资源DAO */
    @Autowired
    private ExtraSsoAuthResourceDAO extraSsoAuthResourceDAO;

    @Test
    private void Test() {
        extraSsoAuthResourceDAO.fuzzyQueryForPage("", 15, 0);
    }

}
