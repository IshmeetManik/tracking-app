package com.demo.tracking.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.demo.tracking.model.TrackingRequestCommand;
import com.demo.tracking.service.NumberGeneratorService;
import com.demo.tracking.service.TrackingNumberGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrackingNumberGeneratorImpl implements TrackingNumberGeneratorService{

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private NumberGeneratorService numberGeneratorService;

    private static final String TRACKING_KEY_PREFIX = "tracking:";

    @Override
    public String generateTrackingNumber(TrackingRequestCommand trackingRequestCommand) {
        String trackingNumber ;
        log.info("Generating tracking number");
        do {
            log.info("Tracking number already exists, generating new tracking number");
            trackingNumber = numberGeneratorService.generateNumber(trackingRequestCommand);
            log.info("Tracking number generated: {}", trackingNumber);
        }while (isTrackingNumberExists(trackingNumber));
        storeTrackingNumber(trackingNumber);
        return trackingNumber;

        
    }


    private boolean isTrackingNumberExists(String trackingNumber) {
        return redisTemplate.hasKey(TRACKING_KEY_PREFIX + trackingNumber);
    }

    private void storeTrackingNumber(String trackingNumber) {
        redisTemplate.opsForValue().set(TRACKING_KEY_PREFIX + trackingNumber, "1", 1, TimeUnit.DAYS);
    }

}
