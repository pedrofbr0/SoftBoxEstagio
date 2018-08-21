<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>

<script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>


<link type="text/css" href="<c:url value="/css/jquery.css"/>"
	rel="stylesheet" />

<link type="text/css" href="<c:url value="/css/bootstrap.css"/>"
	rel="stylesheet" />

<script type="text/javascript">
	$("#Data").datepicker(
			{
				dateFormat : 'dd/mm/yy',
				changeMonth : true,
				changeYear : true,
				dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta',
						'Sexta', 'Sábado' ],
				dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D' ],
				dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex',
						'Sáb', 'Dom' ],
				monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril',
						'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro',
						'Outubro', 'Novembro', 'Dezembro' ],
				monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun',
						'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
				nextText : 'Próximo',
				prevText : 'Anterior'
			});
	
	 function checaProduto(){
		 	     
	      let erro = 0;
	      var formData = new FormData(document.querySelector('form'))
	     
	      p = $('form').serializeArray();
	     
	      $.each(p, function(i, field){
	            if(field.name=="produto.nome" && field.value.length<3){
	                alert("Nome Inválido (min 3 caracteres)");
	                erro = 1;
	                return false;
	            }else if(field.name=="produto.descricao" && field.value.length<3){
	                alert("Descricao Inválida (min 3 caracteres)");
	                erro = 1;
	                return false;
	            } else if(field.name=="produto.preco" && field.value<0){
	                alert("Preço Inválido (negativo)");
	                erro = 1;
	                return false;
			    } else if(field.name=="produto.dataInicioVenda" ==""){
		            alert("Data Inválida");
		            erro = 1;
		            return false;
		        } else if(field.name=="produto.acesso" && (field.value != 'v' || 'g' || 'c')){
		            alert("Acesso Inválido");
		            erro = 1;
		            return false;
		        }
	            
	            //alert(field.name + " valor: " + field.value);
	        });
	     
	      if(erro==1){
	          alert("Ocorreu um erro");
	          return false;
	      }
	     
	  alert(str);
	//   console.log(str);
	  }
</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>FORMULARIO</title>
</head>
<body>

	<ul id="erro">
		<c:forEach items="${errors}" var="error">
			<li class="text-danger">${error.category }-${error.message }</li>
		</c:forEach>
	</ul>


	<h1 class="bg-primary">ADICIONANDO PRODUTO</h1>

	<form action="<c:url value="/produto/adiciona"/>" onsubmit="return checaProduto()" method="post">

		<div class="form-group">
			<label for="produto.nome">NOME:</label> <input name="produto.nome"
				class="form-control" id="produto.nome">
		</div>

		<div class="form-group">
			<label for="produto.preco">PREÇO:</label> <input name="produto.preco"
				class="form-control" id="produto.preco">
		</div>

		<div class="form-group">
			<label for="produto.descricao">DESCRIÇÃO:</label> <input
				name="produto.descricao" class="form-control" id="produto.descricao">
		</div>

		<div class="form-group">
			<label for="produto.dataInicioVenda">DATA DE INÍCIO DA VENDA:</label>
			<%-- 			<caelum:campoData id="dataInicioVenda" name="produto.dataInicioVenda" /> --%>
			<input name="produto.dataInicioVenda" type="text" id="Data" />

		</div>

		<div class="form-group">
			<label for="produto.acesso"> ACESSO: </label> <input
				name="produto.acesso" type="text" />

		</div>

		<div class="checkbox">
			<label><input type="checkbox"> NÃO SOU UM LIXO </label>
		</div>
		<!-- 		<button type="submit" class="btn btn-default">ENVIAR</button> -->

		<button type="submit" class="btn btn-success"
			>Enviar</button>
	</form>


</body>
</html>


