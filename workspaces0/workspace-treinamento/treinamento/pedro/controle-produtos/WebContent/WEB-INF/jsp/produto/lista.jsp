<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"> </script>

<link type="text/css" href="<c:url value="/css/bootstrap.css"/>"rel="stylesheet" />

<link type="text/css" href="<c:url value="/css/jquery.css"/>"rel="stylesheet" />

<link type="text/css" href="<c:url value="/css/tarefas.css"/>"rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>LISTA</title>
</head>
<body>


    
    <script type="text/javascript" src="<c:url value="/js/produto/lista.js"/>"></script>

<h1>PRODUTOS</h1>
<div id="mensagem"></div>

		<ul id = "erro">
			<c:forEach items="${errors}" var="error">
					<li class="text-danger">${error.category }-${error.message }</li>
			</c:forEach>
		</ul>
		
		
<table class="bg-white text-black" class="shadow-none p-3 mb-5 bg-light rounded">

		<tr class="bg-dark text-white" class="text-center">
			<td>NOME</td>
			<td>PREÇO</td>
			<td>DESCRIÇÃO</td>
			<td>DATA DE INÍCIO DA VENDA</td>
		</tr>

	<c:forEach var="produto" items="${produtoList}">
		<tr id="produto${produto.id}" class="text-center">
			<td>${produto.nome}</td>
			<td>${produto.preco}</td>
			<td>${produto.descricao}</td>
			<td><fmt:formatDate pattern="dd/MM/yyyy"
					value="${produto.dataInicioVenda.time}" /></td>

			<td><a href="#" onclick="return removeProduto(${produto.id})">
					REMOVER </a></td>
					
		</tr>
	</c:forEach>
</table>

<a class="btn btn-default" href="#" onclick="return adicionaProduto()" role="button">Adicionar</a>

<!-- <a href="#" onclick="return logout()">Sair do sistema</a> -->

<a class="btn btn-default" href="/controle-produtos/login/logout" role="button">Sair do Sistema</a>
