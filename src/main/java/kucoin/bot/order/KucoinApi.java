package kucoin.bot.order;

import static java.lang.System.out;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinRestClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
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
}
