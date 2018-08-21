
public class Gerente extends Funcionario {
        private int senha;
        private int numeroDeFuncionariosGerenciados;

        public boolean autentica(int senha) {
            if (this.senha == senha) {
                System.out.println("Acesso Permitido!");
                return true;
            } else {
                System.out.println("Acesso Negado!");
                return false;
            }
        }

		public void setNome(String string) {
			this.n
			
		}

		public void setSenha(int i) {
			
		}

        // setter da senha omitido
    }
