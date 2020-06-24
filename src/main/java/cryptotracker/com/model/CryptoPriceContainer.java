package cryptotracker.com.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CryptoPriceContainer {

    private final Lock lock = new ReentrantLock();

    private double btcPrice;

    private String btcTimestamp;

    public Lock getLock() {
        return lock;
    }

    public void setBtcPrice(double btcPrice, String btcTimestamp) {
        this.btcPrice = btcPrice;
        this.btcTimestamp = btcTimestamp;
    }

    public double getBtcPrice() {
        return btcPrice;
    }

    public void setBtcPrice(double btcPrice) {
        this.btcPrice = btcPrice;
    }

    public String getBtcTimestamp() {
        return btcTimestamp;
    }

    public void setBtcTimestamp(String btcTimestamp) {
        this.btcTimestamp = btcTimestamp;
    }
}
