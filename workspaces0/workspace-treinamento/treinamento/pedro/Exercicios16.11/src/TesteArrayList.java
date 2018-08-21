import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TesteArrayList {
	
	public static void main(String[] args) {
		
		Comparator <Integer> comp = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);
			}
		};
	
		List <Integer> numerosLista = new ArrayList<>();
		
		for (int i = 1; i<=1000; i++) {
			numerosLista.add(i);
		}
		
		Collections.sort(numerosLista, comp);
		
		System.out.println(numerosLista);
	}
}

