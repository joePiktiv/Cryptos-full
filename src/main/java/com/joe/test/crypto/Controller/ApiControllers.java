package com.joe.test.crypto.Controller;

import com.joe.test.crypto.Models.Currency;
import com.joe.test.crypto.Service.CurrencyService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiControllers {

    private CurrencyService currencyService;

    public ApiControllers(CurrencyService currencyService) {this.currencyService = currencyService;}

    @GetMapping(value ="/symbols")
    public List<String> getAllSymbols ()
    {
        return currencyService.getSymbols();
    }

    @GetMapping(value = "/currencies")
    public List<Currency> getCurrencies()
    {
        return currencyService.getCurrencies();
    }

    @GetMapping(value = "/highestnormalisedbydate/{date}")
    public Currency getHighestNormalisedByDate (@PathVariable String date)
    {
//        System.out.println("request arrived "+date);
        return currencyService.getHighestNormalisedByDate(date);
    }

}
