package br.com.caelum.contas.main;

import java.util.*;

public class TestaPerformance {

    public static void main(String[] args) {
        System.out.println("Iniciando...");
        Collection<Integer> teste = new ArrayList<>();
        long inicio = System.currentTimeMillis();

        int total = 30000;

        for (int i = 0; i < total; i++) {
            teste.add(i);
        }

        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        System.out.println("Tempo gasto: " + tempo);
        long inicio2 = System.currentTimeMillis();

        for (int i = 0; i < total; i++) {
            teste.contains(i);
        }

        long fim2 = System.currentTimeMillis();
        long tempo2 = fim2 - inicio2;
        System.out.println("Tempo gasto: " + tempo2);
    }
}