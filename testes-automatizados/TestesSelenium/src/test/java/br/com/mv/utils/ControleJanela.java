package br.com.mv.utils;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Classe que controla abertura e fechamento de popups
 * 
 * @author Antonio Júnior <antoniojunior@softbox.com.br>
 */
public class ControleJanela {

	private WebDriver driver;

	private String janelaPrincipal;

	private String popupAtivo;
	
	private AguardaCarregamento agCarregamento;
	
	WebDriverWait wait;

	public ControleJanela(WebDriver driver) {
		super();
		this.driver = driver;
		this.janelaPrincipal = driver.getWindowHandle();
		this.agCarregamento = new AguardaCarregamento(driver);
		this.wait = new WebDriverWait(driver, 20);
	}
	
	/**
	 * Captura o popup anterior e abre o seguinte
	 * @param elemento, elemento do DOM ser usado (ex.: button, select, ...).
	 * @param botao, um boolean informando se o mesmo é um botão
	 * @param valor, valor do elemento (texto)
	 * @throws InterruptedException
	 */
	private void abrirPopup(WebElement elemento, boolean botao, String valor) throws InterruptedException {
		//Set<String> janelasAntes = new HashSet<String>(this.driver.getWindowHandles());
		acionarSeletor(elemento, botao, valor);
		selecionarPopup();
	}
	
	private void abrirPopup(WebElement elemento, boolean botao, String valor, boolean semAguardarCarregamento) throws InterruptedException {
		Set<String> janelasAntes = new HashSet<String>(this.driver.getWindowHandles());
		acionarSeletor(elemento, botao, valor);
		selecionarPopup(janelasAntes, semAguardarCarregamento);
	}
	
	/**
	 * Abre um popup após um Alert
	 * @param accept, informa se a reposta do Alert será positiva (ex.: OK), ou negativa (ex.: Cancelar)
	 * @throws InterruptedException
	 */
	public void abrirPopupAlert(boolean accept) throws InterruptedException {
		//Set<String> janelasAntes = new HashSet<String>(this.driver.getWindowHandles());
		wait.until(ExpectedConditions.alertIsPresent());
		if (accept) {
			driver.switchTo().alert().accept();
		} else {
			driver.switchTo().alert().dismiss();
		}
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		selecionarPopup();
	}
	
	public void abrirPopupAlert(boolean accept, boolean bloquearJanelaConfirmacao) throws InterruptedException {
		//Set<String> janelasAntes = new HashSet<String>(this.driver.getWindowHandles());
		wait.until(ExpectedConditions.alertIsPresent());
		if (accept) {
			driver.switchTo().alert().accept();
		} else {
			driver.switchTo().alert().dismiss();
		}
		
		if (!bloquearJanelaConfirmacao) {
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().dismiss();
		}
		
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		selecionarPopup();
	}
	
	/**
	 * Recebo o Elemento que ira abrir o popup representado um por selector
	 * @param select, elemento
	 * @param valor, texto do select
	 * @throws InterruptedException
	 */
	public void abrirPopupSelect(WebElement select, String valor) throws InterruptedException {
		abrirPopup(select, false, valor);
	}
	
	public void abrirPopupSelect(By select, String valor) throws InterruptedException {
		WebElement element = this.driver.findElement(select);
		abrirPopupSelect(element, valor);
	}
	
	/**
	 * Recebe o elemento a ser clicado
	 * @param botao, selector do botão
	 * @throws InterruptedException
	 */
	public void abrirPopupClick(WebElement botao) throws InterruptedException {
		abrirPopup(botao, true, "");
	}
	
	public void abrirPopupClick(By botao) throws InterruptedException {
		WebElement elemento = this.driver.findElement(botao);
		abrirPopupClick(elemento);
	}
	
	public void abrirPopupClick(WebElement botao, boolean semAguardarCarregamento) throws InterruptedException {
		abrirPopup(botao, true, "", semAguardarCarregamento);
	}
	
	/**
	 * Seleciona o último popup aberto
	 * @param janelasAntes
	 * @throws InterruptedException
	 */
	private void selecionarPopup() throws InterruptedException {
		int qtdJanelas = driver.findElements(By.tagName("iframe")).size();
		System.out.println("qtdJanelas selecionar Popup: " + qtdJanelas);
		
		driver.switchTo().frame(qtdJanelas - 1);
		
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		Thread.sleep(1000);
		System.out.println(driver.getTitle());
	}
	
