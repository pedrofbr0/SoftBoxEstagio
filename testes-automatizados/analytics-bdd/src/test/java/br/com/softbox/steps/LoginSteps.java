package br.com.softbox.steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.softbox.pages.HomePage;
import net.thucydides.core.annotations.Steps;

public class LoginSteps {

	@Steps
	HomePage home;
	//Pages pages;
	
	/**
	 * Endereço da instância. Ex: http://10.41.0.100:8080
	 * 
	 * @param url
	 */
	@Given("Eu acesso a aplicacao $url")
    public void iAccess(String url) {
		
		home.setDefaultBaseUrl(url);
		home.open();
		
    }
	
	/**
	 * Tela de login do sistema.
	 * 
	 * @param loja
	 * @param usuario
	 * @param senha
	 * @throws InterruptedException 
	 */
	@When("Eu preencho o campo $campoUsuario com usuario $usuario o campo $campoSenha com senha $senha e clico no botao $botaoLogin")
    public void iLogin(String campoUsuario, String usuario, String campoSenha, String senha, String botaoLogin) throws InterruptedException {
		
		home.login(campoUsuario, usuario, campoSenha, senha, botaoLogin);
    }
	
	/**
	 * Tela de login do sistema.
	 * 
	 * @param loja
	 * @param usuario
	 * @param senha
	 * @throws InterruptedException 
	 */
	@When("Eu preencho o campo $campo com o valor $valor")
    public void euPreechoCampo(String campo, String valor) throws InterruptedException {
		
		home.preecherCampo(campo, valor);
    }
	
	/**
	 * Tela de login do sistema.
	 * 
	 * @param loja
	 * @param usuario
	 * @param senha
	 * @throws InterruptedException 
	 */
	@When("Eu clico no botao $botaoLogin")
    public void iClicoBotaoLogin(String botaoLogin) throws InterruptedException {
		
		home.clicarBotaoLogin(botaoLogin);
    }
	
	/**
	 * Valida o status da aplicacao
	 * 
	 * @throws InterruptedException 
	 */
	@Then("Eu verifico o status da aplicacao")
    public void euVerificoOStatusDaAplicacao() throws InterruptedException {
		
		home.verificarStatusAplicacao();
    }
	
	/**
	 * Valida o status da aplicacao
	 * 
	 * @throws InterruptedException 
	 */
	@Then("Eu valido a presenca do elemento $elementoTelaInicial")
    public void euVerificoOStatusDaAplicacao(String elementoTelaInicial) throws InterruptedException {
		
		home.verificarPresencaElemento(elementoTelaInicial);
    }
	
	
}