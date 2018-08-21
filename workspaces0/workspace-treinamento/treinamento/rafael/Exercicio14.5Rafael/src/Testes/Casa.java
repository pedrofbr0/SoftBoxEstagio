package Testes;

public class Casa {
	
	private String cor;
	private int totalDePortas;
	private Porta[] portas;
	
	
	Casa (String cor, int totalDePortas){
		this.cor = cor;
		this.totalDePortas = totalDePortas;
		portas = new Porta[totalDePortas];
	}
	
	public void pinta(String cor) {
		this.cor = cor;
	}
	
	public int quantasPortasEstaoAberta() {
		int total = 0;
		
		if (portas[0] == null ) {
			throw new RuntimeException("Não há portas");
		} else {
			for (Porta x : portas) {
				if (x.estaAberta()==true) {
					total++;
				}
			}	
			return total;
		}
	}
	
	public void adicionaPorta(Porta p) {
		for (int i = 0; i < portas.length; i++) {
			if (portas[i]==null) {
				portas[i] = p;
				return;
			}
		}
		
		Porta [] novoPortas = new Porta[portas.length+1];
		
		for (int i = 0; i < portas.length; i++) {
			novoPortas[i] = portas[i];
		}
		
		novoPortas[portas.length] = p;
		portas = novoPortas;
		totalDePortas = portas.length;
	}
	
	public int getTotalDePortas() {
		return this.totalDePortas;
	}
	
}

class Porta{

	private boolean aberta = false;
	
	public void abre() {
		aberta = true;
	}
	
	public void fecha() {
		aberta = false;
	}
	
	public boolean estaAberta() {
		return aberta;
	}
	
}