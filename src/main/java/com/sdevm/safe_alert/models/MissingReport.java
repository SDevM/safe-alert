package com.sdevm.safe_alert.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document("MissingReport")
public class MissingReport {
    @Id
    private String id;
    private String fullname;
    private String aliases;
    private int age;
    private String physicalDescription;
    private String distinguishingFeatures;
    private String clothingDescription;
    private String vehicleDescription;
    private LatLong lastKnownLocation;
    private String lastSeenDate;
    private String image;
}
