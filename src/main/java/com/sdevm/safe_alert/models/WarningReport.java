package com.sdevm.safe_alert.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Document("WarningReport")
@AllArgsConstructor
@ToString
public class WarningReport {
    @Id
    private String id;
    private CriminalActivityType type;
    private String description;
    private LatLong location;
}
