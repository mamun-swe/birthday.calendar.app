package birthday.calendar.app.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Helpers {
    // Generate unique username
    public static String generateUniqueUsername(String firstName, String lastName) {
        long currentTimeInMills = System.currentTimeMillis();
        String sanitizedFirstName = firstName.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
        String sanitizedLastName = lastName.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();

        return sanitizedFirstName + "-" + sanitizedLastName + "-" + currentTimeInMills;
    }

    // Generate encrypted hash password
    public static String generateEncryptedPassword(String plainPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(plainPassword);
    }

    // Verify plain password with hash password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
