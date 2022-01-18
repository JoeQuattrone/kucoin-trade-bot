package kucoin.bot.order;

import static java.lang.System.out;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinRestClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import kucoin.bot.KlineInterval;
import org.springframework.stereotype.Component;

@Component
public class KucoinApi {
  private static KucoinRestClient client;

  static {
    Properties prop = new Properties();
    String fileName = "credentials.txt";

    try (FileInputStream fis = new FileInputStream(fileName)) {
      prop.load(fis);
    } catch (FileNotFoundException ex) {
      out.printf("File not found %n", ex);
    } catch (IOException ex) {
      out.println(ex);
    }

    final String passphrase = prop.getProperty("app.PASSPHRASE");
    out.println(passphrase);

    client =
        new KucoinClientBuilder()
            .withBaseUrl("https://openapi-v2.kucoin.com")
            .withApiKey(prop.getProperty("app.API_KEY"), prop.getProperty("app.SECRET"), passphrase)
            .buildRestClient();
  }

  public KucoinRestClient getClient() {
    return client;
  }

  public List<Kline> getHistoricRates(
      final String symbol, final Long startAt, final Long endAt, final KlineInterval interval) {

    final List<Kline> rates = new ArrayList<>();
    try {
      rates.addAll(
          client.historyAPI().getHistoricRates(symbol, startAt, endAt, interval.getValue()).stream()
              .map(Kline::new)
              .collect(Collectors.toList()));
      return rates;
    } catch (IOException ex) {
      System.out.println(ex);
      throw ex;
    } finally {
      return rates;
    }
  }

  //  public List<SymbolResponse> getAllSymbols() throws IOException {
  //    return client.symbolAPI().getSymbols();
  //  }

  //   1min, 3min, 5min, 15min, 30min, 1hour, 2hour, 4hour, 6hour, 8hour, 12hour, 1day, 1week

}