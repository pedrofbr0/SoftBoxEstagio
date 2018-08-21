public class Conta {
	String titular, agencia;
	int numero;
	double saldo = 0.0;
	Data dataAbertura = new Data();
	
	boolean saca(double valor) {
		if(valor>saldo) {
			return false;
		} else {
			this.saldo -= valor;
			return true;
		}
	}
	
	void deposita(double valor) {
		this.saldo += valor;
	}
	
	double calculaRendimento() {
		return this.saldo*0.1;
	}
	
	String recuperaDadosParaImpressao() {
		String dados = "Titular: " + this.titular;
		dados += "  Agencia: " + this.agencia;
		dados += "  Numero: " + this.numero;
		dados += "  Saldo: " + this.saldo;
		dados += "  Data de abertura: " + this.dataAbertura.dataFormatada();
		dados += "  Rendimento: " + this.calculaRendimento();
		return dados;
	}
}
