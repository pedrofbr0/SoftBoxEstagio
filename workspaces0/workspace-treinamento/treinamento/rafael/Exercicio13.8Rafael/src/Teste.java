
public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s = "762";
		System.out.println(transforma(s));

	}
	
	public static int transforma(String s) {
		
		int num = 0;
		int mult = 1;
		
		for (int i = s.length()-1; i >=0; i--) {
			num+=(s.charAt(i)-'0')*mult;
			mult*=10;
		}	
		return num;
	}
}
