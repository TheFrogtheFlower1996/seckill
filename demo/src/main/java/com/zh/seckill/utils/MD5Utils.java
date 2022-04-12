package seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author zh
 * @date 2022/4/11 15:55
 * @description:说明 MD5工具类
 */
public class MD5Utils {

    //固定盐
    private static final String salt ="1a2b3c4d";

    /**
     * MD5加密方法
     * */
    public static String md5(String src){
        return DigestUtils.md2Hex(src);
    }

    /**
     * 第一次加密
     * */
    public static String inputPassToFromPass(String inputPass){
        String str = salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    /**
     * 第二次加密
     * */
    public static String fromPassToDBPass(String formPass,String salt){
        String str = salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    /**
     * 调用的方法
     * */
    public static String inputPassToDBPass(String inputPass,String salt){
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass, salt);
        return dbPass;
    }

    public static void main(String[] args) {

        System.out.println(inputPassToFromPass("123456"));//82a693d3173db016d77c59a41d302b20
        System.out.println(fromPassToDBPass("82a693d3173db016d77c59a41d302b20","1a2b3c4d"));//a0de3e469073b293e3187df16fe5a6a2
        System.out.println(inputPassToDBPass("123456","1a2b3c4d"));//a0de3e469073b293e3187df16fe5a6a2

    }



}
