
public class Conta {
	String titular;
	int numero;
	String agencia;
	double saldo;
	Data dataDeAbertura = new Data();
	
	void saca(double valor) {
		this.saldo = saldo - valor;
	}
	void deposita(double valor) {
		this.saldo = saldo + valor;
	}
	double calculaRendimento() {
		return this.saldo*0.1;
	}
	String recuperaDadosParaImpressao() {
		String dados = "Titular: " + this.titular;
		dados += "\nNúmero: " + this.numero;
		dados += "\nData de abertura: " + this.dataDeAbertura.formatada();		
		return dados;
	}
}

