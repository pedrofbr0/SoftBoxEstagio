

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>LISTA CONTATOS</title>
	</head>
	
	<body>
	
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
		
		 
		<c:import url="cabecalho.jsp" />
	
		<!-- cria lista -->
		<jsp:useBean id="dao" class="br.com.caelum.jdbc.dao.ContatoDao" />
	
		<table border="1">
	
			<tr bgcolor="ffa345">
				<td>ID</td>
				<td>NOME</td>
				<td>E-MAIL</td>
				<td>ENDEREÇO</td>
				<td>DATA DE NASCIMENTO</td>
			</tr>
	
	
			<!-- percorre contatos montando as linhas da tabela -->
			<c:forEach var="contato" items="${dao.lista}" varStatus="id">
				<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }">
					<td>${id.count}</td>
					<td>${contato.nome}</td>
	
					<td><c:if test="${not empty contato.email}">
							<a href="mailto:${contato.email}">${contato.email}</a>
						</c:if> <c:if test="${empty contato.email}">
	                          		E-mail não informado
	                      		</c:if></td>
	
					<td>${contato.endereco}</td>
					<!-- <fmt: formatDate value="${contato.dataNascimento.time}" 
						pattern="dd/MM/yyyy"/> -->
					<td>${contato.dataNascimento.time}</td>
				</tr>
	
	
			</c:forEach>
		</table>
	
		<c:import url="rodape.jsp" />
	
	</body>
</html>