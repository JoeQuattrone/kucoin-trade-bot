package app.kline;

import app.currency.CurrencyPair;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity(name = "Kline")
@Table(name = "kline")
@NoArgsConstructor
public class Kline {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "period", nullable = false)
  private KlineInterval interval;

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private CurrencyPair currency;

  @Column(unique = true, nullable = false)
  private Long time; // Start time of the candle cycle

  @Column(nullable = false)
  private double open; // Opening price

  @Column(nullable = false)
  private double close; // Closing price

  @Column(nullable = false)
  private double high; // Highest price

  @Column(nullable = false)
  private double low; // Lowest price

  @Column(nullable = false)
  private String volume; // Transaction volume

  @Column(nullable = false)
  private String turnover; // Transaction amount

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
