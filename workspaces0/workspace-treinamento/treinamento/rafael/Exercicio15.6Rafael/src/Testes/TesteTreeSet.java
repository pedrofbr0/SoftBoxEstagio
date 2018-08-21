package Testes;
import java.util.*;

public class TesteTreeSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Comparator<Integer> comp = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);
			}
		};
		
		Set<Integer> tree = new TreeSet<>(comp);
		List <Integer> lista = new ArrayList<>();
		
		for (int i = 1; i<=1000; i++) {
			tree.add(i);
			lista.add(i);
		}
		
		Collections.sort(lista,comp);
		
		System.out.println(tree);
		System.out.println(lista);
	}
}

