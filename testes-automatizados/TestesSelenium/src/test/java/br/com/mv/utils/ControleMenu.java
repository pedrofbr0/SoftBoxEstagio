package br.com.mv.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ControleMenu
 * 
 * @author antoniojunior
 *
 */
public class ControleMenu {

	private WebDriver driver;

	public ControleMenu(WebDriver driver) {
		super();
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Responsável por navegar entre as opções do menu principal
	 * @param links, sequencia de links a serem acessados no menu
	 * @throws InterruptedException 
	 */
	public void navegar(String... links) throws InterruptedException {

		WebElement btnClick = null;
		WebDriverWait wait = new WebDriverWait(this.driver, 90);

		Thread.sleep(3000);
		
		for (int i = 0; i < links.length; i++) {
			//System.out.println("menu: " + links[i]);
			if (i == 0) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" + links[i].trim() + "')]")));
				btnClick = driver.findElement(By.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" + links[i].trim() + "')]"));
				
			} else {
				if (links[i].trim().equals("Relatórios") || links[i].trim().equals("Cadastro")) {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" + links[i-1].trim() + "')]/parent::li/ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']")));
					btnClick = driver.findElement(By.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" + links[i-1].trim() + "')]/parent::li/ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']"));
					
				} else if (links[i-1].trim().equals("Cadastro [Novo]") && links[i].trim().equals("Serviços") || links[i-1].trim().equals("Cadastros") && links[i].trim().equals("Caixa")) {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i-1].trim() + "']/parent::li/ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']")));
					btnClick = driver.findElement(By.xpath(".//ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i-1].trim() + "']/parent::li/ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']"));
					
				} else {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']")));
					btnClick = driver.findElement(By.xpath(".//ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']"));
				}
			}
			
			if (btnClick != null) {
				System.out.println("clicou: " + links[i]);
				btnClick.click();
			}
			
			Thread.sleep(1000);
			
		}
	}
	
	public void navegar(Boolean openPopup, String... links) throws InterruptedException {

		WebElement btnClick = null;
		WebElement dropDown;
		WebDriverWait wait = new WebDriverWait(this.driver, 20);
		Actions actions = new Actions(this.driver);
		ControleJanela controleJanela = new ControleJanela(this.driver);
		
		for (int i = 0; i < links.length; i++) {
			
			if (i > 0) {
				List<WebElement> itens =  driver.findElements(By.xpath("//ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i] + "']"));
				
				for (WebElement item : itens) {
					if (item.isDisplayed()) {
						btnClick = item;
						break;
					}
				}
				
			} else {
				//btnClick = driver.findElement(By.linkText(links[i]));
				btnClick = driver.findElement(By.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" + links[i] + "')]"));
			}
			
			actions.moveToElement(btnClick).build().perform();
			
			if (i + 1 == links.length) {
				
				System.out.printf("Abrir página: %s \n", links[i]);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(links[i])));
				//btnClick.click();
				controleJanela.abrirPopupClick(btnClick);
			} else {
				
				
				System.out.println("Menu: " + links[i + 1]);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(links[i + 1])));
			}
		}
	}

	// html/body/div[1]/nav/ul[6]/li/a[contains(text(),'Troca/Devolução')]
	// html/body/div[1]/nav/ul[6]/li/a[contains(text(),'Troca/Devolução')]/parent::li/ul/li/a[contains(text(),'Pré-Recibos')]
	
	public void navegarMenuNovo(String... links) throws InterruptedException {

		WebElement btnClick = null;
		WebElement dropDown;
		WebDriverWait wait = new WebDriverWait(this.driver, 20);
		Actions actions = new Actions(this.driver);
		ControleJanela controleJanela = new ControleJanela(this.driver);
		
		for (int i = 0; i < links.length; i++) {
			
			if (i > 0) {
				List<WebElement> itens =  driver.findElements(By.xpath("html/body/div[1]/nav/ul[contains(@class, 'nav')]/li/ul/li/a[contains(text(), '" + links[i] + "')]"));
				
				for (WebElement item : itens) {
					if (item.isDisplayed()) {
						btnClick = item;
						break;
					}
				}
				
			} else {
				btnClick = driver.findElement(By.xpath("html/body/div[1]/nav/ul[6]/li/a[contains(text(), '" + links[i] + "')]"));
			}
			
			actions.moveToElement(btnClick).build().perform();
			
			if (i + 1 == links.length) {
				
				System.out.printf("Abrir página: %s \n", links[i]);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(links[i])));
				btnClick.click();
			} else {
				
				System.out.println("Menu: " + links[i + 1]);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(links[i + 1])));
			}
		}
	}
	
}
