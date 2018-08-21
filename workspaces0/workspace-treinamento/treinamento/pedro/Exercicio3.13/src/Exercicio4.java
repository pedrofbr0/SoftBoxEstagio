
class Exercicio4 {
	public static void main(String[] args) {
		
		for (int n = 1; n <=10; n++) {
			int i = 1;
			int fatorial = 1;
			while(i<n){
				fatorial = fatorial*i;
				i++;
			}
			System.out.println(fatorial);
		}
	}
}
