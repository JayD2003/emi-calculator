package com.jay.EmiCalc.dto;

import jakarta.validation.constraints.*;

public class EmiCalcRequest {

    @NotNull(message = "Principal amount is required")
    @Min(value = 1, message = "Principal should be positive")
    private Double principal;

    @NotNull(message = "Annual interest rate is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Interest rate should be positive")
    @DecimalMax(value = "100.0", message = "Interest rate cannot exceed 100")
    private Double annualInterestRate;

    @NotNull(message = "Loan term is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Loan term must be greater than 0")
    @DecimalMax(value = "30.0", message = "Loan term cannot exceed 30 years")
    private Double loanTermYears;

    public Double getPrincipal() {
        return principal;
    }

    public Double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public Double getLoanTermYears() {
        return loanTermYears;
    }
}

