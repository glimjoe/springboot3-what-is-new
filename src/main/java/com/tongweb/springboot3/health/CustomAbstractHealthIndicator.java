package com.tongweb.springboot3.health;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component("health2")
public class CustomAbstractHealthIndicator extends AbstractHealthIndicator {
  @Override
  protected void doHealthCheck(Health.Builder builder) throws Exception {

    int errorCode = check();
    if (errorCode == 1) {
      builder.down().down().withDetail("Error Code", errorCode).build();
      return;
    }
    builder.up().build();

  }

  private int check() {
    // perform some specific health check
    return ThreadLocalRandom.current().nextInt(5);
  }
}
