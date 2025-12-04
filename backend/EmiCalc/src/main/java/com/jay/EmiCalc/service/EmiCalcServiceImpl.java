package com.jay.EmiCalc.service;

import com.jay.EmiCalc.dto.EmiCalcRequest;
import com.jay.EmiCalc.dto.EmiCalcResponse;
import com.jay.EmiCalc.exception.InvalidInputException;
import com.jay.EmiCalc.util.EmiUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EmiCalcServiceImpl implements EmiCalcService{
    @Override
    public EmiCalcResponse calculateEMI(EmiCalcRequest requestDTO) {
        double principal = requestDTO.getPrincipal();
        double annualInterestRate = requestDTO.getAnnualInterestRate();
        double loanTermYears = requestDTO.getLoanTermYears();

        // Validate input
        if (annualInterestRate <= 0 || annualInterestRate >= 100) {
            throw new InvalidInputException("Annual interest rate must be between 0 and 100");
        }
        if (loanTermYears <= 0 || loanTermYears >= 30) {
            throw new InvalidInputException("Loan term must be between 0 and 30 years");
        }

        double tenureMonths = requestDTO.getLoanTermYears() * 12;

        // Calculate EMI
        double emi = EmiUtil.calculateEMI(principal, annualInterestRate, tenureMonths);

        // Calculate total payment & total interest
        double totalPayment = emi * tenureMonths;
        double totalInterest = totalPayment - principal;

        // Round to 2 decimal places
        emi = BigDecimal.valueOf(emi).setScale(2, RoundingMode.HALF_UP).doubleValue();
        totalPayment = BigDecimal.valueOf(totalPayment).setScale(2, RoundingMode.HALF_UP).doubleValue();
        totalInterest = BigDecimal.valueOf(totalInterest).setScale(2, RoundingMode.HALF_UP).doubleValue();

        // Return response DTO
        return new EmiCalcResponse(emi, totalPayment, totalInterest);
    }
}
