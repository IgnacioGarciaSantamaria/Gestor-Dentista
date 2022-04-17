package com.IyJ.PracticaFinalPAT.service.implementation;

import com.IyJ.PracticaFinalPAT.model.Cliente;
import com.IyJ.PracticaFinalPAT.repository.ClienteRepository;
import com.IyJ.PracticaFinalPAT.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplementation implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Iterable<Cliente> retrieveClientes(String nombre) {
        if (nombre == null) {
            return clienteRepository.findAll();
        } else {
            return clienteRepository.retrieveClientesByNombre(nombre);
        }
    }

    @Override
    public Cliente retrieveCliente(String dni) {
        Cliente response = null;
        if (clienteRepository.existsById(dni)) {
            Iterable<Cliente> clientes = clienteRepository.retrieveCliente(dni);
            for (Cliente cliente : clientes) {
                response = cliente;
            }
            return response;
        }
        return response;
    }

    @Override
    public Cliente updateCliente(String dni, Cliente cliente) {
        if (clienteRepository.existsById(dni)) {
            return clienteRepository.save(cliente);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCliente(String dni) {
        clienteRepository.deleteById(dni);
    }    
}
