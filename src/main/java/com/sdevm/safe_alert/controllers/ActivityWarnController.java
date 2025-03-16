package com.sdevm.safe_alert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdevm.safe_alert.models.ResponseResult;
import com.sdevm.safe_alert.models.WarningReport;
import com.sdevm.safe_alert.models.WarningReportRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ActivityWarnController {
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private WarningReportRepository warningReportRepo;

    @PostMapping("/activity-warn")
    public ResponseResult activityWarn(@RequestBody WarningReport warningReport) {
        WarningReport response = null;
        try {
            response = warningReportRepo.save(warningReport);
        } catch (Exception e) {
            return new ResponseResult(false, "Failed to submit report");
        }
        template.convertAndSend("/topic/warning", response);
        return new ResponseResult(true, "Report submitted successfully");
    }
}
