package cryptotracker.com.worker;

import cryptotracker.com.model.CryptoPriceContainer;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

public class PriceUpdateWorker extends Thread {

    public static final String BITCOIN_PRICE_ENDPOINT = "https://api.coindesk.com/v1/bpi/currentprice.json";

    private final OkHttpClient client = new OkHttpClient();

    private final CryptoPriceContainer cryptoPriceContainer;

    public PriceUpdateWorker(CryptoPriceContainer cryptoPriceContainer) {
        this.cryptoPriceContainer = cryptoPriceContainer;
    }

    @Override
    public void run() {
        while (true) {
            cryptoPriceContainer.getLock().lock();
            try {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getBitcoinPrice(getCallback());
            } finally {
                cryptoPriceContainer.getLock().unlock();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getBitcoinPrice(Callback callback) {
        Request request = new Request.Builder()
                .url(BITCOIN_PRICE_ENDPOINT)
                .build();
        client.newCall(request).enqueue(callback);
    }

    private Double parsePrice(String str) throws JSONException {
        JSONObject jsonObject = new JSONObject(str);
        return jsonObject.getJSONObject("bpi").getJSONObject("GBP").getDouble("rate_float");
    }

    private Callback getCallback() {
        return new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("Failed");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String str = Objects.requireNonNull(response.body()).string();
                try {
                    Double price = parsePrice(str);
                    cryptoPriceContainer.setBtcPrice(price);
                    System.out.println("Success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
