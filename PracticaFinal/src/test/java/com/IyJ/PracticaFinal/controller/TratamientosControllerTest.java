package com.IyJ.PracticaFinal.controller;

import com.IyJ.PracticaFinal.model.Tratamiento;
import com.IyJ.PracticaFinal.service.TratamientoService;

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

import java.time.LocalTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TratamientosControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TratamientoService service;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void tratamientosGetTest() {
        Iterable<Tratamiento> tratamientos = service.retreiveTratamientos();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/tratamientos";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<Tratamiento>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Iterable<Tratamiento>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(tratamientos);
    }

    @Test
    public void tratamientoGetTest() {
        String id = "1";
        Tratamiento tratamiento = service.retreiveTratamiento(id);

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/tratamientos/" +id +"/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Tratamiento> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Tratamiento>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(tratamiento);
    }

    @Test
    public void tratamientoDeleteTest(){
        String id = "1";
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/tratamientos/" +id +"/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Tratamiento> result = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            entity,
            new ParameterizedTypeReference<Tratamiento>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void tratamientoPostTest(){
        LocalTime duracion = LocalTime.of(3,0);
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre("nombre");
        Long numero = new Long(2);
        tratamiento.setId(numero);
        tratamiento.setPrecio(40);
        tratamiento.setDescripcion("descripcion");
        tratamiento.setDuracion(duracion);
        
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/tratamientos";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");

        HttpEntity<Tratamiento> entity = new HttpEntity<>(tratamiento,headers);

        ResponseEntity<Tratamiento> result = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<Tratamiento>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(tratamiento);
    }

    @Test
    public void tratamientoPutTest(){
        LocalTime duracion = LocalTime.of(3,0);
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre("nombre");
        Long numero = new Long(3);
        tratamiento.setId(numero);
        tratamiento.setPrecio(40);
        tratamiento.setDescripcion("descripcion");
        tratamiento.setDuracion(duracion);
        
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/tratamientos/" + numero + "/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");

        HttpEntity<Tratamiento> entity = new HttpEntity<>(tratamiento,headers);

        ResponseEntity<Tratamiento> result = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            entity,
            new ParameterizedTypeReference<Tratamiento>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(tratamiento);
    }


}
