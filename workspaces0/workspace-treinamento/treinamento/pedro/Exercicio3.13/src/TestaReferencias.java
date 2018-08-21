
class Conta {
	double saldo;
	
	void deposita(double deposito) {
		this.saldo=deposito;
	}
}

class TestaReferencias {
        public static void main(String[] args) {
            Conta c1 = new Conta();
            c1.deposita(100);

            Conta c2 = c1;  // linha importante!
            c2.deposita(200);

            System.out.println(c1.saldo);
             System.out.println(c2.saldo);
        }
    }

