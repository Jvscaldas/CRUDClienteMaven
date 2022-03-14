package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import model.Cliente;

public class ClienteDao implements IClienteDao {

	private GenericDao gDao;

	public ClienteDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String cpfPessoa(String op, Cliente c) throws SQLException, ClassNotFoundException {

		Connection con = gDao.getConnection();

		String sql = "{CALL sp_cliente (?,?,?,?,?,?,?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, op);
		cs.setInt(2, c.getCpf());
		cs.setString(3, c.getNome());
		cs.setString(4, c.getEmail());
		cs.setFloat(5, c.getLimite_de_credito());
		cs.setDate(6, c.getDt_nascimento());
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();

		String saida = cs.getString(7);

		cs.close();
		con.close();

		return saida;

	}

}
