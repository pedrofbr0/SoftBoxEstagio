
public class BalancoTrimestral {
	
	public static void main(String[] args) {
		
	int gastosJaneiro = 1500;
	int gastosFevereiro = 23000;
	int gastosMarco = 17000;
	
	int gastosTrimestre = gastosJaneiro + gastosFevereiro + gastosMarco;
	
	System.out.println(gastosTrimestre);
	
	float mediaMensal = (gastosJaneiro+gastosFevereiro+gastosMarco)/3;
	
	System.out.println("Valor da média mensal = " + mediaMensal);	
	
	
	}
	
}
