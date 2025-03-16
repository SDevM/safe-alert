package com.sdevm.safe_alert.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmReportRepository extends MongoRepository<ConfirmReport, String> {
}
