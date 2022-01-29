package app.order;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BuySell {
  private final KucoinApi kucoinApi;

  // create buy order to buy based off portfolio percentage
  public void getCandlestick() throws IOException {}

  // create sell order to sell entire position of a coin
}
