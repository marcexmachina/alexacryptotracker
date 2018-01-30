package main.java;

/**
 * Created by marconeill on 30/01/2018.
 */
public enum Instrument {
    BITCOIN("BTC"),
    LITECOIN("LTC"),
    RIPPLE("XRP"),
    ETHEREUM("ETH");

    private final String ticker;

    Instrument(String ticker) {
        this.ticker = ticker;
    }

    String getTicker() {
        return ticker;
    }
}
