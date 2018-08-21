package br.com.mv.steps;

import org.jbehave.core.annotations.When;

import br.com.mv.pages.EmissaoNotaFiscalAvulsaPage;
import br.com.mv.pages.PopupParametrosPage;
import net.thucydides.core.annotations.Steps;

public class NotaFiscalAvulsaSteps {

	@Steps
	PopupParametrosPage popupParametros;
	EmissaoNotaFiscalAvulsaPage nfAvulsa;
	
	@When("Na tela de nota avulsa eu seleciono a empresa $empresa")
	public void naTelaNotaAvulsaEuSelecionoEmpresa(String empresa) throws InterruptedException {
		popupParametros.setEmpresa(empresa);
	}
	
	@When("Na tela de nota avulsa eu seleciono a loja destino $lojaDestino")
	public void naTelaNotaAvulsaEuSelecionoLojaDestino(String lojaDestino) throws InterruptedException {
		popupParametros.setLojaDestino(lojaDestino);
	}
	
	@When("Na tela de nota avulsa eu seleciono o grupo estoque destino $grupoDestino")
	public void naTelaNotaAvulsaEuSelecionoGrupoEstoque(String grupoDestino) throws InterruptedException {
		popupParametros.setGrupoEstoqueDestino(grupoDestino);
	}
	
	@When("Na tela de parametros da nota avulsa eu clico no botao Concluir")
	public void naTelaParametrosNotaAvulsaClicoBotaoConcluir() throws InterruptedException {
		popupParametros.clicarBotaConcluirEFecharJanela();
	}
	
	@When("Na tela de nota avulsa eu clico no botao Concluir")
	public void naTelaNotaAvulsaClicoBotaoConcluir() throws InterruptedException {
		nfAvulsa.clickBtnConcluir();
	}
	
	@When("Na tela de nota avulsa eu clico em Ok no alert")
	public void naTelaNotaAvulsaClicoOkAlert() throws InterruptedException {
		nfAvulsa.clicarOkAlert();
	}
	
	@When("Eu informo o numero da nota com mais de 90 dias")
	public void euInformoNumeroDaNotaMais90Dias() throws InterruptedException {
		popupParametros.informarNumeroNotaMais90Dias();
	}
	
	@When("Eu informo a empresa da nota com mais de 90 dias")
	public void euInformoEmpresaDaNotaMais90Dias() throws InterruptedException {
		popupParametros.informarEmpresaNotaMais90Dias();
	}
	
	@When("Eu informo o codigo loja da nota com mais de 90 dias")
	public void euInformoCodigoLojaDaNotaMais90Dias() throws InterruptedException {
		popupParametros.informarCodigoLojaNotaMais90Dias();
	}
	
	@When("Eu informo o numero da serie da nota com mais de 90 dias")
	public void euInformoNumeroSerieMais90Dias() throws InterruptedException {
		popupParametros.informarNumeroSerie90Dias();
	}
	
	@When("Eu seleciono o cupom fiscal com mais de 90 dias")
	public void euSelecionoCupomFiscalMais90Dias() throws InterruptedException {
		popupParametros.selecionarCF();
	}
	
	@When("Eu informo o numero do cupom de um pedido faturado")
	public void euInformoNumeroCupomPedidoFaturado() throws InterruptedException {
		popupParametros.informarNumeroCupomPedidoFaturado();
	}
	
	@When("Eu informo a empresa do cupom de um pedido faturado")
	public void euInformoEmpresaCupomPedidoFaturado() throws InterruptedException {
		popupParametros.informarEmpresaCupomPedidoFaturado();
	}
	
	@When("Eu informo o codigo loja do cupom de um pedido faturado")
	public void euInformoLojaCupomPedidoFaturado() throws InterruptedException {
		popupParametros.informarLojaCupomPedidoFaturado();
	}
	
	@When("Eu informo o numero da serie do cupom de um pedido faturado")
	public void euInformoSerieCupomPedidoFaturado() throws InterruptedException {
		popupParametros.informarSerieCupomPedidoFaturado();
	}
	
	@When("Eu seleciono o cupom fiscal de um pedido faturado")
	public void euSelecionoCupomPedidoFaturado() throws InterruptedException {
		popupParametros.selecionarCupomPedidoFaturado();
	}
	
	@When("Na tela de nota avulsa eu clico na aba $aba da nota fiscal")
	public void euClicoAbaNotaFiscal(String aba) throws InterruptedException {
		nfAvulsa.clicarAbaNotaFiscalAvulsa(aba);
	}
	
