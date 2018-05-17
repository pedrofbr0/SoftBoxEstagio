import javax.swing.JOptionPane;

public class Exercicio6 {
	public static void main(String[] args) {
		int sum = 0;
		JOptionPane.showMessageDialog(null, "Meu primeiro diálogo");
		JOptionPane.showInputDialog(null, "Qual a sua idade?");
		for(int i = 0; i <= 100; i++){
			sum += sum;
			
			if(i==0){
				System.out.println(0);
			}
			System.out.println(sum);
			
		}
	}
}
