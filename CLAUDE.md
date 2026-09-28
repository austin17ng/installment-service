# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Test Commands

```bash
./gradlew build          # Build the project
./gradlew test           # Run all tests
./gradlew bootRun        # Run the application
./gradlew clean build    # Clean and rebuild
```

Run a single test class:
```bash
./gradlew test --tests "me.austin.ng.installmentservice.SomeTestClass"
```

## Architecture

Spring Boot 4.1.1 application (Java 17, Gradle) implementing an installment payment service using **hexagonal / clean architecture** — the domain layer has no framework dependencies.

### Domain Layer (`domain/`)

All business logic lives under `me.austin.ng.installmentservice.domain`:

- **model/** — Immutable value objects and enums: `InstallmentPlan` (ties an account+transaction to a term, schedules, rates, and fees), `InstallmentSchedule` (single payment period with principal/interest tracking), `PaymentAllocation` (links a repayment to a schedule), `InstallmentTerm` (3/6/9/12/18/24 months), status enums.
- **calculator/** — `InstallmentCalculator` for financial computations (principal-per-month via `BigDecimal` with `RoundingMode.DOWN`).
- **repository/** — Port interfaces (`InstallmentPlanRepository`, `InstallmentScheduleRepository`, `PaymentAllocationRepository`). No implementations yet — adapters will go outside the domain package.

### Key Design Decisions

- All monetary amounts use `BigDecimal` — never use `double`/`float` for money.
- Domain models are immutable (final fields, constructor injection, no setters).
- Repository interfaces are domain ports — implementations (JPA, JDBC, etc.) belong in an infrastructure/adapter layer outside `domain/`.
