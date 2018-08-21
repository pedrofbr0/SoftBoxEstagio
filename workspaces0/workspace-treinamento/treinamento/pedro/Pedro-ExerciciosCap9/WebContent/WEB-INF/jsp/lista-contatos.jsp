<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="css/jquery-ui.min.css" rel="stylesheet">
	    <script src="js/jquery.js"></script>
	    <script src="js/jquery-ui.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>LISTA CONTATOS</title>
	</head>
	
	<body>
	
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		 
		<c:import url="cabecalho.jsp" />
	
		<table border="1">
	
			<tr bgcolor="ffa345">
				<td>ID</td>
				<td>NOME</td>
				<td>E-MAIL</td>
				<td>ENDEREÇO</td>
				<td>DATA DE NASCIMENTO</td>
			</tr>
	
	
			<!-- percorre contatos montando as linhas da tabela -->
			<c:forEach var="contato" items="${contatos}" varStatus="id">
				<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }">
					<td>${id.count}</td>
					
					<td>${contato.nome}</td>
	
					<td><c:if test="${not empty contato.email}">
							<a href="mailto:${contato.email}">${contato.email}</a>
						</c:if> <c:if test="${empty contato.email}">
	                          		E-mail não informado
	                      		</c:if></td>
	
					<td>${contato.endereco}</td>
					<td>
					<fmt:formatDate value="${contato.dataNascimento.time}" 
						pattern="dd/MM/yyyy"/>
					</td>
					
					<td>
         				<a href="mvc?logica=GetIdContato&id=${contato.id}">Alterar</a>
       				</td>
       				
					<td>
         				<a href="mvc?logica=RemoveContatoLogic&id=${contato.id}">Remover</a>
       				</td>
				</tr>
	
	
			</c:forEach>
		</table>
		
		<a href="mvc?logica=GetIdContato&id=0">Adicionar novo Contato</a>
	
		<c:import url="rodape.jsp" />
	</body>
</html>