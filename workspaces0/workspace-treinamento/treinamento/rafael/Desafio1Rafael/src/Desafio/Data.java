package Desafio;

public class Data {
	
	String data;
	private int dia;
	private int mes;
	
	public Data() {}
	
	public Data(int dia, int mes) {
		setData(dia,mes);
	}
	
	public Data (String data) {
		String dados[] = data.split("/");
		this.dia = Integer.parseInt(dados[0]);
		this.mes = Integer.parseInt(dados[1]);
		setData(dia,mes);
	}
	
	public int getMes() {
		return mes;
	}
	
	public void setData (int dia, int mes) {
		if (dia > 28 && mes == 2) {
			data = "Data invalida";
		}else if (dia >30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
			data = "Data invalida";
		}else if (dia>31) {
			data = "Data invalida";
		}else if (mes<1 || mes>12) {
			data = "Data invalida";
		}else {
			data = dia+"/"+mes;
			this.dia = dia;
			this.mes = mes;
		}
	}
	
	public String getData() {				
		return data;
	}
}
