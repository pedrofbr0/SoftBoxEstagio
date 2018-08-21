package Desafio;

public class Data {
	
	private String data;
	private int dia;
	private int mes;
	private int ano;
	
	public Data() {}
	
	public Data(int dia, int mes, int ano) {
		setData(dia,mes,ano);
	}
	
	public Data (String data) {
		long count = 0;
		
		for (int i = 0; i < data.length(); i++) {
			if (data.charAt(i)=='/') {
				count++;
			}
		}

		if (count != 2 || data.length()<5){
			this.data = "invalida";
		}else {
			String dados[] = data.split("/");
			try {
				this.dia = Integer.parseInt(dados[0]);
				this.mes = Integer.parseInt(dados[1]);
				this.ano = Integer.parseInt(dados[2]);
				setData(dia,mes,ano);
			}catch (NumberFormatException|NullPointerException n) {
				this.data = "invalida";
			}
		}
	}
	
	public int getMes() {
		return mes;
	}
	
	public void setData (int dia, int mes, int ano) {
		if (dia > 28 && mes == 2) {
			data = "invalida";
		}else if (dia >30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
			data = "invalida";
		}else if (dia>31) {
			data = "invalida";
		}else if (mes<1 || mes>12) {
			data = "invalida";
		}else if (ano<1900 || ano>2100) {
			data = "invalida";
		}else {
			data = dia+"/"+mes+"/"+ano;
			this.dia = dia;
			this.mes = mes;
			this.ano = ano;
		}
	}
	
	public String getData() {				
		return data;
	}
	
	public boolean isValid() {
		if(data.equals("invalida")) {
			return false;
		}
		return true;
	}
}
