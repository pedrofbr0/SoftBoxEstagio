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
import br.com.mv.utils.AguardaCarregamento;

public class OrdemRecolhimento {

	WebDriver driver;
	
	@FindBy(css = "select[ng-model='produto.localizacao_produto']")
	private WebElement localizacaoProduto;
	
	@FindBy(css = "input[ng-model='servico.contrato']")
	private WebElement numeroContratoServico;
	
	@FindBy(id = "btn-confirmar")
	private WebElement btnConfirmar;
	
	private AguardaCarregamento agCarregamento;
	
	public OrdemRecolhimento(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.agCarregamento = new AguardaCarregamento(driver);
	}
	
	public OrdemRecolhimento setLocalizacaoProduto(int idProdutoReverso, String local) {
		this.localizacaoProduto = driver.findElement(By.xpath("//table/tbody/tr/td[text() = '" + idProdutoReverso + "']/parent::tr/td[8]/select"));
		new Select(this.localizacaoProduto).selectByVisibleText(local);
		return this;
	}
	
	public OrdemRecolhimento setNumeroContratoServico(int idServicoReverso, String numeroContrato) {
		this.numeroContratoServico = driver.findElement(By.xpath("//table/tbody/tr/td[text() = '" + idServicoReverso + "']/parent::tr/td[7]/input"));
		this.numeroContratoServico.clear();
		this.numeroContratoServico.sendKeys(numeroContrato);
		return this;
	}
	
	public void preencherOrdemRecolhimentoProduto(List<Produto> produtosPreReciboReverso, String localizacaoProduto) {
		for (Produto produto : produtosPreReciboReverso) {
			int idProdutoReverso = produto.getIdProdutoReverso();
			
			if (localizacaoProduto == "Loja") {
				setLocalizacaoProduto(idProdutoReverso, localizacaoProduto).validarEndereco(idProdutoReverso);
			} else {
				setLocalizacaoProduto(idProdutoReverso, localizacaoProduto);
				buscarEndereco(idProdutoReverso);
				
				agCarregamento.aguardarCarregamentoNovaArquitetura2();
				selecionarEnderecoPrincipal().clickConfirmar();
			}
		}
	}
	
	public void preencherOrdemRecolhimentoServicos(List<Servico> servicosPreReciboReverso) {
		for (Servico servico : servicosPreReciboReverso) {
			setNumeroContratoServico(servico.getIdServicoReverso(), "123456");
		}
	}
	
	public OrdemRecolhimento clickConfirmar() {
		this.btnConfirmar.click();
		return this;
	}
	
	/**
	 * Valida se endereço foi encontrado dependendo da localização do produto
	 * 
	 * @param idProdutoReverso
	 * @return
	 */
	public OrdemRecolhimento validarEndereco(int idProdutoReverso) {
		driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div[3]/table/tbody/tr/td[1][contains(text(), '" + idProdutoReverso + "')]/parent::tr/td[9]/i[contains(@class, 'text-success')]")).isDisplayed();
		return this;
	}
	
	public OrdemRecolhimento buscarEndereco(int idProdutoReverso) {
		driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div[3]/table/tbody/tr/td[1][contains(text(), '" + idProdutoReverso + "')]/parent::tr/td[10]/a")).click();
		return this;
	}
	
	public OrdemRecolhimento selecionarEnderecoPrincipal() {
		//driver.findElement(By.xpath("html/body/div[2]/div/div[3]/table/tbody/tr/td[7][contains(text(), 'PRINCIPAL')]/parent::tr/td[1]/input"));
		driver.findElement(By.xpath("html/body/div[2]/div/div[3]/table/tbody[1]/tr/td[1]/input"));
		return this;
	}
}