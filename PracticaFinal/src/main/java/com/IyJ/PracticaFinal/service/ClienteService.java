package com.IyJ.PracticaFinal.service;

import java.util.List;

import com.IyJ.PracticaFinal.model.Cliente;
import com.IyJ.PracticaFinal.joins.ClienteHistorialJoin;

public interface ClienteService {
    Iterable<Cliente> retreiveClientes(String apellidos);
    Cliente retreiveCliente(String dni);
    void deleteCliente(String dni);
    Cliente updateCliente(String dni,Cliente cliente);
    Boolean createCliente(Cliente cliente);

    List<ClienteHistorialJoin> retreiveClienteHistorial();
}
