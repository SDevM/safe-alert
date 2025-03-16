package com.sdevm.safe_alert.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdevm.safe_alert.models.ConfirmReportRepository;

import jakarta.annotation.PostConstruct;

@Component
public class Monitor {
    @Autowired
    private ConfirmReportRepository confirmReportRepository;
    @Autowired
    private AiService aiService;

    @PostConstruct
    public void init() {
        confirmReportRepository.findAll().forEach(confirmReport -> {
            aiService.getChatResponse(
                    "Memorize this information in json format describing a criminal activity and the latitude and longitude of the event"
                            + confirmReport.toString());
        });
    }
}