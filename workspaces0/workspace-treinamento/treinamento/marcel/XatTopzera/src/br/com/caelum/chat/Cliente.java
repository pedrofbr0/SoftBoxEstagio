package br.com.caelum.chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
 
public class Cliente {
 
 
    private String host;
    private int porta;
    Socket cliente;
 
    public Cliente (String host, int porta) {
        this.host = host;
        this.porta = porta;
    }
   
    public void conecta() throws IOException{
        cliente = new Socket(this.host, this.porta);
        System.out.println("O cliente se conectou ao servidor!");
    }
   
 
    public void executa(String mensagem) throws UnknownHostException, IOException {
 
        Recebedor r = new Recebedor(cliente.getInputStream());
        new Thread(r).start();
 
        Scanner teclado = new Scanner(System.in);
        System.out.println(mensagem);
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println(mensagem);
 
        saida.close();
        //cliente.close();        
    }
   
    public void fecha() throws IOException {
        cliente.close();
    }
}