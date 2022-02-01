package app;

import app.currency.CurrencyPair;
import app.strategies.EmaAndRsiCrossover;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner root(ApplicationContext ctx) {
    return args -> {
      tradeBtc(ctx);
    };
  }

  // Every morning at 2am
  //  @Scheduled(cron = "0 2 * * *", zone = "UTC")  encountered invalid @Scheduled method
  // 'tradeBtc': Only no-arg methods may be annotated with @Scheduled
  private void tradeBtc(ApplicationContext ctx) {
    final EmaAndRsiCrossover strategy = (EmaAndRsiCrossover) ctx.getBean("emaAndRsiCrossover");
    strategy.run(CurrencyPair.BTC);
  }
}
