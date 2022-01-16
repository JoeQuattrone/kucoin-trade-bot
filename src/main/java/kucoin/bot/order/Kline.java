package kucoin.bot.order;

import java.util.List;
import lombok.Data;

@Data
public class Kline {

  private final String time; // Start time of the candle cycle
  private final String open; // Opening price
  private final String close; // Closing price
  private final String high; // Highest price
  private final String low; // Lowest price
  private final String volume; // Transaction volume
  private final String turnover; // Transaction amount

  public Kline(final List<String> apiResponse) {
    this.time = apiResponse.get(0);
    this.open = apiResponse.get(1);
    this.close = apiResponse.get(2);
    this.high = apiResponse.get(3);
    this.low = apiResponse.get(4);
    this.volume = apiResponse.get(5);
    this.turnover = apiResponse.get(6);
  }
}
