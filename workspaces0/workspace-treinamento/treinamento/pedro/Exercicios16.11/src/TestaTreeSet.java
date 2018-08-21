import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.*;

public class TestaTreeSet {
	
	public static void main(String[] args) {

		Comparator<Integer> comp = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);
			}
		};
		
		Set <Integer> numerosArvore = new TreeSet<>(comp);
		
		for (int i = 0; i <= 1000; i++) {
			numerosArvore.add(i);
		}
	
		
		System.out.println(numerosArvore);
	
	}

}
