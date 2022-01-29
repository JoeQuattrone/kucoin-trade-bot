package kucoin.bot.strategies;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import kucoin.bot.CurrencyPair;
import kucoin.bot.kline.KlineInterval;
import kucoin.bot.indicators.EMA;
import kucoin.bot.kline.Kline;
import kucoin.bot.order.KucoinApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("emaAndRsiCrossover")
@RequiredArgsConstructor
public class EmaAndRsiCrossover {

  private final KucoinApi kucoinApi;
  private static final int ema13Period = 13;
  private static final int ema20Period = 20;

  public void run(final CurrencyPair targetCoin) {
    // get prices
    final List<Double> twentyDayPrices =
        queryKlines(ema20Period).stream().map(Kline::getClose).collect(Collectors.toList());
    final List<Double> thirteenDayPrices = twentyDayPrices.subList(0, 14);

    twentyDayPrices.forEach(System.out::println);

    // calculate values
    final EMA ema13 = new EMA(thirteenDayPrices, ema13Period);
    // final EMA ema20 = new EMA(twentyDayPrices, ema20Period);

    // execute buy or sell order based on values
  }

  // add to constructor , final RSI rsi14, final RSISMA rsiSma14
  private boolean determineTrade(final EMA ema14, final EMA ema20) {
    // if ema14 > ema20
    // and
    // rsi14 > rsiSma14
    // return true
    return false;
  }

  // queries from yesterdays closing time to # of daysAgo
  private List<Kline> queryKlines(final int daysAgo) {
    LocalDate today = LocalDate.now(ZoneId.of("UTC"));
    // add an extra day to calculate the initial EMA
    LocalDate startDate = today.minusDays(daysAgo + 1);
    long todayTimestamp = today.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();
    long startingTimestamp = startDate.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();

    return kucoinApi.getHistoricRates(
        CurrencyPair.BTC, startingTimestamp, todayTimestamp, KlineInterval.ONE_DAY);
  }
}
