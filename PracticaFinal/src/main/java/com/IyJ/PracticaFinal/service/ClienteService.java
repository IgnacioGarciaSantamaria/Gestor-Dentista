package com.IyJ.PracticaFinal.service;

import com.IyJ.PracticaFinal.model.Cliente;

public interface ClienteService {
    Iterable<Cliente> retreiveClientes(String apellidos);
    Cliente retreiveCliente(String dni);
    void deleteCliente(String dni);
    Cliente updateCliente(String dni,Cliente cliente);
}
