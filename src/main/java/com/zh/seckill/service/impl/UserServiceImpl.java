package com.zh.seckill.service.impl;

import com.zh.seckill.pojo.User;
import com.zh.seckill.mapper.UserMapper;
import com.zh.seckill.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
