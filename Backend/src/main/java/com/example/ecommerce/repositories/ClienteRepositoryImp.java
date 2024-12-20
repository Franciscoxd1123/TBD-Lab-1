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
        String sql = "INSERT INTO Cliente (nombre, direccion, email, telefono, password) " +
                "VALUES (:nombre, :direccion, :email, :telefono, :password) " +
                "RETURNING id_cliente";
        try (Connection con = sql2o.open()) {
            Integer id = con.createQuery(sql, true)
                    .addParameter("nombre", cliente.getNombre())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("telefono", cliente.getTelefono())
                    .addParameter("password", cliente.getPassword())
                    .executeUpdate()
                    .getKey(Integer.class);

            cliente.setIdCliente(Long.valueOf(id));
            return cliente;
        }
        catch (Exception e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> getAll() {
        String sql = "SELECT id_cliente AS idCliente, nombre, direccion, email, telefono, password FROM Cliente";

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
        String sql = "SELECT id_cliente AS idCliente, nombre, direccion, email, telefono, password FROM Cliente WHERE id_cliente = :id";

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
        String sql = "UPDATE Cliente SET Nombre = :Nombre, Direccion = :Direccion, Email = :Email, Telefono = :Telefono, Password = :Password  WHERE id_cliente = :id";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("Nombre", cliente.getNombre())
                    .addParameter("Direccion", cliente.getDireccion())
                    .addParameter("Email", cliente.getEmail())
                    .addParameter("Telefono", cliente.getTelefono())
                    .addParameter("Password", cliente.getPassword())
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

    @Override
    public boolean existeEmail(String email) {
        String sql = "SELECT COUNT(*) FROM Cliente WHERE email = :email";
        try (Connection con = sql2o.open()) {
            int count = con.createQuery(sql)
                    .addParameter("email", email)
                    .executeScalar(Integer.class);
            return count > 0;
        } catch (Exception e) {
            System.out.println("Error al verificar el email: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente findByEmail(String email) {
        String sql = "SELECT id_cliente AS idCliente, nombre, direccion, email, telefono, password FROM Cliente WHERE email = :email";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("email", email)
                    .executeAndFetchFirst(Cliente.class);
        } catch (Exception e) {
            System.out.println("Error al intentar iniciar sesión: " + e.getMessage());
            return null;
        }
    }
}
