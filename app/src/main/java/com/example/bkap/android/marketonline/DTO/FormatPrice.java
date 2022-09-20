package com.example.bkap.android.marketonline.DTO;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatPrice {
    public static String fmtCurrency(double amount, Locale locale) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        return fmt.format(amount);
    }
}
