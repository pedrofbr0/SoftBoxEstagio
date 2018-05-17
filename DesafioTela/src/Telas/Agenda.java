package Telas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import Modelos.Contato;
import Modelos.ManipuladorDeContatos;
import Modelos.*;

public class Agenda {
	
	public Agenda() {
		montarTelaPrincipal();
//		montarTelaAdicionar();
//		montarTelaRemover();
		//montarTelaPesquisar();
		//montarTelaEditar();
		//montarTelaRemover();
		//montarTElaListarComInicial();
	}
		
		
	
	private JPanel painelPrincipal = new JPanel();
	private JFrame telaPrincipal = new JFrame("Agenda");
	private JPanel painelAdicionar = new JPanel();
	private JFrame telaAdicionar = new JFrame("Adicionar");
	private JPanel painelListarTodos = new JPanel();
	private JFrame telaListarTodos = new JFrame();
	private JPanel painelPesquisar = new JPanel();
	private JFrame telaPesquisar= new JFrame("Pesquisar");
	private JPanel painelRemover = new JPanel();
	private JFrame telaRemover= new JFrame("Remover");
	
	private JFrame telaListarTodosComInicial = new JFrame("Listar todos com inicial");
	private JPanel painelListarTodosComInicial = new JPanel();	
	
	private JFrame tNivers = new JFrame("Aniversariantes  do mês");
	private JPanel pNivers = new JPanel();
	
	private JFrame tEditar = new JFrame("Editar Contato");
	private JPanel pEditar = new JPanel();
	
	
	private JFileChooser fileChooser = new JFileChooser();

	public ManipuladorDeContatos agenda = new ManipuladorDeContatos();
	
	
	private JButton adicionar = new JButton("Adicionar");
	//adicionar.setBounds(20,50,100, 40);
	
	private JButton remover = new JButton("Remover"); 
	//remover.setBounds(20,100,100, 40);  
	
	private JButton editar = new JButton("Editar");  
	//editar.setBounds(20,150,100, 40);  
	
	private JButton pesquisar = new JButton("Pesquisar");
	//pesquisar.setBounds(20,200,120, 40);
	
	private JButton listarTodos = new JButton("Listar Todos");
	//listarTodos.setBounds(20,250,140, 40);  
	
	private JButton listarComInicial =new JButton("Listar Pela Inicial");  
	//listarComInicial.setBounds(20,300,180, 40);
	
	private JButton imprimirNiver =new JButton("Aniversariantes do Mês");  
	//imprimirNiver.setBounds(20,350,230, 40);
	
	private JButton save = new JButton("Salvar");
	//arquivoSalvar.setBounds(20,350,230, 40); 
	
	private JButton load = new JButton("Load");
	//arquivoLoad.setBounds(20,350,230, 40);
	
	private JButton exit = new JButton("Sair");
	//arquivoLoad.setBounds(20,350,230, 40);
	
	
		
