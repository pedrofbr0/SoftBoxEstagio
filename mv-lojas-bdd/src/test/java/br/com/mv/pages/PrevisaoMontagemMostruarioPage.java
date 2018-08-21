package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.COD_NRO_LOJA;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.dao.LojaDAO;
import br.com.mv.dao.OrdemMontagemDesmontagemDAO;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.Utils;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class PrevisaoMontagemMostruarioPage extends PageObject{

	@FindBy(css = "#btnPesquisarProduto")
	private WebElement btnPesquisaProduto;
	
	// Modal de consulta de produtos
	
	@FindBy(css = "#txtCodProduto")
	private WebElement txtCodProduto;
	
	@FindBy(css = "#btnPesquisarProduto")
	private WebElement btnPesquisarProduto;
	
	@FindBy(xpath = "//*[@id='table:tContent']/tbody/tr/td[2]")
	private WebElement selecionarProduto;

	@FindBy(xpath = "//*[@id='table:tContent']/tbody/tr/td[3]")
	private WebElement selecionarProdutoMontagem;
	
	@FindBy(css = "#btnAdd")
	private WebElement btnAdd;
	
	@FindBy(css = "#btnConfirmar")
	private WebElement btnConfirmar;
	
	@FindBy(css = "input[value='+']")
	private WebElement btnAdicionarProdutoDesmontagem;
	
	@FindBy(css = "input[value='Confirmar']")
	private WebElement btnConfirmarPrevisaoDesmontagem;
	
	Utils utils;
	ControleJanela controleJanela;
	OrdemMontagemDesmontagemDAO ordemMontaDesmontaDAO;
	LojaDAO lojaDAO;
	MontadoraPage montadora;
	
	public PrevisaoMontagemMostruarioPage() {
		utils = new Utils();
		ordemMontaDesmontaDAO = new OrdemMontagemDesmontagemDAO();
		lojaDAO = new LojaDAO();
	}
	
	public int getNroLoja() {
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
		
		int nroLoja = lojaDAO.buscarLoja(Integer.parseInt(codNroLoja), Integer.parseInt(nroEmpresa)).getNroLoja();
		
		return nroLoja;
	}
	
	public void clicarBtnPesquisarProduto() throws InterruptedException {
		System.out.println("pesquisa produto");
		controleJanela.abrirPopupClick(this.btnPesquisaProduto);
	}
	
	public void clicarBtnPesquisarProdutoModal() {
		this.btnPesquisarProduto.click();
	}
	
	public void clicarBtnPesquisarOrdemMontagem() {
		$(".//*[@id='toolbar:btnSearch']").click();
	}
	
	public void buscarProdutoJaPossuaOrdemMontagem() throws InterruptedException {
		
		int nroLoja = this.getNroLoja();
		int nroProduto = ordemMontaDesmontaDAO.getProdutoPossuiOrdemMontagem(nroLoja).getNroProduto();
		
		this.informarCodigoProduto(Integer.toString(nroProduto));
		
		this.clicarBtnPesquisarProdutoModal();
		
		this.selecionarProduto();
	}
	
	public void informarCodigoProduto(String codProduto) {
		this.txtCodProduto.clear();
		this.txtCodProduto.sendKeys(codProduto);
	}
	
	public void selecionarProduto() throws InterruptedException {
		controleJanela.fecharJanelaVoltarUltimoModal(this.selecionarProduto);
	}
	
	public void selecionarProdutoMontagem() throws InterruptedException {
		this.selecionarProdutoMontagem.click();
	}
	
	public void clicarBtnAdicionar() throws InterruptedException {
		this.btnAdd.click();
	}
	
	public void validarMensagemBloqueioJaExisteMontagemProduto() {
		
		String msgAlert = this.getAlert().getText();
		
		Assert.assertTrue("Validação incorreta.", msgAlert.contains("já possui ordem de montagem"));
	}
	
	public void buscarProdutoSemEstoqueEOrdemMontagem() throws InterruptedException {
		int nroLoja = this.getNroLoja();
		int nroProduto = ordemMontaDesmontaDAO.getProdutoSemEstoqueEOrdemMontagem(nroLoja).getNroProduto();
		
		this.informarCodigoProduto(Integer.toString(nroProduto));
		
		this.clicarBtnPesquisarProdutoModal();
		
		this.selecionarProduto();
	}
	
	public void validarMensagemBloqueioProdutoSemEstoqueEOrdemMontagem() {
		
		String msgAlert = this.getAlert().getText();
		
		Assert.assertTrue("Validação incorreta.", msgAlert.contains("não possui estoque"));
	}
	
	public void buscaProdutoComEstoqueESEmOrdemMontagem() throws InterruptedException {
		int nroLoja = this.getNroLoja();
		int nroProduto = ordemMontaDesmontaDAO.getProdutoComEstoqueESemOrdemMontagem(nroLoja).getNroProduto();
		
		this.informarCodigoProduto(Integer.toString(nroProduto));
		
		this.clicarBtnPesquisarProdutoModal();
		
		this.selecionarProduto();
	}
	
	public void validarMensagemSucessoMontagem() throws InterruptedException {
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		String msgAlert = this.getDriver().switchTo().alert().getText();
		
		Assert.assertTrue("Validação incorreta.", msgAlert.contains("Previsao de montagem gerada com sucesso!"));
	}
	
	public void clicarBtnConfirmar() throws InterruptedException {
		this.btnConfirmar.click();
		
		// Aceita gerar previsoes
		controleJanela.abrirPopupAlert(true);
	}
	
	public void validarMensagemSucessoDesMontagem() throws InterruptedException {
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		String msgAlert = this.getDriver().switchTo().alert().getText();
		
		Assert.assertTrue("Validação incorreta.", msgAlert.contains("Previsao de desmontagem gerada com sucesso!"));
	}
	
	public void clicarBtnAdicionarProdutOrdemDesmontagem() {
		this.btnAdicionarProdutoDesmontagem.click();
	}
	
	public void clicarBtnConfirmarDesmontagem() throws InterruptedException {
		this.btnConfirmarPrevisaoDesmontagem.click();
		
		// Aceita gerar previsoes
		controleJanela.abrirPopupAlert(true);
	}
	
	public void validarMensagemProdutoSemOrdemMontagem() {
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		String msgAlert = this.getDriver().switchTo().alert().getText();
		
		Assert.assertTrue("Validação incorreta.", msgAlert.contains("Não foram encontrados registros para essa pesquisa!"));
	}
	
	public void buscarProdutoComEstoqueEOrdemMontagem() throws InterruptedException {
		
		int nroLoja = this.getNroLoja();
		int nroProduto = ordemMontaDesmontaDAO.getProdutoComEstoqueEOrdemMontagem(nroLoja).getNroProduto();
		
		this.informarCodigoProduto(Integer.toString(nroProduto));
		
		this.clicarBtnPesquisarProdutoModal();
		
		this.selecionarProduto();
	}
	
	public void clicarBtnOkGerarOrdemMontagemProdutoOrdemExistente() {
		this.getAlert().accept();
	}
	
	public void clicarBtnOkConfirmarMontadoraValidoMensagemSucesso() throws InterruptedException {
		montadora.clicarBtnOk();
		
		this.validarMensagemSucessoMontagem();
	}
}