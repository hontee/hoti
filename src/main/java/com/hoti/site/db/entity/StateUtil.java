package com.hoti.site.db.entity;

import java.util.Arrays;

/**
 * 通用状态工具
 * 
 * @author larry.qi
 *
 */
public class StateUtil {

  /**
   * 基本状态
   * <li>0=禁用</li>
   * <li>1=启用</li>
   */
  private final static Byte[] STATES01 = {0, 1};

  /**
   * 状态
   * <li>0=禁用</li>
   * <li>1=启用</li>
   * <li>2=锁定 , 仅用于用户状态</li>
   * <li>3=删除 , 软删除状态</li>
   */
  private final static Byte[] USER_STATES = {0, 1, 2, 3};

  /**
   * 验证状态
   * 
   * @param state
   * @return
   */
  public static boolean userValidate(Byte state) {
    return Arrays.asList(USER_STATES).contains(state);
  }
  
  /**
   * 基本状态验证，0=禁用 1=启用
   * 
   * @param state 0 or 1
   * @return
   */
  public static boolean baseValidate(Byte state) {
    return Arrays.asList(STATES01).contains(state);
  }

}
