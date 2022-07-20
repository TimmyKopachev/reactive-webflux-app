package org.reactive.currency;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
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
import reactor.core.publisher.Mono;

@EnableFeignClients
@EnableReactiveFeignClients
@RestController
@EnableEurekaClient
@SpringBootApplication
public class ApplicationCurrencyRunner {

  List<CurrencyDetails> currencyDetailsList =
      List.of(
          new CurrencyDetails("EUR", 50),
          new CurrencyDetails("BYN", 50),
          new CurrencyDetails("RUB", 50),
          new CurrencyDetails("USD", 50));

  @SneakyThrows
  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .sources(ApplicationCurrencyRunner.class)
        .bannerMode(Banner.Mode.OFF)
        .run(args);
  }

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  Flux<CurrencyDetailsTickResponse> getCurrenciesUpdate() {
    return Flux.interval(Duration.ofSeconds(5))
        .flatMap(
            l ->
                Mono.fromCallable(
                    () ->
                        CurrencyDetailsTickResponse.builder()
                            .currencies(generateCurrencyUpdates())
                            .timestamp(Instant.now())
                            .build()));
  }

  private List<CurrencyDetails> generateCurrencyUpdates() {
    Random random = new Random();
    return currencyDetailsList.stream()
        .peek(
            cd -> {
              var isIncreased = random.nextBoolean();
              cd.setAmount(isIncreased ? cd.getAmount() + 1 : cd.getAmount() - 1);
            })
        .collect(Collectors.toList());
  }
}
