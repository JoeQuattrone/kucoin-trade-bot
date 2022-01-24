package kucoin.bot.order;

import java.io.IOException;
import java.util.List;
import kucoin.bot.KlineInterval;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class  BuySell {
  private final KucoinApi kucoinApi;

  public void getCandlestick() throws IOException {
    final List<Kline> rates =
        kucoinApi.getHistoricRates("BTC-USDT", 1641070569L, 1642280169L, KlineInterval.ONE_DAY);

    rates.forEach(
        rate -> {
          System.out.println(rate);
        });
  }
}
