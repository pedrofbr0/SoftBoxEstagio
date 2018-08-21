
class Exercicio5 {
	public static void main(String[] args) {
		
		for (int n = 1; n <=40; n++) {
			int i = 1;
			long fatorial = 1;
			while(i<n){
				fatorial = fatorial*i;
				i++;
			}
			System.out.println(fatorial);
		}
	}
}
