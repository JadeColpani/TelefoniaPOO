import java.util.GregorianCalendar;

public class PosPago extends Assinante{
	
	private float assinatura;

	public PosPago(long cpf, String nome, int numero) {
		super(cpf, nome, numero);
	}
	
	public void fazerChamada(GregorianCalendar date, int duracao) {
		
		float valorMinuto = 1.04f; 
		float valorChamada = valorMinuto*duracao; 
		
		if (chamadas.length>numChamadas) {
			
			Chamada chamada = new Chamada(date, duracao);
			chamadas[numChamadas] = chamada;
			numChamadas++;
			this.assinatura += valorChamada;
		}
		else {	
			
			System.out.println("Não foi possível realizar a chamada!");
			
		}
	}
	
	public void imprimirFatura(int mes) {
		
		float totalFatura=0;
		
		System.out.println("CPF: " + getCpf());
		System.out.println("Nome: " + getNome());
		System.out.println("Numero: " + getNumero());
		
		Chamada[] c = chamadas;
		for(int i=0;i<chamadas.length;i++) {
			System.out.println("Data: "+c[i].getData().getTime());
			System.out.println("Duracao: "+c[i].getDuracao());
			float custo_ligacao = c[i].getDuracao() * 1.04f; 
			System.out.println("Custo: R$ "+custo_ligacao);
			totalFatura = totalFatura + custo_ligacao;
		}

		System.out.println("Valor da Assinatura: R$ "+this.assinatura);
		System.out.println("Total da Fatura: R$"+(totalFatura+this.assinatura));
		
	}

}
