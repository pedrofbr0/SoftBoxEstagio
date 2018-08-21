<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
		<link href="JS/jquery-ui.min.css" rel="stylesheet">
	    <script src="JS/external/jquery/jquery.js"></script>
	    <script src="JS/jquery-ui.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>ALTERA CONTATO</title>
	</head>
	
	<body>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		<%@taglib tagdir="/WEB-INF/tags" prefix="caelum" %>
		 
		<c:import url="cabecalho.jsp" />
		
		<h1>ALTERA CONTATO</h1>
		
		 <table border="1">
	
			<tr bgcolor="ffa345">
				<td>ID</td>
				<td>NOME</td>
				<td>E-MAIL</td>
				<td>ENDEREÇO</td>
				<td>DATA DE NASCIMENTO</td>
			</tr>
			<tr bgcolor="ffffff">
					<td>${contato.id}</td>
					
					<td>${contato.nome}</td>
	
					<td><c:if test="${not empty contato.email}">
							<a href="mailto:${contato.email}">${contato.email}</a>
						</c:if> <c:if test="${empty contato.email}">
	                          		E-mail não informado
	                      		</c:if></td>
	
					<td>${contato.endereco}</td>
 
  					<td>
  					<fmt:formatDate value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy"/>
  					</td>
					
			</tr>
			
			</table>
		
		    
		    <hr/>
		    <form action="mvc">
		    	<input type="hidden" name="logica" value="AlterAddLogic"/>
		    	<input type="hidden" name="id" value="${contato.id}"/>
		        Nome: <input type="text" name="nome" value="${contato.nome}"/><br />
		        E-mail: <input type="text" name="email" value="${contato.email}" /><br />
		        Endereço: <input type="text" name="endereco" value="${contato.endereco}"/><br />
		        Data Nascimento: <caelum:campoData id="dataNascimento"/><br />
		      
		        <input type="submit" value="Alterar" />
		    </form>		
	
		<c:import url="rodape.jsp" />
	</body>
</html>