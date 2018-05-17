import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


public class ManipuladorDeTelas {
	
	private ManipuladorDeNumeros manipuladorNumeros;
	
	public void montarTelaPrincipal() {
		
		JFrame telaPrincipal = new JFrame();
		JPanel painelPrincipal = new JPanel();
		
		JTextField nome= new JTextField("Pedro");
		
		JButton ok = new JButton("OK");
		
		MaskFormatter dataFormato = null;
		try {
			dataFormato = new MaskFormatter("##/##/####");
			dataFormato.setValidCharacters("0123456789");

		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(telaPrincipal,"Data Inválida");
			e1.printStackTrace();
		}
		
		JFormattedTextField data = new JFormattedTextField(dataFormato);
		data.setText("15/03/2018");
		
		JLabel nomeLabel = new JLabel();
		JLabel dataLabel = new JLabel();
				
		ok.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
				manipuladorNumeros = new ManipuladorDeNumeros(nome.getText(), data.getText());
				System.out.println(manipuladorNumeros.getUser().getDataS());
				
				if(manipuladorNumeros.getUser().getDataS().equals("0/0/0")) {
					JOptionPane.showMessageDialog(telaPrincipal,"Data Inválida" +"\n");
					return;
				}
				montarTelaSecundaria();
			}
		});
		
		painelPrincipal.add(nomeLabel);
		painelPrincipal.add(nome);
		painelPrincipal.add(dataLabel);
		painelPrincipal.add(data);
		painelPrincipal.add(ok);
		
		telaPrincipal.add(painelPrincipal);
		
		telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		telaPrincipal.pack();
		telaPrincipal.setVisible(true);
	}
	
	public void montarTelaSecundaria() {
		int flag = 0;
		
		JFrame telaSecundaria = new JFrame("Números da Sorte");
		JPanel painelSecundario = new JPanel();
		
		JButton gerar = new JButton("Gerar");
		JButton voltar = new JButton("Voltar");
		JButton salvar = new JButton("Salvar");
		JButton salvarComo = new JButton("Salvar como...");
		JButton carregar = new JButton("Carregar arquivo");
		
		JTextArea numeros = new JTextArea();
		
		final JScrollPane scroll = new JScrollPane(numeros);
	    scroll.getViewport().add(numeros);
	    scroll.setSize(250, 200);	
	    
	    gerar.setBounds(260, 0, 100, 100);
	    voltar.setBounds(300, 0, 100, 100);
	    salvar.setBounds(340, 0, 100, 100);
	    salvarComo.setBounds(380, 0, 100, 100);
	    carregar.setBounds(420, 0, 100, 100);
	    
	    
	    painelSecundario.add(scroll);
	    painelSecundario.add(gerar);
	    painelSecundario.add(salvar);
	    painelSecundario.add(salvarComo);
	    painelSecundario.add(carregar);
	    telaSecundaria.add(scroll);
	    telaSecundaria.add(painelSecundario);
	    
	    telaSecundaria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    telaSecundaria.setSize(1050, 450);

	    telaSecundaria.setVisible(true);
	    
	    gerar.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
					if(manipuladorNumeros==null) {
						JOptionPane.showMessageDialog(telaSecundaria,"Sem usuário");
					}
					
					if(manipuladorNumeros.getCounter() > 20) {
						JOptionPane.showMessageDialog(telaSecundaria,"Limite de geração excedido (máx. 20)");
						return;
					}
					
					if(manipuladorNumeros.getFlag() >= 1) {
						numeros.setText("");
						manipuladorNumeros.setFlagZero();
					}
					
					if(numeros.getText().equals("")) {
						numeros.append(manipuladorNumeros.toString());
					}
						numeros.append(manipuladorNumeros.gerarNumero() + "\n");
			}
		});
	    
	    salvar.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {
	    		manipuladorNumeros.salvar();
	    		manipuladorNumeros.setFlag();
	    		return;
	    	}
		});
	    
	    salvarComo.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {
	    		
	    		JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					  File arquivo = fileChooser.getSelectedFile();
					  manipuladorNumeros.setArquivoDeEscrita(arquivo);
					  manipuladorNumeros.salvar();
					  manipuladorNumeros.setFlag();
					  return; 
				}
	    	}
	    });
	    
	    carregar.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {
	    		
	    		JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					
					File arquivo = fileChooser.getSelectedFile();
					manipuladorNumeros.setArquivoDeLeitura(arquivo);
						
					numeros.setText(manipuladorNumeros.carregar());
					  
					return;
				}
			}
		});
	    
	    voltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				telaSecundaria.setVisible(false);
			}
		});
	}
} 

