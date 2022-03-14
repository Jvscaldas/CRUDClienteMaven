package persistence;

import java.sql.SQLException;

import model.Cliente;

public interface IClienteDao {

	public String cpfPessoa (String op, Cliente c) throws SQLException, ClassNotFoundException;
	
}
