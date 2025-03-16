package com.sdevm.safe_alert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sdevm.safe_alert.models.LatLong;
import com.sdevm.safe_alert.services.AiService;

@Controller
public class PromptController {
    @Autowired
    private AiService aiService;

    @PostMapping("/prompt")
    public String prompt(@RequestBody LatLong latLong) {
        return aiService.getChatResponse(
                "Based the information you have memorized recently as criminal activity and the latitude and longitude of the event recently, what to you think is the level of risk for being at the following latitude and longitude "
                        + latLong + "?");
    }
}
