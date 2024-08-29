package com.joe.test.crypto.Models;

import java.util.List;

public class Currency {

    String currencyType;
    List<DailyPrice> dailyPrices;
    float min;
    float max;
    float normalisedRange;

    public Currency(String currencyType, List<DailyPrice> dailyPrices) {
        this.currencyType = currencyType;
        this.dailyPrices = dailyPrices;

    }

    public void calculateNormalisedRange() {
        if (!dailyPrices.isEmpty()) {
            this.min =  dailyPrices.stream().map(DailyPrice::getPrice).min(Float::compareTo).get();
            this.max =  dailyPrices.stream().map(DailyPrice::getPrice).max(Float::compareTo).get();
            this.normalisedRange = ((max - min) / min);
        }
    }

    public String getCurrencyType() {
        return currencyType;
    }
    public List<DailyPrice> getDailyPrices() {
        return dailyPrices;
    }
    public float getMin() {
        return min;
    }
    public float getMax() {
        return max;
    }
    public float getNormalisedRange() {
        return normalisedRange;
    }

}
