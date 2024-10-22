package com.example.ecommerce.controllers;

import com.example.ecommerce.models.Cliente;
import com.example.ecommerce.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente clienteNew = clienteService.createCliente(cliente);
        return ResponseEntity.ok(clienteNew);
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> listClientes() {
        List<Cliente> clientes = clienteService.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable int id) {
        Cliente cliente = clienteService.getCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable int id){
        Cliente clienteUpdated = clienteService.updateCliente(cliente, id);
        return ResponseEntity.ok(clienteUpdated);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteCliente(@PathVariable int id) {
        clienteService.deleteCliente(id);
    }
}
