package com.mprtcz.cinkciarzscrapper.scrapper;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Rate {

  String rateId;
  double sellPrice;
  double buyPrice;
}
