package Modelos;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ContatosTabela extends AbstractTableModel {

	private final List<Contato> contatos;

	public ContatosTabela(List<Contato> contatos) {
		this.contatos = contatos;
		}
		
		public int getColumnCount() {
		return 3;
		}
		
		public int getRowCount() {
		return contatos.size();
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
		Contato contato = contatos.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
		return contato.getNome();
		case 1:
		return contato.getNumeroCel();
		case 2:
		return contato.getDataS();
		}
		return null;
	}
}