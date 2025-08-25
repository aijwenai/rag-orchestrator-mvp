package com.example.rag.vector;

import java.util.List;
import java.util.Map;

public interface VectorStore {
    record SearchHit(String id, String content, Map<String,Object> meta, double score) {}
    void upsert(String id, float[] embedding, Map<String,Object> meta, String content);
    List<SearchHit> search(float[] queryEmbedding, int topK, Map<String,Object> filter);
}
