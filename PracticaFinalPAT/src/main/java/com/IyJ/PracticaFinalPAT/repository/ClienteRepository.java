package com.IyJ.PracticaFinalPAT.repository;

import com.IyJ.PracticaFinalPAT.model.Cliente;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, String> {
    @Query("SELECT * FROM CLIENTES WHERE CLIENTES.NOMBRE= :nombre")
    public Iterable<Cliente> retrieveClientesByNombre(String nombre);

    @Query("SELECT * FROM CLIENTES WHERE CLIENTE.DNI= :dni")
    public Iterable<Cliente> retrieveCliente(String dni);
}
