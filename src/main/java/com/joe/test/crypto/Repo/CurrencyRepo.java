package com.joe.test.crypto.Repo;

import com.joe.test.crypto.Models.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

@Repository
public class CurrencyRepo {

    private final List<Currency> currencies;
    private final List<String> symbols;

    public CurrencyRepo() {

        symbols = Arrays.stream(CurrencyType.values())
                .map(Enum::toString)
                .toList();

        currencies = symbols.stream().map(s -> new Currency(s, createDailyPrices(s))).toList();
    }

    private List<DailyPrice> createDailyPrices(String sym) {
        try {
            var dir = System.getProperty("user.dir");

            List<String[]> dailyPricesInStrings = CSVreader.readFile(CSVreader.symbolToFilename(dir, sym));

            List<DailyPrice> dailyPrices = dailyPricesInStrings
                    .stream()
                    .skip(1)
                    .map(dp -> new DailyPrice(toLocalDateTime(parseLong(dp[0])), Float.parseFloat(dp[2])) )
                    .toList();

            return dailyPrices;

        } catch (Exception e) {

            System.err.println("Error reading daily prices for " + sym + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private LocalDateTime toLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);
    }


    public List<String> getSymbols ()
    {
        return symbols;
    }

    public List<Currency> getCurrencies()
    {
        return currencies;
    }

}
