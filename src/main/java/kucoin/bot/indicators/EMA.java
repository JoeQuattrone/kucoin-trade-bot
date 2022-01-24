package kucoin.bot.indicators;

import java.util.List;

/**
 * EXPONENTIAL MOVING AVERAGE
 */
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

    private void init (List<Double> closingPrices) {
        currentEMA = calculate(closingPrices);
    }

    // assumes the prices are in order of most recent
    private double calculate(final List<Double> closingPrices) {
        final double sumClosingPrices = closingPrices.stream().reduce(0.0, Double::sum);
        final double sma = sumClosingPrices / period;
        final double oldestPrice = closingPrices.get(closingPrices.size() -1);

        double ema = calculateEMA(multiplier, oldestPrice, sma);

        for(int i = 1; i < closingPrices.size(); i++) {
            ema = calculateEMA(multiplier, closingPrices.get(i), ema);
        }
        return ema;
    }

    private double calculateEMA(final double multiplier, final double currentPrice, final double previousEma) {
        return multiplier * (currentPrice - previousEma) + previousEma;
    }
}
