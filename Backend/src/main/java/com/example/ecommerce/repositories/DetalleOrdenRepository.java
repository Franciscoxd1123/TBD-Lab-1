package com.example.ecommerce.repositories;

import com.example.ecommerce.models.DetalleOrden;
import java.util.List;

public interface DetalleOrdenRepository {
    DetalleOrden create(DetalleOrden detalleOrden);

    List<DetalleOrden> getAll();

    DetalleOrden getById(int id);

    DetalleOrden update(DetalleOrden detalleOrden, int id);

    void delete(int id);
}