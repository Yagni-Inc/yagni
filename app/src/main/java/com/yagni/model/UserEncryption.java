 package com.yagni.model;

public class UserEncryption {

    public static String userEncryption(String value) {
        // converts the string to a character array to alter each ascii value seperately
        char[] charVals = value.toCharArray();
        String hashedVal = "";
        for (char c1: charVals) {
            // adds a value of 3 to the current ascii or charater value being altered in the string array
            c1 = (char) (c1 + 3);
            hashedVal += c1;
        }
        return hashedVal;
    }
}
