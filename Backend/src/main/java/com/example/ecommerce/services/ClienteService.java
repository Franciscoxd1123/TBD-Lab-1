package com.example.ecommerce.services;

import com.example.ecommerce.models.Cliente;
import com.example.ecommerce.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente createCliente(Cliente cliente){
        return clienteRepository.create(cliente);
    }

    public List<Cliente> getClientes(){
        return clienteRepository.getAll();
    }

    public Cliente getCliente(int id){
        return clienteRepository.getClienteId(id);
    }

    public Cliente updateCliente(Cliente cliente, int id){
        return clienteRepository.update(cliente, id);
    }

    public void deleteCliente(int id){
        clienteRepository.delete(id);
    }
}
