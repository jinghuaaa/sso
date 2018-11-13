/*
 * bbdservice.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.bbd.bdsso.common.dal.manual.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoUserDAO;
import com.bbd.commons.lang.util.page.PageList;
import com.bbd.commons.lang.util.page.Paginator;

public class IbatisExtraSsoUserDAO extends SqlMapClientDaoSupport implements ExtraSsoUserDAO {

    @Override
    public PageList fuzzyQueryForPage(String key, int pageSize, int pageNum) throws DataAccessException {
        Map param = new HashMap();

        param.put("key", key);

        Paginator paginator = new Paginator();
        paginator.setItemsPerPage(pageSize);
        paginator.setPage(pageNum);

        paginator.setItems(((Integer) getSqlMapClientTemplate().queryForObject("MS-BDSSO-Extra-SSO-USER-FUZZY-QUERY-FOR-PAGE-COUNT-FOR-PAGING", param)).intValue());

        PageList pageList = new PageList();
        pageList.setPaginator(paginator);

        if (paginator.getBeginIndex() <= paginator.getItems()) {
            param.put("offset", new Integer(paginator.getOffset()));
            param.put("pageSize", new Integer(pageSize));
            pageList.addAll(getSqlMapClientTemplate().queryForList("MS-Extra-SSO-USER-FUZZY-QUERY-FOR-PAGE", param));
        }

        return pageList;
    }

}