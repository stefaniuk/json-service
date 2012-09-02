package org.stefaniuk.json.service.example2;

import org.stefaniuk.json.service.JsonService;

public class CalculatorService {

    @JsonService
    public Integer add(Integer v1, Integer v2) {

        return v1 + v2;
    }

    @JsonService
    public Integer subtract(Integer v1, Integer v2) {

        return v1 - v2;
    }

    @JsonService
    public Integer multiple(Integer v1, Integer v2) {

        return v1 * v2;
    }

    @JsonService
    public Integer divide(Integer v1, Integer v2) {

        return v1 / v2;
    }

}
