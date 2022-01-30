package app.migrations;

import app.currency.CurrencyPair;
import app.kline.Kline;
import app.kline.KlineDao;
import app.kline.KlineInterval;
import app.order.KucoinApi;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
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
    migrate();
  }

  private void migrate() {
    LocalDate today = LocalDate.now(ZoneId.of("UTC"));
    // add an extra day to calculate the initial EMA
    LocalDate startDate = today.minusDays(365);
    long todayTimestamp = today.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();
    long startingTimestamp = startDate.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();

    CurrencyPair[] currencyPairs = CurrencyPair.values();
    List<CurrencyPair> pairs = new ArrayList<>(Arrays.asList(currencyPairs));
    pairs.remove(0);

    for (CurrencyPair pair : pairs) {
      final List<Kline> klines =
          kucoinApi.getHistoricRates(
              pair, startingTimestamp, todayTimestamp, KlineInterval.ONE_DAY);

      klineService.saveAll(klines);
    }
  }
}
