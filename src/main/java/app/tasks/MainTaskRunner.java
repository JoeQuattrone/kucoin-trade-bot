package app.tasks;

import app.currency.CurrencyPair;
import app.kline.Kline;
import app.kline.KlineInterval;
import app.kline.KlineService;
import app.order.KucoinApi;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
// needed for scheduled task
@Component("mainTaskRunner")
public class MainTaskRunner {
  private final KucoinApi kucoinApi;
  private final KlineService klineService;

  // every morning at 1am UTC
  @Scheduled(cron = "0 0 1 * * *", zone = "UTC")
  public void getLatestPrices() {
    final Kline latestStoredKline = klineService.findLatestKline();
    System.out.println("Latest stored kline: " + latestStoredKline);

    final LocalDate latestStoredKlineDate =
        LocalDate.ofInstant(Instant.ofEpochSecond(latestStoredKline.getTime()), ZoneId.of("UTC"));
    final LocalDate today = LocalDate.now(ZoneId.of("UTC"));
    final long todayTimestamp = today.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();
    final long startingTimestamp =
        latestStoredKlineDate.plusDays(1L).atStartOfDay(ZoneId.of("UTC")).toEpochSecond();

    final List<Kline> newKlines =
        kucoinApi.getHistoricRates(
            CurrencyPair.BTC, startingTimestamp, todayTimestamp, KlineInterval.ONE_DAY);

    klineService.saveAll(newKlines);
    System.out.println(String.format("saved %s klines in klines table", newKlines.size()));
  }
}
