# 🚀 GitInsight AI

AI-powered code review tool for pull requests (PRs), built with **Java + Spring Boot**.

The application analyzes GitHub PRs, extracts code diffs, runs a **rule-based engine**, and prepares structured data ready for AI-based review and frontend visualization.

---

## ✨ Features

* 🔍 Analyze **public GitHub pull requests**
* 🧠 Extract and process **code diffs**
* ⚙️ Built-in **Rule Engine** for deterministic checks
* 📦 Clean, structured API response (UI-ready)
* 🔌 Easily extendable for new providers (GitLab, Bitbucket, etc.)
* 🤖 Ready for AI integration (Ollama / LLMs)

---

## ⚠️ Current Limitations

* Works only with **public repositories**
* Supports **Java code only** (for rule evaluation)
* GitHub is the only provider implemented (for now)

---

## 🏗️ Architecture Overview

```
PR URL → Provider Resolver → GitHub Client → Changed Files
        → Rule Engine → Findings → Aggregated Review Response
```

---

## Quick Test

You can test the application with this public GitHub PR:

```text
https://github.com/YordanovND/GitInsight-AI/pull/1/changes
```

Example request:

```bash
curl -X 'GET' \
  'http://localhost:8080/api/reviews?url=https%3A%2F%2Fgithub.com%2FYordanovND%2FGitInsight-AI%2Fpull%2F1%2Fchanges' \
  -H 'accept: */*'
```

## 🔌 Provider Extensibility

Adding a new provider is straightforward:

1. Implement:

```java
VisualControlsProviderClient
```

2. Add support logic:

```java
boolean supports(String url)
```

3. Register it as a Spring bean (`@Component`)

That’s it — it will be auto-discovered and used via the resolver.

---

## ⚙️ Rule Engine

The rule engine performs deterministic checks on code changes.

### How it works

* Each file is passed through a list of rules
* Each rule returns findings (if any)
* Results are aggregated into a structured response

---

### Adding a new rule

1. Implement the `ReviewRule` interface:

```java
@Component
public class MyCustomRule implements ReviewRule {

    @Override
    public String getName() {
        return "MY_RULE";
    }

    @Override
    public boolean supports(RuleContext context) {
        return context.changedFile().filename().endsWith(".java");
    }

    @Override
    public List<RuleFinding> evaluate(RuleContext context) {
        // your logic here
        return List.of();
    }
}
```

2. Done ✅
   Spring will automatically pick it up and include it in the engine.

---

### Example Rules

* `System.out.println` usage
* Generic exception catch (`catch (Exception e)`)
* Spring field injection (`@Autowired`)

---

## ▶️ Running the Project

### With Docker (recommended)

```bash
make build
```

or

```bash
docker compose up --build
```

---

## 🌐 API Usage

### Get review for a PR

```http
GET /api/reviews?url=<pull_request_url>
```

### Example:

```text
http://localhost:8080/api/reviews?url=https://github.com/user/repo/pull/1
```

---

## 📊 Response

The API returns:

* Summary (score, verdict)
* Findings grouped by severity
* Per-file analysis

Designed to be directly consumed by a frontend.

---

## 🧠 Future Improvements

* AI-generated code review (LLM integration)
* Inline GitHub comments publishing
* GitHub App authentication
* Support for GitLab / Bitbucket
* Multi-language support

---

## 💡 Why this project?

This project demonstrates:

* Clean architecture & extensibility
* Integration with external APIs (GitHub)
* Rule-based analysis engine
* Preparation for AI-powered workflows

---

## 🧑‍💻 Author

Built as a portfolio project to showcase backend + AI integration skills.