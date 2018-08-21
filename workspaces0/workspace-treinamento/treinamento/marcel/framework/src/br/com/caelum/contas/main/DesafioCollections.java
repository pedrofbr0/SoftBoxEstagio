package br.com.caelum.contas.main;

import java.util.*;

public class DesafioCollections {

	public static void main(String[] args) {
		TreeSet<Object> arvere = new TreeSet<Object>();
		ArrayList<Object> raia = new ArrayList<Object>();
		int[] num = new int[1000];
		for (int i = 0; i <= 999; i++) {
			num[i] = i + 1;
			arvere.add(num[i]);
			raia.add(num[i]);
		}
		Iterator<Object> itr = arvere.descendingIterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		Collections.reverse(raia);
		Iterator<Object> itr2 = raia.iterator();
		while (itr2.hasNext()) {
			System.out.println(itr2.next());
		}

	}

}
