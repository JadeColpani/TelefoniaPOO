import java.util.GregorianCalendar;

public class PrePago extends Assinante{

	 private Recarga[] recargas;
	 private int numRecargas;
	 private float creditos;

	public PrePago(long cpf, String nome, int numero) {
		super(cpf, nome, numero);
		this.recargas = new Recarga[100];
	}
	
	@Override
	public void fazerChamada(GregorianCalendar date, int duracao){
		
		float valorMinuto = 1.45f; 
		float valorChamada = valorMinuto*duracao; 
		
		if (chamadas.length>numChamadas && creditos>valorChamada) {
			
			Chamada chamada = new Chamada(date, duracao);
			chamadas[numChamadas] = chamada;
			numChamadas++;
			creditos = creditos-valorChamada;
		}
		else {
			
			System.out.println("Não foi possível realizar a chamada!");
			
		}
	}
	
	public void recarregar(GregorianCalendar data, float valor) {
		
		if(recargas.length<=100) {
			
			Recarga recarga = new Recarga(data, valor);
					recargas[numRecargas] = recarga;
					numRecargas++;
					creditos=creditos+valor;
		}
		else {
			System.out.println("Não foi possível realizar a recarga!");
		}	
	}
	
	@Override
	public void imprimirFatura(int mes) {
		
		float totalFatura = 0;
		
		System.out.println("CPF: " + getCpf());
		System.out.println("Nome: " + getNome());
		System.out.println("Número: " + getNumero());
		
		for(int i=0;i<this.numChamadas;i++) {
			if (chamadas[i].getData().get(GregorianCalendar.MONTH) == (mes-1)) {
				System.out.println("Data: "+chamadas[i].getData().getTime());
				System.out.println("Duracao: "+chamadas[i].getDuracao());
				float custoLigacao = chamadas[i].getDuracao() * 1.45f; 
				System.out.printf("Custo: R$ %.2f\n", custoLigacao);
				totalFatura = totalFatura+custoLigacao;
			}
		}

		System.out.printf("Total da Fatura: R$ %.2f\n", totalFatura);
		System.out.println("Total de Recargas: "+this.numRecargas);
		System.out.println("Creditos Disponiveis: "+this.creditos);
	}		
}
