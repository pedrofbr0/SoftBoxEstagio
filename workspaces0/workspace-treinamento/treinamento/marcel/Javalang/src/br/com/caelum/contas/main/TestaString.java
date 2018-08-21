package br.com.caelum.contas.main;

public class TestaString {

	public static void main(String[] args) {
		String s = "fj11";
		String s1 = "String de testes";
		String s2 = "teste";
		String s3 = "";
		s.replaceAll("1", "2");
		System.out.println(s);
		System.out.println(s1.contains(s2));
		System.out.println(s1.replaceAll("\\s+", ""));
		System.out.println(s3.isEmpty());
		System.out.println(s1.length());
		repetidor(s1);
		reversador(s1);
		reversador("Socorram-me, subi no ônibus em Marrocos");
		reversador("anotaram a data da maratona");
		StringBuilder str = new StringBuilder("UFU.");
		System.out.println(str.reverse());
		String x = "762";
		int i;
		i=conversor(x);
		System.out.println(i);

	}

	public static void repetidor(String s) {
		int n = s.length();
		for (int i = 0; i < n; i++) {
			System.out.println(s.charAt(i));
		}
		System.out.println();
	}

	public static void reversador(String s) {
		int n = s.length();
		for (int i = n - 1; i >= 0; i--) {
			System.out.print(s.charAt(i));
		}
		System.out.println();
	}

	public static int conversor(String s) {
		int n = s.length();
		int[] j = new int[n];
		char[] c = new char[n];
		int retorno;
		
		for (int i = 0; i < n; i++) {
			c[i] = s.charAt(i);
		}


		for (int i = 0; i < n; i++) {
			j[i] = Character.getNumericValue(c[i]);
		}

		StringBuilder strNum = new StringBuilder();
		for (int num : j) {
			strNum.append(num);
		}

		retorno = Integer.parseInt(strNum.toString());
		return retorno;
		}
}