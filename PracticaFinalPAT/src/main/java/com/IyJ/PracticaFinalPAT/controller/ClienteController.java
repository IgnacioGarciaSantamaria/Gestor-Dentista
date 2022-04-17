package com.IyJ.PracticaFinalPAT.controller;

import com.IyJ.PracticaFinalPAT.model.Cliente;
import com.IyJ.PracticaFinalPAT.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/clientes")
    public ResponseEntity<Iterable<Cliente>> retrieveClientes(@RequestParam(required=false) String nombre) {

        Iterable<Cliente> response = clienteService.retrieveClientes(nombre);
        for (Cliente cliente : response) {
            System.out.println(cliente.toString());
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> createWine(@RequestBody Cliente cliente) {
        //cliente.setDni(null);
        Cliente newCliente = clienteService.createCliente(cliente);
        return ResponseEntity.ok().body(newCliente);
    }

    @GetMapping("/clientes/{dni}")
    public ResponseEntity<Cliente> retrieveCliente(@PathVariable String dni) {
        Cliente response = clienteService.retrieveCliente(dni);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/clientes/{dni}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable String dni, @RequestBody Cliente cliente) {
        Cliente newCliente = clienteService.updateCliente(dni, cliente);
        if (newCliente == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(newCliente);
    }

    @DeleteMapping("/clientes/{dni}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable String dni) {
        clienteService.deleteCliente(dni);
        return ResponseEntity.noContent().build();
    }
    
}