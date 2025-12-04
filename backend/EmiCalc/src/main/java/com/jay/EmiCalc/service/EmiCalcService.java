package com.jay.EmiCalc.service;

import com.jay.EmiCalc.dto.EmiCalcRequest;
import com.jay.EmiCalc.dto.EmiCalcResponse;

public interface EmiCalcService {
    EmiCalcResponse calculateEMI(EmiCalcRequest requestDTO);
}
