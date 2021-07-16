package com.fabriciomoreira.exchangerate.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExchangeRateAPIClient implements ExternalExchangeRate {

    public static final String ENDPOINT_API = "https://v6.exchangerate-api.com/v6/b07d26e6a0aaa31d6a0e9e83/latest/EUR";

    public Map<String, BigDecimal> getRateFromSource() {
        try {
            // Making Request
            URL url = new URL(ENDPOINT_API);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            // Convert to JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonObj = root.getAsJsonObject();
            // Accessing object
            JsonObject conversionRates = jsonObj.get("conversion_rates").getAsJsonObject();
            //Convert to map
            Map<String, BigDecimal> currencyMap = new HashMap<>();
            for (String currency : conversionRates.keySet()) {
                currencyMap.putIfAbsent(currency, conversionRates.get(currency).getAsBigDecimal());
            }
            return currencyMap;
        } catch (Exception e) {
            throw new RuntimeException("Exception getting currency from xchangerate-api.com: " + e);
        }
    }

}
