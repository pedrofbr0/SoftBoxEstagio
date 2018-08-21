class Casa {
	String cor;
	Porta porta1 = new Porta();
	Porta porta2 =  new Porta();
	Porta porta3 = new Porta();

	public void pinta(String s) {
		this.cor = s;
	}
	public int quantasPortasEstaoAbertas() {
		int counter = 0;
		if(porta1.aberta) {
			counter++;
		}
		if(porta2.aberta) {
			counter++;
		}
		if(porta3.aberta) {
			counter++;
		}
		return counter;
	}
}