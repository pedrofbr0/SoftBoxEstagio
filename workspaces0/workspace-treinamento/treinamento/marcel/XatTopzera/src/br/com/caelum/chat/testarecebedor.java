package br.com.caelum.chat;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class testarecebedor {
	

    private String host;
    private int porta;
    Socket cliente;
    
	public static void main(String[] args) {
		 
        Recebedor r = new Recebedor(cliente.getInputStream());
        new Thread(r).start();
 
        Scanner teclado = new Scanner(System.in);
        System.out.println(mensagem);
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println(mensagem);
 

	}
}
