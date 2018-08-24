package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_CUPOM_FISCAL;
import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.dao.NotaFiscalDAO;
import br.com.mv.utils.ControleJanela;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.pages.PageObject;

public class ConfirmacaoSaidaEstoquePage extends PageObject {

	@FindBy(css = "toolbar:btnSearch")
	private WebElement btnBuscarPedido;
	
	@FindBy(css = "#txtNroPedido")
	private WebElement txtPedido;
	
	@FindBy(css = "#btnConfirmacao")
	private WebElement btnConfirmacao;
	
	@FindBy(css = "#txtCupomFiscal")
	private WebElement txtCupomFiscal;
	
	@FindBy(css = "#btnConfirmar")
	private WebElement btnConfirmar;
	
	@FindBy(xpath = "html/body/div[5]/div/div/div[2]")
	private WebElement mensagemConfirmacao;
	
	ControleJanela controleJanela;
	
	public ConfirmacaoSaidaEstoquePage() {
		
	}
	
	public ConfirmacaoSaidaEstoquePage setPedido() {
		this.txtPedido.clear();
		this.txtPedido.sendKeys((String) Serenity.sessionVariableCalled(NRO_PEDIDO));
		return this;
	}
	
	public ConfirmacaoSaidaEstoquePage selecionarPedido() {
		$("//*[@id='table:tContent']/tbody/tr[1]/td[1]/input").click();
		
		String nroCupomFiscal = $("//*[@id='table:tContent']/tbody/tr[1]/td[3]").getText();
		Serenity.setSessionVariable(NRO_CUPOM_FISCAL).to(nroCupomFiscal.trim());
		
		return this;
	}
	
	public ConfirmacaoSaidaEstoquePage clickBtnBuscarPedido() {
		this.btnBuscarPedido.click();
		return this;
	}
	
	public ConfirmacaoSaidaEstoquePage clickBtnConfirmacaoPedido() throws InterruptedException {
		//this.btnConfirmacao.click();
		controleJanela.abrirPopupClick(this.btnConfirmacao);
		return this;
	}
	
	public ConfirmacaoSaidaEstoquePage selecionarProdutoPedido() {
		
		List<WebElement> produtos = this.getDriver().findElements(By.xpath("//*[@id='table:tContent']/tbody/*/*/input"));
		
		for (WebElement produto : produtos) {
			produto.click();
		}
		
		return this;
	}
	
	public ConfirmacaoSaidaEstoquePage informarCupomFiscal() {
		String nroCupomFiscal = (String) Serenity.sessionVariableCalled(NRO_CUPOM_FISCAL);
		this.txtCupomFiscal.sendKeys(nroCupomFiscal);
		return this;
	}
	
	public ConfirmacaoSaidaEstoquePage clickBtnConfirmar() {
		this.btnConfirmar.click();
		return this;
	}
	
	public void validarMensagemSucesso() throws InterruptedException {
		//Assert.assertTrue("Erro ao excluir agenda abastecimento", mensagemExclusao.getText().equals("Registro excluido com sucesso."));
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		String msgAlert = this.getDriver().switchTo().alert().getText();
		
		String msg = "Os itens do Cupom/Nota foram confirmados com sucesso.";
		
		Assert.assertTrue("Erro ao realizar confirmação de saída de estoque.", msgAlert.equals(msg));
	}
}