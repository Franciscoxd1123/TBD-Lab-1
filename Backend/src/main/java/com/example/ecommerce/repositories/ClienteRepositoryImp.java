package com.example.ecommerce.repositories;

import com.example.ecommerce.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Repository
public class ClienteRepositoryImp implements ClienteRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public Cliente create(Cliente cliente){
        String sql = "INSERT INTO Cliente (nombre, direccion, email, telefono) " +
                "VALUES (:nombre, :direccion, :email, :telefono) " +
                "RETURNING id_cliente";
        try (Connection con = sql2o.open()) {
            Long id = con.createQuery(sql, true)
                    .addParameter("nombre", cliente.getNombre())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("telefono", cliente.getTelefono())
                    .executeAndFetchFirst(Long.class);

            cliente.setIdCliente(id);
            return cliente;
        }
        catch (Exception e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> getAll() {
        String sql = "SELECT id_cliente AS idCliente, nombre, direccion, email, telefono FROM Cliente";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Cliente.class);
        }
        catch (Exception e) {
            System.out.println("Error al consultar los clientes: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Cliente getClienteId(int id){
        String sql = "SELECT id_cliente AS idCliente, nombre, direccion, email, telefono FROM Cliente WHERE id_cliente = :id";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Cliente.class);
        }
        catch (Exception e) {
            System.out.println("Error al consultar el cliente: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Cliente update(Cliente cliente, int id){
        String sql = "UPDATE Cliente SET Nombre = :Nombre, Direccion = :Direccion, Email = :Email, Telefono = :Telefono WHERE id_cliente = :id";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("Nombre", cliente.getNombre())
                    .addParameter("Direccion", cliente.getDireccion())
                    .addParameter("Email", cliente.getEmail())
                    .addParameter("Telefono", cliente.getTelefono())
                    .executeUpdate();
            return cliente;
        }
        catch (Exception e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(int id){
        String sql = "DELETE FROM Cliente WHERE id_cliente = :id";

        try (Connection con = sql2o.open()){
            con.createQuery(sql).addParameter("id",id).executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        }
    }


}
