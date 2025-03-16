package com.sdevm.safe_alert.services;

import com.theokanning.openai.service.OpenAiService;

import lombok.Value;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AiService {
    private final OpenAiService openAiService;
    @org.springframework.beans.factory.annotation.Value("${openai.api-key}")
    private String apiKey;

    public AiService() { 
        this.openAiService = new OpenAiService(apiKey);
    }

    public String getChatResponse(String prompt) {
        ChatMessage userMessage = new ChatMessage("user", prompt);

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-4") // Specify the model (e.g., "gpt-4" or "gpt-3.5-turbo")
                .messages(Collections.singletonList(userMessage))
                .build();

        return openAiService.createChatCompletion(request)
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }
}