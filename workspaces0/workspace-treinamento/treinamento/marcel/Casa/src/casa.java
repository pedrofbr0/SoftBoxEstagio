public class casa {
	String cor;
	Porta porta1 = new Porta();
	porta1 = new Porta();
	Porta porta2;
	porta2 = new Porta();
	Porta porta3;
	porta3 = new Porta();  

	public void pinta(String s) {
		this.cor = s;
	}
	public int quantasPortasEstaoAbertas() {
		int counter;
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