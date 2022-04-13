package com.zh.seckill.exception;

import com.zh.seckill.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zh
 * @date 2022/4/13 14:33
 * @description:说明 全局异常
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException {

    private RespBeanEnum respBeanEnum;
}
