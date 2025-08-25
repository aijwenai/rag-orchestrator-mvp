package com.example.rag.vector.impl;

import com.example.rag.vector.VectorStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory stub for local testing.
 */
@Component("stub")
public class StubVectorStore implements VectorStore {

    private final Map<String, String> contents = new HashMap<>();
    private final Map<String, Map<String,Object>> metas = new HashMap<>();

    @Override
    public void upsert(String id, float[] embedding, Map<String, Object> meta, String content) {
        contents.put(id, content);
        metas.put(id, meta);
    }

    @Override
    public List<SearchHit> search(float[] queryEmbedding, int topK, Map<String, Object> filter) {
        List<SearchHit> out = new ArrayList<>();
        int i = 0;
        for (var e : contents.entrySet()) {
            if (i++ >= topK) break;
            out.add(new SearchHit(e.getKey(), e.getValue(), metas.getOrDefault(e.getKey(), Map.of()), 0.0));
        }
        return out;
    }
}
