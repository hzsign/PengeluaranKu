package com.app.pengeluaranku.utils;

import java.text.DecimalFormat;

public class FunctionHelper {

    public static String rupiahFormat(int price) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return "Rp " + formatter.format(price).replaceAll(",", ".");
    }
}