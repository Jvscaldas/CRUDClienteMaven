package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Cliente;

public interface IClienteDao {

	public String iudCliente (String op, Cliente c) throws SQLException, ClassNotFoundException;
	public Cliente findCliente (Cliente c) throws SQLException, ClassNotFoundException;
	public List<Cliente> findClientes() throws SQLException, ClassNotFoundException;
	
}
