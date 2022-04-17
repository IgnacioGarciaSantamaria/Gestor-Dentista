package com.IyJ.PracticaFinal.service.Implementation;

import java.util.List;

import com.IyJ.PracticaFinal.model.Cliente;
import com.IyJ.PracticaFinal.repository.ClienteRepository;
import com.IyJ.PracticaFinal.service.ClienteService;
import com.IyJ.PracticaFinal.joins.ClienteHistorialJoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class ClienteServiceImplementation implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Iterable<Cliente> retreiveClientes(String apellidos) {
        if( apellidos == null){
            return clienteRepository.findAll();
        } else {
            return clienteRepository.retreiveClientesByApellidos(apellidos);
        }
    }


    @Override
    public Cliente retreiveCliente(String dni){
        Cliente response = null;
        if(clienteRepository.existsById(dni)){
            Iterable<Cliente> clientes = clienteRepository.retreiveCliente(dni);
            for(Cliente cliente : clientes){
                response = cliente;
            }
            return response;
        }
        return response;
    }

    @Override
    public void deleteCliente(String dni){
        clienteRepository.deleteById(dni);
    }


    @Override
    public Cliente updateCliente(String dni,Cliente cliente){
        if(clienteRepository.existsById(dni)){
            return clienteRepository.save(cliente);
        } else {
            return null;
        }
    }

    @Override
    public List<ClienteHistorialJoin> retreiveClienteHistorial() {
        String query="SELECT CLIENTE.ID, HISTORIAL.ID, CLIENTE.NOMBRE, CLIENTE.APELLIDOS, CLIENTE.TELEFONO, CLIENTE.CORREO, HISTORIAL.ID_TRATAMIENTO "
				+ "FROM CLIENTE "
				+ "LEFT JOIN HISTORIAL ON CLIENTE.DNI=HISTORIAL.DNI;";
		
		
		List<ClienteHistorialJoin> clienteHistoriales = jdbcTemplate.query(
                query,
                (rs, rowNum) -> {
                        return new ClienteHistorialJoin(
                                rs.getLong("CLIENTE.ID"),
                                rs.getLong("HISTORIAL.ID"),
                                rs.getString("NOMBRE"),
                                rs.getString("APELLIDOS"),
                                rs.getInt("TELEFONO"),
                                rs.getString("CORREO"),
                                rs.getLong("ID_TRATAMIENTO"));
                }
        );
		
		
		return clienteHistoriales;
    }
}
