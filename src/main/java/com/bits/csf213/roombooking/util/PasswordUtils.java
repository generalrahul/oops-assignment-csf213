package com.bits.csf213.roombooking.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class PasswordUtils {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return HexFormat.of().formatHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        String hashedPlainPassword = hashPassword(plainPassword);
        return hashedPlainPassword.equals(hashedPassword);
    }
}
