package com.IyJ.PracticaFinal.service.Implementation;

import java.util.Iterator;
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
    public Boolean createCliente(Cliente cliente) {
        Boolean response = false;
        Iterable<Cliente> lista = clienteRepository.retreiveCliente(cliente.getDni());
        Iterator<Cliente> it = lista.iterator();
        int sum = 0;
        while (it.hasNext()) {
            it.next();
            sum++;
        }
        if(sum == 0){
            clienteRepository.save(cliente);
            response = true; 
            return response;
        }else{
            return response;
        }
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
        if(clienteRepository.retreiveCliente(dni) != null){
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
        Long entrada = null;
        Iterable<Long> ids = clienteRepository.retreiveId(dni); 
        for(Long id : ids){
            entrada = id;
        }
        clienteRepository.deleteById(entrada);
    }


    @Override
    public Cliente updateCliente(String dni,Cliente cliente){
        Long entrada = null;
        Iterable<Long> ids = clienteRepository.retreiveId(dni); 
        for(Long id : ids){
            entrada = id;
        }
        if(entrada != null){
            clienteRepository.deleteById(entrada);
            return clienteRepository.save(cliente);
        } else {
            return null;
        }
    }

    @Override
    public List<ClienteHistorialJoin> retreiveClienteHistorial() {
        String query="SELECT HISTORIAL.ID, CLIENTE.NOMBRE, CLIENTE.APELLIDOS, CLIENTE.DNI, HISTORIAL.DATE, HISTORIAL.TIME, HISTORIAL.ID_TRATAMIENTO "
				+ "FROM CLIENTE "
				+ "JOIN HISTORIAL ON CLIENTE.DNI=HISTORIAL.DNI;";
		
		
		List<ClienteHistorialJoin> clienteHistoriales = jdbcTemplate.query(
                query,
                (rs, rowNum) -> {
                        return new ClienteHistorialJoin(
                                rs.getLong("HISTORIAL.ID"),
                                rs.getString("NOMBRE"),
                                rs.getString("APELLIDOS"),
                                rs.getString("CLIENTE.DNI"),
                                rs.getDate("DATE"),
                                rs.getTime("TIME"),
                                rs.getLong("ID_TRATAMIENTO"));
                }
        );
		
		return clienteHistoriales;
    }
}
