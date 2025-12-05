# EMI Calculator

A full-stack application for calculating Equated Monthly Installment (EMI) with a Spring Boot backend and Angular frontend.

## Project Overview

The EMI Calculator is a web application that helps users calculate their monthly loan payments based on:
- Loan Amount
- Annual Interest Rate
- Loan Term (in years)

It displays:
- **Monthly EMI** - The fixed monthly payment amount
- **Total Interest** - Total interest paid over the loan period
- **Total Payable Amount** - Total amount to be paid (principal + interest)

## Project Structure

```
emi-calculator
├─ README.md
├─ backend
│  └─ EmiCalc
│     ├─ pom.xml
│     └─ src
│        ├─ main
│        │  ├─ java
│        │  │  └─ com
│        │  │     └─ jay
│        │  │        └─ EmiCalc
│        │  │           ├─ EmiCalcApplication.java
│        │  │           ├─ controller
│        │  │           │  └─ EmiCalcController.java
│        │  │           ├─ dto
│        │  │           │  ├─ EmiCalcRequest.java
│        │  │           │  └─ EmiCalcResponse.java
│        │  │           ├─ exception
│        │  │           │  ├─ GlobalExceptionHandler.java
│        │  │           │  └─ InvalidInputException.java
│        │  │           ├─ service
│        │  │           │  ├─ EmiCalcService.java
│        │  │           │  └─ EmiCalcServiceImpl.java
│        │  │           └─ util
│        │  │              └─ EmiUtil.java
│        │  └─ resources
│        │     └─ application.properties
│        └─ test
|
└─ frontend
   └─ emi-calc
      ├─ angular.json
      ├─ package-lock.json
      ├─ package.json
      ├─ public
      │  └─ favicon.ico
      ├─ src
      │  ├─ app
      │  │  ├─ app.config.ts
      │  │  ├─ app.css
      │  │  ├─ app.html
      │  │  ├─ app.routes.ts
      │  │  ├─ app.spec.ts
      │  │  ├─ app.ts
      │  │  ├─ emi-calculator
      │  │  │  ├─ emi-calculator.css
      │  │  │  ├─ emi-calculator.html
      │  │  │  ├─ emi-calculator.spec.ts
      │  │  │  └─ emi-calculator.ts
      │  │  └─ services
      │  │     ├─ emi.spec.ts
      │  │     └─ emi.ts
      │  ├─ index.html
      │  ├─ main.ts
      │  └─ styles.css
      ├─ tsconfig.app.json
      ├─ tsconfig.json
      └─ tsconfig.spec.json
```

## Technology Stack

### Backend
- **Framework**: Spring Boot 4.0.0
- **Language**: Java 21
- **Build Tool**: Maven
- **Dependencies**:
  - Spring Boot Starter Validation
  - Spring Boot Starter Web MVC
  - Spring Boot DevTools

### Frontend
- **Framework**: Angular 18+
- **Language**: TypeScript
- **Build Tool**: Angular CLI / Vite
- **Styling**: CSS
- **HTTP Client**: Angular HttpClient

## Features

### Backend Features
- RESTful API endpoints for EMI calculation
- Validates incoming data (principal, interest rate, loan term)
- Handles invalid input with structured error responses
- CORS enabled for secure interaction with Angular frontend
- Mathematical calculation of EMI using the standard formula

### Frontend Features
- User-friendly input form
- Real-time EMI calculation
- Reactive Forms with validation messages
- Decimal input support for interest rates & loan duration
- Display of:
  - Monthly EMI amount
  - Total Interest paid
  - Total Payable amount

## Installation

### Prerequisites
- Java Development Kit (JDK) 21+
- Node.js 22+ and npm
- Maven 3.6+
- Git

### Backend Setup

1. Navigate to the Backend directory:
```bash
cd Backend\EmiCalc
```

2. Build the project:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

The backend server will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the Frontend directory:
```bash
cd Frontend\emi-calc
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:

```bash
ng serve
```

The frontend will be available at `http://localhost:4200`

## API Endpoints

### EMI Calculation
- **Endpoint**: `POST /api/emi/calculate`
- **Request Body (JSON)**:
    The API expects EMI input details in the following JSON format:
```bash
{
  "principal": 500000,
  "annualInterestRate": 9.5,
  "loanTermYears": 7.5
}
```
- **Validation Rules**:
| Field                | Type   | Constraints                   |
| -------------------- | ------ | ----------------------------- |
| `principal`          | Double | Required, must be > 0         |
| `annualInterestRate` | Double | Required, between >0 and <100 |
| `loanTermYears`      | Double | Required, >0 and <30          |

- **Response (JSON)**: 
    The API returns a structured JSON object including EMI result details:
```bash
{
  "emiAmount": 8021.47,
  "totalPayment": 721932.12,
  "totalInterest": 221932.12
}

```
## Usage

1. **Start both backend and frontend servers** (see Installation section)

2. **Open the application** in your browser at `http://localhost:4200`

3. **Enter the loan details**:
   - Loan Amount (must be positive)
   - Annual Interest Rate (0-100%)
   - Loan Term in Years (1-30 years)

4. **Click Calculate** to see the results:
   - Monthly EMI
   - Total Interest
   - Total Payable Amount

## Validation Rules

### Backend Validation
- **Loan Amount**: Must be greater than 0
- **Annual Interest Rate**: Must be between 0 and 100
- **Loan Term**: Must be between 0 and 30 years

### Frontend Validation
- All input fields are required
- Valid number format required

## EMI Formula

The monthly EMI is calculated using the formula:

```
EMI = P × r × (1 + r)^n / ((1 + r)^n - 1)
```

Where:
- **P** = Principal Loan Amount
- **r** = Monthly Interest Rate (Annual Interest Rate / 12 / 100)
- **n** = Number of Months (Loan Term in Years × 12)

**Additional Calculations**:
- **Total Payable Amount** = EMI × n
- **Total Interest** = Total Payable Amount - Principal

## Error Handling

### Backend Error Responses
The backend validates inputs using Jakarta validation and custom exceptions.
It responds with clear error messages for invalid values, such as:
- "Loan Amount must be a positive number"
- "Annual Interest rate must be between 0 and 100"
- "Loan Term years should be between 0 and 30"

### Frontend Error Handling
The frontend uses Reactive Form Validators to validate input before submission.

Invalid fields show user-friendly messages via *ngIf, ensuring users correct issues before the API request is sent.
