# 🚀 GitInsight AI

AI-powered code review assistant for Java/Spring projects.
Analyzes GitHub Merge Requests (Pull Requests), inspects diffs, and generates structured review reports using a hybrid approach (rule-based checks + local LLM).

---

## 🧠 Idea

GitInsight AI is a backend service that performs automated code reviews on GitHub repositories.

Instead of reviewing entire repositories, the system focuses on **Merge Request (PR) diffs**, just like real developers do.

It:

* fetches changes from a GitHub PR
* analyzes modified files
* runs rule-based checks
* sends diffs to a local AI model (Ollama)
* returns structured review results (issues, suggestions, score)

---

## ✨ Features

### 🔍 PR / Merge Request Review

* Input: GitHub PR URL
* Fetches changed files and diffs
* Filters relevant files (`.java`, `.yml`, etc.)

### 🤖 AI Code Review (Ollama)

* Uses local LLM (no paid API)
* Generates:

    * bug risks
    * code smells
    * security concerns
    * refactoring suggestions

### ⚙️ Rule-based Static Checks

* Detects common issues:

    * long methods/classes
    * nested logic
    * missing null checks
    * generic exception handling
    * Spring anti-patterns

### 🧩 Hybrid Review Pipeline

Combines:

* deterministic rule checks
* AI-generated insights

### 📊 Structured Output

Returns clean JSON:

```json
{
  "score": 75,
  "summary": "...",
  "issues": [...],
  "suggestions": [...]
}
```

### 🐳 Fully Dockerized

* Spring Boot backend
* Ollama (AI model)
* PostgreSQL database

---

## 🏗️ Tech Stack

* Java 21
* Spring Boot
* Spring AI (Ollama)
* PostgreSQL
* Docker + Docker Compose
* OpenAPI (Swagger)

---

## 🚀 Getting Started

### 1. Prerequisites

* Docker Desktop
* Git

---

### 2. Clone the repository

```bash
git clone https://github.com/your-username/gitinsight-ai.git
cd gitinsight-ai
```

---

### 3. Run the project

```bash
docker compose up --build
```

👉 This will:

* build the Spring app
* start PostgreSQL
* start Ollama
* download the AI model automatically

---

### 4. Access the application

* API:
  http://localhost:8080

* Swagger UI:
  http://localhost:8080/swagger-ui/index.html

---

## 📡 Example Endpoint

```http
GET /review
```

(Simple test endpoint for now)

---

## 🧪 Planned Endpoint (PR Review)

```http
POST /api/reviews/pr
```

### Request:

```json
{
  "repoUrl": "https://github.com/user/project",
  "prNumber": 42
}
```

### Flow:

1. Fetch PR diff via GitHub API
2. Extract changed files
3. Run rule-based checks
4. Send diff to AI
5. Return structured review

---

## ⚙️ Local Development

### Useful commands

Using Docker:

```bash
docker compose up --build
docker compose down
docker compose logs -f
```

Using Makefile (if installed):

```bash
make build
make logs
make down
```

---

## 🧠 Architecture Overview

```
GitHub PR → Diff Parser → Rule Engine → AI Service → Review Aggregator → Response
```

---

## 📌 Notes

* AI runs locally via Ollama → no API costs
* Designed to be modular (can plug different LLM providers)
* Focused on backend (no frontend yet)

---

## 🛠️ Roadmap

* [ ] PR diff parsing service
* [ ] GitHub API integration
* [ ] AI structured output validation
* [ ] Review history (DB)
* [ ] Authentication (JWT)
* [ ] Frontend dashboard
* [ ] AWS / cloud deployment

---

## 📄 License

MIT

---

## 👨‍💻 Author

Built as a portfolio project to demonstrate:

* backend architecture
* AI integration
* Docker-based development
* real-world engineering patterns
