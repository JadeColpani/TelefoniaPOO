import java.util.GregorianCalendar;

public class Recarga {

	private GregorianCalendar data;
	private float valor;

	  public Recarga(GregorianCalendar data, float valor){

	    this.data = data;
	    this.valor = valor;
	  }
	  
	  public GregorianCalendar getData() {
	    return data;
	  }

	  public float getValor() {
	    return valor;
	  }

	  @Override
	  public String toString(){

	    return "Data: " + this.data + ", Valor: " + this.valor;
	  }

}
