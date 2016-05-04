package com.mybatis.generator;

import org.mybatis.generator.api.ShellRunner;

/**
 * 自动生成实体类与映射文件
 * 
 * @author larry.qi
 */
public class MBGenerator {

  public static void main(String[] args) {
    String configPath = MBGenerator.class.getClassLoader().getResource("").getPath()
        .replace("target/test-classes/", "mybatis-generator/generatorConfig.xml");
    String[] arg = {"-configfile", configPath, "-overwrite"};
    ShellRunner.main(arg);
  }

}
