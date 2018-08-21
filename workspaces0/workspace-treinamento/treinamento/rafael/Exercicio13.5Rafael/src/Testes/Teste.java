package Testes;
import java.io.PrintStream;

public class Teste {
	
	//Parte 1 : verificar se Throwable reescreve to String e equals;
	
	public static void main (String args[]) {
		Throwable t1 = new Throwable("teste");
		Throwable t2 = new Throwable("teste");
		Throwable t3 = t1;
		
		System.out.println(t1.equals(t2));
		System.out.println(t1.equals(t3));
		System.out.println(t1);
		
	//Reescreve toString(), mas nao equals
	
	// Parte : Verificar referencia de System.out
	
		PrintStream saida = System.out;
		
	// System.out retorna um objeto do tipo PrintStream;
	}
}
