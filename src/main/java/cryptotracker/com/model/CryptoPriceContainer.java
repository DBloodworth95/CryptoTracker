package cryptotracker.com.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CryptoPriceContainer {

    private final Lock lock = new ReentrantLock();

    private double btcPrice;

    public Lock getLock() {
        return lock;
    }

    public void setBtcPrice(double btcPrice) {
        this.btcPrice = btcPrice;
    }

    public double getBtcPrice() {
        return btcPrice;
    }
}
