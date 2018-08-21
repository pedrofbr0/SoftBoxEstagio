package br.com.mv.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.PageObject;

/**
 * ControleMenu
 * 
 */
public class ControleMenu extends PageObject {

	private WebDriver driver;

	public ControleMenu(WebDriver driver) {
		//super();
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Responsável por navegar entre as opções do menu principal
	 * @param links, sequencia de links a serem acessados no menu
	 * @throws InterruptedException 
	 */
	public void navegar(String[] links, Boolean abrirPopUp) throws InterruptedException {
		
		

		WebElement btnClick = null;
		WebDriverWait wait = new WebDriverWait(this.driver, 90);
		ControleJanela controleJanela = new ControleJanela(this.driver);
		
		Thread.sleep(3000);		
		
		for (int i = 0; i < links.length; i++) {
			if (i == 0) {
//				wait.until(ExpectedConditions.presenceOfElementLocated(By
//				.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" + links[i].trim() + "')]")));
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" 
								+ links[i].trim() + "')]")));
				
				btnClick = driver.findElement(By
						.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" 
								+ links[i].trim() + "')]"));
				
			} else {
				
				if (links[i-1].trim().equals("Faturamento") && links[i].trim().equals("Cadastros")) {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[text()='Faturamento']/parent::li/ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']")));
					btnClick = driver.findElement(By.xpath(".//a[text()='Faturamento']/parent::li/ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']"));
					
				} else if (links[i-1].trim().equals("Mostruário") && (links[i].trim().equals("Gerar Previsão Ordem Montagem") ||  links[i].trim().equals("Gerar Previsão Ordem Desmontagem"))) {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[text()='Mostruário']/parent::li/ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']")));
					btnClick = driver.findElement(By.xpath(".//a[text()='Mostruário']/parent::li/ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']"));
					
				} else {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']")));
					Thread.sleep(3000);
					btnClick = driver.findElement(By.xpath(".//ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']"));
				}
			}
			
			if (btnClick != null) {
				if (abrirPopUp && (i == links.length - 1)) {
					controleJanela.abrirPopupClick(btnClick);
				} else {
					Thread.sleep(4000);
					btnClick.click();
				}
			}
		}
		
	}
	
	/**
	 * Responsável por navegar entre as opções do menu principal
	 * @param links, sequencia de links a serem acessados no menu
	 */
	public void navegarPDV(String[] links) {
		WebDriverWait wait = new WebDriverWait(this.driver, 90);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".navbar")));
		
		for (int i = 0; i < links.length; i++) {
			System.out.println("Abrir página: " + links[i]);
			driver.findElement(By.xpath("//nav/ul[2]/li/a[contains(text(), '" + links[i] + "')]")).click();
		}
	}
	
	public void acessarMenus(String[] links, boolean abrirPopUp) throws InterruptedException {		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		Actions hover = new Actions(driver);
		WebElement btnClick = null;
		WebDriverWait wait = new WebDriverWait(this.driver, 120);
		ControleJanela controleJanela = new ControleJanela(this.driver);
		
		Thread.sleep(10000);		
		
		int count = 4; 
//				links.length;
		
		for (int i = 0; i < links.length; i++) {
			
			System.out.println("count inicial: " + count);
			
			if (i == 0) {
				
				System.out.println(links[i]);
				wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" + links[i].trim() + "')]")));
				Thread.sleep(3000);
				
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By
//						.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" 
//								+ links[i].trim() + "')]")));
				
				btnClick = driver.findElement(By
						.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" 
								+ links[i].trim() + "')]"));
				Thread.sleep(3000);
				
//				hover.moveToElement(btnClick).build().perform();
				
				hover.moveToElement(btnClick).perform();
				
//				Thread.sleep(3000);
				
//				btnClick.click();

				
//				Thread.sleep(3000);
//			}
				
			} else {
				

					System.out.println("entrei no último else"+ i);//					links[i] = "Fornecedor x Parâmetro de Config";
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']")));
					Thread.sleep(3000);
					System.out.println("esperei o elemento ser clickável"+i);
					btnClick = driver.findElement(By.xpath(".//ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='" + links[i].trim() + "']"));
					Thread.sleep(3000);
//					hover.moveToElement(btnClick).build();
					
					
					
					hover.moveToElement(btnClick).perform();
					
//					js.executeScript("jQuery(\".//ul[contains(@class, 'dropdown-menu')]/li/a[not(contains(@class, 'raiz')) and text()='\" + links[i].trim() + \"']\").mouseover();");
					
//					hover.moveToElement(btnClick).click();
//					hover.moveToElement(btnClick).build().perform();	
//					hover.moveToElement(btnClick);
//					hover.click(btnClick);
//					Thread.sleep(3000);
//					btnClick.click();
//					hover.click();
//					Thread.sleep(3000);
//				}
			}
			
			System.out.println(btnClick.toString());
			
			count--;
			
			System.out.println("count final: " + count);
			
			if (count == 0) {
				System.out.println("entrei no último if" + i);
//				Thread.sleep(4000);
//				hover.build();
//				hover.moveToElement(btnClick).build().perform();
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.visibilityOf(btnClick));
//				wait.until(ExpectedConditions.elementToBeClickable(btnClick));
//				System.out.println("Esperei o elemento ser clickável no último if" + i);
//				hover.click(btnClick);
//				hover.moveToElement(btnClick).click();
//				hover.moveToElement(btnClick).build().perform();
	
				btnClick.click();
				
//				hover.click();
				System.out.println("cliquei");
//				hover.perform();
			}
			
