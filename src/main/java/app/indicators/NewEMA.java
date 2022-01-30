package app.indicators;

import app.currency.CurrencyPair;
import app.kline.KlineService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewEMA {
  private CurrencyPair currency;
  private List<Double> closingPrices;
  /** Map of period to ema */
  private Map<Integer, Double> emaByPeriod;

  private final KlineService klineService;

  public NewEMA init(final CurrencyPair currency, final List<Integer> periods) {
    this.currency = currency;
    final List<Double> closingPrices = klineService.findAllByCurrencyOrdinal(currency);
    this.closingPrices = closingPrices;

    System.out.println("(EMA) closing prices returned: " + closingPrices.size());

    return this;
  }

  final Map<Integer, Double> getEmaByPeriod() {
    return emaByPeriod;
  }

  private void calculateEmas(final List<Integer> periods) {
    periods.forEach(this::calculate);
  }

  private void calculate(final Integer period) {
    System.out.println("calculating ema for period: " + period);
    final double multiplier = 2.0 / (double) (period + 1);
    // uses the oldest sma for the first ema
    final double sumClosingPrices =
        closingPrices.subList(1, closingPrices.size()).stream().reduce(0.0, Double::sum);
    final double sma = sumClosingPrices / period; // correct up to this point
    final double currentPrice = closingPrices.get(0);

    double ema = calculateEMA(multiplier, currentPrice, sma);

    System.out.println(String.format("sma: %s, ema: %s", sma, ema));

    emaByPeriod.put(period, ema);
  }

  private double calculateEMA(
      final double multiplier, final double currentPrice, final double previousEma) {
    // EMA = (today - EMA(previousDay)) * multiplier + EMA(previousDay)
    //    return multiplier * (currentPrice - previousEma) + previousEma;
    return currentPrice * multiplier + previousEma * (1.0 - multiplier);
  }
}