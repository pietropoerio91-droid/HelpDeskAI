package com.example.chatbot.service;

import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// Se vuoi usare OLLAMA locale, decommenta:
// import dev.langchain4j.model.ollama.OllamaChatModel;

@Service
public class Lc4jChatService {

    interface Assistant {
        String chat(String userMessage);
    }

    private final Assistant assistant;

    public Lc4jChatService(
            @Value("${openai.api.key:}") String openAiKey
            // @Value("${ollama.base-url:http://localhost:11434}") String ollamaUrl,
            // @Value("${ollama.model:llama3.1:8b}") String ollamaModel
    ) {
        var memory = TokenWindowChatMemory.withMaxTokens(1500);

        // ====== OPZIONE A: OpenAI se presente la chiave ======
        var model = OpenAiChatModel.builder()
                .apiKey(openAiKey)
                // .modelName("gpt-4o-mini")
                .temperature(0.2)
                .build();

        // ====== OPZIONE B: OLLAMA LOCALE ======
        // var model = OllamaChatModel.builder()
        //         .baseUrl(ollamaUrl)
        //         .modelName(ollamaModel)
        //         .temperature(0.2)
        //         .build();

        this.assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .chatMemory(memory)
                .systemMessage("Sei un assistente utile e conciso. Rispondi in italiano.")
                .build();
    }

    public String reply(String userMessage) {
        String msg = userMessage == null ? "" : userMessage.trim();
        if (msg.isEmpty()) return "Dimmi qualcosa!";
        try {
            return assistant.chat(msg);
        } catch (Exception e) {
            // Fallback amichevole se il modello non risponde (es. nessuna chiave OpenAI)
            return "(Modalità fallback) Hai scritto: "" + msg + """;
        }
    }
}
