import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class debugging {
	
	public static void main(String[] args) {
		File arquivo = new File("lerolero.txt");
		Scanner s;
		try {
			s = new Scanner(arquivo);
			while(s.hasNextLine()) {
				String[] dados;
	            dados = s.nextLine().split(",");
	            dados[0] = dados[0].substring(dados[0].indexOf(" ") + 1);
	            System.out.println(dados[0]);
	            dados[1] = dados[1].substring(dados[1].indexOf(" ") + 1);
	            System.out.println(dados[1]);
	            dados[2] = dados[2].substring(dados[2].indexOf(" ") + 1);
	            dados[2] = dados[2].substring(0, dados[2].indexOf("]"));
	            System.out.println(dados[2]);
	            //s.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	
}
}