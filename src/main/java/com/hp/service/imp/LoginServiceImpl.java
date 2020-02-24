package com.hp.service.imp;


import com.hp.dao.LoginMapper;
import com.hp.dao.UserPowerDao;
import com.hp.pojo.UserLogin;
import com.hp.pojo.UserPower;
import com.hp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Random;


@Service("userService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UserPowerDao userPowerDao;

    @Override
    public UserLogin Login(String username ) {
        return loginMapper.Login(username);
    }

    @Override
    public boolean updateRemarkByUsername(String remark, String username) {
        return loginMapper.updateRemarkByUsername(remark,username);
    }

    @Override
    public int addUser(UserLogin userLogin) {
        String MD5password = getSaltMD5(userLogin.getPassword());
        userLogin.setPassword(MD5password);
        if (userLogin.getToken().equals("学生")) {
            userLogin.setToken("student");
        }else if (userLogin.getToken().equals("教师")) {
            userLogin.setToken("teacher");
        }
        return loginMapper.addUser(userLogin);
    }

    @Override
    public int selectbyUsername(String username) {
            return loginMapper.selectbyUsername(username);
    }

    @Override
    public String selectUserPower(UserPower userPower) {
        return userPowerDao.selectUserPower(userPower);
    }

    @Override
    public int createUser(UserLogin userLogin) {
         try {
             return loginMapper.createUser(userLogin);
         }catch (Exception e){
             return 0;
         }
    }

    @Override
    public String selectUserToken(UserLogin userLogin) {
        UserLogin indirectUser = Login(userLogin.getUsername());
        boolean ifsame = getSaltverifyMD5(userLogin.getPassword(),indirectUser.getPassword());
        if (ifsame) {
            return indirectUser.getToken();
        }else {
            return "";
        }

    }




    /**
     * byte[]字节数组 转换成 十六进制字符串
     *
     * @param arr 要转换的byte[]字节数组
     *
     * @return  String 返回十六进制字符串
     */
    private String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    /**
     * MD5加密,并把结果由字节数组转换成十六进制字符串
     *
     * @param str 要加密的内容
     *
     * @return String 返回加密后的十六进制字符串
     */
    private String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return hex(digest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
    }

    /**
     * 生成含有随机盐的密码
     *
     * @param password 要加密的密码
     *
     * @return String 含有随机盐的密码
     */
    public String getSaltMD5(String password) {
        // 生成一个16位的随机数
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        // 生成最终的加密盐
        String salt = sBuilder.toString();
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }
    /**
     * 验证加盐后是否和原密码一致
     *
     * @param password 原密码
     *
     * @param password 加密之后的密码
     *
     *@return boolean true表示和原密码一致   false表示和原密码不一致
     */
    public boolean getSaltverifyMD5(String password, String md5str) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5str.charAt(i);
            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
            cs2[i / 3] = md5str.charAt(i + 1);
        }
        String Salt = new String(cs2);
        return md5Hex(password + Salt).equals(String.valueOf(cs1));
    }





}

