import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.MaskFormatter;

public class GUI {

	private int contadorDoGeradorDeNumeros = 0;
	private File arquivo = new File("Loteria.txt");
	private Usuario user;
	private Numeros num;
	private JTextArea textArea = new JTextArea(20, 20);

	public void createAndShowGUI() throws ParseException {

		MaskFormatter mascaraData;
		mascaraData = new MaskFormatter("##/##/####");
		mascaraData.setValidCharacters("0123456789");

		JTextField textoComONome = new JTextField(20);
		JFormattedTextField textoComAData = new JFormattedTextField(mascaraData);
		textoComONome.setText("Nome");
		textoComAData.setText("01/01/1970");
		JPanel telaDeEntrada = new JPanel();
		telaDeEntrada.add(new JLabel("Insira o nome: "));
		telaDeEntrada.add(textoComONome);
		telaDeEntrada.add(Box.createVerticalStrut(15)); // a spacer
		telaDeEntrada.add(new JLabel("Insira a data: "));
		telaDeEntrada.add(textoComAData);
		user = new Usuario();
		num = new Numeros();
		Integer result = JOptionPane.showConfirmDialog(null, telaDeEntrada, "Entre os dados do contato",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}
		if (result == JOptionPane.CLOSED_OPTION) {
			System.exit(0);
		}
		if (result == JOptionPane.OK_OPTION) {
			String[] divisorDeVariaveis;
			Data datas = new Data();
			user.setNome(textoComONome.getText());
			divisorDeVariaveis = textoComAData.getText().split("/");
			try {
				datas.setDia(Integer.parseInt(divisorDeVariaveis[0]));
				datas.setMes(Integer.parseInt(divisorDeVariaveis[1]));
				datas.setAno(Integer.parseInt(divisorDeVariaveis[2]));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Entradas devem ser números inteiros!");
				System.exit(0);
			}
			user.setData(datas);
		}

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		JButton button1 = new JButton("Gerar Número");
		JButton button2 = new JButton("Salvar");
		JButton button3 = new JButton("Carregar");
		JButton button4 = new JButton("Alterar Local de Salvamento");
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contadorDoGeradorDeNumeros >= 20) {
					JOptionPane optionPane = new JOptionPane("Não é possivel gerar mais de 20 numeros!",
							JOptionPane.ERROR_MESSAGE);
					JDialog dialog = optionPane.createDialog("Erro");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					return;
				}
				textArea.append(num.geradorDeNumeros().toString() + "\n");
				contadorDoGeradorDeNumeros++;
			}

		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PrintStream stream;
				try {
					stream = new PrintStream(arquivo);
					stream.print("Nome: " + user.getNome() + "     " + user.getData() + "\n\n\n");
					stream.print(num.toString());
					stream.close();
				} catch (FileNotFoundException e) {
					try {
						arquivo.createNewFile();
						stream = new PrintStream(arquivo);
						stream.print("Nome: " + user.getNome() + "     " + user.getData() + "\n\n\n");
						stream.print(num.toString());
						stream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}

		});

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Scanner s;
				File arquivoLeitura = new File("Loteria.txt");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Escolha o local");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);
				int resultado = chooser.showSaveDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					arquivoLeitura = chooser.getSelectedFile();
				}else if (resultado == JFileChooser.CANCEL_OPTION) {
					return;
				}
				try {
					s = new Scanner(arquivoLeitura);
					String[] leituraUsuarioEData = s.nextLine().split(" ");
					// leituraUsuarioEData[1] 1 armazena o nome do usuario 8 armazena a data
					String[] dados = new String[22];
					int counterParaConverterScannerpraString = 0;
					while (s.hasNextLine()) {
						dados[counterParaConverterScannerpraString] = s.nextLine();
						counterParaConverterScannerpraString++;
					}

					int counterNumeroDeLinhasComNumeros = 0;
					for (String d : dados) {
						if (d != null) {
							++counterNumeroDeLinhasComNumeros;
						}
					}

					JFrame janelaArquivo = new JFrame();
					janelaArquivo.setLayout(new BorderLayout());
					JTextArea areaArquivo = new JTextArea();
					areaArquivo.setEditable(false);
					JScrollPane scroll = new JScrollPane(areaArquivo);
					scroll.setPreferredSize(new Dimension(200, 400));
					areaArquivo.append(leituraUsuarioEData[1] + "\n" + leituraUsuarioEData[8] + "\n");
					for (int i = 2; i < counterNumeroDeLinhasComNumeros; i++) {
						areaArquivo.append(dados[i] + "\n"); // Vai de 2 ate n+1, sendo n o numero de dados salvo
					}

					janelaArquivo.add(scroll, BorderLayout.NORTH);
					janelaArquivo.pack();
					janelaArquivo.setVisible(true);

				} catch (FileNotFoundException e) {
					JOptionPane optionPane = new JOptionPane("Não foi possivel ler o arquivo",
							JOptionPane.ERROR_MESSAGE);
					JDialog dialog = optionPane.createDialog("Erro");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					try {
						arquivo.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				} catch (ArrayIndexOutOfBoundsException e2) {
					JOptionPane optionPane = new JOptionPane("Arquivo possui mais de 20 numeros!",
							JOptionPane.ERROR_MESSAGE);
					JDialog dialog = optionPane.createDialog("Erro!");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
			}
		}

		);

		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Escolha o local");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					arquivo = chooser.getSelectedFile();
					if (arquivo.isDirectory()) {
						String path = arquivo.getAbsolutePath();
						path = path + "/numeros.txt";
						arquivo = new File(path);
						try {
							arquivo.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}

		});

		JFrame frame = new JFrame("Agenda de Contatos");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textArea.setText(num.toString());
		textArea.setEditable(false);
		frame.getContentPane().add(textArea);
		frame.pack();

		JPanel panel = new JPanel();
		JScrollPane scroller = new JScrollPane(textArea);
		panel.add(scroller);
		inputpanel.add(button1);
		inputpanel.add(button2);
		inputpanel.add(button3);
		inputpanel.add(button4);
		panel.add(inputpanel);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
}