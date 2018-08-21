package Testes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TesteArquivo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File arquivo = new File("contas.txt");
		Scanner s;
		try {
			s = new Scanner(arquivo);
			String st = s.nextLine();
			String[] split = st.split(",");
			System.out.println(split[2]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("nao achou o arquivo");
			e.printStackTrace();
		}
		
		

	}

}
