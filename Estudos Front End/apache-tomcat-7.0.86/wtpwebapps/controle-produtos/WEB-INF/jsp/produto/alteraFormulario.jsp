<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ALTERA FORMULÁRIO</title>
</head>
<body>

</body>
</html>

<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>">
                  </script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>">
                  </script>
<link type="text/css" href="<c:url value="/css/jquery.css"/>"
	rel="stylesheet" />
<link type="text/css" href="/controle-produtos/css/bootstrap.css" rel="stylesheet" /> 

</head>
<body>
	<form action="<c:url value="/produto/altera"/>">
		<input type="hidden" name="id" value="${produto.id}"> Nome: <input
			name="produto.nome" value="${produto.nome}" /><br /> Descricao: <input
			name="produto.descricao" /><br /> Preo: <input
			name="produto.preco" /><br /> Data de incio da venda:
		<caelum:campoData id="dataInicioVenda" name="produto.dataInicioVenda" />
		<br /> <input type="submit" value="Alterar" />
	</form>
</body>
</html>
