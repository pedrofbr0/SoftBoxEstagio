import javax.swing.JOptionPane;

public class rafael {
	public static void main(String args[]) {
		int op = JOptionPane.showConfirmDialog(null, "C ta de boas?");
		if (op == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "blz entao");
		} else if (op == JOptionPane.NO_OPTION) {
			JOptionPane.showInputDialog(null, "q q foi fi?");
		} else {
			JOptionPane.showMessageDialog(null, "Cancelou mane? Pode crer entao!");
		}
	}
}