	public void montarTelaPrincipal() {
		
		painelPrincipal.add(adicionar);
		painelPrincipal.add(remover);
		painelPrincipal.add(editar);
		painelPrincipal.add(pesquisar);
		painelPrincipal.add(listarTodos);
		painelPrincipal.add(listarComInicial);
		painelPrincipal.add(imprimirNiver);
		painelPrincipal.add(save);
		painelPrincipal.add(load);
		painelPrincipal.add(exit);	
		
		telaPrincipal.add(painelPrincipal);
		          
		          
		telaPrincipal.setSize(400,500);  
		telaPrincipal.setVisible(true);
		telaPrincipal.pack();
		
		
		//Listeners
		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				montarTelaAdicionar();
				mostrarTelaAdicionar();
			}
		});
		
		remover.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				//montarTelaRemover();
				mostrarTelaRemover();
			}
		});
		
		editar.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				montarTelaEditar();
				mostrarTelaEditar();
			}
		});
		
		pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				montarTelaPesquisar();
				mostrarTelaPesquisar();
			}
		});
		
		listarTodos.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				mostrarTelaListarTodos();
			}
		});
		
		listarComInicial.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				montarTelaListarTodosComInicial();
				mostrarTelaListarTodosComInicial();
			}
		});
		
		imprimirNiver.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				montarTelaImprimirNivers();
				mostrarTelaImprimirNivers();
			}
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				agenda.salva();
			}
		});
		
		load.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				agenda.load();
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
				System.out.println("alo");
				
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					  File file = fileChooser.getSelectedFile();
					  agenda.setArquivoLeitura(file);
					  System.out.println("salvei");
					  agenda.salva();
					  System.out.println("salvei2");
					  
					  return;
						
					} else {
						 
				}
			}
		});
		
		load.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
				System.out.println("alo2");
				
				int retorno = fileChooser.showOpenDialog(null);
				
				if (retorno == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					agenda.setArquivoEscrita(file); 
		
				} else {
					}
				}
		});
		
		ActionListener arquivoLeituraListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					  File file = fileChooser.getSelectedFile();
					  agenda.setArquivoLeitura(file);
					  return;
						
					} else {
						 
				}
			}
		};
			
		ActionListener arquivoEscritaListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
		
					
				int retorno = fileChooser.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					  File file = fileChooser.getSelectedFile();
						 agenda.setArquivoEscrita(file); 
		
					} else {
			
					}
			}
		};
			
	}
	
	public void montarTelaAdicionar() {
		
		JTextField nome,numeroCel,data;
			
		nome = new JTextField("Rogerinho do Ingá");
		
		numeroCel = new JTextField("+55 34 99119-9331");
		
		data = new JTextField("01/01");
		
		nome.setBounds(50,50,150,20);  
		
		numeroCel.setBounds(50,150,150,20);  
		
		data.setBounds(50,200,150,20);  
		
		JLabel nomeLabel = new JLabel("Nome");
		JLabel numeroCelLabel = new JLabel("Número");
		JLabel dataLabel = new JLabel("Data de Nascimento");
			
		JButton adicionarContato = new JButton("Adicionar");
		
		adicionarContato.setBounds(50,400,150,20);
		
		adicionarContato.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String n = nome.getText();
				String t = numeroCel.getText();
				String s = data.getText();
				String[] d = null;
				d = s.split("/");	
				agenda.inserir(n, t, Integer.parseInt(d[0]), Integer.parseInt(d[1]));
				System.out.println(agenda);
				System.out.println(agenda.getLista());
				
				telaAdicionar.setVisible(false);
				telaPrincipal.setVisible(true);
			}
		
		});
		
	
		painelAdicionar.add(nomeLabel);
		painelAdicionar.add(nome);
		painelAdicionar.add(numeroCelLabel);
		painelAdicionar.add(numeroCel);
		painelAdicionar.add(dataLabel);
		painelAdicionar.add(data);
		painelAdicionar.add(adicionarContato);
		
		telaAdicionar.add(painelAdicionar);
		
		telaAdicionar.setSize(400, 500);
		//telaAdicionar.setVisible(true);
		telaAdicionar.pack();
	}		
	
	public void montarTelaListarTodos() {
		
		//System.out.println(agenda.listaContatos);
		
		JPanel tabelaPainel = new JPanel();
        JTable tabela = new JTable();
//        tabela.setBorder(new LineBorder(Color.black));
//        tabela.setGridColor(Color.black);
//        tabela.setShowGrid(true);
        tabela.setModel(new ContatosTabela(agenda.getLista()));
        JScrollPane scroll = new JScrollPane();
//        scroll.getViewport().setBorder(null);
        scroll.getViewport().add(tabela);
        scroll.setSize(450, 450);
        tabelaPainel.removeAll();
        tabelaPainel.add(tabela);
		
		/*JFrame telaLista = new JFrame();
		
		JTable tabela = new JTable();

		JPanel painelLista = new JPanel();
		
		tabela.setModel(new ContatosTabela(agenda.getLista()));
		
		JScrollPane barra = new JScrollPane(); 
		barra.getViewport().setBorder(null);
		barra.getViewport().add(tabela); 
		barra.setSize(450, 450);*/
		
		//System.out.println(agenda.getLista());
		//System.out.println(agenda);
		
//		painelListarTodos.removeAll();
//        painelListarTodos.add(tabela);
//			
		JButton voltar = new JButton("Voltar");
		
		voltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				telaListarTodos.setVisible(false);
				telaPrincipal.setVisible(true);
			}
		});
		
		
