package br.com.mv.PageFactory.preRecibo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.dao.pedido.Produto;
import br.com.mv.dao.pedido.Servico;
import br.com.mv.utils.Utils;

public class ElaboracaoPreRecibo {

	WebDriver driver;
	Utils utils;
	
	@FindBy(css = "#filtro-bandeira")
	private WebElement filtroBandeira;
	
	@FindBy(css = "#filtro-bandeira ~ ul li a")
	private WebElement opcaoBandeira;
	
	@FindBy(id = "motivo-devolucao")
	private WebElement motivoDevolucao;
	
	@FindBy(id = "filtro-pedido")
	private WebElement filtroPedido;
	
	@FindBy(id = "filtro-loja")
	private WebElement filtroLoja;
	
	@FindBy(id = "combo-processo")
	private WebElement comboProcesso;
	
	@FindBy(id = "btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(id = "radio-reversao-credito")
	private WebElement reversaoCredito;
	
	@FindBy(id = "btn-add-produtos")
	private WebElement btnAdicionarProdutos;
	
	@FindBy(id = "btn-add-servicos")
	private WebElement btnAdicionarServicos;
	
	@FindBy(id = "btn-confirmar-envio")
	private WebElement btnConfirmarEnvio;
	
	public ElaboracaoPreRecibo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
	}
	
	public ElaboracaoPreRecibo setFiltroBandeira(String bandeira) {
		//this.utils.preencherCampoTypeAHead(bandeira, this.filtroBandeira);
		//this.opcaoBandeira.click();
		new Select(this.motivoDevolucao).selectByVisibleText(bandeira);
		return this;
	}
	
	public ElaboracaoPreRecibo setFiltroPedido(String pedido) {
		this.filtroPedido.clear();
		this.filtroPedido.sendKeys(pedido);
		return this;
	}
	
	public ElaboracaoPreRecibo setFiltroLoja(String loja) {
		this.filtroLoja.clear();
		this.filtroLoja.sendKeys(loja);
		return this;
	}
	
	public ElaboracaoPreRecibo setComboProcesso(String processo) {
		new Select(this.comboProcesso).selectByVisibleText(processo);
		return this;
	}
	
	public ElaboracaoPreRecibo clickPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public ElaboracaoPreRecibo setReversaoCredito() {
		this.reversaoCredito.click();
		return this;
	}
	
	public ElaboracaoPreRecibo clickAdicionarProdutos() {
		this.btnAdicionarProdutos.click();
		return this;
	}
	
	public ElaboracaoPreRecibo clickAdicionarServicos() {
		this.btnAdicionarServicos.click();
		return this;
	}
	
	public ElaboracaoPreRecibo clickConfirmar() {
		this.btnConfirmarEnvio.click();
		return this;
	}
	
	public ElaboracaoPreRecibo removerProdutos(List<Produto> produtos) {
		int qtdProdutos = produtos.size();
		
		for (int i = 1; i < qtdProdutos; i++) {
			driver.findElement(By.cssSelector("#btn-excluir-produto-" + produtos.get(i).getNroProduto())).click();
		}
		
		return this;
	}
	
	public ElaboracaoPreRecibo removerTodosProdutos(List<Produto> produtos) {
		for (Produto produto : produtos) {
			driver.findElement(By.cssSelector("#btn-excluir-produto-" + produto.getNroProduto())).click();
		}
		
		return this;
	}
	
	public ElaboracaoPreRecibo removerServicos(List<Servico> servicos) {
		int qtdServicos = servicos.size();
		
		for (int i = 1; i < qtdServicos; i++) {
			driver.findElement(By.cssSelector("#btn-excluir-servico-" + servicos.get(i).getNroServico())).click();
			driver.switchTo().alert().accept();
		}
		
		return this;
	}
	
	public ElaboracaoPreRecibo removerTodosServicos(List<Servico> servicos) {
		for (Servico servico : servicos) {
			driver.findElement(By.cssSelector("#btn-excluir-servico-" + servico.getNroServico())).click();
		}
		
		return this;
	}
	
	public boolean validarIntegracaoPedido(int tempoAguardarIntegracao) throws InterruptedException {
		Boolean pedidoEncontrado = false;
		
		for (int i = 0; i < tempoAguardarIntegracao; i++) {
			Thread.sleep(1000);
			
			String msgIntegracao = driver.findElement(By.cssSelector(".alert-warning")).getText();
			
			if ("Pedido na fila de integração! Favor aguardar alguns instantes e consulte novamente.".equals(msgIntegracao) || 
					"Consulta em processamento. Por favor, aguarde alguns instantes e consulte novamente.".equals(msgIntegracao)) {
				
				this.clickPesquisar();
			} else {
				pedidoEncontrado  = true;
				
				break;
			}
		}
		
		return pedidoEncontrado;
	}
}