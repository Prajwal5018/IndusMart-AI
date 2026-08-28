package com.indusmart.controller;

import com.indusmart.dto.ChatRequestDTO;
import com.indusmart.dto.ChatResponseDTO;
import com.indusmart.service.ChatbotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = "*")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {

        this.chatbotService = chatbotService;

    }

    /**
     * AI Chatbot
     */
    @PostMapping("/chat")
    public ResponseEntity<ChatResponseDTO> chat(
            @RequestBody ChatRequestDTO request) {

        return ResponseEntity.ok(
                chatbotService.chat(request.getMessage())
        );
    }

}