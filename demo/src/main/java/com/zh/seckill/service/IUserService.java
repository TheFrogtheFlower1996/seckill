package com.zh.seckill.service;

import com.zh.seckill.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.seckill.vo.LoginVo;
import com.zh.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zh
 * @since 2022-04-12
 */
public interface IUserService extends IService<User> {


   RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

   /**
    * 根据cookie获取用户
    * */
   User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);
}
