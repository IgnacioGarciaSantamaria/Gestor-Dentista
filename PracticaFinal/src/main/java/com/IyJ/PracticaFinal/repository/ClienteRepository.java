package com.IyJ.PracticaFinal.repository;

import com.IyJ.PracticaFinal.model.Cliente;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,Long>{
    @Query("SELECT * FROM CLIENTE WHERE CLIENTE.APELLIDOS= :apellidos")
    public Iterable<Cliente> retreiveClientesByApellidos(String apellidos);

    @Query("SELECT * FROM CLIENTE WHERE CLIENTE.DNI= :dni")
    public Iterable<Cliente> retreiveCliente(String dni);

    @Query("SELECT ID FROM CLIENTE WHERE CLIENTE.DNI= :dni")
    public Iterable<Long> retreiveId(String dni);
}
