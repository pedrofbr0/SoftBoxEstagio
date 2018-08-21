package Desafio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

public class InterfaceGrafica {
	
	// parou em listar por letra
	//Janelas e paineis principais
	
	private JPanel painelPrincipal = new JPanel();
	private JFrame janelaPrincipal = new JFrame("Agenda");
	private JPanel painelAdicionar = new JPanel(new BorderLayout());
	private JFrame janelaAdicionar = new JFrame("Adicionar Contato");
	private JPanel painelListar = new JPanel(new BorderLayout());
	private JFrame janelaListar = new JFrame();
	private JPanel painelPesquisar = new JPanel(new BorderLayout());
	private JFrame janelaPesquisar = new JFrame("Pesquisar");
	private JPanel painelRemover = new JPanel(new BorderLayout());
	private JFrame janelaRemover = new JFrame("Remover");
	private JPanel painelAlterar = new JPanel(new BorderLayout());
	private JFrame janelaAlterar = new JFrame("Alterar");
	private JFrame janelaListarPorLetra = new JFrame("Listar por Ketra");
	private JFrame janelaListarAniversariantes = new JFrame("Aniversariantes do Mes");
	
	private JFileChooser fileChooser = new JFileChooser();
	
	//botoes da janela principal
	
	private JButton botaoAdicionar = new JButton("Adicionar Contato");
	private JButton botaoListar = new JButton("Listar Contatos");
	private JButton botaoPesquisar = new JButton("Pesquisar Contato");
	private JButton botaoRemover = new JButton("Remover Contato");
	private JButton botaoArquivoLeitura = new JButton("Escolher arquivo de Leitura");
	private JButton botaoLeitura = new JButton("Carregar Contatos");
	private JButton botaoArquivoEscrita = new JButton("Escolher arquivo de Escrita");
	private JButton botaoEscrita = new JButton("Guardar Contatos");
	private JButton botaoSair = new JButton("Sair");
	private JButton botaoAlterar = new JButton("Alterar Contato");
	private JButton botaoListarPorLetra = new JButton("Listar por Letra");
	private JButton botaoAniversariantes = new JButton("Aniversariantes do Mes");
	
	public GerenciaContato agenda = new GerenciaContato();
	
	//referencias de Contato para fazer operacoes nas funcoes
	private Contato cRemover;				
	private Contato cPesquisar;
	private Contato cAlterar;
	
	public InterfaceGrafica() {
		montarJanelaPrincipal();
		montarJanelaAdicionar();
		agenda.carregaContatos();
	}
	
