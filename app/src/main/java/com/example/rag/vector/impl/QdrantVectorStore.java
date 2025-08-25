package com.example.rag.vector.impl;

import com.example.rag.vector.VectorStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * MVP stub: logs calls. Replace with real Qdrant REST implementation.
 */
@Component("qdrant")
public class QdrantVectorStore implements VectorStore {
    private static final Logger log = LoggerFactory.getLogger(QdrantVectorStore.class);

    @Override
    public void upsert(String id, float[] embedding, Map<String, Object> meta, String content) {
        log.info("[qdrant:STUB] upsert id={} dim={} metaKeys={}", id, embedding.length, meta.keySet());
    }

    @Override
    public List<SearchHit> search(float[] queryEmbedding, int topK, Map<String, Object> filter) {
        log.info("[qdrant:STUB] search dim={} topK={} filter={}", queryEmbedding.length, topK, filter);
        return Collections.emptyList();
    }
}
