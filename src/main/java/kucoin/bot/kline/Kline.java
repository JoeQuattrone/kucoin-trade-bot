package kucoin.bot.kline;

import java.util.List;
import javax.persistence.*;
import kucoin.bot.CurrencyPair;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
public class Kline {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "period", nullable = false)
  private final KlineInterval interval;

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private final CurrencyPair currency;

  @Column(unique = true, nullable = false)
  private final Long time; // Start time of the candle cycle

  @Column(nullable = false)
  private final double open; // Opening price

  @Column(nullable = false)
  private final double close; // Closing price

  @Column(nullable = false)
  private final double high; // Highest price

  @Column(nullable = false)
  private final double low; // Lowest price

  @Column(nullable = false)
  private final String volume; // Transaction volume

  @Column(nullable = false)
  private final String turnover; // Transaction amount

  public Kline(
      final KlineInterval interval, final CurrencyPair currency, final List<String> apiResponse) {
    this.interval = interval;
    this.currency = currency;

    this.time = Long.parseLong(apiResponse.get(0));
    this.open = Double.parseDouble(apiResponse.get(1));
    this.close = Double.parseDouble(apiResponse.get(2));
    this.high = Double.parseDouble(apiResponse.get(3));
    this.low = Double.parseDouble(apiResponse.get(4));
    this.volume = apiResponse.get(5);
    this.turnover = apiResponse.get(6);
  }
}
