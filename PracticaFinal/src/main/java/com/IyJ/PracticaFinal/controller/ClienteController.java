package com.IyJ.PracticaFinal.controller;

import com.IyJ.PracticaFinal.model.Cliente;
import com.IyJ.PracticaFinal.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Iterable<Cliente>> retreiveClientes(@RequestParam(required=false) String apellidos){
        Iterable<Cliente> response = clienteService.retreiveClientes(apellidos);
        return ResponseEntity.ok().body(response);
    }

    // /api/v1/clientes/?apellidos=Fernandez-Picazo

    @GetMapping("/clientes/{dni}/")
    public ResponseEntity<Cliente> retreiveWine(@PathVariable String dni){
        Cliente response = clienteService.retreiveCliente(dni);
        return ResponseEntity.ok().body(response);
    }

    // /api/v1/clientes/02634832K/

    @DeleteMapping("/clientes/{dni}/")
    public ResponseEntity<Cliente> deleteWine(@PathVariable String dni){
        clienteService.deleteCliente(dni);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/clientes/{dni}/")
    public ResponseEntity<Cliente> updateWine(@PathVariable String dni, @RequestBody Cliente cliente){
        Cliente newCliente = clienteService.updateCliente(dni, cliente);
        if (newCliente == null){
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok().body(newCliente);
        }
    }
}
