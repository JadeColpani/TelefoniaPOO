import java.util.GregorianCalendar;

public class PosPago extends Assinante{
	
	private float assinatura;

	public PosPago(long cpf, String nome, int numero) {
		super(cpf, nome, numero);
	}
	
	@Override
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
	
	@Override
	public void imprimirFatura(int mes) {
		
		float totalFatura=0;
		
		System.out.println("CPF: " + getCpf());
		System.out.println("Nome: " + getNome());
		System.out.println("Numero: " + getNumero());
		
		for(int i=0;i<numChamadas;i++) {
			if (chamadas[i].getData().get(GregorianCalendar.MONTH) == (mes-1)) {
			System.out.println("Data: "+chamadas[i].getData().getTime());
			System.out.println("Duracao: "+chamadas[i].getDuracao());
			float custo_ligacao = chamadas[i].getDuracao() * 1.04f; 
			System.out.printf("Custo: R$ %.2f\n",custo_ligacao);
			totalFatura = totalFatura + custo_ligacao;
			}
		}

		System.out.println("Valor da Assinatura: R$ "+this.assinatura);
		System.out.printf("Total da Fatura: R$ %.2f\n", (totalFatura+this.assinatura));
		
	}


}
