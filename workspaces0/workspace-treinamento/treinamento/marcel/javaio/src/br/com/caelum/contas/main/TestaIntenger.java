package br.com.caelum.contas.main;

public class TestaIntenger {

	public static void main(String[] args) {
		 Integer x1 = new Integer(10);
		 Integer x2 = new Integer(10);

	     System.out.println(x1);
	     System.out.println(x2);

		 if (x1 == x2) {
		     System.out.println("igual");
		 } else {
		     System.out.println("diferente");
		 }
		 if (x1.equals(x2)) {
		     System.out.println("igual");
		 } else {
		     System.out.println("diferente");
		 }

	}

}
