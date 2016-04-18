package com.hoti.site.core.security;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * EncryptUtils: 系统加密与安全工具类
 * 
 * @author larry.qi
 */
public class EncryptUtil {

  /**
   * MD5加密处理<br>
   * salt必须经过ByteSource进行加密处理，配合SHIRO密码匹配规则
   * 
   * @param password 密码
   * @param salt = acccessKey
   * @return
   */
  public static String encrypt(String password, String salt) {
    return encrypt(password, ByteSource.Util.bytes(salt));
  }

  /**
   * MD5加密处理
   * 
   * @param password
   * @param byteSource
   * @return
   */
  public static String encrypt(String password, ByteSource byteSource) {
    return new Md5Hash(password, byteSource).toString().toUpperCase();
  }

  /**
   * 生成盐值=accessKey
   * 
   * @return
   */
  public static String getRandomSalt() {
    return UUID.randomUUID().toString().toUpperCase();
  }

}
