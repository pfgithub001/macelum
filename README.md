# macelum

Canonical operating guides for AI assistants working on Next.js and Spring Boot projects.

## Start Here

Read the relevant guide before creating, editing, reviewing, or restructuring application code in this repository.

- Next.js work: `NEXTJS_GUIDE.md`
- Spring Boot work: `SPRING_BOOT_GUIDE.md`

## Purpose

This repository defines strict operating models for AI assistants working on:
- Next.js frontend applications
- Spring Boot backend applications

The guides standardize:
- project creation
- folder and package structure
- routing and API design
- styling and persistence rules
- code placement decisions
- documentation requirements
- validation and forbidden patterns

## What This Repo Contains

- `.env.example`: shared fullstack environment template
- `docker-compose.yml`: root infrastructure orchestration
- `NEXTJS_GUIDE.md`: canonical Next.js operating guide
- `SPRING_BOOT_GUIDE.md`: canonical Spring Boot operating guide
- `AGENTS.md`: thin wrapper pointing assistants to the guide
- `CLAUDE.md`: thin wrapper pointing assistants to the guide
- `GPT.md`: thin wrapper pointing assistants to the guide
- `DEEPSEEK.md`: thin wrapper pointing assistants to the guide
- `.github/copilot-instructions.md`: Copilot wrapper pointing assistants to the guide
- `nextjs-fe/`: frontend app aligned with `NEXTJS_GUIDE.md`
- `springboot-be/`: backend app aligned with `SPRING_BOOT_GUIDE.md`

## How To Use

1. Read the relevant guide first.
2. Follow it as the source of truth for the task.
3. Preserve existing repo patterns unless migration is explicitly requested.
4. Do not introduce competing architectures without explicit instruction.

## Local Fullstack Workflow

1. Copy the shared environment template:

```bash
cp .env.example .env
```

2. Start shared infrastructure:

```bash
docker compose up -d
```

3. Run the backend locally:

```bash
cd springboot-be
./mvnw spring-boot:run
```

4. Run the frontend locally in another terminal:

```bash
cd nextjs-fe
pnpm dev
```
