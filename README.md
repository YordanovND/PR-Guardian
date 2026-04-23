# PR Guardian

PR Guardian is a lightweight rule-based pull request review service for GitHub.

It reads changed files from a pull request, runs JSON-defined rules against the diff, and returns a structured review response that can be consumed by a UI or another service.

## Features

- Review public GitHub pull requests by URL
- Rule-based analysis over changed files
- Rules stored as external JSON files
- Language-aware rule selection
- Structured API response per file and per violation
- Easy to extend with new rules and providers

## Tech Stack

- Java 21
- Spring Boot
- Gradle
- OpenAPI / Swagger UI

## How it works

1. A pull request URL is sent to the API
2. The provider is resolved from the URL
3. Changed files are fetched from GitHub
4. The rule engine loads matching rules for the file language
5. Violations are aggregated into a review response

## API

### Get review result
`GET /api/reviews?url=<pull_request_url>`

### Example
`curl "http://localhost:8080/api/reviews?url=https://github.com/owner/repo/pull/1"`


### Example response

```json
{
  "pullRequestUrl": "https://github.com/owner/repo/pull/1",
  "filesReviewed": 2,
  "issuesFound": 1,
  "fileResults": [
    {
      "fileName": "src/main/java/com/example/Demo.java",
      "language": "JAVA",
      "violations": [
        {
          "ruleId": "java-system-out",
          "ruleName": "SYSTEM_OUT_PRINTLN",
          "severity": "LOW",
          "type": "CODE_SMELL",
          "message": "Avoid using System.out.println or System.out.print in application code. Prefer structured logging."
        }
      ]
    }
  ]
}
```

### Rules

Rules are defined as JSON files under:
`src/main/resources/rules/`

Each rule contains metadata such as language, severity, type, message, and matching conditions.

This makes the engine easy to extend without changing the core review logic.

### Run locally
`./gradlew bootRun`

Or run the main application class directly from your IDE.

### Project structure
```
src/main/java/com/example/prguardian
├── controller
├── client
├── resolver
├── service
└── model

src/main/resources
└── rules
```

### Notes
The current implementation is focused on GitHub pull requests
Review results are based on changed code, not full repository analysis
New languages and rules can be added incrementally