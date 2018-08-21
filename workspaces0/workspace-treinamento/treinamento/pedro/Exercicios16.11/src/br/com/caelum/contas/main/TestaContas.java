package br.com.caelum.contas.main;

import br.com.caelum.javafx.api.main.SistemaBancario;

import br.com.caelum.contas.modelo.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import br.com.caelum.contas.*;

public class TestaContas {

   public static void main(String[] args) {
       SistemaBancario.mostraTela(true);
       
        // TelaDeContas.main(args);
	   
	   	/*File arquivo = new File("contas.txt");
		Scanner s;
		try {
			s = new Scanner(arquivo);
			String st = s.nextLine();
			String[] split = st.split(",");
			System.out.println(split[3]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("nao achou o arquivo");
			e.printStackTrace();
		}*/

    }
}