/*
 * Copyright (c) 2013-2014, starteasy Inc. All Rights Reserved.
 * 
 * Project Name: codegen
 * $Id:  2017-07-28 15:06:12 $ 
 */
package com.power.yuneng.activity.web.common;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import com.power.core.restful.ITypeReference;

import java.util.Map;

/**
 * 支持泛型，这里把所有的 request的泛型参数在这里做记录    （因为spring requestbody处理会丢掉泛型信息）
 * <p/>
 * 创建时间: 14/11/30 下午7:53<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class TypeReferenceMaps implements ITypeReference {

    private Map<String, TypeReference> typeReferenceMaps = Maps.newHashMap();
    public void init(){
        //typeReferenceMaps.put("/admin/mubs/role/test", new TypeReference<RequestT<BaseDomain>>(){});
        //typeReferenceMaps.put("/user/login", RawTypeReference.stringTypeReference);

        //TODO add here
    }

    @Override
    public TypeReference getTypeReference(String url){
        return typeReferenceMaps.get(url);
    }
}