import java.util.List;

import javax.swing.table.AbstractTableModel;

class contatosModelo extends AbstractTableModel {
         
           /**
	 * 
	 */
	private static final long serialVersionUID = -3208421904540049102L;
		private final List<Contatos> contatos;
         
           public contatosModelo(List<Contatos> contatos) {
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
             Contatos c = contatos.get(rowIndex);
             
             switch (columnIndex) {
             case 0:
               return c.getNome();
             case 1:
               return c.getTelefone();
             case 2:
               return c.getNascimento();
             }
             return null;
           }
    }