package com.sdevm.safe_alert.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseResult {
    private boolean success;
    private String message;
}
