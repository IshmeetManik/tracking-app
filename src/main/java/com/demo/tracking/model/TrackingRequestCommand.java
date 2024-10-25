package com.demo.tracking.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class TrackingRequestCommand {
    private String originCountryId;
    private String destinationCountryId;
    private double weight;
    private String customerId;
    private String customerName;
    private String customerSlug;
    private String createdAt;

}
