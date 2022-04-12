package com.zh.generator.service.impl;

import com.zh.generator.pojo.User;
import com.zh.generator.mapper.UserMapper;
import com.zh.generator.service.IUserService;
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
