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
        System.out.println("Koden er: {1, 2, 5}");
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

}



