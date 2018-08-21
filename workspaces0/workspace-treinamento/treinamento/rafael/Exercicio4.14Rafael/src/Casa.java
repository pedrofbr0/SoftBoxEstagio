public class Casa {

	String cor;
	Porta porta1 = new Porta();
	Porta porta2 = new Porta();
	Porta porta3 = new Porta();
	
	void pinta (String s) {
		cor = s;
	}
	
	int quantasPortasEstaoAbertas() {
		int num = 0;
		
		if(porta1.aberta) {
			num++;
		}
		if (porta2.aberta) {
			num++;
		}
		if (porta3.aberta) {
			num++;
		}
		
		return num;
	}
}
