package br.com.mv.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.Utils;
import net.thucydides.core.pages.PageObject;

public class DetalhesAtendimentoPage extends PageObject {

	@FindBy(xpath = ".//*[@id='form-tratativas']/div[5]/div[2]/button[contains(text(), 'Próximo')]")
	private WebElement btnProximo;
	
	Utils utils;
	
	public DetalhesAtendimentoPage() {
		this.utils = new Utils();
	}
	
	public void clicarBtnProximo() throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		btnProximo.click();
	}
}