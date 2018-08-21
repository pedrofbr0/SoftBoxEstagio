package execcao;

public class SaldoInsuficienteException extends RuntimeException {
	
	public SaldoInsuficienteException(String message) {
		super(message);
	}
	public SaldoInsuficienteException(double valor) {
		super("Saldo insuficiente para sacar o valor de: " + valor);
	}
	
}
