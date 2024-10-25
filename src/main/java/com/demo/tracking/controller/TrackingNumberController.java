package com.demo.tracking.controller;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.tracking.model.TrackingNumberResponse;
import com.demo.tracking.model.TrackingRequestCommand;
import com.demo.tracking.service.TrackingNumberGeneratorService;
import com.demo.tracking.util.ControllerUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TrackingNumberController {
    @Autowired
    private TrackingNumberGeneratorService trackingNumberGeneratorService;


    @GetMapping("/next-tracking-number")
    public ResponseEntity<TrackingNumberResponse> getNextTrackingNumber(@RequestParam(name = "origin_country_id",required = false,defaultValue = "") String originCountryId,
        @RequestParam(name = "destination_country_id",required = false,defaultValue = "") String destinationCountryId,
        @RequestParam(name ="weight",required = false,defaultValue = "0.00") double weight,
        @RequestParam(name = "created_at",required = false,defaultValue = "") String createdAt,
        @RequestParam(name = "customer_id",required = false,defaultValue = "") String customerId,
        @RequestParam(name = "customer_name",required = false,defaultValue = "") String customerName,
        @RequestParam(name = "customer_slug",required = false,defaultValue = "") String customerSlug){
            log.info("Request received for tracking number generation");

            TrackingRequestCommand requestCommand =
             ControllerUtil.getTrackingRequestCommand(originCountryId, destinationCountryId, weight, customerId, customerName, customerSlug);

        return ResponseEntity.ok(TrackingNumberResponse.builder()
                .trackingNumber(trackingNumberGeneratorService.generateTrackingNumber(requestCommand))
                .createdAt(OffsetDateTime.now())
                .build());

    }

}