	public void montarJanelaPrincipal() {
		painelPrincipal.setLayout(new BoxLayout(painelPrincipal,BoxLayout.Y_AXIS));
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
		
		//alinhando botoes;
		botaoAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoListar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoListarPorLetra.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoAniversariantes.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoPesquisar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoRemover.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoArquivoLeitura.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoLeitura.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoArquivoEscrita.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoEscrita.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoAlterar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botaoSair.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		painelPrincipal.add(botaoAdicionar);
		painelPrincipal.add(botaoListar);
		painelPrincipal.add(botaoListarPorLetra);
		painelPrincipal.add(botaoAniversariantes);
		painelPrincipal.add(botaoPesquisar);
		painelPrincipal.add(botaoRemover);
		painelPrincipal.add(botaoArquivoLeitura);
		painelPrincipal.add(botaoLeitura);
		painelPrincipal.add(botaoArquivoEscrita);
		painelPrincipal.add(botaoEscrita);
		painelPrincipal.add(botaoAlterar);
		painelPrincipal.add(botaoSair);

		
		
		montaBotoes();
		
		
		janelaPrincipal.add(painelPrincipal);
		janelaPrincipal.setLocationRelativeTo(null);
		janelaPrincipal.setSize(540, 540);
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void montarJanelaAdicionar() {
		
		JPanel painelNome = new JPanel();
		JPanel painelTelefone = new JPanel();
		JPanel painelData = new JPanel();
		JPanel painelNorte = new JPanel(new BorderLayout());
		
//		painelNome.setBorder(BorderFactory.createEmptyBorder(50,200,10,200));
//		painelTelefone.setBorder(BorderFactory.createEmptyBorder(50,180,50,180));
//		painelData.setBorder(BorderFactory.createEmptyBorder(0,100,10,100));
		
		painelAdicionar.setBorder(BorderFactory.createEmptyBorder(0,0,300,0));
		
		JLabel nomeLabel = new JLabel("nome");
		JLabel telefoneLabel = new JLabel("telefone");
		JLabel dataLabel = new JLabel("Data de Nascimento");
		
		JTextField nomeField = new JTextField(05);
		JTextField telefoneField = new JTextField(07);
		JTextField diaField = new JTextField(02);
		JTextField mesField = new JTextField(02);
		
		JButton add = new JButton("Adicionar");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String n = nomeField.getText();
				String t = telefoneField.getText();
				String d = diaField.getText();
				String m = mesField.getText();
				
				boolean b = agenda.addContato(n, t, Integer.parseInt(d), Integer.parseInt(m));
				
				if(!b) {
					JOptionPane.showMessageDialog(janelaAdicionar,"Contato já existe ou mes inválido");
				}
				
				janelaAdicionar.setVisible(false);
				janelaPrincipal.setVisible(true);
			}
		});
		
		painelNome.add(nomeLabel, BorderLayout.WEST);
		painelNome.add(nomeField, BorderLayout.EAST);
		painelTelefone.add(telefoneLabel, BorderLayout.WEST);
		painelTelefone.add(telefoneField, BorderLayout.EAST);
		painelData.add(dataLabel);
		painelData.add(diaField);
		painelData.add(mesField);
		painelNorte.add(painelNome, BorderLayout.NORTH);
		painelNorte.add(painelTelefone,BorderLayout.CENTER);
		painelNorte.add(painelData,BorderLayout.SOUTH);
		painelAdicionar.add(painelNorte,BorderLayout.NORTH);
		painelAdicionar.add(add,BorderLayout.SOUTH);
		
