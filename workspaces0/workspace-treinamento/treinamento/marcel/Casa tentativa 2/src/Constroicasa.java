public class Constroicasa {

	public static void main(String[] args) {
		Casa esmero = new Casa();
		esmero.pinta("Vermelho");
		esmero.porta1.abre();
		esmero.porta2.fecha();
		esmero.porta3.abre();
		esmero.porta2.fecha();
		esmero.porta3.abre();
		esmero.porta1.fecha();
		System.out.print(esmero.quantasPortasEstaoAbertas());
		
	}

}