	@When("Na tela de nota avulsa eu clico no botao Incluir Produto")
	public void naTelaNotaAvulsaClicoIncluirProduto() throws InterruptedException {
		nfAvulsa.clicarBotaoIncluirProduto();
	}
	
	@When("Na tela de nota avulsa eu clico no botao Incluir Produto geral")
	public void naTelaNotaAvulsaClicoIncluirProdutoGeral() throws InterruptedException {
		nfAvulsa.clickBtnIncluirProduto();
	}
	
	@When("Na tela de nota avulsa eu informo a descricao $descricao do item da nota")
	public void naTelaNotaAvulsaInformoDescricaoItemNota(String descricao) throws InterruptedException {
		nfAvulsa.informarDescricaoProdutoGeral(descricao);
	}
	
	@When("Na tela de nota avulsa eu informo o valor $vlrItem do item da nota")
	public void naTelaNotaAvulsaInformoValorItemNota(String vlrItem) throws InterruptedException {
		nfAvulsa.informarValorProdutoGeral(vlrItem);
	}
	
	@When("Na tela de nota avulsa eu informo a aliquota ICMS $aliquotaICMS do item da nota")
	public void naTelaNotaAvulsaInformoAliquotaICMSItemNota(String aliquotaICMS) throws InterruptedException {
		nfAvulsa.informarAliquotaICMS(aliquotaICMS);
	}
	
	@When("Na tela de nota avulsa eu informo a base calculo ICMS $baseCalculoICMS do item da nota")
	public void naTelaNotaAvulsaInformoBaseCalculoICMSItemNota(String baseCalculoICMS) throws InterruptedException {
		nfAvulsa.informarBaseCalculoICMS(baseCalculoICMS);
	}
	
	@When("Na tela de nota avulsa eu informo o ICMS $valorICMS do item da nota")
	public void naTelaNotaAvulsaInformoValorICMSItemNota(String valorICMS) throws InterruptedException {
		nfAvulsa.informarValorICMS(valorICMS);
	}
	
	@When("Na tela de nota avulsa eu informo a aliquota ICMS $aliquotaICMS do item da nota fiscal")
	public void naTelaNotaAvulsaInformoAliquotaICMSItemNotaFiscal(String aliquotaICMS) throws InterruptedException {
		nfAvulsa.informarAliquotaICMSNotaFiscal(aliquotaICMS);
	}
	
	@When("Na tela de nota avulsa eu informo a base calculo ICMS $baseCalculoICMS do item da nota fiscal")
	public void naTelaNotaAvulsaInformoBaseCalculoICMSItemNotaFiscal(String baseCalculoICMS) throws InterruptedException {
		nfAvulsa.informarBaseCalculoICMSNotaFiscal(baseCalculoICMS);
	}
	
	@When("Na tela de nota avulsa eu informo o ICMS $valorICMS do item da nota fiscal")
	public void naTelaNotaAvulsaInformoValorICMSItemNotaFiscal(String valorICMS) throws InterruptedException {
		nfAvulsa.informarValorICMSNotaFiscal(valorICMS);
	}
	
	@When("Na tela de nota avulsa eu informo a observacao do usuario $obsUsuario na nota fiscal")
	public void naTelaNotaAvulsaInformoObservacaoUsuario(String obsUsuario) throws InterruptedException {
		nfAvulsa.setObsUsuario(obsUsuario);
	}
	
	@When("Na tela de nota avulsa eu informo o CFOP $valorCFOP do item da nota fiscal")
	public void naTelaNotaAvulsaInformoValorCFOPItemNotaFiscal(String valorCFOP) throws InterruptedException {
		nfAvulsa.informarValorCFOPNotaFiscal(valorCFOP);
	}
	
	@When("Na tela de nota avulsa eu informo a quantidade $qtdProduto do item da nota fiscal")
	public void naTelaNotaAvulsaInformoQuantidadeProdutoItemNota(String qtdProduto) throws InterruptedException {
		nfAvulsa.informarQuantidadeProdutoNotaFiscal(qtdProduto);
	}
	
	@When("Na tela de nota avulsa eu informo a quantidade $qtdProduto para os itens da nota nota fiscal")
	public void naTelaNotaAvulsaInformoQuantidadeProdutoParaOsItensNota(String qtdProduto) throws InterruptedException {
		nfAvulsa.informarQuantidadeTodosProdutosNotaFiscal(qtdProduto);
	}
	
	@When("Eu informo o numero do cupom de um pedido faturado consumidor NFCE")
	public void euInformoNumeroCupomPedidoFaturadoConsumidorNfce() throws InterruptedException {
		popupParametros.informarNumeroCupomPedidoFaturadoConsumidorNfce();
	}
	
