package br.com.mv.PageFactory.pedido;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.mv.utils.AguardaCarregamento;

public class AnaliseComercial {

	WebDriver driver;
	
	@FindBy(css = "#tab-analise-comercial")
	private WebElement abaAnaliseComercial;
	
	private AguardaCarregamento agCarregamento;
	
	public AnaliseComercial(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AnaliseComercial clickAbaAnaliseComercial() {
		this.abaAnaliseComercial.click();
		return this;
	}
	
	public AnaliseComercial setValorProduto(HashMap<String, String> prod) {
		if (prod.containsKey("vlr_produto_a_maior")) {
			clickAbaAnaliseComercial();
			//agCarregamento.aguardarCarregamentoNovaArquitetura();
			driver.findElement(By.xpath("//td[contains(text(), '" + prod.get("cod_nro_produto") + "')]/parent::tr/td[7]/input")).clear();
			driver.findElement(By.xpath("//td[contains(text(), '" + prod.get("cod_nro_produto") + "')]/parent::tr/td[7]/input")).sendKeys(prod.get("vlr_produto_a_maior") + Keys.ENTER);
		}
		
		return this;
	}
}