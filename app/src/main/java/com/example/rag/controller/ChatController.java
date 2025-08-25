package com.example.rag.controller;

import com.example.rag.service.RagService;
import com.example.rag.vector.VectorStore;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final RagService rag;

    public ChatController(RagService rag) {
        this.rag = rag;
    }

    @PostMapping(path="/ingest", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String ingest(@RequestBody Map<String,Object> body) {
        String id = (String) body.getOrDefault("id", "doc-"+System.currentTimeMillis());
        String content = (String) body.get("content");
        Map<String,Object> meta = (Map<String,Object>) body.getOrDefault("meta", Map.of());
        rag.ingest(id, content, meta);
        return "OK: " + id;
    }

    @GetMapping("/search")
    public List<VectorStore.SearchHit> search(@RequestParam String q,
                                              @RequestParam(defaultValue = "3") int k) {
        return rag.retrieve(q, k);
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String q) {
        return rag.chat(q);
    }
}
