/*
 * orientacaoaobjetos
 * 
 * Version 0.1
 *
 * 02/03/2018 8:00
 * 
 * Marcel Santee
 * Esse programa foi feito com o intuito de aprender orientação a objetos
 */


public class orientacaoaobjetos {
	public static void main(String args[]) {
		Conta c1 = new Conta();
		Conta c2 = new Conta();
		c1.titular = "Hugo";
        c1.numero = 123;
        c1.agencia = "45678-9";
        c1.saldo = 50.0;
        //c1.deposita(100.0);
        c2.titular = "Hugo";
        c2.numero = 123;
        c2.agencia = "45678-9";
        c2.saldo = 50.0;
        comparaponteiros(c1,c2);
        c1=c2;
        comparaponteiros(c1,c2);
        c1.dataDeAbertura.ano = 2018;
        c1.dataDeAbertura.dia = 2;
        c1.dataDeAbertura.mes = 3;
        c1.dataDeAbertura.formatar();
        System.out.println(c1.recuperaDadosParaImpressao());
        
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 1; i <= 6; i++) {
            int resultado = fibonacci.calculaFibonacci(i);
            System.out.println(resultado);
        }
        
        Fibonacci fibonacci2 = new Fibonacci();
        for (int i = 1; i <= 6; i++) {
            int resultado = fibonacci2.calculaFibonacci(i);
            System.out.println(resultado);
        }
	}

	private static void comparaponteiros(Conta c1, Conta c2) {
		if (c1 == c2) {
			System.out.println("iguais");
		} else {
			System.out.println("diferentes");
		}
	}

}

class Conta {
	int numero; // numero da conta
	String titular; // dados do titular
	String agencia; // dados da agencia
	double saldo; // saldo da conta
	Data dataDeAbertura = new Data();


void saca(double quantidade) {
    double novoSaldo = this.saldo - quantidade; 
    this.saldo = novoSaldo; 
    }


void deposita(double quantidade) { 
    this.saldo += quantidade; 
    }

double calculaRendimento() {
	double rendimento; // cria variavel de rendimentos
	rendimento = this.saldo*0.1; // Multiplica o saldo por 0.1
	return rendimento; // retorna o valor do rendimento
	}

String recuperaDadosParaImpressao() {
    String dados = "Titular: " + this.titular;
    dados += "\nNúmero: " + this.numero;
    dados += "\nAgência: " + this.agencia;
    dados += "\nSaldo: " + this.saldo;
    dados += "\nData de abertura: " + this.dataDeAbertura.formatar();
    return dados;
}

}

class Data {
    int dia;
    int mes;
    int ano;
    String formatada;

String formatar() {
	String formatada;
	formatada = "Dia " + String.valueOf(this.dia) + " do " + String.valueOf(this.mes) + " de " + String.valueOf(this.ano);
	return formatada;
}

    }

class Fibonacci {

	public int calculaFibonacci(int i) {
		if (i == 0) {
		  return 0;
		} else if (i == 1) {
		  return 1;
		} else {
		  return calculaFibonacci(i - 1) + calculaFibonacci(i - 2);
		}
	}
	public int calculaFibonacci2(int i) {
		return i <= 2 ? 1 : calculaFibonacci(i-1)+calculaFibonacci(i-2);
	}

}
