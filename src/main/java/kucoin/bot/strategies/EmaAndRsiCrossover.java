package kucoin.bot.strategies;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import kucoin.bot.CurrencyPair;
import kucoin.bot.KlineInterval;
import kucoin.bot.indicators.EMA;
import kucoin.bot.order.Kline;
import kucoin.bot.order.KucoinApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("emaAndRsiCrossover")
@RequiredArgsConstructor
public class EmaAndRsiCrossover {

  private final KucoinApi kucoinApi;
  private static final int ema14Period = 14;
  private static final int ema20Period = 20;

  public void run(final CurrencyPair targetCoin) {
    // get prices
    final List<Double> twentyDayPrices =
        queryKlines(ema20Period).stream().map(Kline::getClose).collect(Collectors.toList());
    final List<Double> fourteenDayPrices = twentyDayPrices.subList(0, 13);

    // calculate values
    final EMA ema14 = new EMA(fourteenDayPrices, 14);
    final EMA ema20 = new EMA(twentyDayPrices, ema20Period);

    // execute buy or sell order based on values
  }

  // add to constructor , final RSI rsi14, final RSISMA rsiSma14
  private boolean shouldBuy(final EMA ema14, final EMA ema20) {
    // if ema14 > ema20
    // and
    // rsi14 > rsiSma14
    // return true
    return false;
  }

  private List<Kline> queryKlines(final int daysAgo) {
    LocalDate today = LocalDate.now(ZoneId.of("UTC"));
    LocalDate startDate = today.minusDays(daysAgo);

    return kucoinApi.getHistoricRates(
        CurrencyPair.BTC, today.toEpochDay(), startDate.toEpochDay(), KlineInterval.ONE_DAY);
  }
}
