# RAG Orchestrator (Java, pluggable pgvector/Qdrant) — MVP

This is a **minimal, end-to-end scaffold** for a multi-theme RAG orchestrator with pluggable vector backends (**pgvector** or **Qdrant**), guardrails, observability and an audit trail.

> Goal: give you a complete repo to iterate file-by-file for Cambridge/GTV/Job portfolio.

## Features (MVP)
- Spring Boot (Java 17) REST API: `/api/chat`, `/api/search`
- Pluggable `VectorStore` interface: `pgvector` or `qdrant` (stubs provided)
- Multi-theme datasets (`/data/infra|policy|fun`) via config
- Guardrails config placeholders (`/guardrails/*.yml`)
- Observability: Actuator + Prometheus endpoint (`/actuator/prometheus`)
- Infra docker-compose with Postgres+pgvector, Qdrant, Prometheus, Grafana

> **Note**: Vector backends are **stubbed** in the MVP so the app compiles without external services.
> You will replace the stubs with real implementations (SQL / REST) during iteration.

## Quickstart (MVP compile & run)
```bash
# 1) Build & run the app (no DB required for MVP stubs)
cd app
./mvnw spring-boot:run

# The API will be at http://localhost:8080
# Swagger UI (once you add springdoc): http://localhost:8080/swagger-ui.html

# 2) (Optional) Spin up infra when you start real vector impls
cd ..
docker compose -f infra/docker-compose.yml up -d
```

## Switch themes
Edit `app/src/main/resources/application.yml`:
```yaml
app:
  theme: infra   # or policy | fun
  vector:
    backend: stub  # change to pgvector | qdrant when you implement them
```

## Project structure
```
rag-orchestrator/
 ├─ app/                     # Spring Boot app
 │   ├─ src/main/java/com/example/rag/...
 │   └─ src/main/resources/application.yml
 ├─ data/infra|policy|fun    # your datasets
 ├─ guardrails/*.yml         # guardrail rules per theme
 ├─ infra/docker-compose.yml # pgvector, qdrant, prom, grafana
 └─ docs/
     ├─ whitepaper-policy-ops.md
     └─ architecture.png (add your diagram)
```

## Next steps
1) Implement **PgVectorStore** (SQL) and **QdrantVectorStore** (REST) replacing stubs.  
2) Add **embedding service** (cloud or local) and document loaders.  
3) Wire **Audit Trail** (DB table) & **Grafana dashboards**.  
4) Add **springdoc-openapi** for Swagger UI.  
5) Create a tiny **web demo** client (or Unity/Godot) calling `/api/chat`.

Good luck & have fun!
