# Chat Bot Java (Spring Boot + Thymeleaf + LangChain4j)

## Requisiti
- JDK 21 (consigliato) o 17
- Maven 3.9+

## Avvio rapido
```bash
mvn spring-boot:run
```
Poi apri: http://localhost:8080/chat

### Usare OpenAI
Esporta la chiave prima di avviare:
```bash
export OPENAI_API_KEY=sk-...  # macOS/Linux
# su Windows PowerShell:
# $env:OPENAI_API_KEY = "sk-..."
```

### Usare un modello locale con Ollama (opzionale)
1. Installa Ollama e avvia `ollama serve`
2. Scarica un modello: `ollama pull llama3.1:8b`
3. Vedi commenti in `Lc4jChatService` per abilitare Ollama.
