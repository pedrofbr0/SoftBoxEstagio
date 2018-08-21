public class Fibonacci {

	int calculaFibonacci(int i) {
		return i <= 2 ? 1 : calculaFibonacci(i-1)+calculaFibonacci(i-2);
	}
	
}
