package com.sdevm.safe_alert.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LatLong {
    private final double latitude;
    private final double longitude;
}