//		painelListarTodos.add(tabela);
//		painelListarTodos.add(voltar);
		
		tabelaPainel.add(voltar);
		
		telaListarTodos.setSize(540,540);
		telaListarTodos.add(tabelaPainel);
		telaListarTodos.pack();
			
		//telaPrincipal.setVisible(false);
		//telaListarTodos.setVisible(true);

	}
	
	public void montarTelaRemover() {

		JTextField contato = new JTextField("Rogerinho do Ingá");
		JLabel contatoLabel = new JLabel("Contato");
		JButton removerContato = new JButton("Remover");
		
		contato.setBounds(50,50,150,20); 
		
		removerContato.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {	
				System.out.println(agenda.getLista());
				agenda.remover(contato.getText());
				telaAdicionar.setVisible(false);
				telaPrincipal.setVisible(true);
			}
		});
		
		painelRemover.add(contatoLabel);
		painelRemover.add(removerContato);
		painelRemover.add(contato);
		
		telaRemover.add(painelRemover);
		telaRemover.setSize(540,540);
		telaRemover.pack();
		
	}
	
	public void montarTelaListarTodosComInicial() {	
		
        painelListarTodosComInicial.removeAll();
		JTextField inicial = new JTextField("R");
        JLabel inicialLabel = new JLabel("Inicial");
		JButton pesquisarInicial = new JButton("Listar");
		
		pesquisarInicial.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				JFrame telaListaInicial = new JFrame("Lista pela Inicial");
				
				JPanel painelListaInicial = new JPanel();
				
				JTable tabela = new JTable();
				tabela.setBorder(new LineBorder(Color.black));
//			    tabela.setGridColor(Color.black);
//			    tabela.setShowGrid(true);
			    tabela.setModel(new ContatosTabela(agenda.listarTodosComInicial(inicial.getText().charAt(0))));
			    System.out.println(agenda.listarTodosComInicial(inicial.getText().charAt(0)));
			    
			    JScrollPane scroll = new JScrollPane();
//		        scroll.getViewport().setBorder(null);
		        scroll.getViewport().add(tabela);
		        scroll.setSize(450, 450);
		        
				JButton voltar1 = new JButton("Voltar");
				
				voltar1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						telaListaInicial.setVisible(false);
						telaListarTodosComInicial.setVisible(true);
						
					}
				});
				
				painelListaInicial.removeAll();
			    painelListaInicial.add(tabela);
			    telaListaInicial.add(painelListaInicial);
//			    telaListaInicial.add(voltar1);
			    telaListaInicial.pack();
				telaListarTodosComInicial.setVisible(false);
		        telaListaInicial.setVisible(true);   
			}
		});
		  	
		/*JFrame telaLista = new JFrame();
		
		JTable tabela = new JTable();

		JPanel painelLista = new JPanel();
		
		tabela.setModel(new ContatosTabela(agenda.getLista()));
		
		JScrollPane barra = new JScrollPane(); 
		barra.getViewport().setBorder(null);
		barra.getViewport().add(tabela); 
		barra.setSize(450, 450);*/
		
		//System.out.println(agenda.getLista());
		//System.out.println(agenda);
		
