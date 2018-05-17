<html>

	<head>
        <link href="jquery-ui-1.12.1.custom/jquery-ui.css" rel="stylesheet">
        <script src="jquery-ui-1.12.1.custom/external/jquery/jquery.js"></script>
        <script src="jquery-ui-1.12.1.custom/jquery-ui.js"></script>
    </head>
    
	<body>
	
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="caelum" %>
    
		<c:import url="cabecalho.jsp" />
	
	
		    <h1>Adiciona Contatos</h1>
		    <hr />
		    <form action="mvc">
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

<!-- <body>
<c:import url="/WEB-INF/jsp/cabecalho.jsp" />
    <h1>Adicionar Contatos</h1>
    <hr />
    <form method="POST" action="mvc" >
        Nome: <input type="text" name="nome" /><br />
        E-mail: <input type="text" name="email" /><br />
        Endereço: <input type="text" name="endereco" /><br />
        Data Nascimento: <caelum:campoData id="dataNascimento" /><br />
        <input type="hidden" name="logica" value="AdicionaContatoLogic"/>  
        <input type="submit" value="Gravar" />
    </form>
<c:import url="/WEB-INF/jsp/rodape.jsp" />
</body> -->