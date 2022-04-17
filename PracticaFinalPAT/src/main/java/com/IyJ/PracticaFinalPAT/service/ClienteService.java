package com.IyJ.PracticaFinalPAT.service;

import com.IyJ.PracticaFinalPAT.model.Cliente;

public interface ClienteService {
    Cliente createCliente(Cliente cliente);
    Iterable<Cliente> retrieveClientes(String nombre);
    Cliente retrieveCliente(String dni);
    Cliente updateCliente(String dni, Cliente cliente);
    void deleteCliente(String dni);
}
