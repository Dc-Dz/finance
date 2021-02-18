package com.qianxia.finance.utils;

import java.util.Random;

public class SaltUtil {

    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890.+-*/!@#$%^&*()_+{}[]-=?><.,\\|'\";:'".toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

}
