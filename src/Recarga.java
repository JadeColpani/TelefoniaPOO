
import java.text.SimpleDateFormat;
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
		  
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	       String formattedDate = dateFormat.format(data.getTime());

	    return "Data: " + formattedDate + ", Valor: " + this.valor;
	  }

}
