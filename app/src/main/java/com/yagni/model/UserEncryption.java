package com.yagni.model;

public class UserEncryption {

    private static String hashedVal; // String declaration

    private static char[] charVals; // char array declaration

    // Method takes string value and returns encrypted text
    public static String userEncryption(String value) {

        // Converts the string to a character array to alter each ASCII value separately
        charVals = value.toCharArray();

        // initialization of string
        hashedVal = "";

        // for loop to parse through string values and alter their ASCII value by set
        // amount
        for (char c1 : charVals) {

            // adds a value of 3 to the current ascii
            c1 = (char) (c1 + 3);

            // resets hashedVal to next element in the character array
            hashedVal += c1;
        }
        // returns hashedVal to be used where method will be called
        return hashedVal;
    }
}
