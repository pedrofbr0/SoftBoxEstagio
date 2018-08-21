
public class Programa2 {
	public static void main(String[] args) {
		Porta porta = new Porta();
		
		porta.aberta = true;
		porta.cor = "Vermelha";
		
		porta.dimensaoX = 1;
		porta.dimensaoY = 2;
		porta.dimensaoZ = 0.1;
		
		porta.estaAberta();
		
	}
}
