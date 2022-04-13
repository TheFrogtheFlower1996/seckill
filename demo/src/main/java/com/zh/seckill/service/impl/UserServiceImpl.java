package com.zh.seckill.service.impl;

import com.zh.seckill.exception.GlobalException;
import com.zh.seckill.exception.GlobalExceptionHandler;
import com.zh.seckill.pojo.User;
import com.zh.seckill.mapper.UserMapper;
import com.zh.seckill.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.seckill.utils.MD5Utils;
import com.zh.seckill.utils.ValidatorUtil;
import com.zh.seckill.vo.LoginVo;
import com.zh.seckill.vo.RespBean;
import com.zh.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

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

    /**
     * 登录
     * */
    @Override
    public RespBean doLogin(LoginVo loginVo) {

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

        return RespBean.success();
    }
}
