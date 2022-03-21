<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>CRUD cliente</title>
</head>
<body>
	<div align="center" class="container">
		<form action="cliente1" method="post">
			<p class="title">
				<b>CLIENTE</b>
			</p>
			<table>
				<tr>
					<td><input class="input_data" type="number" id="cpf"
						name="cpf" placeholder="CPF"
						value='<c:out value="${cliente.cpf }"></c:out>'></td>
					<td><input type="submit" id="botao" name="botao"
						value="Consultar"></td>
				</tr>
				<tr>
					<td><input class="input_data" type="text" id="nome"
						name="nome" placeholder="Nome"
						value='<c:out value="${cliente.nome }"></c:out>'></td>
				</tr>
				<tr>
					<td><input class="input_data" type="text" id="email"
						name="email" placeholder="Email"
						value='<c:out value="${cliente.email }"></c:out>'></td>
				</tr>
				<tr>
					<td><input class="input_data" type="number" min="0"
						step="0.01" id="limite" name="limite"
						placeholder="Limite de Crédito"
						value='<c:out value="${cliente.limite_de_credito }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td><input class="input_data" type="date" id="dt_nascimento"
						name="dt_nascimento" placeholder="Data de Nascimento"
						value='<c:out value="${cliente.dt_nascimento }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td><input type="submit" id="botao" name="botao"
						value="Inserir"> <input type="submit" id="botao"
						name="botao" value="Atualizar"> <input type="submit"
						id="botao" name="botao" value="Deletar"> <input
						type="submit" id="botao" name="botao" value="Listar"></td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2>
				<c:out value="${erro }" />
			</H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty saida }">
			<H3>
				<c:out value="${saida }" />
			</H3>
		</c:if>
	</div>
	<br />
	<br />
	<div align="center">
		<c:if test="${not empty clientes }">
			<table class="table_round">
				<thead>
					<tr>
						<th>CPF</th>
						<th>Nome</th>
						<th>E-Mail</th>
						<th>Limite de Crédito</th>
						<th>Data Nascimento</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${clientes }">
						<tr>
							<td><c:out value="${c.cpf }" /></td>
							<td><c:out value="${c.nome }" /></td>
							<td><c:out value="${c.email }" /></td>
							<td><c:out value="${c.limite_de_credito }" /></td>
							<td><c:out value="${c.dt_nascimento }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>