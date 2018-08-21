package br.com.caelum.contas;
import br.com.caelum.contas.modelo.*;
import br.com.caelum.javafx.api.util.Evento;

public class ManipuladorDeContas {
	
	private Conta conta;
	
	public void criaConta(Evento evento) {
		 this.conta = new Conta();
		 conta.setAgencia("1234");
	     conta.setNumero(56789);
	     conta.setTitular("Batman");
	}
	public void deposita(Evento evento) {
		double valor = evento.getDouble("valor");
		this.conta.deposita(valor);
	}
	public void saca(Evento evento) {
		double valor = evento.getDouble("valor");
		this.conta.saca(valor);
	}
}