	@When("Eu informo a empresa do cupom de um pedido faturado consumidor NFCE")
	public void euInformoEmpresaCupomPedidoFaturadoConsumidorNfce() throws InterruptedException {
		popupParametros.informarEmpresaCupomPedidoFaturadoConsumidorNfce();
	}
	
	@When("Eu informo o codigo loja do cupom de um pedido faturado consumidor NFCE")
	public void euInformoLojaCupomPedidoFaturadoConsumidorNfce() throws InterruptedException {
		popupParametros.informarLojaCupomPedidoFaturadoConsumidorNfce();
	}
	
	@When("Eu informo o numero da serie do cupom de um pedido faturado consumidor NFCE")
	public void euInformoSerieCupomPedidoFaturadoConsumidorNfce() throws InterruptedException {
		popupParametros.informarSerieCupomPedidoFaturadoConsumidorNfce();
	}
	
	@When("Eu seleciono o cupom fiscal de um pedido faturado consumidor NFCE")
	public void euSelecionoCupomPedidoFaturadoConsumidorNfce() throws InterruptedException {
		popupParametros.selecionarCupomPedidoFaturadoConsumidorNfce();
	}
	
	@When("Eu clico no botao Concluir parametros e informo CPF na nota")
	public void euClicoOkParaInformarCpf() throws InterruptedException {
		popupParametros.clicarOkparaInformarCpf();
	}
	
	
	@When("Eu informo o numero do cupom de um pedido faturado cpf NFCE")
	public void euInformoNumeroCupomPedidoFaturadoCpfNfce() throws InterruptedException {
		popupParametros.informarNumeroCupomPedidoFaturadoCpfNfce();
	}
	
	@When("Eu informo a empresa do cupom de um pedido faturado cpf NFCE")
	public void euInformoEmpresaCupomPedidoFaturadoCpfNfce() throws InterruptedException {
		popupParametros.informarEmpresaCupomPedidoFaturadoCpfNfce();
	}
	
	@When("Eu informo o codigo loja do cupom de um pedido faturado cpf NFCE")
	public void euInformoLojaCupomPedidoFaturadoCpfNfce() throws InterruptedException {
		popupParametros.informarLojaCupomPedidoFaturadoCpfNfce();
	}
	
	@When("Eu informo o numero da serie do cupom de um pedido faturado cpf NFCE")
	public void euInformoSerieCupomPedidoFaturadoCpfNfce() throws InterruptedException {
		popupParametros.informarSerieCupomPedidoFaturadoCpfNfce();
	}
	
	@When("Eu seleciono o cupom fiscal de um pedido faturado cpf NFCE")
	public void euSelecionoCupomPedidoFaturadoCpfNfce() throws InterruptedException {
		popupParametros.selecionarCupomPedidoFaturadoCpfNfce();
	}
	
	@When("Na tela de parametros da nota avulsa eu clico no radio Cliente")
	public void naTelaParametrosNotaAvulsaClicoRadioCliente() throws InterruptedException {
		popupParametros.clicarRadioCliente();
	}
	
	@When("Na tela de parametros da nota avulsa eu clico botao Pesquisar cliente")
	public void naTelaParametrosNotaAvulsaClicoBotaoPesquisarCliente() throws InterruptedException {
		popupParametros.clicarBtnPesquisarCliente();
	}
	
	@When("Na tela de parametros da nota avulsa eu clico no radio Fornecedor")
	public void naTelaParametrosNotaAvulsaClicoRadioFornecedor() throws InterruptedException {
		popupParametros.clicarRadioFornecedor();
	}
	
	@When("Na tela de parametros da nota avulsa eu clico botao Pesquisar fornecedor")
	public void naTelaParametrosNotaAvulsaClicoBotaoPesquisarFornecedor() throws InterruptedException {
		popupParametros.clicarBtnPesquisarFornecedor();
	}

	@When("Na tela de parametros da nota avulsa eu seleciono o endereco do cliente")
	public void naTelaParametrosNotaAvulsaSelecionoEnderecoCliente() throws InterruptedException {
		popupParametros.selecionarEnderecoCliente();
	}
	
	@When("Na tela de parametros da nota avulsa eu seleciono o endereco do fornecedor")
	public void naTelaParametrosNotaAvulsaSelecionoEnderecoFornecedor() throws InterruptedException {
		popupParametros.selecionarEnderecoCliente();
	}
	
