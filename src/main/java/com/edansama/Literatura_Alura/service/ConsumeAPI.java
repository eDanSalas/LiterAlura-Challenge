package com.edansama.Literatura_Alura.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ConsumeAPI {
    public String getInfo(String url) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Error al obtener los datos, pila: ", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Solicitud interrumpida, pila: ", e);
        }

        return response.body();
    }
}
