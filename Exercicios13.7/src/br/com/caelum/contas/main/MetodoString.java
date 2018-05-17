package br.com.caelum.contas.main;

public class MetodoString {
	public void imprimeMesmo (String string) {
		for(int i = 0; i < string.length(); i++) {
			char a = string.charAt(i);
				if (a!='2') {
					System.out.println();
					System.out.print(a);
					for(int j = i; j < string.length(); j++) {
						if(a == string.charAt(j)) {
							System.out.print(a);
					}
					string  = string.replace(a,'2');
				}
			}
		}
	}
	
	public void imprimeUmaUm (String string) {
		for (int i = 0; i < string.length(); i++) {
			System.out.println(string.charAt(i));
		}
	}
	
	public void imprimeContrario (String string) {
		for (int i = string.length(); i > 0; i--) {
			System.out.print(string.charAt(i-1));
		}
	}
	
	public void imprimeContrarioBuilder (String string) {
		for (int i = string.length(); i > 0; i--) {
			System.out.print(string.charAt(i-1));
		}
	}
}