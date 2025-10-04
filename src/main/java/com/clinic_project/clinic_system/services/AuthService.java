package com.clinic_project.clinic_system.services;


import com.clinic_project.clinic_system.models.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AuthService {

    // In a real application, this would interact with a database

    public boolean registerUser(User user) {
        try {
            // Hash the password before storing
            String hashedPassword = hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            // TODO: Save user to database
            // For now, return true to simulate success
            System.out.println("Registering user: " + user.getUsername());

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendPasswordResetEmail(String email) {
        try {
            // TODO: Generate reset token
            // TODO: Send email with reset link

            System.out.println("Sending password reset email to: " + email);

            // Simulate email sending
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User authenticateUser(String username, String password) {
        try {
            // Hash the provided password
            String hashedPassword = hashPassword(password);

            // TODO: Query database for user with matching username and password
            // For now, return a mock user
            User user = new User();
            user.setId(1);
            user.setUsername(username);
            user.setFullName("Admin User");
            user.setEmail("admin@example.com");
            user.setRole("ADMIN");

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}