import java.util.GregorianCalendar;

public class PrePago extends Assinante{

	 private Recarga[] recargas;
	 private int numRecargas;
	 private float creditos;

	public PrePago(long cpf, String nome, int numero, Recarga[] recargas, int numRecargas, float creditos) {
		super(cpf, nome, numero);
		this.recargas = new Recarga[100];
		this.numRecargas = numRecargas;
		this.creditos = creditos;
	}
	
	public void fazerChamada(GregorianCalendar data, int duracao){
		
		float valorMinuto = 1.45f; 
		float valorChamada = valorMinuto*duracao; 
		
		if (chamadas.length>numChamadas || creditos>valorChamada) {
			
			Chamada chamada = new Chamada(date, duracao);
			chamadas[numChamadas] = chamada;
			numChamadas++;
			creditos = creditos-valorChamada;
		}
		else {
			
			System.out.println("Não foi possível realizar a chamada!");
			
		}
	}
}
