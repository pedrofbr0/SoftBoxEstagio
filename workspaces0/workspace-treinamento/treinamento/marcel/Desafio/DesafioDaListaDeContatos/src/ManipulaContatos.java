import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class ManipulaContatos implements ItemListener {
	JPanel cards; // a panel that uses CardLayout
	File location;
	Agenda agenda = new Agenda();

	public void addComponentToPane(Container pane) {
		// Put the JComboBox in a JPanel to get a nicer look.
		JPanel comboBoxPane = new JPanel(new GridLayout(10, 1)); // use FlowLayout
		agenda.carregaArquivos();

		// Create the "cards".
		JButton Button1 = new JButton("Inserir Contato");
		JButton Button2 = new JButton("Remover Contato");
		JButton Button3 = new JButton("Pesquisar Contato por Nome");
		JButton Button4 = new JButton("Listar Contatos");
		JButton Button5 = new JButton("Listar Contatos por Inicial");
		JButton Button6 = new JButton("Listar Aniveriariantes do Mês");
		JButton Button7 = new JButton("Alterar local de salvamento");
		JButton Button8 = new JButton("Salvar e sair");
		JButton Button9 = new JButton("Alterar Contato");
		Button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button5.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button6.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button7.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button8.setAlignmentX(Component.CENTER_ALIGNMENT);
		Button9.setAlignmentX(Component.CENTER_ALIGNMENT);

		Button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Contatos c = new Contatos();
				JTextField xField = new JTextField(20);
				JTextField yField = new JTextField(20);
				JTextField zField = new JTextField(20);

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Nome do Contato"));
				myPanel.add(xField);
				myPanel.add(Box.createVerticalStrut(15)); // a spacer
				myPanel.add(new JLabel("Telefone do Contato"));
				myPanel.add(yField);
				myPanel.add(Box.createVerticalStrut(15)); // a spacer
				myPanel.add(new JLabel("Aniversario(dd/mm)"));
				myPanel.add(zField);
				int result = JOptionPane.showConfirmDialog(null, myPanel, "Entre os dados do contato",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					System.out.println("nome " + xField.getText());
					System.out.println("tel: " + Integer.parseInt(yField.getText()));
					System.out.println("niver: " + zField.getText());
					c.setNome(xField.getText());
					c.setTelefone(Integer.parseInt(yField.getText()));
					c.setNascimento(zField.getText());
					agenda.adicionaContato(c);
				}
			}
		});
		Button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Contatos c = new Contatos();
				JTextField xField = new JTextField(20);
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Nome do Contato a ser REMOVIDO:"));
				myPanel.add(xField);
				int result = JOptionPane.showConfirmDialog(null, myPanel, "REMOÇÃO PERMANENTE DE CONTATOS",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					c.setNome(xField.getText());
					agenda.removeContato(c.getNome());
				}

			}

		});
		Button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Contatos c = new Contatos();
				JTextField xField = new JTextField(20);
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Pesquisar contato por nome: "));
				myPanel.add(xField);
				int result = JOptionPane.showConfirmDialog(null, myPanel, "Nome do contato a ser pesquisado: ",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					c.setNome(xField.getText());
					agenda.mostraPorNome(c.getNome());
				}

			}
		});
		Button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame janela = new JFrame();
				janela.setSize(540, 540);
				janela.setLocationRelativeTo(null);
				JPanel myPanel = new JPanel();
                JPanel tabelaPainel = new JPanel(new BorderLayout());
                JTable tabela = new JTable();
                tabela.setBorder(new LineBorder(Color.black));
                tabela.setGridColor(Color.black);
                tabela.setShowGrid(true);
                tabela.setModel(new contatosModelo(agenda.retornaLista()));
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().setBorder(null);
                scroll.getViewport().add(tabela);
                scroll.setSize(450, 450);
                tabelaPainel.removeAll();
                tabelaPainel.add(tabela);
                myPanel.add(tabela);
                janela.add(myPanel);
                janela.setVisible(true);
				agenda.listaContatos();
			}
		});
		Button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Contatos c = new Contatos();
				JTextField xField = new JTextField(20);
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Pesquisar por inicial"));
				myPanel.add(xField);
				int result = JOptionPane.showConfirmDialog(null, myPanel, "Listar contatos que começam com a letra: ",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					agenda.listaContatosPorInicial(xField.getText().charAt(0));
				}

			}

		});
		Button6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				agenda.aniversariantes();
			}

		});
		Button7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				agenda.alteraLocal();

			}
		});
		Button8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				agenda.gravaArquivos();
				System.exit(0);

			}

		});
		Button9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Contatos c1 = new Contatos();
				Contatos c2 = new Contatos();
				JTextField xField = new JTextField(20);
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Nome do Contato a ser alterado:"));
				myPanel.add(xField);
				int result = JOptionPane.showConfirmDialog(null, myPanel, "Alterar dados de contatos",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					c1 = agenda.retornaContato(xField.getText());
					if (c1 == null) {
						System.out.println("Nome não encontrado!");
					} else {
						JTextField x1Field = new JTextField(20);
						JTextField y1Field = new JTextField(20);
						JTextField z1Field = new JTextField(20);
						JPanel myPanel1 = new JPanel();
						myPanel1.add(new JLabel("Nome do Contato"));
						myPanel1.add(x1Field);
						myPanel1.add(Box.createVerticalStrut(15)); // a spacer
						myPanel1.add(new JLabel("Telefone do Contato"));
						myPanel1.add(y1Field);
						myPanel1.add(Box.createVerticalStrut(15)); // a spacer
						myPanel1.add(new JLabel("Aniversario(dd/mm)"));
						myPanel1.add(z1Field);
						int result1 = JOptionPane.showConfirmDialog(null, myPanel1, "Entre os dados do contato",
								JOptionPane.OK_CANCEL_OPTION);
						if (result1 == JOptionPane.OK_OPTION) {
							System.out.println("nome " + x1Field.getText());
							System.out.println("tel: " + Integer.parseInt(y1Field.getText()));
							System.out.println("niver: " + z1Field.getText());
							c2.setNome(xField.getText());
							c2.setTelefone(Integer.parseInt(y1Field.getText()));
							c2.setNascimento(z1Field.getText());
							agenda.alteraContato(c1, c2);
						}
					}
				}

			}

		});

		Box card1 = Box.createVerticalBox();
		card1.add(Button1);
		card1.add(Button2);
		card1.add(Button3);
		card1.add(Button4);
		card1.add(Button5);
		card1.add(Button6);
		card1.add(Button7);
		card1.add(Button8);
		card1.add(Button9);
		// Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1);

		pane.add(comboBoxPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);
	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, (String) evt.getItem());
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	public void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Agenda de Contatos");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		// Create and set up the content pane.
		ManipulaContatos demo = new ManipulaContatos();
		demo.addComponentToPane(frame.getContentPane());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}


