package kucoin.bot.order;

import static java.lang.System.out;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinRestClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    final List<Kline> klines = new ArrayList<>();
    try {
      System.out.println(
          String.format(
              "pair: %s, start time: %s, end time: %s, interval: %s",
              pair.getValue(), startAt, endAt, interval.getValue()));

      klines.addAll(
          client.historyAPI().getHistoricRates(pair.getValue(), startAt, endAt, interval.getValue())
              .stream()
              .map(Kline::new)
              .collect(Collectors.toList()));

      System.out.println("Klines returned: " + klines.size());
    } catch (Exception ex) {
      System.out.println(Arrays.toString(ex.getStackTrace()));
      System.exit(0);
    }
    return klines;
  }
}
