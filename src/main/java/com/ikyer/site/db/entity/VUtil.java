package com.ikyer.site.db.entity;

import java.util.Arrays;

/**
 * 服务层工具
 * 
 * @author larry.qi
 *
 */
public class VUtil {

  /**
   * 用户类型
   * <li>1=普通用户</li>
   * <li>2=管理员</li>
   */
  private final static Byte[] USER_TYPES = {1, 2};
  
  /**
   * 基本状态
   * <li>0=禁用</li>
   * <li>1=启用</li>
   */
  private final static Byte[] BASE_STATES = {0, 1};

  /**
   * 审核状态
   * <li>1=未审核</li>
   * <li>2=审核通过</li>
   * <li>3=审核拒绝</li>
   */
  private final static Byte[] AUDIT_STATES = {1, 2, 3};
  
  /**
   * 用户状态
   * <li>0=禁用</li>
   * <li>1=启用</li>
   * <li>2=锁定 , 仅用于用户状态</li>
   * <li>3=删除 , 软删除状态</li>
   */
  private final static Byte[] USER_STATES = {0, 1, 2, 3};


  /**
   * 验证用户类型
   * 
   * @param type 
   * @return
   */
  public static boolean assertUserType(Byte type) {
    return Arrays.asList(USER_TYPES).contains(type);
  }

  /**
   * 基本状态验证，0=禁用 1=启用
   * 
   * @param state 0,1
   * @return
   */
  public static boolean assertBaseState(Byte state) {
    return Arrays.asList(BASE_STATES).contains(state);
  }

  /**
   * 验证审核状态
   * 
   * @param audit 1,2,3
   * @return
   */
  public static boolean assertAudit(Byte audit) {
    return Arrays.asList(AUDIT_STATES).contains(audit);
  }

  /**
   * 验证用户状态
   * 
   * @param state 0,1,2,3
   * @return
   */
  public static boolean assertUserState(Byte state) {
    return Arrays.asList(USER_STATES).contains(state);
  }

}
