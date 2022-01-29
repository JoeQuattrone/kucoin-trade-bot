package app.kline;

import lombok.Getter;

public enum KlineInterval {
  ONE_MIN("1min"),
  FIVE_MIN("5min"),
  ONE_HOUR("1hour"),
  ONE_DAY("1day"),
  ONE_WEEK("1week");

  @Getter private final String value;

  KlineInterval(String value) {
    this.value = value;
  }
}
