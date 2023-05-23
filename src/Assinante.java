public class Assinante {
	
    private long cpf;
    private String nome;
    private int numero;
    protected Chamada[] chamadas;
    protected int numChamadas;


    public Assinante(long cpf, String nome, int numero) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.chamadas = new Chamada[10];
    }

    public long getCpf() {
        return cpf;
    }
    
    public String getNome() {
		return nome;
	}

	public int getNumero() {
		return numero;
	}


	@Override
    public String toString() {
        return "CPF: " + this.cpf + ", Nome: " + this.nome + ", Numero: " + this.numero;
    }
	
 
}
