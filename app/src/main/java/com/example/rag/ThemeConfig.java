package com.example.rag;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThemeConfig {
    @Value("${app.theme:infra}")
    public String theme;

    @Value("${app.vector.backend:stub}")
    public String vectorBackend;
}
