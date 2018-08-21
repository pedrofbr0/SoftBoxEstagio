package Testes;

public class TesteCasa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Casa casa = new Casa("Vermelho", 3);
		
		casa.pinta("Preto");
		
		Porta p1 = new Porta();
		Porta p2 = new Porta();
		Porta p3  = new Porta();
		
		p1.abre();
		p2.fecha();
		p3.abre();
		
		casa.adicionaPorta(p1);
		casa.adicionaPorta(p2);
		casa.adicionaPorta(p3);
		
		System.out.println(casa.quantasPortasEstaoAberta());
		System.out.println(casa.getTotalDePortas());
		
		Porta p4 = new Porta();
		casa.adicionaPorta(p4);
		
		System.out.println(casa.getTotalDePortas());

		
		

	}

}