	@When("Na tela de nota avulsa eu informo a descricao especie $descEspecieProduto")
	public void naTelaNotaAvulsaInformoDescricaoEspecie(String descEspecieProduto) throws InterruptedException {
		nfAvulsa.setDescEspecieProduto(descEspecieProduto);
	}
	
	@When("Na tela de nota avulsa eu informo a quantidade especie $qtdProdutoEspecie")
	public void naTelaNotaAvulsaInformoQuantidadeEspecie(String qtdProdutoEspecie) throws InterruptedException {
		nfAvulsa.setQtdProdutoEspecie(qtdProdutoEspecie);
	}
	
	@When("Na tela de nota avulsa eu informo o peso bruto $pesoBruto")
	public void naTelaNotaAvulsaInformoPesoBruto(String pesoBruto) throws InterruptedException {
		nfAvulsa.setPesoBruto(pesoBruto);
	}

	@When("Eu informo o numero da nota de entrada via integracao tipo $tipoNF")
	public void euInformoNumeroDaNotaEntradaViaIntegracaoTipo(String tipoNF) throws InterruptedException {
		popupParametros.informarNumeroNotaEntradaViaIntegracaoTipo(tipoNF);
	}
	
	@When("Eu informo o numero da nota via integracao tipo $tipoNF")
	public void euInformoNumeroDaNotaViaIntegracaoTipo(String tipoNF) throws InterruptedException {
		popupParametros.informarNumeroNotaViaIntegracaoTipo(tipoNF);
	}
	
	@When("Eu informo a empresa da nota via integracao tipo $tipoNF")
	public void euInformoEmpresaDaNotaViaIntegracaoTipo(String tipoNF) throws InterruptedException {
		popupParametros.informarEmpresaNotaViaIntegracaoTipo(tipoNF);
	}
	
	@When("Eu informo o codigo loja da nota via integracao tipo $tipoNF")
	public void euInformoCodigoLojaDaNotaViaIntegracaoTipo(String tipoNF) throws InterruptedException {
		popupParametros.informarCodigoLojaNotaViaIntegracaoTipo(tipoNF);
	}
	
	@When("Eu informo o numero da serie da nota via integracao tipo $tipoNF")
	public void euInformoNumeroSerieViaIntegracaoTipo(String tipoNF) throws InterruptedException {
		popupParametros.informarNumeroSerieViaIntegracaoTipo(tipoNF);
	}
	
	@When("Eu seleciono o fornecedor da nota via integracao tipo $tipoNF")
	public void euSelecionoFornecedorNotaViaIntegracaoTipo(String tipoNF) throws InterruptedException {
		popupParametros.selecionarFornecedorNotaViaIntegracaoTipo(tipoNF);
	}
	
	@When("Eu seleciono o endereco do fornecedor")
	public void euSelecionoEnderecoFornecedor() throws InterruptedException {
		popupParametros.selecionarEnderecoFornecedor();
	}
	
	@When("No popup de parametros eu informo o numero da nota tipo $tipoNF da loja $codNroLoja")
	public void euInformoNumeroNotaPorTipoELoja(String tipoNF, String codNroLoja) throws InterruptedException {
		popupParametros.informarNumeroNotaPorTipoELoja(tipoNF, codNroLoja);
	}
	
	@When("No popup de parametros eu informo a empresa da nota tipo $tipoNF da loja $codNroLoja")
	public void euInformoEmpresaNotaPorTipoELoja(String tipoNF, String codNroLoja) throws InterruptedException {
		popupParametros.informarEmpresaNotaPorTipoELoja(tipoNF, codNroLoja);
	}
	
	@When("No popup de parametros eu informo o codigo loja da nota tipo $tipoNF da loja $codNroLoja")
	public void euInformoCodigoLojaPorTipoELoja(String tipoNF, String codNroLoja) throws InterruptedException {
		popupParametros.informarCodigoLojaNotaPorTipoELoja(tipoNF, codNroLoja);
	}
	
	@When("No popup de parametros eu informo o numero da serie da nota tipo $tipoNF da loja $codNroLoja")
	public void euInformoNumeroSeriePorTipoELoja(String tipoNF, String codNroLoja) throws InterruptedException {
		popupParametros.informarNumeroSerieNotaPorTipoELoja(tipoNF, codNroLoja);
	}
	
	@When("Na tela de nota avulsa eu seleciono o tipo de frete $tipoFrete")
	public void euSelecionoTipoFrete(String tipoFrete) throws InterruptedException {
		nfAvulsa.setTipoFrete(tipoFrete);
	}
	
}