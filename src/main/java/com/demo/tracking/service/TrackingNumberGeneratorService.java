package com.demo.tracking.service;

import com.demo.tracking.model.TrackingRequestCommand;

public interface TrackingNumberGeneratorService {

    String generateTrackingNumber(TrackingRequestCommand trackingRequestCommand);

}
