package kucoin.bot.strategies;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import kucoin.bot.CurrencyPair;
import kucoin.bot.KlineInterval;
import kucoin.bot.indicators.EMA;
import kucoin.bot.order.Kline;
import kucoin.bot.order.KucoinApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmaAndRsiCrossover {

  private KucoinApi kucoinApi;
  private static final int ema14Period = 14;
  private static final int ema20Period = 20;

  // get ema 14
  // get ema 20

  // get rsi 14
  // get rsi 14 sma

  public void run(final CurrencyPair targetCoin) {
    // get prices
    final List<Kline> twentyDayPrices = getPrices(ema20Period);

    // calculate values

    // execute buy or sell order based on values
  }

  private boolean shouldBuy(final EMA ema14, final EMA ema20, final RSI rsi14, final RSISMA rsiSma14) {
    // if ema14 > ema20
    // and
    // rsi14 > rsiSma14
    // return true
    return false
  }
  public EMA getEma(final List<Double> closingPrices, ) {

  }

  /** if ema 14 is greater then ema 20 and rsi14 is greater then rsiSMA and rsi14 >= 55 */
  public List<Kline> getPrices(final int daysAgo) {
    LocalDate today = LocalDate.now(ZoneId.of("UTC"));
    LocalDate startDate = today.minusDays(daysAgo);

    return kucoinApi.getHistoricRates(
        CurrencyPair.BTC,
        today.toEpochDay(),
            startDate.toEpochDay(),
        KlineInterval.ONE_DAY);
  }
}
