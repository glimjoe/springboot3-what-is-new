package com.tongweb.springboot3.micrometer;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObservedAspectConfiguration {

  @Bean
  public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
    // 这一行可以去掉，使用自带的Handler也是可以的
    // 去掉这一行就可以不用重写ObservationHandler类，也就是SimpleLoggingHandler类
    observationRegistry.observationConfig().observationHandler(new SimpleLoggingHandler());
    return new ObservedAspect(observationRegistry);
  }
}
