package org.reactive.discovery;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;
import reactivefeign.spring.config.EnableReactiveFeignClients;


@EnableEurekaServer
@SpringBootApplication
public class ApplicationEurekaRunner {

  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .sources(ApplicationEurekaRunner.class)
        .bannerMode(Banner.Mode.OFF)
        .run(args);
  }
}
