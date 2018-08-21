package br.com.caelum.contas.modelo;
import br.com.caelum.contas.data.*;

public interface Conta {

	public int teste = 0;
	
	/* Getters e setters */
	
	public String getTitular();
	public void setTitular(String titular); 
	public void setDataAbertura(int dia, int mes, int ano);
	public String getDataAbertura();
	public String getAgencia();
	public void setAgencia(String agencia);	
	public int getNumero();
	public void setNumero(int numero);
	public double getSaldo();
	public int getIdentificador();
	public boolean deposita(double valor);
	public boolean saca(double valor);
	public double getRendimento();
	public String getTipo();
	public void transfere (double valor, Conta conta);

}
