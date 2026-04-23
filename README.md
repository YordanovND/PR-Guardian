# PR Guardian

PR Guardian is a lightweight rule-based pull request review service for GitHub.

It reads changed files from a pull request, runs JSON-defined rules against the diff, and returns a structured review response.

---

## Features

- Review GitHub pull requests by URL
- Rule-based analysis (no AI)
- Rules defined in external JSON files
- Language-aware rule selection
- Structured response per file and per violation
- Easy to extend with new rules and languages

---

## Live Demo

The application is currently deployed on AWS EC2:

Example request:

```bash
curl "http://<aws-dynamic-ip>:8080/api/reviews?url=https://github.com/YordanovND/PR-Guardian/pull/1"
```

Note: The service may be temporarily unavailable if the EC2 instance is stopped.

---

## API

### Endpoint

```
GET /api/reviews?url=<pull_request_url>
```

### Example

```bash
curl "http://localhost:8080/api/reviews?url=https://github.com/owner/repo/pull/1"
```

### Example response

```json
{
  "pullRequestUrl": "https://github.com/owner/repo/pull/1",
  "filesReviewed": 2,
  "issuesFound": 1,
  "fileResults": [
    {
      "fileName": "Example.java",
      "language": "JAVA",
      "violations": [
        {
          "ruleId": "java-system-out",
          "ruleName": "SYSTEM_OUT_PRINTLN",
          "severity": "LOW",
          "type": "CODE_SMELL",
          "message": "Avoid using System.out.println or System.out.print."
        }
      ]
    }
  ]
}
```

---

## Rules

Rules are defined as JSON files located in:

```
src/main/resources/rules/
```

Each rule includes:
- language
- severity
- type
- message
- conditions (regex-based)

This allows adding new rules without changing the application code.

---

## How it works

1. A pull request URL is provided
2. The system resolves the provider (GitHub)
3. Changed files are fetched
4. Rules are applied based on file language
5. Results are returned in a structured format

---

## Run locally

```bash
./gradlew bootRun
```

Or run the main application class from your IDE.

---

## Project structure

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

---

## Deployment

The service is deployed on AWS EC2 using:

- Amazon Linux instance (t3.micro)
- Spring Boot executable JAR
- Open ports (8080) via security groups
- Note: The service is hosted on AWS and may be restarted periodically.

---

## Notes

- Analysis is based on pull request diffs, not full repository scanning
- Only public GitHub repositories are supported currently
- New languages can be added via rules

---

## Author

Nikolay Yordanov