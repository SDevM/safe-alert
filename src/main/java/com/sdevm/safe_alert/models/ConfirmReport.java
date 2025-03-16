package com.sdevm.safe_alert.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Document("ConfirmReport")
@AllArgsConstructor
@ToString
public class ConfirmReport {
    @Id
    private String id;
    private CriminalActivityType type;
    private String description;
    private LatLong location;
}
