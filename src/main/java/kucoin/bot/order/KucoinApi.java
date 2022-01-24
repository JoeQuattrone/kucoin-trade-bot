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
import kucoin.bot.CurrencyPair;
import kucoin.bot.KlineInterval;
import org.springframework.stereotype.Component;

@Component
public class KucoinApi {
  private static KucoinRestClient client;

  static {
    Properties prop = new Properties();
    final String fileName = "credentials.txt";

    try (FileInputStream fis = new FileInputStream(fileName)) {
      prop.load(fis);
    } catch (FileNotFoundException ex) {
      out.printf("File not found %s", ex.getMessage());
      System.exit(0);
    } catch (IOException ex) {
      out.println(ex.getMessage());
      System.exit(0);
    }

    final String passphrase = prop.getProperty("PASSPHRASE");
    out.println(passphrase);

    client =
        new KucoinClientBuilder()
            .withBaseUrl("https://openapi-v2.kucoin.com")
            .withApiKey(prop.getProperty("API_KEY"), prop.getProperty("SECRET"), passphrase)
            .buildRestClient();
  }

  public KucoinRestClient getClient() {
    return client;
  }

  public List<Kline> getHistoricRates(
      final CurrencyPair pair, final Long startAt, final Long endAt, final KlineInterval interval) {

    final List<Kline> rates = new ArrayList<>();
    try {
      rates.addAll(
          client.historyAPI().getHistoricRates(pair.getValue(), startAt, endAt, interval.getValue())
              .stream()
              .map(Kline::new)
              .collect(Collectors.toList()));

      System.out.printf("Klines: %s", rates);
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
