package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupSelecaoProdutoPedidoPreRecibo extends PopupPreRecibo{
	
	public PopupSelecaoProdutoPedidoPreRecibo(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css = "input[name='tableProdutosNaoSelecionados:tHeader:radio']")
	private WebElement chkSelecionarTodosProdutos;
	
	@FindBy(css = "tableServicosNaoSelecionados:tHeader:radio")
	private WebElement chkSelecionarTodosServicos;
	
	public PopupSelecaoProdutoPedidoPreRecibo clickSelecionarTodosProdutos() {
		chkSelecionarTodosProdutos.click();
		return this;
	}
	
	public PopupSelecaoProdutoPedidoPreRecibo clickSelecionarTodosServicos() {
		chkSelecionarTodosServicos.click();
		return this;
	}
	
	public PopupSelecaoProdutoPedidoPreRecibo selecionarProdutos(int[] produtos) {
		for (int i=0; i<produtos.length; i++) {
			driver.findElement(By.xpath("//table[@id='tableProdutosNaoSelecionados:tContent']/tbody/tr/td[text()='" + produtos[i] + "']/parent::tr/td[3]")).click();
		}
		return this;
	}
	
	public PopupSelecaoProdutoPedidoPreRecibo selecionarServicos(int[] servicos) {
		for (int i=0; i<servicos.length; i++) {
			driver.findElement(By.xpath("//table[@id='tableServicosNaoSelecionados:tContent']/tbody/tr/td[text()='" + servicos[i] + "']/parent::tr/td[1]")).click();
		}
		return this;
	}

}
