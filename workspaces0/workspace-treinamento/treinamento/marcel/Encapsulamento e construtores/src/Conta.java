
public class Conta {
	private String titular;
	private int numero;
	private double saldo;
	private static int identificador;
	private String agencia;
	Data dataDeAbertura = new Data();
	
	public Conta() {
		identificador++;
	}
	
	public Conta() { }
	
	public Conta(String titular) {
      this.titular = titular;
    }
	
	public static int getIdentificador() {
		return identificador;
	}

	public void saca(double quantidade) {
		double novoSaldo = this.saldo - quantidade;
		this.saldo = novoSaldo;
	}

	public void deposita(double quantidade) {
		double novoSaldo = this.saldo + quantidade;
		this.saldo = novoSaldo;
	}
	
	public String getTitular() {
        return this.titular;
    }
	
	
    public void setTitular(String titular) {
        this.titular = titular;
    }
    
    public double calculaRendimento() {
    	double rendimento; // cria variavel de rendimentos
    	rendimento = this.saldo*0.1; // Multiplica o saldo por 0.1
    	return rendimento; // retorna o valor do rendimento
    	}

    public String recuperaDadosParaImpressao() {
        String dados = "Titular: " + this.titular;
        dados += "\nNúmero: " + this.numero;
        dados += "\nAgência: " + this.agencia;
        dados += "\nSaldo: " + this.saldo;
        dados += "\nData de abertura: " + this.dataDeAbertura.formatar();
        return dados;
    }

}
