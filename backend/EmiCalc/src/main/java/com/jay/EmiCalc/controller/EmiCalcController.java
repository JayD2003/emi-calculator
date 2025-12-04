package com.jay.EmiCalc.controller;

import com.jay.EmiCalc.dto.EmiCalcRequest;
import com.jay.EmiCalc.dto.EmiCalcResponse;
import com.jay.EmiCalc.service.EmiCalcService;
import com.jay.EmiCalc.service.EmiCalcServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/emi")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular frontend
public class EmiCalcController {
    @Autowired
    private final EmiCalcService emiCalcService;

    public EmiCalcController(EmiCalcServiceImpl emiCalcService){
        this.emiCalcService = emiCalcService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<EmiCalcResponse> calculateEMI(@Valid @RequestBody EmiCalcRequest requestDTO) {
        return ResponseEntity.ok(emiCalcService.calculateEMI(requestDTO));
    }

}
