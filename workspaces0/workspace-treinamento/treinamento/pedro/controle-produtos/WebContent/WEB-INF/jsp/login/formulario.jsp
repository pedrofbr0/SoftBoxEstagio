<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"> </script>

<link type="text/css" href="<c:url value="/css/bootstrap.css"/>"rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN</title>
</head>
<body>

	<h2 class="d-inline p-2 bg-dark text-white">LOGIN NO CONTROLE DE PRODUTOS</h2>
	
	<form action="<c:url value="/login/autentica"/>">
	
		<br/>
		
		<ul id = "erro">
			<c:forEach items="${errors}" var="error">
					<li class="text-danger">${error.category }-${error.message }</li>
			</c:forEach>
		</ul>
	
	<div class="form-group">
	
		<label for="usuario">Login: </label>
		<input type="text" class="form-control" name="usuario.login"/>
		
	</div>
		<br/>
		
	<div class="form-group">
	
		<label for="senha">Senha: </label>
		<input type="password" class="form-control" name="usuario.senha" />
		
	</div>
		
		<input type="submit" value="Autenticar" class = "btn btn-default active" />
		
		
	</form>
	
<!-- 	<h2 class="d-inline p-2 bg-dark text-white">LOGIN NO CONTROLE DE PRODUTOS</h2> -->
	
<%-- 	<form action="<c:url value="/login/autentica"/>"> --%>
<!-- 		Login: <input type="text" name="usuario.login" /><br /> Senha: <input -->
<!-- 			type="password" name="usuario.senha" /> <input type="submit" -->
<!-- 			value="Autenticar" class = "btn btn-default active" /> -->
<!-- 	</form> -->
	

</body>
</html>