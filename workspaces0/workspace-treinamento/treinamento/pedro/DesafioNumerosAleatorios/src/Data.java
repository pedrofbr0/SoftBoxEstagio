
public class Data {
	
	//Atributos
	private int dia;
	private int mes;
	private int ano;
	
	//Métodos
	public String getDataSt() {
		String dataFormatada = this.dia + "/" + this.mes + "/" + this.ano;
		return dataFormatada;
		
	}
	
	public String setData(int dia, int mes, int ano) {
		if ((ano % 400 == 0) || ((ano % 4 == 0) 
				&& (ano % 100 !=0))) {
			if(mes == 2) {
				if(dia > 1 && dia <= 29) {
					this.dia = dia;
					this.mes = mes;
					this.ano = ano;
					return "OK";
				} else {
					return "Data Inválida";
				} 

			} else {
				return "Data Inválida";
			}
		} else {
			if(mes == 2) {
				if(dia > 1 && dia <= 28) {
					this.dia = dia;
					this.mes = mes;
					this.ano = ano;
					return "OK";
				} else {
					return "Data Inválida";
				}
			}
			if(mes == 1 || mes == 3 
					|| mes == 5 || mes == 7 
					|| mes == 8 || mes == 10 
					|| mes == 12) {
				if(dia > 1 && dia <= 31) {
					this.dia = dia;
					this.mes = mes;
					this.ano = ano;
					return "OK";
				} else {
					return "Data Inválida";
				}
			}
			if(mes == 4 || mes == 6 
					|| mes == 9 || mes == 11) {
				if(dia > 1 && dia <= 30) {
					this.dia = dia;
					this.mes = mes;
					this.ano = ano;
					return "OK";
				} else {
					return "Data Inválida";
				}
			}
			return "Data Inválida";
		}
	}
	
	public String setDataSt(String data) {
		
		String d[] = data.split("/");				
		return this.setData(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]));
	}
	
}