//			if (btnClick != null) {
//				if (abrirPopUp && (i == links.length - 1)) {
//					controleJanela.abrirPopupClick(btnClick);
//				} else {
//					Thread.sleep(4000);
////					hover.build();
////					hover.click();
////					hover.perform();
////					btnClick.click();
//				}
//			}
		}
		
		
		
//		Thread.sleep(3000);
//		int count = 0;
//		
//		Actions builder = new Actions(driver);
//		
//		WebElement toHover = driver.findElement(By.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '\" + links[count].trim() + \"')]"));
//		 
//		
//		toHover.click();
//		
//		for(int i = 0; i<links.length; i++) {
		
		
//		builder.moveToElement(toHover).perform();
//		toHoverBy locator = By.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '\\\" + links[count].trim() + \\\"')]");
//		driver.click(locator);
		 
	       // adding 2 seconds wait to avoid Sync issue
	 
//	       Thread.sleep(2000);
	 
	       // Dropdown items are coming in <a> tag so below xpath will get all
	 
	       // elements and findElements will return list of WebElements
	 
//	       List<WebElement> list = driver.findElements(By.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '\\\" + links[count].trim() + \\\"')]"));
	 
	       // We are using enhanced for loop to get the elements
	 
//	       for (WebElement ele : list)
//	 
//	       {
//	    	   count++;
//	 
//	          // for every elements it will print the name using innerHTML
//	 
//	          System.out.println("Values " + ele.getAttribute("innerHTML"));
//	 
//	 
//	 
//	          // Here we will verify if link (item) is equal to Java Script
//	 
//	          if (ele.getAttribute("innerHTML").contains(links[count])) {
//	 
//	             // if yes then click on link (iteam)
//	 
//	             ele.click();
//	 
//	 
//	 
//	             // break the loop or come out of loop
//	 
//	             break;
//	 
//	          }
//	 
//	       }
	       
	       /*
			int count = 0;
			Actions action = new Actions(driver); 
//			WebElement x = driver.findElement(By
//					.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" 
//							+ links[0].trim() + "')]"));
//			
//			x.click();
//			
//			List<WebElement> s = driver.findElements(By
//					.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" 
//							+ links[0].trim() + "')]"));
			
			Thread.sleep(3000);
			
//			WebElement mySelectElement = driver.findElement(By
//					.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" + links[0].trim() + "')]"));
//			Select dropdown= new Select(mySelectElement);
//			List<WebElement> options = dropdown.getOptions();
			
//			for (WebElement option : options) {
//				
//				System.out.println(option.getText());
//				
//			}
			
			for(int i = 0; i < links.length; i++) {
				WebElement optionsList = driver.findElement(By.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" + links[count].trim() + "')]"));
				action.moveToElement(optionsList);
				
				System.out.println(action.toString());
				
				List<WebElement> options = driver.findElements(By.xpath(".//*[@id='conteudoMenu']/li/a[contains(text(), '" + links[count].trim() + "')]"));
				for(WebElement option : options) {
				    if (option.getText().equals(links[count])) {
				    	System.out.println(links[count]);
				        option.click();
				        count++;
				    }
				}
			}
			   
			*/
	 
	       // here you can write rest piece of code
	 
//	       .//*[@id='conteudoMenu']/li/a[contains(text(), '\" + links[count].trim() + \"')]
	   }
}