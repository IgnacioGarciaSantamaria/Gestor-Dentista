package com.IyJ.PracticaFinal.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;

import com.IyJ.PracticaFinal.joins.HistorialTratamientosJoin;
import com.IyJ.PracticaFinal.service.HistorialService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HistorialControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private HistorialService service;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void historialTratamientosJoinTest(){
        List<HistorialTratamientosJoin> historialTratamientos = service.retreiveHistorialTratamiento();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/join/historial/tratamientos";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");

        ResponseEntity<List<HistorialTratamientosJoin>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<List<HistorialTratamientosJoin>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(historialTratamientos);
    }
}
