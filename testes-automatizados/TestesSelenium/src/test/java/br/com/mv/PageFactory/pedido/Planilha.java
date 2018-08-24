package br.com.mv.PageFactory.pedido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.Utils;

public class Planilha {

	WebDriver driver;
	
	@FindBy(css = "#tab-planilha")
	private WebElement abaPlanilha;
	
	@FindBy(css = "#btn-nova-planilha")
	private WebElement btnInsereFormaPagamento;
	
	@FindBy(css = "#btn-credito-sgr")
	private WebElement btnCreditoEnterprise;
	
	// Credito Enterprise
	
	@FindBy(css = "#nro_pedido_enterprise_sgr")
	private WebElement nroPedidoEnterprise;
	
	@FindBy(css = "#nro_loja_enterprise_sgr")
	private WebElement nroLojaEnterprise;
	
	@FindBy(css = "#cmb_empresa_enterprise_chosen")
	private WebElement empresa;
	
	@FindBy(css = "#btn-verificar-credito-sgr")
	private WebElement btnVerificarCredito;
	
	@FindBy(css = "#btn-salvar-credito-sgr")
	private WebElement btnUtilizarCredito;
	
	private Utils utils;
	
	private AguardaCarregamento agCarregamento;
	
	public Planilha(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
		this.agCarregamento = new AguardaCarregamento(driver);
	}
	
	public Planilha clickAbaPlanilha() {
		this.abaPlanilha.click();
		return this;
	}
	
	public Planilha clickBtnInsereProduto() {
		this.btnInsereFormaPagamento.click();
		return this;
	}
	
	public Planilha selecionarTipoFormaPagto(int indiceFormaPagto, String tipoFormaPagto) {
		WebElement elemTipoFormapagto = driver.findElement(By.cssSelector("#grd-planilha-pagto tr[id='" + indiceFormaPagto + "'] select[data-column='flag_tipo_pagto_pedido']"));
		
		new Select(elemTipoFormapagto).selectByVisibleText(tipoFormaPagto);
		
		return this;
	}
	
	public Planilha selecionarFormaPagto(int indiceFormaPagto, String formaPagto) {
		WebElement elemFormaPagto = driver.findElement(By.cssSelector("#grd-planilha-pagto tr[id='" + indiceFormaPagto + "'] select[data-column='nro_forma_pagto']"));
		
		new Select(elemFormaPagto).selectByVisibleText(formaPagto);
		
		return this;
	}
	
	public Planilha informarValorPagto(int indiceFormaPagto, String valorFormaPagto) {
		WebElement elemValorFormaPagto = driver.findElement(By.cssSelector("#grd-planilha-pagto tr[id='" + indiceFormaPagto + "'] select[data-column='vr_planilha']"));
		
		elemValorFormaPagto.sendKeys(valorFormaPagto);
		
		return this;
	}
	
	public Planilha adicionarFormaPagamento(int indiceFormaPagto, String tipoFormaPagto, String formaPagto, String valorFormaPagto) {
		clickAbaPlanilha();
		clickBtnInsereProduto();
		
		selecionarTipoFormaPagto(indiceFormaPagto, tipoFormaPagto);
		selecionarFormaPagto(indiceFormaPagto, formaPagto);
		
		if (!valorFormaPagto.isEmpty()) {
			informarValorPagto(indiceFormaPagto, valorFormaPagto);
		}
		
		return this;
	}
	
	public Planilha clickCreditoEnterprise() {
		this.btnCreditoEnterprise.click();
		return this;
	}
	
	public Planilha setPedidoEnterprise(String nroPedidoEnterprise) {
		this.nroPedidoEnterprise.clear();
		this.nroPedidoEnterprise.sendKeys(nroPedidoEnterprise);
		return this;
	}
	
	public Planilha setLojaEnterprise(String nroLojaEnterprise) {
		this.nroLojaEnterprise.clear();
		this.nroLojaEnterprise.sendKeys(nroLojaEnterprise);
		return this;
	}
	
	public Planilha setEmpresa(String nomeEmpresa) {
		this.utils.preencherCampoChosen(this.empresa, nomeEmpresa);
		return this;
	}
	
	public Planilha clickVerificarCredito() {
		this.btnVerificarCredito.click();
		return this;
	}
	
	public Planilha clickUtilizarCredito() {
		this.btnUtilizarCredito.click();
		return this;
	}
	
	public Planilha adicionarCreditoEnterprise(String nroPedidoEnterprise, String nroLojaEnterprise, String nomeEmpresa) {
		clickCreditoEnterprise();
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		
		setPedidoEnterprise(nroPedidoEnterprise);
		setLojaEnterprise(nroLojaEnterprise);
		setEmpresa(nomeEmpresa);
		
		clickUtilizarCredito();
		
		return this;
	}
	
}
