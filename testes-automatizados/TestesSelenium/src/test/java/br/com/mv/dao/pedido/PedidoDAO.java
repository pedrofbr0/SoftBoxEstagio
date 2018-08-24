package br.com.mv.dao.pedido;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

import br.com.mv.jdbc.Conexao;

public class PedidoDAO {

	private Connection conn;
	
	public void resetDatabase() {
		
		this.conn = new Conexao().getConexao();
		
		String s            = new String();
	    StringBuffer sb = new StringBuffer();
	
	    try
	    {
	        FileReader fr = new FileReader(new File("")); //C:/Users/weverton\mv-lojas-vendas\scripts_sql_reset_base_testes\GERA_PEDIDO_TROCA_DE_UM_PRODUTO.sql
	        // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character
	
	        BufferedReader br = new BufferedReader(fr);
	
	        while((s = br.readLine()) != null)
	        {
	            sb.append(s);
	        }
	        br.close();
	
	        // here is our splitter ! We use ";" as a delimiter for each request
	        // then we are sure to have well formed statements
	        String[] inst = sb.toString().split(";");
	
	        Statement st = conn.createStatement();
	
	        for(int i = 0; i<inst.length; i++)
	        {
	            // we ensure that there is no spaces before or after the request string
	            // in order to not execute empty statements
	            if(!inst[i].trim().equals(""))
	            {
	                //st.executeUpdate(inst[i]);
	                System.out.println(">>"+inst[i]);
	            }
	        }
	
	    }
	    catch(Exception e)
	    {
	        System.out.println("*** Error : "+e.toString());
	        System.out.println("*** ");
	        System.out.println("*** Error : ");
	        e.printStackTrace();
	        System.out.println("################################################");
	        System.out.println(sb.toString());
	    }
	}
}
