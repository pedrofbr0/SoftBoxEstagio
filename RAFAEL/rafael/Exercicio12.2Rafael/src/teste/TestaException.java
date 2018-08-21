package teste;

import java.io.FileNotFoundException;

public class TestaException {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new java.io.FileInputStream("teste.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
