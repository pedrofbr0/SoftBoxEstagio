public class Porta {
	
	boolean aberta = false;
	String cor;
	double dimensaoX, dimensaoY, dimensaoZ;
	
	void abre() {
		this.aberta = true;
	}
	
	void fecha() {
		this.aberta = false;
	}
	
	void estaAberta () {
		if(this.aberta) {
			System.out.println("A porta esta aberta");
		}else {
			System.out.println("A porta esta fechada");
		}
	}
}
