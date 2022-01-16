package kucoin.bot.order;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BuySell {
  private final KucoinApi kucoinApi;

  public void getCandlestick() throws IOException {
    final List<List<String>> rates =
        kucoinApi
            .getClient()
            .historyAPI()
            .getHistoricRates("BTC-USDT", 1566703297L, 1566789757L, "1day");

    rates.forEach(
        rate -> {
          System.out.println(rate);
        });
  }
}
