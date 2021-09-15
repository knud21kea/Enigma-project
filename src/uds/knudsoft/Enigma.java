package uds.knudsoft;

import java.util.Scanner;

public class Enigma {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        //Enigma project: Number cipher, Caesar cipher, Vigenere cipher

        //Intro
        System.out.println("\nWelcome to 'Enigma'.");
        System.out.println("This program encodes or decodes a string using one of 3 different encryption models: Number, Caesar and Vigenere ciphers.");
        System.out.println("The user starts by choosing encode or decode, and then which model.");
        System.out.println("The program quits with an invalid option entry.");

        /*Encode/decode? Number/Caesar/Vigenere?
        optionMainMenu stores choices as bits:
        bit 0 for encode/decode
        bit 1 for Caesar ciphers instead of Number
        bit 2 for Vigenere ciphers*/

        int optionMainMenu;
        do {
            System.out.print("\nEnter e for encode or d for decode: ");
            String inputMainMenu = input.nextLine().toUpperCase();
            if (inputMainMenu.startsWith("E")) {
                optionMainMenu = 0;
            } else if (inputMainMenu.startsWith("D")) {
                optionMainMenu = 1;
            } else {
                optionMainMenu = 7;
            }
            System.out.print("Enter n for Number, c for Caesar or v for Vigenere: ");
            inputMainMenu = input.nextLine().toUpperCase();
            if (inputMainMenu.startsWith("N")) { //Number cipher is default
            } else if (inputMainMenu.startsWith("C")) {
                optionMainMenu += 2;
            } else if (inputMainMenu.startsWith("V")) {
                optionMainMenu += 4;
            } else {
                optionMainMenu = 7;
            }
            switch (optionMainMenu) {
                case 0 -> encodeNumberCipher();
                case 1 -> decodeNumberCipher();
                case 2 -> encodeCaesarCipher();
                case 3 -> decodeCaesarCipher();
                case 4 -> encodeVigenereCipher();
                case 5 -> decodeVigenereCipher();
                default -> System.out.println("Unrecognized input...");
            }
        }
        while (optionMainMenu < 7);
    }

    //Encode Number
    public static void encodeNumberCipher() {
        System.out.println("Encoding with Number Cipher...");
        //input a string converted to upper case - test with "abe"
        System.out.print("Input a string: ");
        String inputString = input.nextLine();
        inputString = inputString.toUpperCase();

        //convert the input string to a list of numbers
        int[] intArray = stringToNumbers(inputString);

        //convert the list of numbers to a formatted string of codes
        String outputString = convertListOfNumbersToString(intArray);

        //output encoded string
        System.out.println("Koden er: " + outputString);
    }

    //Decode Number
    public static void decodeNumberCipher() {
        System.out.println("Decoding with Number Cipher...");
        System.out.println("Input a list in the form {x, y, ... z}");
        System.out.print("Or paste a previously coded list: ");
        String inputString = input.nextLine();

        //Find how many code numbers in the list
        final int elements = getNumberOfElements(inputString);

        //Extract the code numbers with custom algorithm
        int[] codeString = new int[elements];
        String[] subString = sliceStringIntoCodeStrings(inputString, elements);

        //convert the code number strings to code numbers and output the decoded letters.
        System.out.print("The text was: ");
        for (int i = 0; i < elements; i++) {
            codeString[i] = Integer.parseInt(subString[i]);
            subString[i] = convertNumberToLetter(codeString[i]);
            System.out.print(subString[i]);
        }
    }

    //Encode Caesar
    //Space is encoded as a normal character, this improves security as the encoded text may have spaces that are not between words
    public static void encodeCaesarCipher() {
        System.out.println("Encoding with Caesar Cipher...");
        //input a text and a value used to shift the letters
        System.out.print("Input a text to encode: ");
        String inputString = input.nextLine();
        inputString = inputString.toUpperCase();
        System.out.print("Input a code number: ");
        int shiftCaesar = input.nextInt();
        input.nextLine(); //Flush cr from the nextInt
        shiftCaesar = (Math.abs(shiftCaesar) % 30); //only shift by 0-30

        //convert the input string to a list of numbers
        int[] intArray = stringToNumbers(inputString);

        //Shift by amount shiftCaesar
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (intArray[i] + shiftCaesar) % 30;
        }

        //convert the list of numbers to an encoded string
        String outputString = convertListOfNumbersToText(intArray);

        //output encoded string
        System.out.println("The encoded text is: " + outputString);
    }

    //Decode Caesar
    //If there were spaces in the original text they will be preserved
    public static void decodeCaesarCipher() {
        System.out.println("Decoding with Caesar Cipher...");
        //input a text and a value used to shift the letters
        System.out.print("Input a text to decode: ");
        String inputString = input.nextLine();
        inputString = inputString.toUpperCase();
        System.out.print("Input the code number: ");
        int shiftCaesar = input.nextInt();
        input.nextLine(); //Flush cr from the nextInt
        shiftCaesar = (Math.abs(shiftCaesar) % 30); //only shift by 0-30

        //convert the input string to a list of numbers
        int[] intArray = stringToNumbers(inputString);

        //Shift by amount -shiftCaesar
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (intArray[i] - shiftCaesar + 30) % 30;
        }

        //convert the list of numbers to an encoded string
        String outputString = convertListOfNumbersToText(intArray);

        //output encoded string
        System.out.println("The original text was: " + outputString);
    }

    //Encode Vigenere
    public static void encodeVigenereCipher() {
        System.out.println("Encoding with Vigenere Cipher...");
        commonVigenereCipher(1);
    }

    //Decode Vigenere
    public static void decodeVigenereCipher() {
        System.out.println("Decoding with Vigenere Cipher...");
        commonVigenereCipher(-1);
    }

    //method common to Vigenere encoding/decoding controlled by a switch
    public static void commonVigenereCipher(int sign) {
        //input a text and a codeword used to shift the letters converted to uppercase
        System.out.print("Input a text to encode: ");
        String inputString = input.nextLine().toUpperCase();
        System.out.print("Input a code word: ");
        StringBuilder shiftVigenere = new StringBuilder(input.nextLine().toUpperCase());

        //make the codeword at least as long as inputString by repeating as necessary
        while (shiftVigenere.length() < inputString.length()) {
            shiftVigenere.append(shiftVigenere);
        }

        //convert the input string to a list of numbers
        int[] intArray = stringToNumbers(inputString);

        //convert the code word to a list of numbers
        int[] shiftArray = stringToNumbers(shiftVigenere.toString());

        //Shift by amount -shiftVigenere
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (intArray[i] + sign * shiftArray[i] + 30) % 30;
        }

        //convert the list of numbers to an encoded string
        String outputString = convertListOfNumbersToText(intArray);

        //output encoded string
        System.out.println("The encoded text is: " + outputString);
    }

    //method to convert a string to a list of numbers
    public static int[] stringToNumbers(String inputString) {
        int inputLength = inputString.length();
        int[] intArray = new int[inputLength];
        String letter;
        for (int i = 0; i < inputLength; i++) {
            letter = inputString.substring(i, i + 1);
            int index = letterToInt(letter);
            intArray[i] = index;
        }
        return intArray;
    }

    //method to convert 1 letter to a number
    public static int letterToInt(String letter) {
        final String CHARACTERS = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        return CHARACTERS.indexOf(letter);
    }

    //method to convert a list of numbers to a text
    public static String convertListOfNumbersToText(int[] intArray) {
        final String CHARACTERS = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        StringBuilder outputString = new StringBuilder();
        for (int j : intArray) {
            outputString.append(CHARACTERS.charAt(j));
        }
        return outputString.toString();
    }

    //method to convert a list of numbers to a string
    public static String convertListOfNumbersToString(int[] intArray) {
        int inputLength = intArray.length;
        StringBuilder outputString = new StringBuilder("{");
        for (int i = 0; i < inputLength - 1; i++) {
            outputString.append(intArray[i]).append(", ");
        }
        outputString.append(intArray[inputLength - 1]).append("}");
        return outputString.toString();
    }

    //method to convert 1 number to a letter
    public static String convertNumberToLetter(int number) {
        final String CHARACTERS = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        return CHARACTERS.substring(number, number + 1);
    }

    //find how many elements = number of commas +1
    public static int getNumberOfElements(String inputString) {
        int firstComma = inputString.indexOf(",");
        int nextComma = 0;
        int lastComma = inputString.lastIndexOf(",", firstComma + 1);
        int count = -1;
        while (nextComma != lastComma) {
            count++;
            nextComma = inputString.indexOf(",", firstComma + 1);
            firstComma = nextComma;
        }
        return count + 1;
    }

    //extract code strings from input string
    public static String[] sliceStringIntoCodeStrings(String inputString, int codes) {
        String[] codeString = new String[codes];
        for (int i = 0; i < codes; i++) {
            codeString[i] = "";
        }
        int code = 0;
        int position = 0;
        char ch = '{';
        while (ch != '}') { //stop if end of list
            ch = inputString.charAt(position);
            if (Character.isDigit(ch)) {
                while (Character.isDigit(ch)) {
                    codeString[code] += ch;
                    position++;
                    ch = inputString.charAt(position);
                }
                code++; //move to next code string
            }
            position++; //point to next character
        }
        return codeString;
    }
}




