/*
 * Exercico2
 * 
 * 1.0					 
 *
 * 02/03/2018             
 * 
 * Pedro                
 * Copyright notice      
 * 
 * O exercício em questão pede que se crie um classe Conta com inúmeros
 * atributos e métodos essenciais à este objeto, e depois aplique essa 
 * classe para a criação de objetos Conta
 */

class ExercicioConta1ao9 {
	
	public static void main(String[] args) {
		Conta c1 = new Conta();

        c1.titular = "Hugo";
        c1.numero = 123;
        c1.agencia = "45678-9";
        c1.saldo = 50.0;
        c1.dataDeAbertura.dia = 1;
        c1.dataDeAbertura.mes = 1;
        c1.dataDeAbertura.ano = 2018;

        c1.deposita(100.0);
        System.out.println("saldo atual:" + c1.saldo);
        System.out.println("rendimento mensal:" + c1.calculaRendimento());
		
		Conta c2 = new Conta();
		c2.titular = "Danilo";
		c2.saldo = 100;
		
		Conta c3 = new Conta();
		c3.titular = "Danilo";
		c3.saldo = 100;
		
		Conta c4 = new Conta();
		c4.titular = "Hugo";
		c4.saldo = 100;
		
		Conta c5 = c4;
		
		if (c1 == c2) {
			System.out.println("iguais");
		} else {
			System.out.println("diferentes");
		}
		
		if (c4 == c5) {
			System.out.println("iguais");
		} else {
			System.out.println("diferentes");
		}
		
		Conta c6 = new Conta();
		Data data = new Data();
		c6.dataDeAbertura = data;
		
		
	}
}