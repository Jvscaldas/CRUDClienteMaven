package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import persistence.ClienteDao;
import persistence.GenericDao;

@WebServlet("/cliente1")
public class CPFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CPFServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cliente c = new Cliente();

		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String limite_de_credito = request.getParameter("limite");
		String dt_nascimento = request.getParameter("dt_nascimento");
		String botao = request.getParameter("botao");
		String op = botao.substring(0, 1);

		if (op.equals("A")) {
			op = "U";
		}

		GenericDao gDao = new GenericDao();
		ClienteDao cDao = new ClienteDao(gDao);
		String erro = "";
		String saida = "";
		List<Cliente> clientes = new ArrayList<Cliente>();

		c = validaCliente(botao, cpf, nome, email, limite_de_credito, dt_nascimento);

		try {
			if (botao.equals("Listar")) {
				clientes = cDao.findClientes();
			} else {
				if (c != null) {
					if (botao.equals("Inserir") || botao.equals("Atualizar") || botao.equals("Deletar")) {
						saida = cDao.iudCliente(op, c);
						c = null;
					}
					if (botao.equals("Consultar")) {
						c = cDao.findCliente(c);
					}
				} else {
					erro = "Preencha os campos";
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("cliente", c);
			request.setAttribute("clientes", clientes);
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			rd.forward(request, response);
		}

	}

	private Cliente validaCliente(String botao, String cpf, String nome, String email, String limite_de_credito,
			String dt_nascimento) {
		Cliente c = new Cliente();

		if (botao.equals("Inserir") || botao.equals("Atualizar")) {
			if (cpf.equals("") || nome.equals("") || email.equals("") || limite_de_credito.equals("")
					|| dt_nascimento.equals("")) {
				c = null;
			} else {
				c.setCpf(cpf);
				c.setNome(nome);
				c.setEmail(email);
				c.setLimite_de_credito(Float.parseFloat(limite_de_credito));
				c.setDt_nascimento(dt_nascimento);
			}
		}

		if (botao.equals("Consultar") || botao.equals("Deletar")) {
			if (cpf.equals("")) {
				c = null;
			} else {
				c.setCpf(cpf);
			}
		}

		return c;
	}

}
