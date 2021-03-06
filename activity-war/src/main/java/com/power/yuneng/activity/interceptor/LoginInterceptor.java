package com.power.yuneng.activity.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.power.core.cache.RedisRepository;
import com.power.core.exception.BizException;
import com.power.yuneng.activity.common.UserContext;
import com.power.yuneng.activity.entity.ERRORCODE;
import com.power.yuneng.activity.entity.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跨域拦截器
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisRepository<String,String> redis;

    public LoginInterceptor()
    {

    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)){
            throw new BizException(ERRORCODE.TOKEN_CANNOT_NULL.getCode(), ERRORCODE.TOKEN_CANNOT_NULL.getMessage());
        }
        if (!redis.exists(token)){
            throw new BizException(ERRORCODE.TOKEN_INVALID_OR_NOTHINGNESS.getCode(),ERRORCODE.TOKEN_INVALID_OR_NOTHINGNESS.getMessage());
        }
        //当token存在一定有用户信息存在
            //抓取用户上下文对象
        UserInfoDTO userInfoDTO = JSON.parseObject(redis.get(token), UserInfoDTO.class);
        UserContext.setCurrentUser(userInfoDTO);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

}
