package app.strategies;

import app.currency.CurrencyPair;
import app.indicators.EMA;
import app.indicators.NewEMA;
import app.kline.Kline;
import app.kline.KlineInterval;
import app.kline.KlineService;
import app.order.KucoinApi;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("emaAndRsiCrossover")
@RequiredArgsConstructor
public class EmaAndRsiCrossover {

  private final KucoinApi kucoinApi;
  private final KlineService klineService;
  private static final int ema13Period = 13;
  private static final int ema20Period = 20;

  public void run(final CurrencyPair currency) {

    final NewEMA ema =
        new NewEMA(klineService).init(currency, Arrays.asList(ema13Period, ema20Period));

    ema.getEmas();

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
  private List<Kline> queryKlines(final CurrencyPair currency, final int daysAgo) {
    LocalDate today = LocalDate.now(ZoneId.of("UTC"));
    // add an extra day to calculate the initial EMA
    LocalDate startDate = today.minusDays(daysAgo + 1);
    long todayTimestamp = today.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();
    long startingTimestamp = startDate.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();

    return kucoinApi.getHistoricRates(
        currency, startingTimestamp, todayTimestamp, KlineInterval.ONE_DAY);
  }
}