//		painelListarTodos.removeAll();
//        painelListarTodos.add(tabela);
//			
		JButton voltar2 = new JButton("Voltar");
		
		voltar2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				telaListarTodosComInicial.setVisible(false);
				telaPrincipal.setVisible(true);
			}
		});
		
		
//		painelListarTodos.add(tabela);
//		painelListarTodos.add(voltar);
		
		//tabelaPainel.add(voltar);
		
		painelListarTodosComInicial.add(inicialLabel);
		painelListarTodosComInicial.add(inicial);
		painelListarTodosComInicial.add(pesquisarInicial);
		painelListarTodosComInicial.add(voltar2);
		
		telaListarTodosComInicial.setSize(540,540);
		telaListarTodosComInicial.add(painelListarTodosComInicial);
		telaListarTodosComInicial.pack();
			
		//telaPrincipal.setVisible(false);
		//telaListarTodos.setVisible(true);
	}
	
	public void montarTelaPesquisar() {
		
		painelPesquisar.removeAll();
		JTextField contato = new JTextField("Rogerinho do Ingá");
		JLabel contatoLabel = new JLabel("Contato");
		JButton pesquisarContato = new JButton("Pesquisar"); 
		
		pesquisarContato.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {	
				JLabel nome = new JLabel("agenda.pesquisar(contato.getText()))");	
				System.out.println(agenda.pesquisar(contato.getText()));
				
				painelPesquisar.removeAll();
				painelPesquisar.add(nome);
				
				telaAdicionar.setVisible(false);
				telaPrincipal.setVisible(true);
			}
		});
		
		painelPesquisar.add(contatoLabel);
		painelPesquisar.add(pesquisarContato);
		painelPesquisar.add(contato);
	
		telaPesquisar.add(painelPesquisar);
		telaPesquisar.setSize(540,540);
		telaPesquisar.pack();
		
	}
	
	public void montarTelaImprimirNivers() {
		
		
		
		JTextField mesNiver = new JTextField("");
		
		pNivers.removeAll();
		
		JTextField mes = new JTextField("01");
        JLabel mesLabel = new JLabel("Mês");
		JButton pesquisarNivers = new JButton("Pesquisar");
		
		pesquisarNivers.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				pNivers.removeAll();
				
				JTable tabela = new JTable();
				tabela.setBorder(new LineBorder(Color.black));
//			    tabela.setGridColor(Color.black);
//			    tabela.setShowGrid(true);
			    tabela.setModel(new ContatosTabela(agenda.imprimirNiversMes(Integer.parseInt(mes.getText()))));
			    System.out.println(agenda.imprimirNiversMes(Integer.parseInt(mes.getText())));
			    
			    JScrollPane scroll = new JScrollPane();
//		        scroll.getViewport().setBorder(null);
		        scroll.getViewport().add(tabela);
		        scroll.setSize(450, 450);
		        
				/*JButton voltar1 = new JButton("Voltar");
				
				voltar1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						telaListaInicial.setVisible(false);
						telaListarTodosComInicial.setVisible(true);
						
					}
				});*/
				
				
			    pNivers.add(tabela);
			    tNivers.add(pNivers);
//			    telaListaInicial.add(voltar1);
			    tNivers.pack();
		        tNivers.setVisible(true);   
			}
		});
		  	
		/*JFrame telaLista = new JFrame();
		
		JTable tabela = new JTable();

		JPanel painelLista = new JPanel();
		
		tabela.setModel(new ContatosTabela(agenda.getLista()));
		
		JScrollPane barra = new JScrollPane(); 
		barra.getViewport().setBorder(null);
		barra.getViewport().add(tabela); 
		barra.setSize(450, 450);*/
		
		//System.out.println(agenda.getLista());
		//System.out.println(agenda);
		
//		painelListarTodos.removeAll();
//        painelListarTodos.add(tabela);
//			
		JButton voltar2 = new JButton("Voltar");
		
		voltar2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tNivers.setVisible(false);
				telaPrincipal.setVisible(true);
			}
		});
		
		
