# Installment Service

## Quick Start

Prerequisites: Java 17, PostgreSQL

```bash
./gradlew build          # Build the project
./gradlew test           # Run all tests
```

### Run locally (dev)

```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### Run in production

```bash
./gradlew bootRun --args='--spring.profiles.active=prod'
```

Requires environment variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`

---

## 1. Overview

`installment-service` manages the installment lifecycle for credit card transactions.

Main responsibilities:

* Create an installment plan from an existing credit card transaction
* Generate installment schedules
* Track installment payments
* Allocate repayments to installment schedules
* Support early settlement
* Manage installment and schedule statuses

Example:

```text
Transaction: 12,000,000 VND
Term: 6 months

Installment Plan
├── Schedule #1
├── Schedule #2
├── Schedule #3
├── Schedule #4
├── Schedule #5
└── Schedule #6
```

---

## 2. Domain Layer

```text
domain/
├── model/          — InstallmentPlan, InstallmentSchedule, PaymentAllocation,
│                     InstallmentTerm, InstallmentOption, Transaction
├── common/         — InstallmentCalculator, InstallmentTermPolicy
├── repository/     — port interfaces (InstallmentPlanRepository, etc.)
├── gateway/        — port interfaces (TransactionGateway)
└── usecase/        — GetInstallmentPlansByTransactionUseCase, CreateInstallmentUseCase
```

### InstallmentPlan

Represents the whole installment plan.

```text
PENDING
   ↓
ACTIVE
   ↓
COMPLETED / CANCELED
```

### InstallmentSchedule

Represents an individual repayment period.

```text
PENDING
   ↓
PARTIALLY_PAID
   ↓
PAID
```

A schedule can also become:

```text
OVERDUE
```

### InstallmentTerm

Supported terms: 3, 6, 9, 12, 18, 24 months.

### PaymentAllocation

Represents how a repayment is allocated to an installment schedule.

---

## 3. Communication With Other Services

### Account Management Service (`acc-mg-service`)

Owns:

* Account
* Card
* Transaction
* Statement

`installment-service` calls `acc-mg-service` to get information about the original transaction.

```text
installment-service
        │
        │ REST
        ▼
acc-mg-service
```

---

### Repayment Service (`repayment-service`)

Owns repayment/payment processing.

```text
installment-service
        │
        │ REST
        ▼
repayment-service
```

Repayment results are sent back through Kafka:

```text
repayment-service
        │
        │ repayment.succeeded
        ▼
      Kafka
        │
        ▼
installment-service
```

---

## 4. Infrastructure

### PostgreSQL

`installment-service` owns its own database:

```text
installment_db
├── installment_plans
├── installment_schedules
└── payment_allocations
```

Services do not directly access each other's databases.

### Kafka Topics

Events consumed:

```text
repayment.succeeded
repayment.failed
```

Events published:

```text
installment.created
installment.completed
installment.cancelled
```

---

## 5. Mobile App

The Mobile App communicates with the backend through REST APIs.

### Get Installment Plans by Transaction

User selects a transaction to view available installment options.

```http
GET /transactions/{transactionId}/installments
```

Fetches the transaction amount from `acc-mg-service`, then calculates installment options for all available terms (3, 6, 9, 12, 18, 24 months). Each option shows the monthly principal, interest rate, processing fee, and total amount.

---

### Create Installment

User picks a plan from the list and confirms.

```http
POST /installments
```

Request:

```json
{
  "transactionId": "txn-001",
  "term": 6
}
```

Flow:

```text
Mobile App
    │
    │ 1. GET /transactions/{transactionId}/installments
    │    (view available plans)
    │
    │ 2. POST /installments
    │    (confirm selected plan)
    ▼
installment-service
    │
    │ GET transaction
    ▼
acc-mg-service
```

---

### Get Installment Details

Used to display installment plan information including all schedules.

```http
GET /installments/{id}
```

Returns: plan details (term, status, start date, amounts, rates, fees) and the list of schedules (due date, status, principal, interest).

---

### Early Settlement

User chooses to settle the remaining installment amount.

```http
POST /installments/{id}/settle
```

The Installment Service calculates the settlement amount and coordinates with the Repayment Service to process the payment.

