public class Data {
	int dia;
	int mes;
	int ano;
	String formatada;

	public String formatar() {
		String formatada;
		if(this.mes==2&&this.dia>29||this.dia>31) {
		  formatada = "Data inválida!";
		  return formatada;
		}
		formatada = "Dia " + String.valueOf(this.dia) + " do " + String.valueOf(this.mes) + " de "
				+ String.valueOf(this.ano);
		return formatada;
	}

}
