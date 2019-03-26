package com.common.utils;

/**
 * 加密工具类
 *
 */

import com.common.base.user.entity.User;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.security.Key;

/**
 *
 * @ClassName: EndecryptUtils
 * @Description: 加密工具类
 * @author gaogang
 * @date 2016年7月12日 下午4:24:19
 *
 */
public class EndecryptUtils {
    /**
     * base64进制加密
     *
     * @param password
     * @return
     */
    public static String encrytBase64(String password) {
        byte[] bytes = password.getBytes();
        return Base64.encodeToString(bytes);
    }
    /**
     * base64进制解密
     * @param cipherText
     * @return
     */
    public static String decryptBase64(String cipherText) {
        return Base64.decodeToString(cipherText);
    }
    /**
     * 16进制加密
     *
     * @param password
     * @return
     */
    public static String encrytHex(String password) {
        byte[] bytes = password.getBytes();
        return Hex.encodeToString(bytes);
    }
    /**
     * 16进制解密
     * @param cipherText
     * @return
     */
    public static String decryptHex(String cipherText) {
        return new String(Hex.decode(cipherText));
    }

    public static String generateKey()
    {
        AesCipherService aesCipherService=new AesCipherService();
        Key key=aesCipherService.generateNewKey();
        return Base64.encodeToString(key.getEncoded());
    }
    /**
     * 对密码进行md5加密,并返回密文和salt，包含在User对象中
     * @param username 用户名
     * @param password 密码
     * @param hashIterations 迭代次数
     * @return UserEntity对象，包含密文和salt
     */
    public static User md5Password(String phone, String password, int hashIterations){
        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
        String salt= secureRandomNumberGenerator.nextBytes().toHex();
        //组合username,两次迭代，对密码进行加密
        String password_cryto = new Md5Hash(password,phone+salt,hashIterations).toString();
        User user=new User();
        user.setPassword(password_cryto);
        user.setCredentialsSalt(salt);
        user.setPhone(phone);
        return user;
    }



/*    public static void main(String[] args) {
          String salt= "8d78869f470951332959580424d4bf4f";
          //组合username,两次迭代，对密码进行加密
          String password_cryto = new Md5Hash("123456","administrator"+salt,2).toString();
          System.out.println(password_cryto);
    } */
}
