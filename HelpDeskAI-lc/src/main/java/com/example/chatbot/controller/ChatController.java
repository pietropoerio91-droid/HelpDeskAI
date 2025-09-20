package com.example.chatbot.controller;

import com.example.chatbot.dto.ChatRequest;
import com.example.chatbot.dto.ChatResponse;
import com.example.chatbot.service.Lc4jChatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class ChatController {

    private final Lc4jChatService chat;

    public ChatController(Lc4jChatService chat) { this.chat = chat; }

    @GetMapping({"/", "/chat"})
    public String chatPage(Model model){
        return "chat"; // templates/chat.html
    }

    @PostMapping("/api/chat")
    @ResponseBody
    public ChatResponse chatApi(@RequestBody ChatRequest req){
        return new ChatResponse(chat.reply(req.getMessage()));
    }
}
