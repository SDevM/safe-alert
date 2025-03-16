package com.sdevm.safe_alert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdevm.safe_alert.models.ConfirmReport;
import com.sdevm.safe_alert.models.ConfirmReportRepository;
import com.sdevm.safe_alert.models.ResponseResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ActivityConfirmController {
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private ConfirmReportRepository confirmReportRepo;

    @PostMapping("/activity-confirm")
    public ResponseResult activityConfirm(@RequestBody ConfirmReport confirmReport) {
        ConfirmReport response = null;
        try {
            response = confirmReportRepo.save(confirmReport);
        } catch (Exception e) {
            return new ResponseResult(false, "Failed to submit report");
        }
        template.convertAndSend("/topic/confirmation", response);
        return new ResponseResult(true, "Report submitted successfully");
    }
}