//		painelListarTodos.add(tabela);
//		painelListarTodos.add(voltar);
		
		//tabelaPainel.add(voltar);
		
		pNivers.add(mesLabel);
		pNivers.add(mes);
		pNivers.add(pesquisarNivers);
		
		tNivers.add(pNivers);
		tNivers.pack();
			
		//telaPrincipal.setVisible(false);
		//telaListarTodos.setVisible(true);
		
	}
	
	public void montarTelaEditar() {
		
		pEditar.removeAll();
		
		JTextField edicao = new JTextField("Rogerinho do Ingá");
		JLabel edicaoLabel = new JLabel("Contato");
		JButton pesquisarContato = new JButton("Pesquisar");

		
		pesquisarContato.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				for(Contato contato : agenda.getLista()) {
					
					//if(edicao.getText().equals(contato.getNome())) {
						
						JFrame tEditarContato = new JFrame("Edição");
						JPanel pEditarContato = new JPanel();
						
						JTextField nome,numeroCel,data;
						
						nome = new JTextField("Roger");
						
						numeroCel = new JTextField("+55 34 99690 9789");
						
						data = new JTextField("01/04");
						
//						nome.setBounds(50,50,150,20);  
//						
//						numeroCel.setBounds(50,150,150,20);  
//						
//						data.setBounds(50,200,150,20);  
						
						JLabel nomeLabel = new JLabel("Nome");
						JLabel numeroCelLabel = new JLabel("Número");
						JLabel dataLabel = new JLabel("Data de Nascimento");
							
						JButton editarContato1 = new JButton("Editar");
						
						//editarContato.setBounds(50,400,150,20);
						
						editarContato1.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								System.out.println("alo");
								String n = nome.getText();
								String t = numeroCel.getText();
								String s = data.getText();
								String[] d = null;
								d = s.split("/");	
								System.out.println(edicao.getText());
								agenda.editar(edicao.getText(),n, t, Integer.parseInt(d[0]), Integer.parseInt(d[1]));
								System.out.println(agenda);
								
								tEditar.setVisible(false);
								telaPrincipal.setVisible(true);
							}
						
						});
						
					
						pEditarContato.add(nomeLabel);
						pEditarContato.add(nome);
						pEditarContato.add(numeroCelLabel);
						pEditarContato.add(numeroCel);
						pEditarContato.add(dataLabel);
						pEditarContato.add(data);
						pEditarContato.add(editarContato1);
						
						tEditarContato.add(pEditarContato);
						
						//telaAdicionar.setSize(400, 500);
						//telaAdicionar.setVisible(true);
						tEditarContato.pack();
						tEditarContato.setVisible(true);
						
					//}
				}
				
			}
		});
		
		pEditar.add(edicaoLabel);
		pEditar.add(pesquisarContato);
		pEditar.add(edicao);
	
		tEditar.add(pEditar);
		tEditar.setSize(540,540);
		tEditar.pack();
		
	}
	
	
	public void mostrarTelaPrincipal() {
			telaPrincipal.setVisible(true);
	}
			
	public void mostrarTelaAdicionar() {
			telaPrincipal.setVisible(false);
			telaAdicionar.setVisible(true);
	}
		
	public void mostrarTelaRemover() {
			telaPrincipal.setVisible(false);
			telaRemover.setVisible(true);
	}
	
	public void mostrarTelaListarTodos() {
		montarTelaListarTodos();
		telaPrincipal.setVisible(false);
		telaListarTodos.setVisible(true);
	}
	
	public void mostrarTelaListarTodosComInicial() {
		telaListarTodosComInicial.setVisible(true);
	}
	
	public void mostrarTelaPesquisar() {
		telaPesquisar.setVisible(true);
	}
	
	public void mostrarTelaImprimirNivers() {
		tNivers.setVisible(true);
	}
	
	public void mostrarTelaEditar() {
		tEditar.setVisible(true);
	}
}



