package com.fhrugran.starfield.utils;


/**
 * Created by Fhrugran
 */

public final class Utils {
    public static Utils instance;

    private Utils() {
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }
}
