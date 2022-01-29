package app.migrations;

import app.currency.CurrencyPair;
import app.kline.Kline;
import app.kline.KlineDao;
import app.kline.KlineInterval;
import app.order.KucoinApi;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SeedKline {
  final KucoinApi kucoinApi;
  final KlineDao klineService;

  public
  // get Kline data for all currencies for one year and save to DB
  final void run() {
    migrateBtc();
  }

  private void migrateBtc() {
    LocalDate today = LocalDate.now(ZoneId.of("UTC"));
    // add an extra day to calculate the initial EMA
    LocalDate startDate = today.minusDays(365);
    long todayTimestamp = today.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();
    long startingTimestamp = startDate.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();

    final List<Kline> klines =
        kucoinApi.getHistoricRates(
            CurrencyPair.BTC, startingTimestamp, todayTimestamp, KlineInterval.ONE_DAY);

    klineService.saveAll(klines);
  }
}
