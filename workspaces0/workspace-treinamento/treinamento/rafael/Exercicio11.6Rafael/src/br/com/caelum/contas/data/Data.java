package br.com.caelum.contas.data;

public class Data {
	
	String data;
	
	public void setData (int dia, int mes, int ano) {
		if (dia > 28 && mes == 2) {
			data = "Data invalida";
		}else if (dia >30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
			data = "Data invalida";
		}else if (dia>31) {
			data = "Data invalida";
		}else {
			data = dia+"/"+mes+"/"+ano;
		}
	}
	
	public String getData() {				
		return data;
	}
}
