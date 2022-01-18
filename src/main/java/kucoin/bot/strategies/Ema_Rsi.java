package kucoin.bot.strategies;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import kucoin.bot.CurrencyPair;
import kucoin.bot.KlineInterval;
import kucoin.bot.order.Kline;
import kucoin.bot.order.KucoinApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Ema_Rsi {

  private KucoinApi kucoinApi;
  private static final int ema14Period = 14;
  private static final int ema20Period = 20;

  // get ema 14
  // get ema 20

  // get rsi 14
  // get rsi 14 sma

  /** if ema 14 is greater then ema 20 and rsi14 is greater then rsiSMA and rsi14 >= 55 */
  public List<Kline> getEma20() {
    ZoneId zoneid1 = ZoneId.of("UTC");
    LocalDate today = LocalDate.now(zoneid1);
    LocalDate twentyDaysAgo = today.minusDays(ema20Period);

    return kucoinApi.getHistoricRates(
        CurrencyPair.BTC.getValue(),
        today.toEpochDay(),
        twentyDaysAgo.toEpochDay(),
        KlineInterval.ONE_DAY);
  }
}
