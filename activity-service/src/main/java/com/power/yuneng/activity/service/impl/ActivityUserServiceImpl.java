/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:14 $ 
 */




package com.power.yuneng.activity.service.impl;


import com.power.core.service.impl.AbstractPageService;
import com.power.yuneng.activity.dao.IActivityUserDAO;
import com.power.yuneng.activity.entity.ActivityUser;
import com.power.yuneng.activity.service.IActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ActivityUserServiceImpl")
public class ActivityUserServiceImpl extends AbstractPageService<Long, IActivityUserDAO, ActivityUser> implements IActivityUserService {
    @Autowired
    private IActivityUserDAO activityUserDAO;

    @Override
    public IActivityUserDAO getDao() {
        return activityUserDAO;
    }

//    @Override
//    public void insert(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void update(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void updateNull(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    @Override
//    public void deleteByProperty(String property, Object value) {
//
//    }
//
//    @Override
//    public BaseDomain fetch(Long id) {
//        return null;
//    }
//
//    @Override
//    public BaseDomain findOne(@Param("property") String property, @Param("value") Object value) {
//        return null;
//    }
//
//    @Override
//    public List findList(String property, Object value) {
//        return null;
//    }
//
//    @Override
//    public void deleteByCondition(Map condition) {
//
//    }
//
//    @Override
//    public void updateMap(@Param("map") Map entityMap) {
//
//    }
//
//    @Override
//    public List<ActivityUser> findAll() {
//        return activityUserDAO.findAll();
//    }
//
//    @Override
//    public List like(String property, Object value) {
//        return null;
//    }
//
//    @Override
//    public Long selectMaxId() {
//        return null;
//    }
//
//    @Override
//    public BaseDomain updateOrSave(BaseDomain baseDomain, Long id) {
//        return null;
//    }
//
//    @Override
//    public BaseDomain selectOne(String mapperId, Object obj) {
//        return null;
//    }
//
//    @Override
//    public List selectList(String mapperId, Object obj) {
//        return null;
//    }
//
//    @Override
//    public Class getEntityClass() {
//        return null;
//    }
//
//    @Override
//    public int count(Map condition) {
//        return 0;
//    }
//
//    @Override
//    public BaseDomain queryOne(Map condition) {
//        return null;
//    }
//
//    @Override
//    public List queryList(@Param("condition") Map condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy) {
//        return null;
//    }
//
//    @Override
//    public List queryPage(@Param("condition") Map condition, @Param("offset") int offset, @Param("rows") int rows) {
//        return null;
//    }
//
//    @Override
//    protected ActivityUserDAO getDao() {
//        return activityUserDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
