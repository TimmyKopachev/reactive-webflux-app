package org.common.model;

import java.time.Instant;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyDetailsTickResponse {

  private Instant timestamp;
  private List<CurrencyDetails> currencies;
}
