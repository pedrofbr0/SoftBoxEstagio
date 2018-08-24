package br.com.mv.PageFactory.preRecibo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.dao.pedido.Servico;
import br.com.mv.utils.AguardaCarregamento;

public class ServicosPedido {

	WebDriver driver;
	
	@FindBy(id = "btn-selecionar-servicos")
	private WebElement btnSelecionar;
	
	ElaboracaoPreRecibo elaboracaoPreRecibo;
	private AguardaCarregamento agCarregamento;
	
	public ServicosPedido(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.elaboracaoPreRecibo = new ElaboracaoPreRecibo(driver);
		this.agCarregamento = new AguardaCarregamento(driver);
	}
	
	public ServicosPedido clickSelecionar() {
		this.btnSelecionar.click();
		return this;
	}
	
	public ServicosPedido adicionarServico(int nroServico) {
		driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/table/tbody/tr/td[contains(text(), '" + nroServico + "')]/parent::tr/td[1]/input")).click();
		this.btnSelecionar.click();
		return this;
	}
	
	public ServicosPedido removerServico(int nroServico) {
		driver.findElement(By.cssSelector("#btn-excluir-servico-" + nroServico)).click();
		return this;
	}
	
	public ServicosPedido removeAdicionaUmServico(List<Servico> servicos) {
		for (Servico servico : servicos) {
			removerServico(servico.getNroServico());
			elaboracaoPreRecibo.clickAdicionarServicos();
			agCarregamento.aguardarCarregamentoNovaArquitetura2();
			adicionarServico(servico.getNroServico());
			
			break;
		}
		
		return this;
	}
}