package com.jay.EmiCalc.service;

import com.jay.EmiCalc.dto.EmiCalcRequest;
import com.jay.EmiCalc.dto.EmiCalcResponse;
import com.jay.EmiCalc.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmiCalcServiceTest {

    private final EmiCalcServiceImpl emiCalcService = new EmiCalcServiceImpl();

    @Test
    void calculateEMI_ValidInputs_ReturnsCorrectValues() {
        // Principal: 100000, Rate: 10%, Tenure: 1 yer
        // EMI = [P x R x (1+R)^N]/[(1+R)^N-1]
        EmiCalcRequest request = new EmiCalcRequest();
        request.setPrincipal(100000);
        request.setAnnualInterestRate(10);
        request.setLoanTermYears(1);

        EmiCalcResponse response = emiCalcService.calculateEMI(request);
        
        assertNotNull(response);
        assertTrue(response.getEmi() > 0);
        assertEquals(response.getTotalPayment(), response.getEmi() * 12, 0.01);
    }

    @Test
    void calculateEMI_BoundaryLoanTerm_Allowed() {
        EmiCalcRequest request = new EmiCalcRequest();
        request.setPrincipal(100000);
        request.setAnnualInterestRate(10);
        request.setLoanTermYears(30);

        assertDoesNotThrow(() -> emiCalcService.calculateEMI(request));
    }

    @Test
    void calculateEMI_InvalidLoanTerm_ThrowsException() {
        EmiCalcRequest request = new EmiCalcRequest();
        request.setPrincipal(100000);
        request.setAnnualInterestRate(10);
        request.setLoanTermYears(31); 

        assertThrows(InvalidInputException.class, () -> emiCalcService.calculateEMI(request));
    }

    @Test
    void calculateEMI_InvalidInterestRate_ThrowsException() {
        EmiCalcRequest request = new EmiCalcRequest();
        request.setPrincipal(100000);
        request.setAnnualInterestRate(101); 
        request.setLoanTermYears(10);

        assertThrows(InvalidInputException.class, () -> emiCalcService.calculateEMI(request));
    }
}
