package br.com.caelum.chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Servidor {

    public static void main(String[] args) throws IOException {
    	
        new Servidor(4445).executa();
    }

    private int porta;
    private List<PrintStream> clientes;
    private Vector <String> log = new Vector<>();
    
    public Servidor (int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<PrintStream>();
    }

    public void executa () throws IOException {
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Porta 98765 aberta!");

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Nova conexão com o cliente " +     
                cliente.getInetAddress().getHostAddress()
            );

            PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);

            TrataCliente tc = 
            new TrataCliente(cliente.getInputStream(), this);
            new Thread(tc).start();
        }

    }

    public void distribuiMensagem(String msg) {
    	
    	log.add(msg);
    	
        for (PrintStream cliente : this.clientes) {
            cliente.println(log);
        }
        
        System.out.flush();
        System.out.println(log);
    }
    
    
    
}