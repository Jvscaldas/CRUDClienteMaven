package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDao implements IClienteDao {

	private GenericDao gDao;

	public ClienteDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String iudCliente(String op, Cliente c) throws SQLException, ClassNotFoundException {

		Connection con = gDao.getConnection();

		String sql = "{CALL sp_iud_cliente (?,?,?,?,?,?,?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, op);
		cs.setString(2, c.getCpf());
		cs.setString(3, c.getNome());
		cs.setString(4, c.getEmail());
		cs.setFloat(5, c.getLimite_de_credito());
		cs.setString(6, c.getDt_nascimento());
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();

		String saida = cs.getString(7);

		cs.close();
		con.close();

		return saida;

	}

	@Override
	public Cliente findCliente(Cliente c) throws SQLException, ClassNotFoundException {
		Connection con = gDao.getConnection();

		String sql = "SELECT * FROM cliente WHERE cpf = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getCpf());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			c.setCpf(rs.getString("cpf"));
			c.setNome(rs.getString("nome"));
			c.setEmail(rs.getString("email"));
			c.setLimite_de_credito(rs.getFloat("limite_de_credito"));
			c.setDt_nascimento(rs.getString("dt_nascimento"));
		}

		rs.close();
		ps.close();
		con.close();

		return c;
	}

	@Override
	public List<Cliente> findClientes() throws SQLException, ClassNotFoundException {
		Connection con = gDao.getConnection();

		List<Cliente> clientes = new ArrayList<Cliente>();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT SUBSTRING(cpf,1,3)+'.'+SUBSTRING(cpf,4,3)+'.'+SUBSTRING(cpf,7,3)+'-'+SUBSTRING(cpf,10,2) AS cpf, ");
		sql.append("nome, email, limite_de_credito, ");
		sql.append("CONVERT(CHAR(10), dt_nascimento, 103) AS dt_nascimento FROM cliente");
		PreparedStatement ps = con.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cliente c = new Cliente();

			c.setCpf(rs.getString("cpf"));
			c.setNome(rs.getString("nome"));
			c.setEmail(rs.getString("email"));
			c.setLimite_de_credito(rs.getFloat("limite_de_credito"));
			c.setDt_nascimento(rs.getString("dt_nascimento"));

			clientes.add(c);
		}

		rs.close();
		ps.close();
		con.close();

		return clientes;
	}

}
