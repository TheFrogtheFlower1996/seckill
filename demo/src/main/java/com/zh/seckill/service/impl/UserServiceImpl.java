package com.zh.seckill.service.impl;

import com.zh.seckill.config.RedisConfig;
import com.zh.seckill.exception.GlobalException;
import com.zh.seckill.exception.GlobalExceptionHandler;
import com.zh.seckill.pojo.User;
import com.zh.seckill.mapper.UserMapper;
import com.zh.seckill.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.seckill.utils.CookieUtil;
import com.zh.seckill.utils.MD5Utils;
import com.zh.seckill.utils.UUIDUtil;
import com.zh.seckill.utils.ValidatorUtil;
import com.zh.seckill.vo.LoginVo;
import com.zh.seckill.vo.RespBean;
import com.zh.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zh
 * @since 2022-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 登录
     * */
    @Override
    public RespBean doLogin(LoginVo loginVo,HttpServletRequest request, HttpServletResponse response) {

        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //参数校验

//        if (StringUtils.isEmpty(mobile ) || StringUtils.isEmpty(password)){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }

//        if (!ValidatorUtil.isMobile(mobile)){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }

        //根据手机号查询用户
        User user = userMapper.selectById(mobile);
        if (user == null){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //判断密码是否正确
        if (!MD5Utils.fromPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        //生成cookie
        String ticket = UUIDUtil.uuid();
        //request.getSession().setAttribute(ticket,user);

        //将用户信息存入redis中
        redisTemplate.opsForValue().set("user:"+ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);

        return RespBean.success(ticket);
    }

    /**
     * 根据cookie获取用户
     * */
    @Override
    public User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response) {
        if (ObjectUtils.isEmpty(userTicket)) {
            return null;
        }

        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);// .cast 强转快捷键
        if (user != null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
}