	/**
	 * Seleciona o último popup aberto
	 * @param janelasAntes
	 * @throws InterruptedException
	 */
	private void selecionarPopup(Set<String> janelasAntes, boolean semAguardarCarregamento) throws InterruptedException {
		/*Set<String> janelasDepois = new HashSet<String>(this.driver.getWindowHandles());
		janelasDepois.removeAll(janelasAntes);
		this.popupAtivo = janelasDepois.iterator().next();
		this.driver.switchTo().window(this.popupAtivo);*/
		
		int qtdJanelas = driver.findElements(By.tagName("iframe")).size();
		System.out.println("qtdJanelas selecionar Popup: " + qtdJanelas);
		
		driver.switchTo().frame(qtdJanelas - 1);
		
		Thread.sleep(1000);
		System.out.println(driver.getTitle());
	}
	
	/**
	 * Aciona o elemento que ira disparar a abertura do popup 
	 * @param elemento, selector do elemento
	 * @param click, um boolean informando se o evente será um click
	 * @param valor, value do selector
	 */
	private void acionarSeletor(final WebElement elemento, final boolean click, final String valor) {
		Thread t = new Thread() {
			public void run() {
				if (click) {
					System.out.println("clicou");
					elemento.click();
				} else {
					new Select(elemento).selectByVisibleText(valor);
				}
			};
		};
		t.start();
		try {
			t.join(1000);
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Volta para o último popup aberto na tela
	 * @throws InterruptedException
	 */
	public void voltarJanelaPrincipal() throws InterruptedException {
		/*for(String winHandle : driver.getWindowHandles()){
			this.janelaPrincipal = winHandle;
		}
		this.driver.switchTo().window(this.janelaPrincipal);*/
		this.driver.switchTo().defaultContent();
		System.out.println(driver.getTitle());
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
	}
	
	/**
	 * Volta para o último popup aberto na tela
	 * @param alert, informa se a reposta do Alert será positiva (ex.: OK), ou negativa (ex.: Cancelar)
	 */
	public void voltarJanelaPrincipal(boolean alert) {
		for(String winHandle : driver.getWindowHandles()){
			this.janelaPrincipal = winHandle;
		}
		this.driver.switchTo().window(this.janelaPrincipal);
		wait.until(ExpectedConditions.alertIsPresent());
		System.out.println("Alert: " + driver.switchTo().alert().getText());
		if (alert) {
			driver.switchTo().alert().accept();
		} else {
			driver.switchTo().alert().dismiss();
		}
	}
	
	/**
	 * Aciona o selector que fechará a janela, e aguarda até que a mesma tenha se fechado.
	 * @param elemento, selector do elemento que fechará a janela
	 * @throws InterruptedException
	 */
	public void fecharJanela(WebElement elemento) throws InterruptedException {
		//int qtdJanelas = driver.getWindowHandles().size();
		elemento.click();
		//Thread.sleep(1000);
		//while (driver.getWindowHandles().size() >= qtdJanelas);
		voltarJanelaPrincipal();
	}
	
	/**
	 * Aciona o selector que fechará a janela, e aguarda até que a mesma tenha se fechado.
	 * @param elemento, selector do elemento que fechará a janela
	 * @throws InterruptedException
	 */
	public void fecharJanelaVoltarUltimoModal(WebElement elemento) throws InterruptedException {
		//int qtdJanelas = driver.findElements(By.tagName("iframe")).size(); //driver.getWindowHandles().size();
		//System.out.println("qtdIframes: " + qtdJanelas);
		elemento.click();
		Thread.sleep(1000);
		//while (driver.getWindowHandles().size() >= qtdJanelas);
		voltarUltimoModal();
		//voltarJanelaPrincipal();
	}
	
	/**
	 * Volta para o último popup aberto na tela
	 * @throws InterruptedException
	 */
	public void voltarUltimoModal() throws InterruptedException {
		
		/*for(String winHandle : driver.getWindowHandles()){
			this.janelaPrincipal = winHandle;
		}*/
		
		//this.driver.switchTo().window(this.janelaPrincipal);
		//this.getDriver().switchTo().defaultContent();
		
		/*List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        // print your number of frames
        System.out.println("qtd iframes apos selecionar cliente: " + iframes.size());

        WebElement ultimoModal = null;
        
        // you can reach each frame on your site
        for (WebElement iframe : iframes) {

        	ultimoModal = iframe;
        }
        
        // switch to every frame
        driver.switchTo().frame(iframes.size() - 1);*/
        
		/*int qtdJanelas = driver.findElements(By.tagName("iframe")).size();
		System.out.println("voltarUltimoModal: " + qtdJanelas);
		*/
		System.out.println("frame pai");
		//driver.switchTo().frame(2);
		driver.switchTo().parentFrame();
		System.out.println(driver.getTitle());
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		//selecionarPopup();
	}
	
	public void fecharJanela(By elemento) throws InterruptedException {
		fecharJanela(driver.findElement(elemento));
	}
}