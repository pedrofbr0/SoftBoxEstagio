package br.com.mv.model;
import static br.com.mv.model.SessionVariables.NRO_PEDIDO;
import static br.com.mv.model.SessionVariables.COD_NRO_LOJA;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Values.Update;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import br.com.mv.dao.LojaDAO;
import br.com.mv.dao.PedidoDAO;
import br.com.mv.pages.HomePage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class RelatorioAutomacao {
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Relatorio Automacao";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-relatorio-automacao-" + SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.gravar.pedido.relatorio.versao"));

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/sheets.googleapis.com-java-quickstart
     */
    private static final List<String> SCOPES =
        Arrays.asList(SheetsScopes.SPREADSHEETS); //_READONLY

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            RelatorioAutomacao.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Sheets API client service.
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    public static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void salvarPedidoRelatorio(String codTeste, String nroPedido) throws IOException {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("d"); // Ex.: Dia "3" ao inves de Dia "03" 
		String data = sdf.format(new Date());
		
        // Build a new authorized API client service.
        Sheets service = getSheetsService();
        
        String spreadsheetId = SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.gravar.pedido.planilha");
        String aba = SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.gravar.pedido.aba.fixa");
        Boolean abaFixa = Boolean.parseBoolean(aba);
        
        if (!abaFixa) {
        	aba = "Exec" + data;
        }
        
        // ---------------------------------------------
        // Atualiza a data de execução dos testes, célula: C5
        // --------------------------------------------
        
        String rangeData = aba + "!C5";
        
        ValueRange responseData = service.spreadsheets().values()
                .get(spreadsheetId, rangeData)
                .execute();
        
		List<List<Object>> valuesData = responseData.getValues();

		if (valuesData == null || valuesData.size() == 0) {
			System.out.println("No data found.");
		} else {
			
			valuesData.get(0).set(0, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, rangeData, responseData)
					.setValueInputOption("USER_ENTERED");

			request.execute();
		}
        
		// ---------------------------------------------
        // Atualiza o status dos testes e o nro do pedido relacionado
        // --------------------------------------------
		
        String celulas = SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.gravar.pedido.intervalo.celulas");
        
        String range = aba + "!" + celulas;
        
        ValueRange response = service.spreadsheets().values()
            .get(spreadsheetId, range)
            .execute();
        
        List<List<Object>> values = response.getValues();
        
        if (values == null || values.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	
			for (List<Object> row : values) {
				String col0 = (String) row.get(0);

				if (col0.equals(codTeste)) {
					row.set(2, "Sucesso");
					row.set(3, nroPedido);
				}
			}
		}
        
		try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, range, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
    }
    
    public static void validarValoresCalculoReverso() throws IOException {
    	
    	// Build a new authorized API client service.
        Sheets service = getSheetsService();
        
        String spreadsheetId = SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.gravar.pedido.planilha.calculo.reverso");
        String aba = "Analise";
        
        //String celulas = SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.gravar.pedido.planilha.calculo.reverso.intervalo.celulas");
        
        //String range = aba + "!" + celulas;
        
        PedidoDAO pedidoDAO = new PedidoDAO();
        LojaDAO lojaDAO = new LojaDAO();
        
        String codNroLoja = (String) Serenity.sessionVariableCalled(COD_NRO_LOJA);
        
        Map<String, String> metadata = HomePage.getTags();
		
		String base = "RELOH";
		
		if (!metadata.get("base").isEmpty()) {
			base = metadata.get("base");
		}
		
		String nroEmpresa = "1";
		
		if (base.equals("RELOH")) {
			nroEmpresa = "5";
		}
			
        String nroLoja = Integer.toString(lojaDAO.buscarLoja(Integer.parseInt(codNroLoja), Integer.parseInt(nroEmpresa)).getNroLoja()); //"630";
        String nroPedido = (String) Serenity.sessionVariableCalled(NRO_PEDIDO); //4653553
        
        // **************************************
        // PRODUTOS DO PEDIDO
        // **************************************
        
        String rangeProduto = aba + "!A2:C2";
        
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, rangeProduto)
                .execute();
           
        List<List<Object>> valuesProdutos = response.getValues();
        
        if (valuesProdutos == null || valuesProdutos.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	System.out.println("Produto");
			for (List<Object> row : valuesProdutos) {
				String codProduto = (String) row.get(0);
				
				System.out.println("codProduto: " + codProduto);
				
				ItemPedidoProduto produtoPedido = pedidoDAO.buscarValoresPrestacaoEntradaItemPedidoProduto(nroLoja, nroPedido, codProduto);
				
				row.set(2, produtoPedido.getVrUnitarioProduto());
				
				System.out.println(row.get(1) + " : entrada: " + produtoPedido.getVrEntradaProduto() + " prestacao: " + produtoPedido.getVrPrestacaoProduto());
			}
		}
        
        try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, rangeProduto, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
        
        // **************************************
        // GARANTIAS DO PEDIDO
        // **************************************
        
        String rangeGarantias = aba + "!A3:C4";
        
        response = service.spreadsheets().values()
                .get(spreadsheetId, rangeGarantias)
                .execute();
            
        List<List<Object>> valuesGarantias = response.getValues();
        
        if (valuesGarantias == null || valuesGarantias.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	System.out.println("Garantias");
			for (List<Object> row : valuesGarantias) {
				String codGarantia = (String) row.get(0);
				
				System.out.println("codGarantia: " + codGarantia);
				
				ItemPedidoServico garantiaPedido = pedidoDAO.buscarValoresPrestacaoItemPedidoServico(nroLoja, nroPedido, codGarantia);
				
				row.set(2, garantiaPedido.getVrUnitarioServico());
				
				System.out.println(row.get(1) + " : vrUnitario: " + garantiaPedido.getVrUnitarioServico() + " prestacao: " + garantiaPedido.getVrPrestacaoServico());
			}
		}
        
        try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, rangeGarantias, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
        
        // **************************************
        // SERVICOS DO PEDIDO
        // **************************************
        
        String rangeServicos = aba + "!A5:C8";
        
        response = service.spreadsheets().values()
                .get(spreadsheetId, rangeServicos)
                .execute();
            
        List<List<Object>> valuesServicos = response.getValues();
        
        if (valuesServicos == null || valuesServicos.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	System.out.println("Serviços");
			for (List<Object> row : valuesServicos) {
				String nroServico = (String) row.get(0);
				
				System.out.println("nroServico: " + nroServico);
				
				ItemPedidoServico servicoPedido = pedidoDAO.buscarValoresPrestacaoPedidoServico(nroLoja, nroPedido, nroServico);
				
				//if (!nroServico.equals("300175")) { // SEGURO CDC
					row.set(2, servicoPedido.getVrUnitarioServico());
				//}
				
				System.out.println(row.get(1) + " : entrada: " + servicoPedido.getVrUnitarioServico() + " prestacao: " + servicoPedido.getVrPrestacaoServico());
			}
		}
        
		try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, rangeServicos, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
		
		// **************************************
        // FATORES DO PLANO DE PAGAMENTO / QTD PARCELAS DO PEDIDO
        // **************************************
		
		String rangeFatorPlano = aba + "!B19";
        
        response = service.spreadsheets().values()
                .get(spreadsheetId, rangeFatorPlano)
                .execute();
            
        List<List<Object>> valueFatorPlano = response.getValues();
        
        if (valueFatorPlano == null || valueFatorPlano.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	PlanoPagamento planoPagto = pedidoDAO.buscarFatoresPlanoPagamento(nroLoja, "66", "01", "6");
        	valueFatorPlano.get(0).set(0, planoPagto.getFatorPlano());
        	
			System.out.println("fatorPlano: " + planoPagto.getFatorPlano());
		}
        
		try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, rangeFatorPlano, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
		
		// FATOR REVERSAO
		
		String rangeFatorReversao = aba + "!B18";
        
        response = service.spreadsheets().values()
                .get(spreadsheetId, rangeFatorReversao)
                .execute();
            
        List<List<Object>> valueFatorReversao = response.getValues();
        
        if (valueFatorReversao == null || valueFatorReversao.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	PlanoPagamento planoPagto = pedidoDAO.buscarFatoresPlanoPagamento(nroLoja, "66", "J1", "6");
        	valueFatorReversao.get(0).set(0, planoPagto.getFatorPlano());
        	
			System.out.println("fatorReversao: " + planoPagto.getFatorPlano());
		}
        
		try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, rangeFatorReversao, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
		
		// VALOR DE ENTRADA
		
		Pedido pedido = pedidoDAO.buscarPedidoPorLoja(nroLoja, nroPedido);
		
		String rangeEntrada = aba + "!B15";
        
        response = service.spreadsheets().values()
                .get(spreadsheetId, rangeEntrada)
                .execute();
            
        List<List<Object>> valueEntrada = response.getValues();
        
        if (valueEntrada == null || valueEntrada.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	valueEntrada.get(0).set(0, pedido.getVrEntrada());
        	
			System.out.println("vrEntrada: " + pedido.getVrEntrada());
		}
        
		try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, rangeEntrada, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
		
		// QUANTIDADE DE PARCELAS
		
		String rangeQtdeParcelas = aba + "!B21";
        
        response = service.spreadsheets().values()
                .get(spreadsheetId, rangeQtdeParcelas)
                .execute();
            
        List<List<Object>> valueQtdeParcelas = response.getValues();
        
        if (valueQtdeParcelas == null || valueQtdeParcelas.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	valueQtdeParcelas.get(0).set(0, pedido.getQtdeParcelas());
        	
			System.out.println("qtdeParcelas: " + pedido.getQtdeParcelas());
		}
        
		try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, rangeQtdeParcelas, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
		
		// FATOR CDC
		String rangeFatorCDC = aba + "!B16";
        
        response = service.spreadsheets().values()
                .get(spreadsheetId, rangeFatorCDC)
                .execute();
            
        List<List<Object>> valueFatorCDC = response.getValues();
        
        if (valueFatorCDC == null || valueFatorCDC.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	valueFatorCDC.get(0).set(0, pedidoDAO.buscarFatorCDC(pedido.getQtdeParcelas()));
        	
			System.out.println("valueFatorCDC: " + pedidoDAO.buscarFatorCDC(pedido.getQtdeParcelas()));
		}
        
		try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, rangeFatorCDC, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
		
		// VALOR DA PRESTACAO
		
		String rangeValorPrestacao = aba + "!B27";
        
        response = service.spreadsheets().values()
                .get(spreadsheetId, rangeValorPrestacao)
                .execute();
            
        List<List<Object>> valueValorPrestacao = response.getValues();
        
        if (valueValorPrestacao == null || valueValorPrestacao.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	valueValorPrestacao.get(0).set(0, pedido.getVrPrestacao());
        	
			System.out.println("valueValorPrestacao: " + pedido.getVrPrestacao());
		}
        
		try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, rangeValorPrestacao, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
		
		
		// VALIDA A PRESTACAO CALCULADA COM A PRESTACAO DO BANCO
		
		String rangeValorDiferencaPrestacao = aba + "!C27";
        
        response = service.spreadsheets().values()
                .get(spreadsheetId, rangeValorDiferencaPrestacao)
                .execute();
            
        List<List<Object>> valueValorDiferencaPrestacao = response.getValues();
        
        if (valueValorDiferencaPrestacao == null || valueValorDiferencaPrestacao.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	
        	String difPrestacao = (String) valueValorDiferencaPrestacao.get(0).get(0);
        	difPrestacao = difPrestacao.replaceAll(",", ".");
        	
        	System.out.println("difPrestacao: " + difPrestacao);
        	double vrDiferencaPrestacao = Double.parseDouble(difPrestacao);
        	
        	Assert.assertTrue("A diferença da prestação está fora da margem permitida!", vrDiferencaPrestacao > -0.10 && vrDiferencaPrestacao < 0.10);
        	
        	System.out.println("valueValorPrestacao: " + valueValorDiferencaPrestacao.get(0).get(0) + " vrDiferencaPrestacao: " + vrDiferencaPrestacao);
		}
    }
    
    public static void salvarNotaFiscalRelatorio(String codTeste, String nroNota) throws IOException {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("d"); // Ex.: Dia "3" ao inves de Dia "03" 
		String data = sdf.format(new Date());
		
        // Build a new authorized API client service.
        Sheets service = getSheetsService();
        
        String spreadsheetId = SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.gravar.nota_fiscal.planilha");
        String aba = SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.gravar.nota_fiscal.aba.fixa");
        Boolean abaFixa = Boolean.parseBoolean(aba);
        
        if (!abaFixa) {
        	aba = "Exec" + data;
        }
        
        String celulas = SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.gravar.nota_fiscal.intervalo.celulas");
        
        String range = aba + "!" + celulas;
        
        ValueRange response = service.spreadsheets().values()
            .get(spreadsheetId, range)
            .execute();
        
        List<List<Object>> values = response.getValues();
        
        if (values == null || values.size() == 0) {
        	System.out.println("No data found.");
        } else {
        	
			for (List<Object> row : values) {
				String col0 = (String) row.get(0);

				if (col0.equals(codTeste)) {
					row.set(2, "Sucesso");
					row.set(3, nroNota);
				}
			}
		}
        
		try {
			Update request = service.spreadsheets().values()
					.update(spreadsheetId, range, response)
					.setValueInputOption("USER_ENTERED");

			request.execute();
			
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			System.out.println(error);
		}
	}
}