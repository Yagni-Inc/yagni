package com.yagni.model;

//Class name decleration

public class UserEncryption {

    // method within class delceration
    // Method takes string value and returns cypered text

    public static String userEncryption(String value) {

        // converts the string to a character array to alter each ASCII value seperately
        char[] charVals = value.toCharArray();

        // Variable declertion
        String hashedVal = "";

        // For loop to parse through string values and alter their ASCII value by set
        // amount
        for (char c1 : charVals) {

            // adds a value of 3 to the current ascii or charatver value being altered in
            // the string array
            c1 = (char) (c1 + 3);

            // resets hashedVal to next element in the character array
            hashedVal += c1;
        }

        // returns hashedVal to be used where method will be called

        return hashedVal;
    }
}
