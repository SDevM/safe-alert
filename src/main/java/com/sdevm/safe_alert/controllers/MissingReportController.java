package com.sdevm.safe_alert.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdevm.safe_alert.models.MissingReport;
import com.sdevm.safe_alert.models.MissingReportDepository;
import com.sdevm.safe_alert.models.ResponseResult;
import com.sdevm.safe_alert.services.AwsServiceImplementation;

@RestController
public class MissingReportController {
    @Autowired
    private MissingReportDepository missingReportRepo;
    @Autowired
    private AwsServiceImplementation awsService;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.cloudfront.domain}")
    private String cloudfrontDomain;

    @PostMapping("/missing")
    public ResponseResult missingReport(@RequestParam("model") String model,
            @RequestParam(value = "file", required = true) MultipartFile file)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MissingReport missingReport = mapper.readValue(model, MissingReport.class);
        String hexTimestamp = Long.toHexString(System.currentTimeMillis());
        awsService.uploadFile(bucket, file.getOriginalFilename(), file.getSize(), file.getContentType(),
                file.getInputStream());
        missingReport.setImage(hexTimestamp + missingReport.getId());
        try {
            missingReportRepo.save(missingReport);
            return new ResponseResult(true, "Report submitted successfully");
        } catch (Exception e) {
            return new ResponseResult(false, "Failed to submit report");
        }
    }
}
