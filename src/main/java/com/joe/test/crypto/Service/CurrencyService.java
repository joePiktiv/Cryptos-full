package com.joe.test.crypto.Service;

import com.joe.test.crypto.Models.Currency;
import com.joe.test.crypto.Repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CurrencyService {

    private final CurrencyRepo repo;

    @Autowired
    public CurrencyService(CurrencyRepo repo) {
        this.repo = repo;
    }


    public List<String> getSymbols ()
    {
        return repo.getSymbols();
    }


    public List<Currency> getCurrencies ()
    {
        return repo.getCurrencies();
    }

    public Currency getHighestNormalisedByDate (String dateStr)
    {
        var date = LocalDate.parse(dateStr);

        System.out.println(date);

        var currencyCollections = repo.getCurrencies()
                .stream()
                .map(currency -> new Currency (currency.getCurrencyType(), currency.getDailyPrices().stream().filter(dp -> dp.isSameDate(date)).toList()))
                .toList();

        currencyCollections.forEach(Currency::calculateNormalisedRange);

        Optional<Currency> highestNormalised = currencyCollections.stream().max(Comparator.comparing(Currency::getNormalisedRange));

        return highestNormalised.get();
    }

}
