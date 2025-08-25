package com.example.rag.vector.impl;

import com.example.rag.vector.VectorStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * MVP stub: logs calls. Replace with real pgvector SQL implementation.
 */
@Component("pgvector")
public class PgVectorStore implements VectorStore {
    private static final Logger log = LoggerFactory.getLogger(PgVectorStore.class);

    @Override
    public void upsert(String id, float[] embedding, Map<String, Object> meta, String content) {
        log.info("[pgvector:STUB] upsert id={} dim={} metaKeys={}", id, embedding.length, meta.keySet());
    }

    @Override
    public List<SearchHit> search(float[] queryEmbedding, int topK, Map<String, Object> filter) {
        log.info("[pgvector:STUB] search dim={} topK={} filter={}", queryEmbedding.length, topK, filter);
        return Collections.emptyList();
    }
}
