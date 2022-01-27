package kucoin.bot.indicators;

import java.util.List;

/** EXPONENTIAL MOVING AVERAGE */
public class EMA {

  private double currentEMA;
  private final int period;
  private final double multiplier;

  public EMA(List<Double> closingPrices, int period) {
    currentEMA = 0;
    this.period = period;
    this.multiplier = 2.0 / (double) (period + 1);
    init(closingPrices);
  }

  public double get() {
    return currentEMA;
  }

  private void init(List<Double> closingPrices) {
    currentEMA = calculate(closingPrices);
    System.out.printf("Period: %s, Ema: %s", period, currentEMA);
  }

  // assumes the prices are in order of most recent
  private double calculate(final List<Double> closingPrices) {
    System.out.println("calculating ema for period: " + period);
    final double sumClosingPrices = closingPrices.stream().reduce(0.0, Double::sum);
    final double sma = sumClosingPrices / period; // correct up to this point
    final double oldestPrice =
        closingPrices.get(closingPrices.size() - 1); // 43067.3 which is correct

    double ema = calculateEMA(multiplier, oldestPrice, sma);

    for (int i = closingPrices.size() - 2; i >= 0; i--) {
      System.out.println(closingPrices.get(i));
      ema = calculateEMA(multiplier, closingPrices.get(i), ema);
    }
    return ema;
  }

  private double calculateEMA(
      final double multiplier, final double currentPrice, final double previousEma) {
    // EMA = (Close - EMA(previousBar)) * multiplier + EMA(previousBar)
    return multiplier * (currentPrice - previousEma) + previousEma;
  }
}
