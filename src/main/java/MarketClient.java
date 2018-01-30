package main.java;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marconeill on 30/01/2018.
 */
public class MarketClient {

    private static final String baseUrl = "https://api.btcmarkets.net";

    private final Map<String, Instrument> tickerMap = new HashMap();

    MarketClient() {
        tickerMap.put("bitcoin", Instrument.BITCOIN);
        tickerMap.put("litecoin", Instrument.LITECOIN);
        tickerMap.put("ripple", Instrument.RIPPLE);
        tickerMap.put("ethereum", Instrument.ETHEREUM);
    }

    public String getPrice(String instrument) throws IOException {
        URL url = new URL(getEndpoint(instrument));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        conn.connect();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        JSONObject obj = new JSONObject(content.toString());

        return Double.toString(obj.getDouble("lastPrice"));
    }

    private String getEndpoint(String instrument) {
        String ticker = tickerMap.get(instrument).getTicker();
        String endpoint = baseUrl + "/market/" + ticker + "/AUD/tick";
        return endpoint;
    }
}
