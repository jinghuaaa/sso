/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.bbd.bdsso.common.service.facade.result.BdssoAccountValidResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.util.ContentType;
import com.bbd.bdsso.common.service.facade.vo.SsoAccountValidVO;

/**
 * 用户账户验证服务接口
 * 
 * @author byron
 * @version $Id: SsoAccountValidFacade.java, v 0.1 Sep 26, 2017 9:44:52 AM byron Exp $
 */
@Path("/api/v1.0/bdsso/account_valid")
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public interface SsoAccountValidFacade {

    /**
     * 根据资源id来查询
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param id            资源id
     * @return
     */
    @POST
    @Path("/queryById")
    public BdssoAccountValidResult queryById(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("id") String id);

    /**
     * 根据关键字模糊查询
     * 
     * @param uid           用户id
     * @param token         用户访问token
     * @param pageSize      每页显示的数目
     * @param pageNum       当前页码
     * @param key           查询关键字
     * @return
     */
    @POST
    @Path("/fuzzyQueryForPage")
    public BdssoForPageResult<SsoAccountValidVO> fuzzyQueryForPage(@HeaderParam("uid") String uid, @HeaderParam("token") String token, @FormParam("pageSize") int pageSize,
                                                                   @FormParam("pageNum") int pageNum, @FormParam("key") String key);
}
