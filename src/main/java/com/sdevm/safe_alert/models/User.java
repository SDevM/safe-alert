package com.sdevm.safe_alert.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document("User")
public class User {
    @Id
    private String id;
    private String identifier;
    private String password;
    private UserType userType;
}
