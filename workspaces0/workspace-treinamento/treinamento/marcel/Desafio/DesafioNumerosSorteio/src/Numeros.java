import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Numeros {
	
	private List<Integer> numeros;
	
	public Numeros () {
		 this.numeros = new ArrayList<Integer>();
	}

	public Integer geradorDeNumeros() {
		int flagIgualdade = 0;
		int randomNum = ThreadLocalRandom.current().nextInt(10000000, 100000000);
		for (Integer n : numeros) {
			if (n.equals(randomNum)) {
				flagIgualdade = 1;
			}
		}
		if (flagIgualdade == 0) {
			numeros.add(randomNum);
			return randomNum;
		}
		else {
			geradorDeNumeros();
		}
		return null;
	}

	public List<Integer> getNumeros() {
		return numeros;
	}

	public void setNumeros(List<Integer> numeros) {
		this.numeros = numeros;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeros == null) ? 0 : numeros.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Numeros other = (Numeros) obj;
		if (numeros == null) {
			if (other.numeros != null)
				return false;
		} else if (!numeros.equals(other.numeros))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String retorno = "";
		for (Integer n : numeros) {
			retorno = retorno + Integer.toString(n) + "\n";
		}
		return retorno;
	}

}