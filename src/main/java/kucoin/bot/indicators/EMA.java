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
    // calculate sma for the previous 20 days. Exclude the latest price.
    final double sumClosingPrices =
        closingPrices.subList(1, closingPrices.size()).stream().reduce(0.0, Double::sum);
    final double sma = sumClosingPrices / period; // correct up to this point
    final double currentPrice = closingPrices.get(0);

    double ema = calculateEMA(multiplier, currentPrice, sma);

    System.out.println(String.format("sma: %s, ema: %s", sma, ema));
    return ema;
  }

  private double calculateEMA(
      final double multiplier, final double currentPrice, final double previousEma) {
    // EMA = (today - EMA(previousDay)) * multiplier + EMA(previousDay)
    //    return multiplier * (currentPrice - previousEma) + previousEma;
    return currentPrice * multiplier + previousEma * (1.0 - multiplier);
  }
}
