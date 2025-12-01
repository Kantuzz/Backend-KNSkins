package com.csgoskins.catalogservice.controller;

import com.csgoskins.catalogservice.service.DollarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/external")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DollarController {

    private final DollarService dollarService;

    @GetMapping("/dolar")
    public Map<String, Object> getDolar() {
        Double valor = dollarService.getDollarValue();

        return Map.of(
                "codigo", "USDCLP",
                "valor", valor
        );
    }
}
