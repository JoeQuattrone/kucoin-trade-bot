package kucoin.bot.order;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
public class Kline {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private final Long time; // Start time of the candle cycle
  private final double open; // Opening price
  private final double close; // Closing price
  private final double high; // Highest price
  private final double low; // Lowest price
  private final String volume; // Transaction volume
  private final String turnover; // Transaction amount

  public Kline(final List<String> apiResponse) {
    this.time = Long.parseLong(apiResponse.get(0));
    this.open = Double.parseDouble(apiResponse.get(1));
    this.close = Double.parseDouble(apiResponse.get(2));
    this.high = Double.parseDouble(apiResponse.get(3));
    this.low = Double.parseDouble(apiResponse.get(4));
    this.volume = apiResponse.get(5);
    this.turnover = apiResponse.get(6);
  }
}
