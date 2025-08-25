package com.example.rag.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

/**
 * MVP placeholder: deterministic pseudo-embedding from text hash.
 * Replace with real embedding (cloud/local).
 */
@Service
public class EmbeddingService {

    public float[] embed(String text, int dim) {
        try {
            var md = MessageDigest.getInstance("SHA-256");
            byte[] h = md.digest(text.getBytes(StandardCharsets.UTF_8));
            float[] v = new float[dim];
            Random r = new Random(0);
            for (int i = 0; i < dim; i++) {
                v[i] = ((h[i % h.length] & 0xff) / 255.0f) - 0.5f + (float)(r.nextGaussian()*0.01);
            }
            return v;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
