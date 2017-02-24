package LineaDeProduccion;

import Monitor.Monitor;

public class Pieza {
	
	private Monitor monitor;
	private int[][] disparos;
	private int cantidadDisparos;
	
	public Pieza(Monitor monitor, int[][] disparos, int cantidadDisparos) {
		this.monitor = monitor;
		this.disparos = disparos;
		this.cantidadDisparos = cantidadDisparos;
	}
	
	public void run() {
		for(int i=0; i<cantidadDisparos; i++) {
			int[] disparo = new int[monitor.red().transiciones()];
			for(int k=0; k<monitor.red().transiciones(); k++) {
				disparo[k] = disparos[i][k];
			}
			monitor.disparar(disparo); 
		}
	}
	
}
