package com.hoti.site.core.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProps implements Serializable {

  private static final long serialVersionUID = 1L;

  @Value("${memcached.host}")
  private String host;
  
  @Value("${memcached.port}")
  private String port;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  @Override
  public String toString() {
    return "ApplicationProps [host=" + host + ", port=" + port + "]";
  }
  
}
