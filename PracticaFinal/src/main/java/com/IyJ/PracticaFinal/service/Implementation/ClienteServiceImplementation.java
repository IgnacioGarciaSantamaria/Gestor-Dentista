package com.IyJ.PracticaFinal.service.Implementation;

import com.IyJ.PracticaFinal.model.Cliente;
import com.IyJ.PracticaFinal.repository.ClienteRepository;
import com.IyJ.PracticaFinal.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplementation implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

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

}
