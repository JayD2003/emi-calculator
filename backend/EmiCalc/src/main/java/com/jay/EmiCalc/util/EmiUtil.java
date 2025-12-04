package com.jay.EmiCalc.util;

public class EmiUtil {

    public static double calculateEMI(double principal, double annualInterestRate, double months) {
        double monthlyRate = annualInterestRate / 12 / 100; // Convert annual rate to monthly decimal
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);
    }

}
