package com.jay.EmiCalc.dto;

import jakarta.validation.constraints.NotNull;

public class EmiCalcResponse {
    @NotNull
    private Double emiAmount;

    @NotNull
    private Double totalPayment;

    @NotNull
    private Double totalInterest;

    public EmiCalcResponse(Double emiAmount, Double totalPayment, Double totalInterest) {
        this.emiAmount = emiAmount;
        this.totalPayment = totalPayment;
        this.totalInterest = totalInterest;
    }

    public Double getEmiAmount() {
        return emiAmount;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public Double getTotalInterest() {
        return totalInterest;
    }
}
