package kucoin.bot;

import lombok.Getter;

public enum CurrencyPair {
  BTC("BTC-USDT"),
  ETH("ETH-USDT"),
  SOLANA("SOL-USDT"),
  POLKADOT("DOT-USDT"),
  CARDANO("ADA-USDT"),
  BNB("BNB-USDT"),
  TRON("TRX-USDT");

  @Getter private final String value;

  //  @Getter
  //  private final List<CurrencyPair> all =
  // Arrays.asList(this.getDeclaringClass().getEnumConstants());

  CurrencyPair(String value) {
    this.value = value;
  }
}