//		janelaAdicionar.add(painelNome);
//		janelaAdicionar.add(painelTelefone);
		janelaAdicionar.add(painelAdicionar);
		janelaAdicionar.setSize(540, 540);
		janelaAdicionar.setLocationRelativeTo(null);
		janelaAdicionar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void mostrarJanelaPrincipal() {
		janelaPrincipal.setVisible(true);
	}
	
	public void mostrarJanelaAdicionar() {
		janelaPrincipal.setVisible(false);
		janelaAdicionar.setVisible(true);
	}
	
	public void mostrarJanelaListar() {
		JTable tabela = new JTable();
		tabela.setBorder(new LineBorder(Color.black));
		tabela.setGridColor(Color.black);
		tabela.setShowGrid(true);
		tabela.setModel(new contatosModelo(agenda.getLista()));
		JScrollPane scroll = new JScrollPane(); 
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tabela); 
		scroll.setSize(450, 450);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				janelaListar.setVisible(false);
				janelaPrincipal.setVisible(true);
			}
		});
		
		painelListar.add(tabela,BorderLayout.NORTH);
		painelListar.add(cancelar,BorderLayout.SOUTH);
		
		janelaListar.setSize(540,540);
		janelaListar.setLocationRelativeTo(null);
		janelaListar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaListar.add(painelListar);
		
		
		janelaPrincipal.setVisible(false);
		janelaListar.setVisible(true);
		
	}
	
	public void mostrarJanelaPesquisar() {
		
		janelaPesquisar.setSize(540,540);
		
		JPanel nomePainel = new JPanel();
		JPanel botoesPainel = new JPanel();
		JPanel contatoPainel = new JPanel();
		
		JLabel nomeLabel = new JLabel("Nome");
		JTextField nomeField = new JTextField(07);
		JLabel telefoneLabel = new JLabel("Telefone: ");
		JLabel dataLabel = new JLabel("Aniversario: ");
		
		JButton pesquisar = new JButton("Pesquisar");
		pesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String nome = nomeField.getText();
				cPesquisar = agenda.pesquisaContato(nome);
				if (cPesquisar==null) {
					JOptionPane.showMessageDialog(janelaPesquisar,"Contato nao encontrado");
				}
				else {
					telefoneLabel.setText("Telefone: " + cPesquisar.getTelefone());
					dataLabel.setText("Anivesario: " + cPesquisar.getData());

					janelaPesquisar.setVisible(false);
					janelaPesquisar.setVisible(true);
					cPesquisar = null;
					nomeField.setText("");
				}
			}
		});
		
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nomeLabel.setText("");
				telefoneLabel.setText("Telefone: ");
				dataLabel.setText("Anivesario: ");
				janelaPesquisar.setVisible(false);
				janelaPrincipal.setVisible(true);
			}
		});
		
		nomePainel.add(nomeLabel);
		nomePainel.add(nomeField);
		painelPesquisar.add(nomePainel, BorderLayout.NORTH);
		botoesPainel.add(pesquisar);
		botoesPainel.add(voltar);
		botoesPainel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
		painelPesquisar.add(botoesPainel, BorderLayout.CENTER);
		contatoPainel.add(telefoneLabel);
		contatoPainel.add(dataLabel);
		contatoPainel.setBorder(BorderFactory.createEmptyBorder(0,100,200,100));
		janelaPesquisar.add(contatoPainel, BorderLayout.SOUTH);
		painelPesquisar.setBorder(BorderFactory.createEmptyBorder(0,50,100,50));
		janelaPesquisar.setLocationRelativeTo(null);
		janelaPesquisar.add(painelPesquisar);
		janelaPrincipal.setVisible(false);
		janelaPesquisar.setVisible(true);
		
		
	}
	
	public void mostrarJanelaRemover() {
		janelaRemover.setSize(540,540);
		
		JPanel nomePainel = new JPanel();
		JPanel botoesPainel = new JPanel();
		JPanel contatoPainel = new JPanel();
		
		JLabel nomeLabel = new JLabel("Nome");
		JTextField nomeField = new JTextField(07);
		JLabel telefoneLabel = new JLabel("Telefone: ");
		JLabel dataLabel = new JLabel("Aniversario: ");
		
		JButton pesquisar = new JButton("Pesquisar");
		pesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String nome = nomeField.getText();
				cRemover= agenda.pesquisaContato(nome);
				if (cRemover==null) {
					JOptionPane.showMessageDialog(janelaPesquisar,"Contato nao encontrado");
				}
				else {
					telefoneLabel.setText("Telefone: " + cRemover.getTelefone());
					dataLabel.setText("Anivesario: " + cRemover.getData());
					janelaRemover.setVisible(false);
					janelaRemover.setVisible(true);
					
				}
			}
		});
		
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nomeLabel.setText("");
				telefoneLabel.setText("Telefone: ");
				dataLabel.setText("Anivesario: ");
				janelaRemover.setVisible(false);
				janelaPrincipal.setVisible(true);
			}
		});
		
		JButton remover = new JButton("Remover");
		remover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cRemover==null) {
					JOptionPane.showMessageDialog(janelaPesquisar,"Nenhum contato selecionado");
				}
				else {
					System.out.println(agenda.removeContato(cRemover.getNome()));
					System.out.println(agenda.getLista());
					janelaRemover.setVisible(false);
					janelaRemover.setVisible(true);
					cRemover=null;
				}
			}
		});
		
		nomePainel.add(nomeLabel);
		nomePainel.add(nomeField);
		painelRemover.add(nomePainel, BorderLayout.NORTH);
		botoesPainel.add(pesquisar);
		botoesPainel.add(voltar);
		botoesPainel.add(remover);
		botoesPainel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
		painelRemover.add(botoesPainel, BorderLayout.CENTER);
		contatoPainel.add(telefoneLabel);
		contatoPainel.add(dataLabel);
		contatoPainel.setBorder(BorderFactory.createEmptyBorder(0,100,200,100));
		janelaRemover.add(contatoPainel, BorderLayout.SOUTH);
		painelRemover.setBorder(BorderFactory.createEmptyBorder(0,50,100,50));
		janelaRemover.setLocationRelativeTo(null);
		janelaRemover.add(painelRemover);
		janelaRemover.setVisible(false);
		janelaRemover.setVisible(true);
		
	}
	
	public void mostrarJanelaAlterar() {
		janelaAlterar.setSize(540,540);
		
		
		JPanel nomePainel = new JPanel();
		JPanel botoesPainel = new JPanel();
		JPanel contatoPainel = new JPanel();
		JPanel fundoPainel = new JPanel();
		JPanel novoContatoPainel = new JPanel();
		
		JLabel nomeLabel = new JLabel("Nome");
		JTextField nomeField = new JTextField(07);
		
		JLabel alteracao = new JLabel("Novos dados do Contato");
		
		JLabel novoNomeLabel = new JLabel("Nome: ");
		JTextField novoNomeField = new JTextField(07);
		JLabel novoTelefoneLabel = new JLabel("Telefone: ");
		JTextField novoTelefoneField = new JTextField(07);
		JLabel novaDataLabel = new JLabel("Aniversario: ");
		JTextField novaDataField = new JTextField(07);
		
		JButton pesquisar = new JButton("Pesquisar");
		pesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String nome = nomeField.getText();
				cAlterar = agenda.pesquisaContato(nome);
				if (cAlterar==null) {
					JOptionPane.showMessageDialog(janelaPesquisar,"Contato nao encontrado");
				}
				else {

					novoNomeField.setText(cAlterar.getNome());
					novoTelefoneField.setText(cAlterar.getTelefone());
					novaDataField.setText(cAlterar.getData());
					janelaAlterar.setVisible(false);
					janelaAlterar.setVisible(true);
					
				}
			}
		});
		
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nomeLabel.setText("");
				janelaAlterar.setVisible(false);
				janelaPrincipal.setVisible(true);
			}
		});
		
		JButton alterar = new JButton("Alterar");
		alterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cAlterar==null) {
					JOptionPane.showMessageDialog(janelaAlterar,"Nenhum contato selecionado");
				}
				else {
					Contato c = new Contato(cAlterar.getNome(), "333", 01, 01 );
					agenda.removeContato(cAlterar.getNome());
					agenda.addContato(novoNomeField.getText(),novoTelefoneField.getText(), novaDataField.getText());
					janelaAlterar.setVisible(false);
					janelaAlterar.setVisible(true);
					cAlterar=null;
				}
			}
		});
		
		// Painel de pesquisa
		
		nomePainel.add(nomeLabel);
		nomePainel.add(nomeField);
		painelAlterar.add(nomePainel, BorderLayout.NORTH);

		// Botoes
		
		botoesPainel.add(pesquisar);
		botoesPainel.add(voltar);
		botoesPainel.add(alterar);
		botoesPainel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
		painelAlterar.add(botoesPainel, BorderLayout.CENTER);
		
		//Painel do fundo com informacoes e alteracao
		
		contatoPainel.add(alteracao);
		contatoPainel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		novoContatoPainel.add(novoNomeLabel);
		novoContatoPainel.add(novoNomeField);
		novoContatoPainel.add(novoTelefoneLabel);
		novoContatoPainel.add(novoTelefoneField);
		novoContatoPainel.add(novaDataLabel);
		novoContatoPainel.add(novaDataField);
		novoContatoPainel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		fundoPainel.add(contatoPainel,BorderLayout.NORTH);
		fundoPainel.add(novoContatoPainel,BorderLayout.SOUTH);
		fundoPainel.setBorder(BorderFactory.createEmptyBorder(0,0,200,0));
		
		janelaAlterar.add(fundoPainel, BorderLayout.SOUTH);
		painelAlterar.setBorder(BorderFactory.createEmptyBorder(0,50,100,50));
		janelaAlterar.setLocationRelativeTo(null);
		janelaAlterar.add(painelAlterar);
		janelaAlterar.setVisible(false);
		janelaAlterar.setVisible(true);
	}
	
	public void mostrarJanelaListarPorLetra() {
		janelaListarPorLetra.setSize(540, 540);
		
		JPanel botoesPainel = new JPanel();
		JPanel letraPainel = new JPanel();
		JPanel fundoPainel = new JPanel(new BorderLayout());
		JPanel tabelaPainel = new JPanel(new BorderLayout());
		
		JLabel letraLabel = new JLabel("Letra Inicial: ");
		JTextField letraField = new JTextField(02);
		
		JButton listar = new JButton("Listar");
		listar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String letra = letraField.getText();
				
				if(letra.length()!=1) {
					JOptionPane.showMessageDialog(janelaListarPorLetra,"Tamanho inválido");
					letraField.setText("");
				} else if (!(letra.charAt(0)>=65&&letra.charAt(0)<=90)&&!(letra.charAt(0)>=97&&letra.charAt(0)<=122)){
					JOptionPane.showMessageDialog(janelaListarPorLetra,"Não é uma letra");
					letraField.setText("");
				}else {		
					
					List <Contato> lista = null;
					lista = agenda.getListaLetra(letra.charAt(0));
					if (lista==null) {
						JOptionPane.showMessageDialog(janelaListarPorLetra,"Nenhum contato encontrado");
					}else {
						JTable tabela = new JTable();
						tabela.setBorder(new LineBorder(Color.black));
						tabela.setGridColor(Color.black);
						tabela.setShowGrid(true);
						tabela.setModel(new contatosModelo(lista));
						JScrollPane scroll = new JScrollPane(); 
						scroll.getViewport().setBorder(null);
						scroll.getViewport().add(tabela); 
						scroll.setSize(450, 450);
						tabelaPainel.removeAll();
						tabelaPainel.add(tabela);
						janelaListarPorLetra.add(tabelaPainel,BorderLayout.NORTH);
						janelaListarPorLetra.setVisible(false);
						janelaListarPorLetra.setVisible(true);
					}
					
					letraField.setText("");
				}
			}
		});
		
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				letraField.setText("");
				janelaListarPorLetra.remove(tabelaPainel);
				janelaListarPorLetra.setVisible(false);
				janelaPrincipal.setVisible(true);
			}
		});
		
		letraPainel.add(letraLabel);
		letraPainel.add(letraField);
		botoesPainel.add(listar);
		botoesPainel.add(voltar);
		fundoPainel.add(letraPainel,BorderLayout.NORTH);
		fundoPainel.add(botoesPainel,BorderLayout.SOUTH);
		janelaListarPorLetra.setLocationRelativeTo(null);
		janelaListarPorLetra.add(fundoPainel,BorderLayout.SOUTH);
		janelaListarPorLetra.setVisible(true);
		janelaPrincipal.setVisible(false);		
		
	}
	
	public void mostrarJanelaListarAniversariantes() {
		janelaListarAniversariantes.setSize(540, 540);
		
		JPanel botoesPainel = new JPanel();
		JPanel mesPainel = new JPanel();
		JPanel fundoPainel = new JPanel(new BorderLayout());
		JPanel tabelaPainel = new JPanel(new BorderLayout());
		
		JLabel mesLabel = new JLabel("Mes: ");
		JTextField mesField = new JTextField(02);
		
		JButton listar = new JButton("Listar");
		listar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String mes = mesField.getText();
				
				if(mes.length()!=1 && mes.length()!=2) {
					JOptionPane.showMessageDialog(janelaListarPorLetra,"Tamanho inválido");
					mesField.setText("");
				} else if (Integer.parseInt(mes)<1 || Integer.parseInt(mes)>12){
					JOptionPane.showMessageDialog(janelaListarPorLetra,"Não é um mes válido");
					mesField.setText("");
				}else {		
					
					List <Contato> lista = null;
					lista = agenda.getListaAniversario(Integer.parseInt(mes));
					if (lista==null) {
						JOptionPane.showMessageDialog(janelaListarPorLetra,"Nenhum contato encontrado");
					}else {
						JTable tabela = new JTable();
						tabela.setBorder(new LineBorder(Color.black));
						tabela.setGridColor(Color.black);
						tabela.setShowGrid(true);
						tabela.setModel(new contatosModelo(lista));
						JScrollPane scroll = new JScrollPane(); 
						scroll.getViewport().setBorder(null);
						scroll.getViewport().add(tabela); 
						scroll.setSize(450, 450);
						tabelaPainel.removeAll();
						tabelaPainel.add(tabela);
						janelaListarAniversariantes.add(tabelaPainel,BorderLayout.NORTH);
						janelaListarAniversariantes.setVisible(false);
						janelaListarAniversariantes.setVisible(true);
					}
					
					mesField.setText("");
				}
			}
		});
		
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesField.setText("");
				janelaListarAniversariantes.remove(tabelaPainel);
				janelaListarAniversariantes.setVisible(false);
				janelaPrincipal.setVisible(true);
			}
		});
		
		mesPainel.add(mesLabel);
		mesPainel.add(mesField);
		botoesPainel.add(listar);
		botoesPainel.add(voltar);
		fundoPainel.add(mesPainel,BorderLayout.NORTH);
		fundoPainel.add(botoesPainel,BorderLayout.SOUTH);
		janelaListarAniversariantes.setLocationRelativeTo(null);
		janelaListarAniversariantes.add(fundoPainel,BorderLayout.SOUTH);
		janelaListarAniversariantes.setVisible(true);
		janelaPrincipal.setVisible(false);		
		
	}
	
	public void montaBotoes() {
		botaoSair.addActionListener(sairListener);
		botaoAdicionar.addActionListener(adicionarListener);
		botaoListar.addActionListener(listarListener);
		botaoArquivoLeitura.addActionListener(arquivoLeituraListener);
		botaoArquivoEscrita.addActionListener(arquivoEscritaListener);
		botaoLeitura.addActionListener(leituraListener);
		botaoEscrita.addActionListener(escritaListener);
		botaoPesquisar.addActionListener(pesquisarListener);
		botaoRemover.addActionListener(removerListener);
		botaoAlterar.addActionListener(alterarListener);
		botaoListarPorLetra.addActionListener(listarPorLetraListener);
		botaoAniversariantes.addActionListener(aniversariantesDoMesListener);
	}
	
	//Listeners dos botoes 
	ActionListener sairListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			agenda.gravaContatos();
		    System.exit(0);			
		}
	};
	ActionListener adicionarListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			mostrarJanelaAdicionar();
		}
	};
	
	ActionListener listarListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			mostrarJanelaListar();
		}
	};
	
	ActionListener arquivoLeituraListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				  File file = fileChooser.getSelectedFile();
				  agenda.setArquivoLeitura(file);
				  return;
				  // faz alguma coisa com arquivo
				} else {
				  // dialogo cancelado
			}
		}
	};
	
	ActionListener arquivoEscritaListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			int retorno = fileChooser.showOpenDialog(null);
			if (retorno == JFileChooser.APPROVE_OPTION) {
				  File file = fileChooser.getSelectedFile();
				  agenda.setArquivoEscrita(file); 
				  // faz alguma coisa com arquivo
				} else {
				  // dialogo cancelado
				}
		}
	};
	
	ActionListener leituraListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				  agenda.carregaContatos();

		}
		
	};
	
	ActionListener escritaListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				  agenda.gravaContatos();

		}
		
	};

	ActionListener pesquisarListener = new ActionListener() {
		
		public void actionPerformed (ActionEvent arg0) {
			mostrarJanelaPesquisar();
		}
	};
	
	ActionListener removerListener = new ActionListener() {
		
		public void actionPerformed (ActionEvent arg0) {
			mostrarJanelaRemover();
		}
	};
	
	ActionListener alterarListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				  mostrarJanelaAlterar();

		}
		
	};

	
	ActionListener listarPorLetraListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				  mostrarJanelaListarPorLetra();

		}
		
	};
	
	ActionListener aniversariantesDoMesListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				  mostrarJanelaListarAniversariantes();

		}
		
	};
	
	
	//Tabela para listar os contatos:
	
	class contatosModelo extends AbstractTableModel {
		 
		   private final List<Contato> contatos;
		 
		   public contatosModelo(List<Contato> contatos) {
		     this.contatos = contatos;
		   }
		 
		   @Override
		   public int getColumnCount() {
		     return 3;
		   }
		 
		   @Override
		   public int getRowCount() {
		     return contatos.size();
		   }
		 
		   @Override
		   public Object getValueAt(int rowIndex, int columnIndex) {
		     Contato c = contatos.get(rowIndex);
		     
		     switch (columnIndex) {
		     case 0:
		       return c.getNome();
		     case 1:
		       return c.getTelefone();
		     case 2:
		       return c.getData();
		     }
		     return null;
		   }
	}
	

	
}

