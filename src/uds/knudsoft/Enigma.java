package uds.knudsoft;

import java.util.Scanner;

public class Enigma {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        //Enigma project: Number cipher, Caesar cipher, Vigenere cipher

        //Intro
        //Encode/decode? Number/Caesar/Vigenere?

        int optionMainMenu = 0;
        do {
            System.out.print("\nTast e for encode eller d for decode: ");
            String inputMainMenu = input.nextLine().toUpperCase();
            if (inputMainMenu.startsWith("E")) {
                optionMainMenu = 0;
            } else if (inputMainMenu.startsWith("D")) {
                optionMainMenu = 1;
            } else {
                optionMainMenu = 7;
            }
            System.out.print("Tast n for Number, c for Caesar eller v for Vigenere: ");
            inputMainMenu = input.nextLine().toUpperCase();
            if (inputMainMenu.startsWith("N")) {

            } else if (inputMainMenu.startsWith("C")) {
                optionMainMenu += 2;
            } else if (inputMainMenu.startsWith("V")) {
                optionMainMenu += 4;
            } else {
                optionMainMenu = 7;
            }
            switch (optionMainMenu) {
                case 0:
                    encodeNumberCipher();
                    break;
                case 1:
                    decodeNumberCipher();
                    break;
                case 2:
                    encodeCaesarCipher();
                    break;
                case 3:
                    decodeCaesarCipher();
                    break;
                case 4:
                    encodeVigenereCipher();
                    break;
                case 5:
                    decodeVigenereCipher();
                    break;
                default:
                    System.out.println("Unrecognized input...");
            }
        }
        while (optionMainMenu < 7);
    }

    //Encode Number
    public static void encodeNumberCipher() {
        System.out.println("Encoding with Number Cipher...");
        //input a string converted to upper case - test with "abe"
        System.out.print("Indtast en streng: ");
        String inputString = input.nextLine();
        inputString = inputString.toUpperCase();

        //convert the input string to a list of numbers
        int[] intArray = stringToNumbers(inputString);

        //Could shift here for Caesar

        //convert the list of numbers to a formatted string of codes
        String outputString = convertListOfNumbersToString(intArray);

        //output encoded string
        System.out.println("Koden er: " + outputString);
    }

    //Decode Number
    public static void decodeNumberCipher() {
        System.out.println("Decoding with Number Cipher...");
        System.out.println("Teksten var: ABE");
    }

    //Encode Caesar
    public static void encodeCaesarCipher() {
        System.out.println("Encoding with Caesar Cipher...");
        System.out.println("Koden er: DEH");
    }

    //Decode Caesar
    public static void decodeCaesarCipher() {
        System.out.println("Decoding with Caesar Cipher...");
        System.out.println("Teksten var: ABE");
    }

    //Encode Vigenere
    public static void encodeVigenereCipher() {
        System.out.println("Encoding with Vigenere Cipher...");
        System.out.println("Koden er: DEH");
    }

    //Decode Vigenere
    public static void decodeVigenereCipher() {
        System.out.println("Decoding with Vigenere Cipher...");
        System.out.println("Teksten var: ABE");
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

    //method to convert a list of numbers to a string
    public static String convertListOfNumbersToString(int[] intArray) {
        int inputLength = intArray.length;
        String outputString = "{";
        for (int i = 0; i < inputLength - 1; i++) {
            outputString += intArray[i] + ", "; //todo research StringBuilder
        }
        outputString += intArray[inputLength - 1] + "}";
        return outputString;
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




