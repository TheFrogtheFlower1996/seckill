package com.example.demo;

import com.zh.seckill.utils.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author zh
 * @date 2022/7/13 9:46
 * @description:说明
 */
public class TestDemo {

    @Test
    public void test01(){
        String uuid = UUIDUtil.uuid();
//        System.out.println(uuid);

        UUID uuid1 = UUID.randomUUID();
        String s = uuid1.toString();
        String s1 = s.replace("-", "");
        System.out.println(uuid1);
        System.out.println(s);
        System.out.println(s1);
    }
}
