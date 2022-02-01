package app.strategies;

import app.currency.CurrencyPair;
import app.indicators.EMA;
import app.kline.KlineService;
import app.order.KucoinApi;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Buys when EMA13 is higher than EMA20 and RSI14 is higher than RSI14 SMA and RSI14 >= 55 Sells
 * when EMA13 is lower than EMA20
 */
@Component("emaAndRsiCrossover")
@RequiredArgsConstructor
public class EmaAndRsiCrossover {

  private final KucoinApi kucoinApi;
  private final KlineService klineService;
  private static final int ema13Period = 13;
  private static final int ema20Period = 20;

  public void run(final CurrencyPair currency) {

    final EMA ema = new EMA(klineService).init(currency, Arrays.asList(ema13Period, ema20Period));

    ema.getEmaByPeriod();

    // execute buy or sell order based on values
  }

  // add to constructor , final RSI rsi14, final RSISMA rsiSma14
  private boolean determineTrade(final EMA ema) {
    // if ema14 > ema20
    // and
    // rsi14 > rsiSma14
    // return true
    return false;
  }
}
