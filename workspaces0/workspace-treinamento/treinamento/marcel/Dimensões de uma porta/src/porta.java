
public class porta {

	public static void main(String[] args) {
		Porta portadecasa = new Porta();
		portadecasa.abre();
		portadecasa.fecha();
		portadecasa.abre();
		portadecasa.fecha();
		portadecasa.abre();
		portadecasa.fecha();
		portadecasa.cor = "Vermelha";
		portadecasa.cor = "Amarela";
		portadecasa.cor = "Verde";
		portadecasa.cor = "Marrom";
		portadecasa.cor = "Escarlate";
		portadecasa.cor = "Preta";
		portadecasa.Dimensionx = 10;
		portadecasa.Dimensiony = 50;
		portadecasa.Dimensionz = 2;
		portadecasa.estaAberta();
		System.out.println(portadecasa.cor);
		System.out.println(portadecasa.Dimensionx);

	}
}

class Porta {
	boolean aberta;
	String cor;
	double Dimensionx, Dimensiony, Dimensionz;

	void abre() {
		this.aberta = true;
	}

	void fecha() {
		this.aberta = false;
	}

	void estaAberta() {
		if (this.aberta) {
			System.out.println("A porta está aberta");
		} else {
			System.out.println("A porta está fechada");
		}
	}
}
