package com.zh.seckill.config;

import com.zh.seckill.pojo.User;
import com.zh.seckill.service.IUserService;
import com.zh.seckill.utils.CookieUtil;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zh
 * @date 2022/4/21 10:58
 * @description:说明 拦截器 自定义用户参数 user
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    private IUserService iUserService;

    /**
     * 判断为 true 走 resolveArgument
     * */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> aClass = parameter.getParameterType();
        return aClass == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
            String ticket = CookieUtil.getCookieValue(request, "userTicket");
        if (ObjectUtils.isEmpty(ticket)){
            return null;
        }
        User user = iUserService.getUserByCookie(ticket, request, response);
        return user;
    }
}
