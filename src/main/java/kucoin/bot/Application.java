package kucoin.bot;

import com.kucoin.sdk.rest.response.SymbolResponse;
import java.util.List;
import kucoin.bot.order.KucoinApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      System.out.println("Let's inspect the beans provided by Spring Boot:");

      //      final BuySell buySell = (BuySell) ctx.getBean("buySell");
      //      buySell.getCandlestick();

      final KucoinApi kucoinApi = (KucoinApi) ctx.getBean("kucoinApi");
      List<SymbolResponse> res = kucoinApi.getAllSymbols();

      System.out.println(res);
    };
  }
}
