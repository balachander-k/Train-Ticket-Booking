package com.strain.utility;
public class PasswordEncryption {

    // Method to encrypt the text (including alphanumeric and special characters)
    public String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // Shift the ASCII value of the character
            char ch = (char) (c + shift);
            encrypted.append(ch);
        }
        System.out.println("Encrypted pass: "+encrypted.toString());
        
        return encrypted.toString();
    }

    // Method to decrypt the text (including alphanumeric and special characters)
    public String decrypt(String text, int shift) {
        StringBuilder decrypted = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // Reverse the ASCII value shift
            char ch = (char) (c - shift);
            decrypted.append(ch);
        }
        
        return decrypted.toString();
    }
}
