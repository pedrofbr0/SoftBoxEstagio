package br.com.mv.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {


	public Utils() {

	}


	public void preencherCampoTypeAHead(List<String> textos, WebElement elemento, WebElement opcao) {

		for (String texto : textos) {
			elemento.clear();
			elemento.sendKeys(texto);
			opcao.click();
		}
	}

	public void preencherCampoTypeAHead(String texto, WebElement elemento) throws InterruptedException {
		char[] textoParaArray = texto.toCharArray();

		for (char caractere : textoParaArray) {
			elemento.sendKeys(Character.toString(caractere));
			// Thread.sleep(300);
		}
	}

	public void preencherCampoChosen(WebElement elemento, String texto) {
		elemento.click();
		elemento.findElement(By.cssSelector(".chosen-drop .chosen-search input")).clear();
		elemento.findElement(By.cssSelector(".chosen-drop .chosen-search input")).sendKeys(texto);
		elemento.findElement(By.cssSelector(".chosen-results .active-result")).click();
	}

	public void preencherCampoChosenWebDriver(String id, String texto, WebDriver driver) {
		driver.findElement(By.cssSelector(id)).click();
		driver.findElement(By.cssSelector(id)).findElement(By.cssSelector(".chosen-drop .chosen-search input")).clear();
		driver.findElement(By.cssSelector(id)).findElement(By.cssSelector(".chosen-drop .chosen-search input"))
				.sendKeys(texto);
		driver.findElement(By.cssSelector(id)).findElement(By.cssSelector(".chosen-results .active-result")).click();
	}

	public void preencherCampoChosenWebDriverBy(By by, String texto, WebDriver driver) {
		driver.findElement(by).click();
		driver.findElement(by).findElement(By.cssSelector(".chosen-drop .chosen-search input")).clear();
		driver.findElement(by).findElement(By.cssSelector(".chosen-drop .chosen-search input")).sendKeys(texto);
		driver.findElement(by).findElement(By.cssSelector(".chosen-results .active-result")).click();
	}

	public String lerArquivo(String nomeArquivo) throws IOException {
		FileReader arq;
		StringBuffer plSql = new StringBuffer();

		try {
			arq = new FileReader(nomeArquivo);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = null;

			while ((linha = lerArq.readLine()) != null) {
				plSql.append(linha);
				plSql.append(" ");
			}

			arq.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return plSql.toString();
	}

	private boolean comPontos = true;

	private int randomiza(int n) {
		int ranNum = (int) (Math.random() * n);
		return ranNum;
	}

	private int mod(int dividendo, int divisor) {
		return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
	}

	public String gerarCpf() {
		int n = 9;
		int n1 = randomiza(n);
		int n2 = randomiza(n);
		int n3 = randomiza(n);
		int n4 = randomiza(n);
		int n5 = randomiza(n);
		int n6 = randomiza(n);
		int n7 = randomiza(n);
		int n8 = randomiza(n);
		int n9 = randomiza(n);
		int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

		d1 = 11 - (mod(d1, 11));

		if (d1 >= 10)
			d1 = 0;

		int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

		d2 = 11 - (mod(d2, 11));

		String retorno = null;

		if (d2 >= 10)
			d2 = 0;
		retorno = "";

		if (comPontos)
			retorno = "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;
		else
			retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

		return retorno;
	}

	public String gerarCnpj() {
		int n = 9;
		int n1 = randomiza(n);
		int n2 = randomiza(n);
		int n3 = randomiza(n);
		int n4 = randomiza(n);
		int n5 = randomiza(n);
		int n6 = randomiza(n);
		int n7 = randomiza(n);
		int n8 = randomiza(n);
		int n9 = 0; // randomiza(n);
		int n10 = 0; // randomiza(n);
		int n11 = 0; // randomiza(n);
		int n12 = 1; // randomiza(n);
		int d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4
				+ n1 * 5;

		d1 = 11 - (mod(d1, 11));

		if (d1 >= 10)
			d1 = 0;

		int d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4
				+ n2 * 5 + n1 * 6;

		d2 = 11 - (mod(d2, 11));

		if (d2 >= 10)
			d2 = 0;

		String retorno = null;

		if (comPontos)
			retorno = "" + n1 + n2 + "." + n3 + n4 + n5 + "." + n6 + n7 + n8 + "/" + n9 + n10 + n11 + n12 + "-" + d1
					+ d2;
		else
			retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10 + n11 + n12 + d1 + d2;

		return retorno;
	}

	public String geraCNPJ() {

		int digito1 = 0, digito2 = 0, resto = 0;
		String nDigResult;
		String numerosContatenados;
		String numeroGerado;
		Random numeroAleatorio = new Random();
		// numeros gerados
		int n1 = numeroAleatorio.nextInt(10);
		int n2 = numeroAleatorio.nextInt(10);
		int n3 = numeroAleatorio.nextInt(10);
		int n4 = numeroAleatorio.nextInt(10);
		int n5 = numeroAleatorio.nextInt(10);
		int n6 = numeroAleatorio.nextInt(10);
		int n7 = numeroAleatorio.nextInt(10);
		int n8 = numeroAleatorio.nextInt(10);
		int n9 = numeroAleatorio.nextInt(10);
		int n10 = numeroAleatorio.nextInt(10);
		int n11 = numeroAleatorio.nextInt(10);
		int n12 = numeroAleatorio.nextInt(10);
		int soma = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4
				+ n1 * 5;
		int valor = (soma / 11) * 11;
		digito1 = soma - valor;
		// Primeiro resto da divisão por 11.
		resto = (digito1 % 11);
		if (digito1 < 2) {
			digito1 = 0;
		} else {
			digito1 = 11 - resto;
		}
		int soma2 = digito1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3
				+ n3 * 4 + n2 * 5 + n1 * 6;
		int valor2 = (soma2 / 11) * 11;
		digito2 = soma2 - valor2;
		// Primeiro resto da divisão por 11.
		resto = (digito2 % 11);
		if (digito2 < 2) {
			digito2 = 0;
		} else {
			digito2 = 11 - resto;
		}
		// Conctenando os numeros
		numerosContatenados = String.valueOf(n1) + String.valueOf(n2) + "." + String.valueOf(n3) + String.valueOf(n4)
				+ String.valueOf(n5) + "." + String.valueOf(n6) + String.valueOf(n7) + String.valueOf(n8) + "/"
				+ String.valueOf(n9) + String.valueOf(n10) + String.valueOf(n11) + String.valueOf(n12) + "-";
		// Concatenando o primeiro resto com o segundo.
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
		numeroGerado = numerosContatenados + nDigResult;
		System.out.println("Digito 2 ->" + digito2);
		System.out.println("CNPJ Gerado " + numeroGerado);
		return numeroGerado;
		
	}

	public String mostraResultado() throws Exception {
		String resultadoCNPJ = geraCNPJ();
		return resultadoCNPJ;
	}

	public String getDataAtual() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(new Date());

		return data;
	}

	public String getDataAdd(int qtdDias) {
		Date d = new Date();

		Calendar c = Calendar.getInstance();
		c.setTime(d);

		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + qtdDias);

		String data = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());

		return data;
	}
}
