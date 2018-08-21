<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@taglib tagdir="/WEB-INF/tags" prefix="caelum" %>    
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

		<link href="JS/jquery-ui.min.css" rel="stylesheet">
	    <script src="JS/external/jquery/jquery.js"></script>
	    <script src="JS/jquery-ui.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Tarefa</title>
</head>
	<body>
	
	

	
		<h3>Alterar tarefa - ${tarefa.id}</h3>
	      <form action="alteraTarefa" method="post">
	
	          <input type="hidden" name="id" value="${tarefa.id}" />
	
	          Descrição:<br />
	          <textarea name="descricao" cols="100" rows="5"><%--
	              --%>${tarefa.descricao}<%--
	          --%></textarea>
	          <br />            
	
	          Finalizado? <input type="checkbox" name="finalizado" 
	              value="true" ${tarefa.finalizado? 'checked' : '' }/> <br />            
	
	          Data de finalização: <br />
	          <input type="text" name="dataFinalizacao" 
	            value="<fmt:formatDate 
	              value="${tarefa.dataFinalizacao.time}" 
	              pattern="dd/MM/yyyy" />"/> 
	          <br />
	          
<%-- 	          Data de finalização: <caelum:campoData id="dataFinalizacao"/><br />  --%>
	
	          <input type="submit" value="Alterar"/>
	      </form>
	
	</body>
</html>