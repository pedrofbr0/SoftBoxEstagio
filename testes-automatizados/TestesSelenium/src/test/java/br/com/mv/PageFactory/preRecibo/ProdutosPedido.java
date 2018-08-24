package br.com.mv.PageFactory.preRecibo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.dao.pedido.Produto;
import br.com.mv.dao.pedido.Servico;
import br.com.mv.utils.AguardaCarregamento;

public class ProdutosPedido {

	WebDriver driver;
	
	@FindBy(id = "btn-selecionar-produtos")
	private WebElement btnSelecionar;
	
	ElaboracaoPreRecibo elaboracaoPreRecibo;
	private AguardaCarregamento agCarregamento;
	
	public ProdutosPedido(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.elaboracaoPreRecibo = new ElaboracaoPreRecibo(driver);
		this.agCarregamento = new AguardaCarregamento(driver);
	}
	
	public ProdutosPedido clickSelecionar() {
		this.btnSelecionar.click();
		return this;
	}
	
	public ProdutosPedido adicionarProduto(int nroProduto) {
		driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/table/tbody/tr/td[3][contains(text(), '" + nroProduto + "')]/parent::tr/td[1]/input")).click();
		this.btnSelecionar.click();
		return this;
	}
	
	public ProdutosPedido removerProduto(int nroProduto) {
		driver.findElement(By.cssSelector("#btn-excluir-produto-" + nroProduto)).click();
		return this;
	}
	
	public ProdutosPedido removeAdicionaUmProduto(List<Produto> produtos) {
		for (Produto produto : produtos) {
			removerProduto(produto.getNroProduto());
			elaboracaoPreRecibo.clickAdicionarProdutos();
			agCarregamento.aguardarCarregamentoNovaArquitetura2();
			adicionarProduto(produto.getNroProduto());
			
			break;
		}
		
		return this;
	}
}