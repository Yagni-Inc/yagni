package com.yagni.model;

public class UserEncryption {

    private static String hashedVal;

    // Method takes string value and returns encrypted text
    public static String userEncryption(String value) {

        // Converts the string to a character array to alter each ASCII value separately
        char[] charVals = value.toCharArray();

        // Variable declaration
        hashedVal = "";

        // for loop to parse through string values and alter their ASCII value by set amount
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
