package com.sdevm.safe_alert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import com.sdevm.safe_alert.models.ConfirmReportRepository;

@SpringBootApplication
public class SafeAlertApplication {
	@Autowired
	ConfirmReportRepository confirmReportRepository;

	public static void main(String[] args) {
		SpringApplication.run(SafeAlertApplication.class, args);
	}
}
