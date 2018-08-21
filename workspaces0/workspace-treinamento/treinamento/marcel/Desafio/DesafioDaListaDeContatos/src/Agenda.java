
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Agenda {
	List<Contatos> listaDeContatos = new ArrayList<>();
	Map<String, Contatos> mapaDeContatosPorNome = new HashMap<>();
	private File arquivo = new File("lerolero.txt");

	public void gravaArquivos() {
		PrintStream stream;
		try {
			stream = new PrintStream(arquivo);
			for (Contatos c : listaDeContatos) {
				stream.println(c);
			}
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				arquivo.createNewFile();
				stream = new PrintStream(arquivo);
				for (Contatos c : listaDeContatos) {
					stream.println(c);
				}
				stream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void carregaArquivos() {
		Scanner s;
		String[] dados;
		Contatos c;

		try {
			s = new Scanner(arquivo);

			while (s.hasNextLine()) {
				dados = s.nextLine().split(",");
				c = new Contatos();
				dados[0] = dados[0].substring(dados[0].indexOf(" ") + 1);
				dados[1] = dados[1].substring(dados[1].indexOf(" ") + 1);
				dados[2] = dados[2].substring(dados[2].indexOf(" ") + 1);
				dados[2] = dados[2].substring(0, dados[2].indexOf("]"));
				c.setNome(dados[0]);
				c.setTelefone(Integer.parseInt(dados[1]));
				c.setNascimento(dados[2]);
				listaDeContatos.add(c);
				mapaDeContatosPorNome.put(dados[0], c);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Não foi capaz de ler o arquivo");
			try {
				arquivo.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void adicionaContato(Contatos c) {
		listaDeContatos.add(c);
		mapaDeContatosPorNome.put(c.getNome(), c);
	}

	public void removeContato(String nome) {
		for (Contatos c : listaDeContatos) {
			if (c.getNome() != null && c.getNome().contains(nome)) {
				listaDeContatos.remove(c);
			}
		}
	}

	public Contatos mostraPorNome(String nome) {
		for (Contatos c : listaDeContatos) {
			if (c.getNome() != null && c.getNome().contains(nome)) {
				System.out.println(c);
				return c;
			}
		}
		return null;
	}

	public void listaContatos() {
		System.out.println(listaDeContatos);
	}
	
	public List<Contatos> retornaLista() {
		return listaDeContatos;
	}

	public void listaContatosPorInicial(char letra) {
		for (Contatos c : listaDeContatos) {
			if (c.getNome() != null && c.getNome().charAt(0) == letra) {
				System.out.println(c);
			}
		}
	}

	public void alteraContato(Contatos c1, Contatos c2) {
		int index = -1;
		for (Contatos c : listaDeContatos) {
			if (c.getNome() != null && c.getNome().equals(c1.getNome())) {
				index = listaDeContatos.indexOf(c);
			}
			if (index != -1) {

				listaDeContatos.set(index, c2);
			}
		}
	}

	public void aniversariantes() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd G");
		formato.format(cal.getTime());
		int mes = Calendar.MONTH + 1;
		for (Contatos c : listaDeContatos) {
			int mesaniversario = Integer.parseInt(c.getNascimento().substring(3, 5));
			if (mesaniversario == mes) {
				System.out.println(c);
			}
		}

	}

	public Contatos retornaContato(String nome) {
		for (Contatos c : listaDeContatos) {
			if (c.getNome() != null && c.getNome().equals(nome)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivo == null) ? 0 : arquivo.hashCode());
		result = prime * result + ((listaDeContatos == null) ? 0 : listaDeContatos.hashCode());
		result = prime * result + ((mapaDeContatosPorNome == null) ? 0 : mapaDeContatosPorNome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (arquivo == null) {
			if (other.arquivo != null)
				return false;
		} else if (!arquivo.equals(other.arquivo))
			return false;
		if (listaDeContatos == null) {
			if (other.listaDeContatos != null)
				return false;
		} else if (!listaDeContatos.equals(other.listaDeContatos))
			return false;
		if (mapaDeContatosPorNome == null) {
			if (other.mapaDeContatosPorNome != null)
				return false;
		} else if (!mapaDeContatosPorNome.equals(other.mapaDeContatosPorNome))
			return false;
		return true;
	}

	public File alteraLocal() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Escolha o local");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			// System.out.println("getCurrentDirectory(): "
			// + chooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
			arquivo = chooser.getSelectedFile();
			return chooser.getSelectedFile();
		} else {
			return chooser.getCurrentDirectory();
		}
	}

	@Override
	public String toString() {
		return listaDeContatos + "\n";
	}

}
