package com.ink.config.Service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;

@Component("baseService")
public class BaseService {

  @Value("#{configToolkitProp.config_center_connectStr}")
  private String zkAddress;
  private CuratorFramework client;
  
  public void setZkAddress(String zkAddress)
  {
    this.zkAddress = zkAddress;
  }
  
  @PostConstruct
  private void init()
  {
    this.client = CuratorFrameworkFactory.newClient(this.zkAddress, new ExponentialBackoffRetry(1000, 3));
    this.client.start();
  }
  
  @PreDestroy
  private void destroy()
  {
    if (this.client != null) {
      this.client.close();
    }
  }
  
  public CuratorFramework getClient()
  {
    return this.client;
  }
}
