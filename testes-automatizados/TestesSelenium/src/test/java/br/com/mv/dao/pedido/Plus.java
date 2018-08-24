package br.com.mv.dao.pedido;

public class Plus {

	private float percentualPlusVendedor;
	private float plusProdSemPromo;
	private float plusJurosSemPromo;
	private float plusProdComPromo;
	private float plusJurosComPromo;
	
	public Plus() {}
	
	public float getPercentualPlusVendedor() {
		return percentualPlusVendedor;
	}

	public void setPercentualPlusVendedor(float percentualPlusVendedor) {
		this.percentualPlusVendedor = percentualPlusVendedor;
	}
	
	public float getPlusProdSemPromocao() {
		return plusProdSemPromo;
	}

	public void setPlusProdSemPromocao(float plusProdSemPromo) {
		this.plusProdSemPromo = plusProdSemPromo;
	}
	
	public float getPlusProdComPromocao() {
		return plusProdComPromo;
	}

	public void setPlusProdComPromocao(float plusProdComPromo) {
		this.plusProdComPromo = plusProdComPromo;
	}
	
	public float getPlusJurosComPromocao() {
		return plusJurosComPromo;
	}

	public void setPlusJurosComPromocao(float plusJurosComPromo) {
		this.plusJurosComPromo = plusJurosComPromo;
	}
	
	public float getPlusJurosSemPromocao() {
		return plusJurosSemPromo;
	}

	public void setPlusJurosSemPromocao(float plusJurosSemPromo) {
		this.plusJurosSemPromo = plusJurosSemPromo;
	}
}