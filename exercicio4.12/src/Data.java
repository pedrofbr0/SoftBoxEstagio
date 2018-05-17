
public class Data {
	int dia;
	int mes;
	int ano;
	
	String formatada() {
		String dataFormatada = this.dia + "/";
		dataFormatada = this.mes + "/";
		dataFormatada = this.ano + "/";
		return dataFormatada;
	}
}
