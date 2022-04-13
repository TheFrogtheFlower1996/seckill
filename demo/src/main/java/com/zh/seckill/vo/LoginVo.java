package com.zh.seckill.vo;

import com.zh.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author zh
 * @date 2022/4/12 14:17
 * @description:说明 登录参数
 */
@Data
public class LoginVo {

    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    @Length(min = 32)
    private String password;

}
