
public class Exercicio {
	
	public static void main (String args[]) {
		
		System.out.println("parte 1 : imprimir de 150 a 300");

		
		for (int i = 150; i<=300; i++) {
			System.out.print(i+" ");
		}
			
		System.out.println("\n");
		
		System.out.println("parte 2 : imprimir soma de 1 a 1000");
		
		int soma = 0;
		
		for (int i = 0; i <=1000; i++) {
			soma += i;
		}	
		
		System.out.println(soma + "\n");
		
		System.out.println("parte 3 : imprimir multiplos de 3 de 1 a 100");
		
		for (int i = 1; i <=100; i++) {
			if (i%3==0) {
				System.out.print(i+ " ");
			}
			
		}	
		
		System.out.println("\n");
		
		System.out.println("parte 4 : imprimir fatoriais de 1 a 10");
		
		for (int i = 1; i <=10; i++) {
			
			System.out.print(fatorial(i)+" ");
			
		}
		
		System.out.println("\n");
		
		System.out.println("parte 5 : imprimir fatoriais de 1 a 30, testar nova funcao com long");

		for (int i = 1; i <=30; i++) {
			
			System.out.print(fatorial(i)+" ");
			
		}
		
		System.out.println("\n");
		System.out.println("com long: ");
		
		
		for (int i = 1; i <=30; i++) {
			
			System.out.print(longFatorial(i)+" ");
			
		}
		
		
		System.out.println("\n");
		System.out.println("parte 6: imprimir sequencia de fibonacci até 100");
		
		for(int i = 0; i < 100; i++)
		{
			System.out.print(fib(i)+" ");
			
			if (fib(i)>100) {
				break;
			}
				
		}
		
		System.out.println("\n");
		System.out.println("parte 7: programa que modifica x até ele ser 1");
		
		int x = 40; // valor de teste da apostila
		
		while(true) {
				
			System.out.print(x+ " ");		

			if (x==1) {
				break;
			}
				
			
			if (x%2==0) {
				x = (int)x/2;
			}
			
			else {
				x = 3*x+1;
			}
		}
		
		System.out.println("\n");
		System.out.println("parte 8: imprimir tabela usando 2 fors");
		
		
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= i; j++ ) {
				
				System.out.print(j*i + " ");
			}
			
			System.out.println();
			
		}
		
		System.out.println("\n");
		System.out.println("Desafio: fibonacci com duas variaveis");
		
		int fib = 2; // uma variavel
		int fibant = 1; // duas variaveis
		
		System.out.print("0 1 1 ");
		
		while (true)
		{
			System.out.print(fib + " ");
			
			if (fib>100) {
				break;
			}

				fib = fib + fibant;
				fibant = fib - fibant; 
		}
		
		
	}

	static int fatorial(int i)
	{
		if (i == 0) {
			return 1;
		}
		else {
			return i*fatorial(i-1);
		}
		
	}
	
	static long longFatorial(int i)
	{
		if (i == 0) {
			return 1;
		}
		else {
			return i*longFatorial(i-1);
		}
		
	}
	
	static int fib(int i)
	{
		if (i==0 || i==1)
			return 1;
		else
			return (fib(i-1)+fib(i-2));
	}
}
