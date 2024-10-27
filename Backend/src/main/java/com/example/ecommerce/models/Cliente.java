package com.example.ecommerce.models;

import lombok.Data;
@Data
public class Cliente{

    private Long idCliente;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;
}