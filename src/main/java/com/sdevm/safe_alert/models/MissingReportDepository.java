package com.sdevm.safe_alert.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MissingReportDepository extends MongoRepository<MissingReport, String> {
}