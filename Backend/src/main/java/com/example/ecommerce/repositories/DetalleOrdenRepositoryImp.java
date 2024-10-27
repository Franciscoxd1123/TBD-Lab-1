package com.example.ecommerce.repositories;

import com.example.ecommerce.models.DetalleOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Repository
public class DetalleOrdenRepositoryImp implements DetalleOrdenRepository {
    @Autowired
    private Sql2o sql2o;

    public DetalleOrden create(DetalleOrden detalleOrden) {
        String sql = "INSERT INTO DetalleOrden (id_orden, id_producto, cantidad, precio_unitario) " +
                "VALUES (:idOrden, :idProducto, :cantidad, :precioUnitario) " +
                "RETURNING id_detalle";

        try (Connection con = sql2o.open()) {
            Long id = con.createQuery(sql, true)
                    .addParameter("idOrden", detalleOrden.getIdOrden())
                    .addParameter("idProducto", detalleOrden.getIdProducto())
                    .addParameter("cantidad", detalleOrden.getCantidad())
                    .addParameter("precioUnitario", detalleOrden.getPrecioUnitario())
                    .executeAndFetchFirst(Long.class);

            detalleOrden.setIdDetalle(id);
            return detalleOrden;
        }
    }

    public List<DetalleOrden> getAll() {
        String sql = "SELECT * FROM DetalleOrden";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(DetalleOrden.class);
        } catch (Exception e) {
            System.out.println("Error al consultar los detalles de orden: " + e.getMessage());
            return null;
        }
    }

    public DetalleOrden getById(int id) {
        String sql = "SELECT * FROM DetalleOrden WHERE id_detalle = :idDetalle";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("idDetalle", id).executeAndFetchFirst(DetalleOrden.class);
        } catch (Exception e) {
            System.out.println("Error al consultar el detalle de orden: " + e.getMessage());
            return null;
        }
    }

    public DetalleOrden update(DetalleOrden detalleOrden, int id) {
        String sql = "UPDATE DetalleOrden SET id_orden = :idOrden, id_producto = :idProudcto, cantidad = :cantidad, precio_unitario = :precioUnitario";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("idOrden", detalleOrden.getIdOrden())
                    .addParameter("idProducto", detalleOrden.getIdProducto())
                    .addParameter("cantidad", detalleOrden.getCantidad())
                    .addParameter("precioUnitario", detalleOrden.getPrecioUnitario())
                    .executeUpdate();
            return detalleOrden;
        } catch (Exception e) {
            System.out.println("Error al actualizar el detalle de orden: " + e.getMessage());
            return null;
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM DetalleOrden WHERE id_orden = :idOrden";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("idOrden", id).executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar el detalle de orden: " + e.getMessage());
        }
    }
}
