package com.demo.tracking.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackingNumberResponse {
@JsonAlias("tracking_number")    
private String trackingNumber;
@JsonAlias("created_at")
private OffsetDateTime createdAt;

}
