<html>

	<head>
        <link href="JS/jquery-ui.min.css" rel="stylesheet">
	    <script src="JS/external/jquery/jquery.js"></script>
	    <script src="JS/jquery-ui.js"></script>
    </head>
    
	<body>
	
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib tagdir="/WEB-INF/tags" prefix="caelum" %>
    
		<c:import url="cabecalho.jsp" />
	
	
		    <h1>Adiciona Contatos</h1>
		    <hr />
		    <form method="POST" action="mvc">
		    	<input type="hidden" name="logica" value="AdicionaContatoLogic"/>
		    	<input type="hidden" name="id" value="${contato.id}"/>
		        Nome: <input type="text" name="nome" /><br />
		        E-mail: <input type="text" name="email" /><br />
		        Endereço: <input type="text" name="endereco" /><br />
		        Data Nascimento: <caelum:campoData id="dataNascimento" /><br />
		        <input type="submit" value="Gravar" />
		    </form>
	    
	    <c:import url="rodape.jsp" />
	</body>
</html>