# Policy-to-Engineering: Turning Transparency & Accountability into System Metrics (MVP Outline)

**Objective:** Show how policy goals for AI systems (transparency, accountability, safety) are mapped to concrete engineering artefacts in this RAG service.

## 1. Transparency
- **Prompt Template Versioning:** each response logs `template_id`, model, temperature.
- **Provenance:** store retrieved document IDs, scores, and filters per request.
- **Observability:** expose latency histograms and error rates via Prometheus.

## 2. Accountability
- **Audit Trail:** persist `request_id`, user, timestamps, inputs (PII-redacted), outputs (hash), guardrail action.
- **Reproducibility:** deterministic eval mode (fixed seed), replay request by `request_id`.

## 3. Safety & Guardrails
- **Content policy:** rule-based and model-based classifiers (poison, PII, NSFW, legal disclaimers).
- **Refusal strategy:** graceful fallback with reason codes; partial answers with citations only.

## 4. Cost & Risk Management
- **Budgets:** per-user/model cost budgets; cost meters and alerts.
- **Caching/Retrieval Optimisation:** top-k caps, rerank thresholds.

## 5. Governance Coverage (Theme-aware)
- **infra:** ML pipeline incident handbook coverage (% answered with citations).
- **policy:** EU AI Act/UK AI White Paper sections coverage and refusal rates.
- **fun:** hallucination/recall@k metrics.

> This MVP ships stubs; fill in metrics and screenshots as you wire real backends.
