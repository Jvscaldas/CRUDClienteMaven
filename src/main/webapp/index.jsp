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
		<form action="cliente" method="post">
			<p class="title">
				<b>CLIENTE</b>
			</p>
			<table>
				<tr>
					<td>
						<input class="input_data" type="text" 
						id="cpf" name="cpf"
						placeholder="CPF"
						value='<c:out value="${cliente.cpf }"></c:out>'>
					</td>
				</tr>
					<tr>
					<td>
						<input class="input_data" type="text" 
						id="nome" name="nome"
						placeholder="Nome"
						value='<c:out value="${cliente.nome }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td>
						<input class="input_data" type="text" 
						id="email" name="email"
						placeholder="Email"
						value='<c:out value="${cliente.email }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td>
						<input class="input_data" type="number" 
						min="0" step="0.01" id="limite" name="limite"
						placeholder="Limite de Crédito"
						value='<c:out value="${cliente.limite_de_credito }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" 
						id="botao" name="botao"
						value="Inserir">
						
						<input type="submit" 
						id="botao" name="botao"
						value="Atualizar">
						
						<input type="submit" 
						id="botao" name="botao"
						value="Deletar">
						
						<input type="submit" 
						id="botao" name="botao"
						value="Listar">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<br />
	<div align="center">
		<H2><c:out value="${erro}" /></H2>	
	</div>
	<div align="center">
		<H3><c:out value="${saida}" /></H3>	
	</div>
</body>
</html>