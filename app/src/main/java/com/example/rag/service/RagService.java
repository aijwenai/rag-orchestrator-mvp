package com.example.rag.service;

import com.example.rag.ThemeConfig;
import com.example.rag.vector.VectorStore;
import com.example.rag.vector.VectorStore.SearchHit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RagService {

    private final VectorStore store;
    private final EmbeddingService embedding;
    private final ThemeConfig config;

    public RagService(@Qualifier("stub") VectorStore store, EmbeddingService embedding, ThemeConfig config) {
        this.store = store;
        this.embedding = embedding;
        this.config = config;
    }

    public void ingest(String id, String content, Map<String,Object> meta) {
        float[] emb = embedding.embed(content, 256);
        store.upsert(id, emb, meta, content);
    }

    public List<SearchHit> retrieve(String query, int k) {
        float[] emb = embedding.embed(query, 256);
        Map<String,Object> filter = new HashMap<>();
        filter.put("theme", config.theme);
        return store.search(emb, k, filter);
    }

    public String chat(String user) {
        var hits = retrieve(user, 3);
        StringBuilder ctx = new StringBuilder();
        for (var h : hits) {
            ctx.append("- ").append(h.id()).append(": ").append(h.content()).append("\n");
        }
        return "[theme=" + config.theme + "] You asked: " + user + "\n" +
               "Here are some relevant notes (MVP stub):\n" + ctx +
               "\n(Replace this with a real LLM call.)";
    }
}
