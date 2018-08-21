
public class Sintaxe {

	public static void main(String[] args) {
		for (int i = 150; i <= 300; i++) {
			System.out.print(i + " ");
		}
		System.out.print("\n\n");

		int j = 0;
		for (int i = 0; i <= 300; i++) {
			j = j + i;
		}
		System.out.print(j + "\n\n");

		for (int i = 1; i <= 100; i++) {
			if (i % 3 == 0) {
				System.out.print(i + " ");
			}
		}
		System.out.print(j + "\n\n");

		int fatorial = 1;
		System.out.print(fatorial + " ");
		for (int n = 1; n <= 10; n++) {
			System.out.print(fatorial * n + " ");
			fatorial = fatorial * n;
		}

		long fatorials = 1;
		System.out.print(fatorials + " ");
		for (int n = 1; n <= 40; n++) {
			System.out.print(fatorials * n + " ");
			fatorials = fatorials * n;
		}
		System.out.print(j + "\n\n");

		long A = 0, C = 0;
		System.out.print(A + " ");
		long B = A + 1;
		System.out.print(B + " ");
		while (C < 100) {
			C = A + B;
			System.out.print(C + " ");
			A = B;
			B = C;
		}
		System.out.print(j + "\n\n");

		int x = 13;
		while (x != 1) {
			if (x % 2 == 0) {
				x = x / 2;
			} else {
				x = 3 * x + 1;
			}
			System.out.print(x + " ");
		}
		System.out.print(j + "\n\n");

		for (int i = 1; i < 6; i++) {
			for (int k = 1; k <= i; k++) {
				System.out.print(i * k + " ");
			}
			System.out.print("\n");
		}

	}
}
