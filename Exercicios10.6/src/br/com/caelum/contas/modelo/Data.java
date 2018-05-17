package br.com.caelum.contas.modelo;
/*
 * Exercicio4
 * 
 * 1.0
 *
 * 05/03/2018 10:22
 * 
 * Pedro
 * 
 * Copyright notice
 * 
 */

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
	
	boolean ValidaData() {
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
	}
}

