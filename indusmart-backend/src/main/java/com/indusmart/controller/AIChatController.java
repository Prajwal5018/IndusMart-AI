package com.indusmart.controller;

import com.indusmart.dto.AIChatRequest;
import com.indusmart.dto.AIChatResponse;
import com.indusmart.service.AIChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIChatController {

    private final AIChatService aiChatService;

    public AIChatController(AIChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    /**
     * Industrial AI Chat
     */
    @PostMapping("/chat")
    public ResponseEntity<AIChatResponse> chat(
            @RequestBody AIChatRequest request) {

        AIChatResponse response =
                aiChatService.chat(request.getMessage());

        return ResponseEntity.ok(response);
    }

}