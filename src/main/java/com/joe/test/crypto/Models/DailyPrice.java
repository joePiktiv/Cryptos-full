package com.joe.test.crypto.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyPrice {

    private LocalDateTime timestamp;
    private float price;

    public DailyPrice(LocalDateTime timestamp, float price) {
        this.timestamp = timestamp;
        this.price = price;

    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public float getPrice() {
        return price;
    }

    public boolean isSameDate(LocalDate selectedDate)
    {
//        System.out.println(timestamp.toLocalDate() + " "+selectedDate);
//        if (timestamp.toLocalDate().equals(selectedDate))
//        {
//            System.out.println(timestamp+" "+selectedDate);
//            return true;
//        } else return false;

        return timestamp.toLocalDate().equals(selectedDate);
    }

}

