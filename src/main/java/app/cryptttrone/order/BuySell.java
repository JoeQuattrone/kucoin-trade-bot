package app.cryptttrone.order;

import java.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuySell {
  private final KucoinApi kucoinApi;

  public void getCandlestick() throws IOException {
    kucoinApi
        .getClient()
        .historyAPI()
        .getHistoricRates("btc-eth", 1566703297L, 1566789757L, "1day");
  }
}
