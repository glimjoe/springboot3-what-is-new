package com.tongweb.springboot3.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("health1")
public class NewHealthIndicator implements HealthIndicator {

  @Override
  public Health getHealth(boolean includeDetails) {
    return HealthIndicator.super.getHealth(includeDetails);
  }

  @Override
  public Health health() {
    return Health.down().withDetail("msg", "测试消息").build();
  }
}
