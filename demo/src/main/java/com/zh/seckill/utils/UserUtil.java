package com.zh.seckill.utils;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.seckill.pojo.User;
import com.zh.seckill.vo.RespBean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 生成用户工具类
 *
 * @author: LC
 * @date 2022/3/4 3:29 下午
 * @ClassName: UserUtil
 */
public class UserUtil {

    private static void createUser(int count) throws Exception {
        List<User> userList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(17800000000L+i);
            user.setNickname("user"+i);
            user.setSalt("1a2b3c4d");
            user.setLoginCount(1);
            user.setRegisterDate(new Date());
            user.setPassword(MD5Utils.inputPassToDBPass("123456",user.getSalt()));
            userList.add(user);
        }
        System.out.println("创建用户完成。。。。");

        //插入数据
        Connection connection = getConn();
        String sql = "insert into t_user(login_count,nickname,register_date,salt,password,id) values(?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            ps.setInt(1,user.getLoginCount());
            ps.setString(2,user.getNickname());
            ps.setTimestamp(3,new Timestamp(user.getRegisterDate().getTime()));
            ps.setString(4,user.getSalt());
            ps.setString(5,user.getPassword());
            ps.setLong(6,user.getId());
            ps.addBatch();
        }
        //批处理
        ps.executeBatch();
        ps.clearParameters();
        connection.close();
        System.out.println("插入db");

        //登录，生成UserTicket
        String urlString = "http://localhost:8080/login/doLogin";
        File file = new File("C:\\Users\\13717\\Desktop\\config.txt");
        if (file.exists()){//已存在删除
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        raf.seek(0);
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            URL url = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection) url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile=" + user.getId() + "&password=" + MD5Utils.inputPassToFromPass("123456");
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buff)) >= 0){
                bout.write(buff,0,len);
            }
            inputStream.close();
            bout.close();
            String response = new String(bout.toByteArray());
            ObjectMapper mapper = new ObjectMapper();
            RespBean respBean = mapper.readValue(response, RespBean.class);
            String userTicket = (String) respBean.getObj();
            System.out.println("create userTicket:" + userTicket);
            String row = user.getId() + "," + userTicket;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file:" + user.getId());

        }
        raf.close();
        System.out.println("over");

    }

    private static Connection getConn() throws Exception {

        String url = "jdbc:mysql://localhost:3306/seckill?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "123456";
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);

        return DriverManager.getConnection(url,username,password);
    }

    public static void main(String[] args) throws Exception {

        createUser(5000);
    }


}
