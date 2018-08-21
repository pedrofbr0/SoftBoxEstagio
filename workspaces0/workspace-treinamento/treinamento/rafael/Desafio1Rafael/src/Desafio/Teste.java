package Desafio;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		InterfaceGrafica ig = new InterfaceGrafica();
		ig.montarJanelaPrincipal();
		ig.mostrarJanelaPrincipal();
		
	}

}
