package com.demo.tracking.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.demo.tracking.model.TrackingRequestCommand;
import com.demo.tracking.service.NumberGeneratorService;

@Component
public class NumberGeneratorImpl implements NumberGeneratorService {

    // Length of the tracking number
    private static final int TRACKING_NUMBER_LENGTH = 16;
    private static final String TRACKING_REGEX = "^[A-Z0-9]{1,16}$";

    @Override
    public String generateNumber(TrackingRequestCommand trackingRequestCommand) {
        
   try {
    String input =  trackingRequestCommand.getOriginCountryId() +
    trackingRequestCommand.getDestinationCountryId() +
     trackingRequestCommand.getWeight() + trackingRequestCommand.getCustomerId() 
     + trackingRequestCommand.getCustomerName() + trackingRequestCommand.getCustomerSlug()+trackingRequestCommand.getCreatedAt()+ OffsetDateTime.now();
            // Combine all input fields into a single string
             // Hash the combined input
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            String base64EncodedHash = Base64.getUrlEncoder().encodeToString(hash);

            // Take a substring of the hashed value for the tracking number length
            String trackingNumber = base64EncodedHash.replaceAll("[^A-Z0-9]", "").substring(0, TRACKING_NUMBER_LENGTH);

            // Check if the generated tracking number matches the regex
            if (trackingNumber.matches(TRACKING_REGEX)) {
                return trackingNumber;
            } else {
                // Generate a fallback random tracking number if the hash doesn't match
                return generateRandomTrackingNumber();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating tracking number", e);
        }
    }

    private static String generateRandomTrackingNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(TRACKING_NUMBER_LENGTH);

        for (int i = 0; i < TRACKING_NUMBER_LENGTH; i++) {
            // Randomly select an alphanumeric character
            if (random.nextBoolean()) {
                // Choose a digit (0-9)
                sb.append(random.nextInt(10));
            } else {
                // Choose a letter (A-Z)
                sb.append((char) ('A' + random.nextInt(26)));
            }
        }
        return sb.toString();
    }


}
