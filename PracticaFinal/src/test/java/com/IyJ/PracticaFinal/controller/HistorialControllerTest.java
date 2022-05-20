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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.IyJ.PracticaFinal.joins.HistorialTratamientosJoin;
import com.IyJ.PracticaFinal.model.Historial;
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



    @Test
    public void historialesGetTest() {
        Iterable<Historial> historiales = service.retreiveHistoriales(null);

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/historiales";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<Historial>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Iterable<Historial>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(historiales);
    }

    @Test
    public void historialGetTest() {
        String id = "10825450V";
        Historial historial = service.retreiveHistorial(id);

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/historiales/" +id +"/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Historial> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Historial>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(historial);
    }

    @Test
    public void historialDeleteTest(){
        String id = "2";
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/historiales/" +id +"/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Historial> result = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            entity,
            new ParameterizedTypeReference<Historial>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void historialPostTest(){
        LocalTime duracion = LocalTime.of(19,0);
        LocalDate date = LocalDate.of(2022,12,15);
        Historial historial = new Historial();
        Long numero = new Long(17);
        historial.setDni("10825450V");
        historial.setId(numero);
        historial.setIdTratamiento("4");
        historial.setDate(date);
        historial.setTime(duracion);
        
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/historiales";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");

        HttpEntity<Historial> entity = new HttpEntity<>(historial,headers);

        ResponseEntity<Historial> result = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<Historial>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(historial);
    }

    @Test
    public void historialPutTest(){
        LocalTime duracion = LocalTime.of(19,0);
        LocalDate date = LocalDate.of(2022,12,15);
        Historial historial = new Historial();
        Long numero = new Long(17);
        historial.setDni("10825450V");
        historial.setId(numero);
        historial.setIdTratamiento("4");
        historial.setDate(date);
        historial.setTime(duracion);
        
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/historiales/" + "10825450V" + "/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");

        HttpEntity<Historial> entity = new HttpEntity<>(historial,headers);

        ResponseEntity<Historial> result = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            entity,
            new ParameterizedTypeReference<Historial>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(historial);
    }
}
