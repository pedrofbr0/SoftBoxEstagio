/*
 * Teste            
 * 
 * v0.1   
 *
 * 02/03/2018 10:12                  
 * 
 * Rafael               
 */

public class Teste {
	
	public static void main(String args[]) {
		
		/* Parte 2 e 3 do exercicio */
		
		Conta c = new Conta();
		
		c.titular = "Cerginho da Pereira Nunes";
		c.agencia = "3333-3";
		c.numero = 123456;
		c.saldo = 10.0;
		c.dataAbertura.dia = 2;
		c.dataAbertura.mes = 3;
		c.dataAbertura.ano = 2018;
		
		/* Exibindo atributos */
		
		System.out.println(c.recuperaDadosParaImpressao());
		
		/* Testando a funcao de saque */ 
		
		System.out.println("Sacando 10 dinheiros: ");
		c.saca(10.0);
		System.out.println(c.recuperaDadosParaImpressao());
		
		/* Testando a funcao de deposito e calculo de rendimento */
		
		System.out.println("Depositando 20 dinheiros: ");
		c.deposita(20.0);		
		System.out.println(c.recuperaDadosParaImpressao());
		
		/* Parte 4 e 5 do exercicio */
		
		Conta c1 = new Conta();        
	    c1.titular = "Cerginho";
	    c1.saldo = 100;

	    Conta c2 = new Conta();        
	    c2.titular = "Cerginho";
	    c2.saldo = 100;

	    if (c1 == c2) {
	        System.out.println("iguais");
	    }else {
	        System.out.println("diferentes");        
	    }
		
		Conta c3 = c1;
		
		if (c1 == c3) {
	        System.out.println("iguais");
	    }else {
	        System.out.println("diferentes");        
	    }
		
		/* Parte 8 do exercicio : Conta.saldo não faz sentido
		 * porque Conta é uma classe e o operador ponto só funciona em objetos,
		 *  se fosse feito Conta conta = new conta(); conta.saldo faria sentido;
		 *  */
		
	}
	
}
