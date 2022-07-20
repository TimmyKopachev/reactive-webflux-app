package org.reactive.bank;

import lombok.AllArgsConstructor;
import org.common.model.CurrencyDetails;
import org.common.model.CurrencyDetailsTickResponse;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactor.core.publisher.Flux;

@EnableFeignClients
@EnableReactiveFeignClients
@RestController
@EnableEurekaClient
@SpringBootApplication
@AllArgsConstructor
public class ApplicationBankRunner {

  private final CurrencyReactiveClient currencyReactiveClient;

  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .sources(ApplicationBankRunner.class)
        .bannerMode(Banner.Mode.OFF)
        .run(args);
  }

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<CurrencyDetailsTickResponse> getCurrenciesUpdate() {
    return currencyReactiveClient.getCurrenciesUpdate();
  }
}
