package kucoin.bot;

import kucoin.bot.strategies.EmaAndRsiCrossover;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  // runs every hour
  @Scheduled(cron ="0 0 0/1 1/1 * ?")
  public CommandLineRunner root(ApplicationContext ctx) {
    return args -> {
      tradeBtc(ctx);
    };
  }

  private void tradeBtc(ApplicationContext ctx) {
    final EmaAndRsiCrossover strategy = (EmaAndRsiCrossover) ctx.getBean("emaAndRsiCrossover");
    strategy.run(CurrencyPair.BTC);
  }
}
