package com.edansama.Literatura_Alura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class Deserialize implements IDeserialize {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getInformation(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("No fue posible deserializar el JSON, pila: " + e);
        }
    }
}
