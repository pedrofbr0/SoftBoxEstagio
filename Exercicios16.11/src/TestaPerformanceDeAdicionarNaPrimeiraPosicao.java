import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class TestaPerformanceDeAdicionarNaPrimeiraPosicao {
	public static void main(String[] args) {
        System.out.println("Iniciando...");
        long inicio = System.currentTimeMillis();

        // trocar depois por ArrayList                
        
        List<Integer> teste = new ArrayList<>();

        for (int i = 0; i < 30000; i++) {
            teste.add(0, i);
        }
        
        long fim = System.currentTimeMillis();
        double tempo = (fim - inicio) / 1000.0;
        System.out.println("Tempo gasto: " + tempo);

        long inicio1 = System.currentTimeMillis();
        
        for (int i = 0; i < 30000; i++) {
            teste.get(i);
        }
        
        long fim1 = System.currentTimeMillis();
        double tempo1 = (fim1 - inicio1) / 1000.0;
        System.out.println("Tempo gasto: " + tempo1);
    }
}
