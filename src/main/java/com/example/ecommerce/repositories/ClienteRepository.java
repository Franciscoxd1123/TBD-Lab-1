import com.example.ecommerce.models.Cliente;

import java.util.List;

import com.example.ecommerce.models.Cliente;
public interface ClienteRepository {

    Cliente create(Cliente cliente);

    List<Cliente> getAll();

    Cliente getClienteId(int id);

    Cliente update(Cliente cliente, int id);

    void delete(int id);


}