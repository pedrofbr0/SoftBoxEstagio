package br.com.mv.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

/**
 * AguardarCarregamento
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class AguardaCarregamento {
	
	private WebDriver driver;
	private Boolean abriuModalCarregamento = false;
	
	public AguardaCarregamento(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public Boolean blockOverlayVisivel() {
		return (Boolean)((JavascriptExecutor)this. driver).executeScript("return $('.blockOverlay').is(':visible')");
	}
	
	public void aguardarCarregamentoNovaArquitetura() {
		
		Boolean blockUI = true;
		
		while (abriuModalCarregamento == false) {
			blockUI = blockOverlayVisivel();
			
			if (blockUI == true) {
				abriuModalCarregamento = true;
				
				while (blockUI == true) {
					blockUI = blockOverlayVisivel();
				}
			}
		}
	}
	
	public void aguardarCarregamentoNovaArquitetura2() {
		WebDriverWait wait = new WebDriverWait(this.driver, 50);
		Predicate<WebDriver> carregamentoCompleto = new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver d) {
				try {
					Thread.sleep(500);
					
					Boolean blockUI = (Boolean)((FirefoxDriver)d).executeScript("return $('.blockOverlay').is(':visible') == false");
					String textBody = driver.findElement(By.xpath("html/body")).getText();
					
					Boolean msgCarregando = textBody.contains("Carregando, favor aguardar");
					
					//System.out.println("Aguarde: BlockUI: " + blockUI + " msgCarregando: " + msgCarregando);
					
					if (blockUI == true && msgCarregando == false) {
						return true;
					}
					
					return false;
							
				} catch (WebDriverException e) {
					return e.getMessage().startsWith("$ is not defined");
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return false;
			}
		};
		//wait.until(carregamentoCompleto);
	}
	
	public void aguardarCarregamentoArquiteturaAntiga() throws InterruptedException {
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(this.driver, 30);
		
		if (validarExistenciaAguardar()) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='divAguarde']")));
		}
		Thread.sleep(1000);
	}
	
	private boolean validarExistenciaAguardar() {
		boolean present;
		try {
			boolean divAguarde = driver.findElement(By.cssSelector("div[id='divAguarde']")).isDisplayed();
			if (!divAguarde) {
				return false;
			}
			present = true;
		} catch (NoSuchElementException e) {
			present = false;
		}
		return present;
	}
	
	public void aguardarCarregamentoArquiteturaAguarde() throws InterruptedException {
		Thread.sleep(500);
		WebDriverWait wait = new WebDriverWait(this.driver, 30);
		
		if (validarExistenciaAguardar()) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='_mv_aguarde_']")));
		}
		Thread.sleep(500);
	}
	
	private boolean validarExistenciaAguardarX() {
		boolean present;
		try {
			boolean divAguarde = driver.findElement(By.cssSelector("div[id='_mv_aguarde_']")).isDisplayed();
			if (!divAguarde) {
				return false;
			}
			present = true;
		} catch (NoSuchElementException e) {
			present = false;
		}
		return present;
	}
	
}
