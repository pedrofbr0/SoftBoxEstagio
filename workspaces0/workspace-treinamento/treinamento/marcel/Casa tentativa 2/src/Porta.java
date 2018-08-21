class Porta {
	boolean aberta;
	String cor;
	double dimensionx, dimensiony, dimensionz;

	public void abre() {
		this.aberta = true;
	}

	public void fecha() {
		this.aberta = false;
	}

	public void estaAberta() {
		if (this.aberta) {
			System.out.println("A porta está aberta");
		} else {
			System.out.println("A porta está fechada");
		}
	}
}
