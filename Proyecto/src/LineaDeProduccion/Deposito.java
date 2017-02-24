package LineaDeProduccion;

public class Deposito {

	String tipo;
	int cantidad;
	
	public Deposito(String tipo){
		//Deposito puede ser de tipo 'ENTRADA' o 'SALIDA'
		
		this.tipo = tipo;
		
		if(tipo.equals("ENTRADA")){
			cantidad=999;
		}
		if(tipo.equals("SALIDA")){
			cantidad=0;
		}
		
	}
	
	public void incrementarPieza(){
		
	}
	
	public void decrementarPieza(){
		
	}
	
	public String getTipo(){
		return tipo;
	}
	
	public int getCantidad(){
		return cantidad;
	}
}
