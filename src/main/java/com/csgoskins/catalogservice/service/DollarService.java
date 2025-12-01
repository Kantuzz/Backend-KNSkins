package com.csgoskins.catalogservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class DollarService {

    private final RestTemplate rest = new RestTemplate();

    public Double getDollarValue() {
        try {
            String url = "https://mindicador.cl/api/dolar";

            Map<String, Object> data = rest.getForObject(url, Map.class);
            if (data == null) return null;

            List<Map<String, Object>> serie =
                    (List<Map<String, Object>>) data.get("serie");

            if (serie == null || serie.isEmpty()) return null;

            Object valor = serie.get(0).get("valor");

            if (valor instanceof Number) {
                return ((Number) valor).doubleValue();
            }

            return null;

        } catch (Exception e) {
            System.out.println("Error obteniendo valor del dólar: " + e.getMessage());
            return null;
        }
    }
}
