package Modelos;

public class Data {
	
	//Atributos
	String data;	
	
	//Construtores
	public Data () {}
	
	public Data (String data) {
		this.data = data;
	}
	
	public Data(int dia, int mes) {
		setData(dia,mes);
	}
	
	//Métodos
	/*public String formatada() {
		String dataFormatada = this.dia + "/";
		dataFormatada = this.mes + "/";
		dataFormatada = this.ano + "/";
		return dataFormatada;
	}*/
	
	public String getData() {				
		return data;
	}
	
	public void setData (int dia, int mes) {
		if (mes == 2 && dia >= 29) {
			data = "Data inválida";
		}else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia >= 31) {
			data = "Data inválida";
		}else if (dia>31) {
			data = "Data inválida";
		}else {
			data = dia+"/"+mes;
		}
	}	
	
	/*boolean validaData() {
		if ((this.ano % 400 == 0) || ((this.ano % 4 == 0) 
				&& (this.ano % 100 !=0))) {
			if(this.mes == 2) {
				if(this.dia > 1 && this.dia <= 29) {
					return true;
				} else {
					return false;
				} 

			} else {
				return false;
			}
		} else {
			if(this.mes == 2) {
				if(this.dia > 1 && this.dia <= 28) {
					return true;
				} else {
					return false;
				}
			}
			if(this.mes == 1 || this.mes == 3 
					|| this.mes == 5 || this.mes == 7 
					|| this.mes == 8 || this.mes == 10 
					|| this.mes == 12) {
				if(this.dia > 1 && this.dia <= 31) {
					return true;
				} else {
					return false;
				}
			}
			if(this.mes == 4 || this.mes == 6 
					|| this.mes == 9 || this.mes == 11) {
				if(this.dia > 1 && this.dia <= 30) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		}
	}*/
	
}
