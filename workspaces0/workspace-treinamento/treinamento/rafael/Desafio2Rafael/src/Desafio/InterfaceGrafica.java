package Desafio;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

public class InterfaceGrafica {
	
	private JFrame janelaPrincipal;
	JTextArea areaArquivo;
	private ListaDeNumeros lista;
	
	public InterfaceGrafica () {
		montarJanelaPrincipal();
	}
	
	public void montarJanelaPrincipal(){
		
		janelaPrincipal = new JFrame ();
		JPanel painelPrincipal = new JPanel ();
		
		painelPrincipal.setLayout(new BoxLayout(painelPrincipal,BoxLayout.Y_AXIS));
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		
		JFrame janelaArquivo = new JFrame();
		janelaArquivo.setLayout(new BorderLayout());
		
		areaArquivo = new JTextArea(20,20);
		areaArquivo.setEditable(false);
		JScrollPane scroll = new JScrollPane(areaArquivo);
		scroll.setPreferredSize(new Dimension(50,50));
		
		//botoes
		
		JButton selecionarUsuario = new JButton(new AbstractAction("Selecionar Usuario") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	try {
					mostrarJanelaSelecionaUsuario();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		JButton mostrarUsuario = new JButton(new AbstractAction("Mostrar Usuario") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	mostrarJanelaMostraUsuario();
		    }
		});
		
		JButton gerarNumero = new JButton(new AbstractAction("Gerar Numero") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	mostrarJanelaGerarNumero();
		    }
		});
		
		JButton selecionarArquivo = new JButton(new AbstractAction("Selecionar Arquivo") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if(lista==null) {
					JOptionPane.showMessageDialog(janelaPrincipal,"Nenhum Usuario Foi selecionado");
				}else {
					JFileChooser fileChooser = new JFileChooser();
					if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						  File file = fileChooser.getSelectedFile();
						  lista.setArquivo(file);
						  return;
						} 
				}
				
			}
		});
		
		JButton gravarDados = new JButton(new AbstractAction("Gravar Dados") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	mostrarJanelaGravarDados();
		    }
		});
		
		JButton exibirArquivo = new JButton(new AbstractAction("Exibir Arquivo") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	mostrarJanelaExibirArquivo();
		    }
		});
			
		selecionarUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		mostrarUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		gerarNumero.setAlignmentX(Component.CENTER_ALIGNMENT);
		selecionarArquivo.setAlignmentX(Component.CENTER_ALIGNMENT);
		gravarDados.setAlignmentX(Component.CENTER_ALIGNMENT);
		exibirArquivo.setAlignmentX(Component.CENTER_ALIGNMENT);
		scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
		scroll.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		painelPrincipal.add(selecionarUsuario);
		painelPrincipal.add(mostrarUsuario);
		painelPrincipal.add(gerarNumero);
		painelPrincipal.add(selecionarArquivo);
		painelPrincipal.add(gravarDados);
		painelPrincipal.add(exibirArquivo);
		painelPrincipal.add(scroll);
		
		janelaPrincipal.getRootPane().setDefaultButton(selecionarUsuario);
		
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaPrincipal.add(painelPrincipal);
		janelaPrincipal.pack();
		janelaPrincipal.setSize(540, 540);
		janelaPrincipal.setLocationRelativeTo(null);
		janelaPrincipal.setVisible(true);
		
	}
	
	public void mostrarJanelaSelecionaUsuario() throws ParseException {
		
		MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		mascaraData.setValidCharacters("0123456789");
		
		
		JFrame janelaUsuario = new JFrame();
		JPanel painelUsuario = new JPanel();
		JLabel nomeLabel = new JLabel("Nome");
		JTextField nomeField = new JTextField(10);
		JLabel dataLabel = new JLabel("Data");
		JFormattedTextField dataField = new JFormattedTextField (mascaraData);
		
		painelUsuario.add(nomeLabel);
		painelUsuario.add(nomeField);
		painelUsuario.add(dataLabel);
		painelUsuario.add(dataField);
		
		JButton adicionar = new JButton(new AbstractAction("Selecionar") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	Usuario usuario = new Usuario(nomeField.getText(),dataField.getText());
		    	if(usuario.isValid()) {
		    		if(lista==null) {
		    			lista = new ListaDeNumeros(usuario);
		    		}
		    			areaArquivo.setText("");
		    			areaArquivo.append("Nome: " + usuario.getNome() + "         Data: " + usuario.getData() + "\n\n");
				    	janelaPrincipal.setVisible(true);
				    	janelaUsuario.setVisible(false);
		    	}else {
					JOptionPane.showMessageDialog(janelaUsuario,"Data Inválida");
		    	}
		    }
		});
		
		painelUsuario.add(adicionar);
		
		painelUsuario.add(new JButton(new AbstractAction("Voltar") {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
		    	janelaUsuario.setVisible(false);
		    	janelaPrincipal.setVisible(true);
		    }
		}));	
		
		janelaUsuario.add(painelUsuario);
		janelaUsuario.pack();
		
		janelaUsuario.getRootPane().setDefaultButton(adicionar);
		
		janelaUsuario.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		janelaUsuario.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        janelaUsuario.setVisible(false);
		        janelaPrincipal.setVisible(true);
		    }
		});

		
		janelaUsuario.setLocationRelativeTo(null);
		janelaUsuario.setVisible(true);
		janelaPrincipal.setVisible(false);
		
	}
	
	public void mostrarJanelaMostraUsuario() {
		
		JFrame janelaUsuario = new JFrame();
		JPanel painelIUsuario = new JPanel();
		
		if(getLista()==null) {
			JOptionPane.showMessageDialog(janelaUsuario,"Nenhum Usuario Foi selecionado");
			
		}else {
			JLabel ipLabel = new JLabel("Usuario: " + getLista().getUsuario().getNome());
			JLabel portaLabel = new JLabel("  Data: " + getLista().getUsuario().getData());
			
			painelIUsuario.add(ipLabel);
			painelIUsuario.add(portaLabel);
			
			JButton voltar = new JButton(new AbstractAction("Voltar") {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
			    	janelaUsuario.setVisible(false);
			    	janelaPrincipal.setVisible(true);
			    }
			});
			
			painelIUsuario.add(voltar);
			janelaUsuario.add(painelIUsuario);
			janelaUsuario.pack();
	
			janelaUsuario.getRootPane().setDefaultButton(voltar);
			janelaUsuario.setLocationRelativeTo(null);
			janelaUsuario.setVisible(true);
			janelaPrincipal.setVisible(false);
		}
	}
	
	public void mostrarJanelaGerarNumero () {
		
		if(getLista()==null) {
			JOptionPane.showMessageDialog(janelaPrincipal,"Nenhum Usuario Foi selecionado");
			
		}else if(getLista().getTamanho()>20){
			JOptionPane.showMessageDialog(janelaPrincipal,"Número máximo de números gerados (20)");
		}else {
			areaArquivo.append(getLista().geraNumero()+"\n");
		}
	}
	
	public void mostrarJanelaGravarDados() {

		if(getLista()==null) {
			JOptionPane.showMessageDialog(janelaPrincipal,"Nenhum Usuario Foi selecionado");
		}else {
			getLista().gravaNumeros();
			JOptionPane.showMessageDialog(janelaPrincipal,"Dados Gravados");
		}	
	}
	
	public void mostrarJanelaExibirArquivo() {
		if(getLista()==null) {
			JOptionPane.showMessageDialog(janelaPrincipal,"Nenhum Usuario Foi selecionado");
		}else {				
			Scanner s;
			
			JFrame janelaArquivo = new JFrame();
			janelaArquivo.setLayout(new BorderLayout());
			JPanel painelArquivo = new JPanel();
			JTextArea areaArquivo = new JTextArea();
			areaArquivo.setEditable(false);
			JScrollPane scroll = new JScrollPane(areaArquivo);
			scroll.setPreferredSize(new Dimension(400,400));
			
			try {
				s = new Scanner(getLista().getArquivo());
				
				while(s.hasNextLine()) {
					String string = s.nextLine();
					areaArquivo.append(string + "\n");
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(janelaPrincipal,"Arquivo não foi gerado");
			}

			painelArquivo.add(new JButton(new AbstractAction("Voltar") {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
			    	janelaArquivo.setVisible(false);
			    	janelaPrincipal.setVisible(true);
			    }
			}));
			
			janelaArquivo.add(scroll,BorderLayout.NORTH);
			janelaArquivo.pack();

			
			janelaArquivo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			janelaArquivo.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        janelaArquivo.setVisible(false);
			        janelaPrincipal.setVisible(true);
			    }
			});
			
			janelaArquivo.setLocationRelativeTo(null);
			janelaArquivo.setVisible(true);
			janelaPrincipal.setVisible(false);		
		}	
	}
	
	public ListaDeNumeros getLista() {
		return this.lista;
	}
}
