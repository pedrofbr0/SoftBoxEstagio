package Testes;
import java.lang.StringBuilder;

public class TesteString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Parte 3
		
		String s = "fj11";
		s.replaceAll("1", "2");
		System.out.println(s);
		s = s.replaceAll("1", "2");
		System.out.println(s);
		
		//Parte 4 - Testes com String
		
		String s1 = "String teste";
		String s2 = "teste";
		
		System.out.println(s1.contains(s2));
		
		String s3 = "    teste    ";
		System.out.println(s3.trim());
		
		String s4 = "";
		System.out.println(s4.isEmpty());
		
		String s5 = "asd";
		System.out.println(s5.length());
		
		// Parte 5
		
		String s6 = "teste";
				
		for (int i = 0; i < s6.length(); i++) {
			System.out.println(s6.charAt(i));
		}
		
		//Parte 6
		
		String s7 = "Socorram-me, subi no ônibus em Marrocos";
		String s8 = "anotaram a data da maratona";
		System.out.println(reverso(s7));
		System.out.println(reverso(s8));
		
		//Parte 7
		
		System.out.println(reversoStringBuilder(s7));
		System.out.println(reversoStringBuilder(s8));
	}


	public static String reverso(String s) {
		
		String s2 = "";
		
		for (int i = s.length()-1; i>=0; i--) {
			s2 += s.charAt(i);
		}
		
		return s2;
	}
	
	public static String reversoStringBuilder(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.reverse();
		return sb.toString();
	}
	
}
