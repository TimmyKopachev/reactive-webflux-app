package org.reactive.bank;

import org.common.model.CurrencyDetailsTickResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(name = "currency-data-provider")
public interface CurrencyReactiveClient {

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  Flux<CurrencyDetailsTickResponse> getCurrenciesUpdate();
}
