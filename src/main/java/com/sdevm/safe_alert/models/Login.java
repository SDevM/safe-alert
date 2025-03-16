package com.sdevm.safe_alert.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {
    private String identifier;
    private String password;
}
