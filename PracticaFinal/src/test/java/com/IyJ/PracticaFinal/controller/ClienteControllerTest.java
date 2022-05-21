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

import com.IyJ.PracticaFinal.joins.ClienteHistorialJoin;
import com.IyJ.PracticaFinal.model.Cliente;
import com.IyJ.PracticaFinal.service.ClienteService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ClienteService service;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void clienteGetTest() {
        Cliente cliente = service.retreiveCliente("90753587A");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/clientes/90753587A/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Cliente> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Cliente>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(cliente);
    }

    public void clientesGetTest() {
        Iterable<Cliente> clientes = service.retreiveClientes(null);

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/clientes";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<Cliente>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<Iterable<Cliente>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(clientes);
    }

    @Test
    public void clienteDeleteTest(){
        String id = "89343029N";
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/clientes/" +id +"/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Cliente> result = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            entity,
            new ParameterizedTypeReference<Cliente>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }



    @Test
    public void clientePostTest(){
        Cliente cliente = new Cliente();
        Long numero = new Long(17);
        cliente.setDni("53857194A");
        cliente.setId(numero);
        cliente.setNombre("Ignacio");
        cliente.setApellidos("Garcia Santamaria");
        cliente.setTelefono(628389354);
        cliente.setCorreo("ignacio7gs@gmail.com");
        
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/clientes";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");

        HttpEntity<Cliente> entity = new HttpEntity<>(cliente,headers);

        ResponseEntity<Cliente> result = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<Cliente>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void clienteHistorialJoinTest(){
        List<ClienteHistorialJoin> clienteHistorial = service.retreiveClienteHistorial();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/join/clientes/historiales";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.add("Authorization", "Basic amFpbWU6ZGVudGlzdGE=");

        ResponseEntity<List<ClienteHistorialJoin>> result = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<List<ClienteHistorialJoin>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
