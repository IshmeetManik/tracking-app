package com.demo.tracking.util;

import com.demo.tracking.model.TrackingRequestCommand;

public class ControllerUtil {

    public static TrackingRequestCommand getTrackingRequestCommand(String originCountryId, String destinationCountryId, double weight, String customerId, String customerName, String customerSlug) {
        return TrackingRequestCommand.builder()
                .originCountryId(originCountryId)
                .destinationCountryId(destinationCountryId)
                .weight(weight)
                .customerId(customerId)
                .customerName(customerName)
                .customerSlug(customerSlug)
                .build();
    }

}
