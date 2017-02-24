package Monitor;

public class RedDePetri {
	
	private int[][] matrizIncidencia;
	private int[] marcado;
	private int plazas; //filas
	private int transiciones; //columnas
	private boolean posibleDisparar = true;
	
	public RedDePetri(int[][] matrizIncidencia, int[] marcado, int plazas, int transiciones) { 
		this.matrizIncidencia = matrizIncidencia;
		this.marcado = marcado;
		this.plazas = plazas;
		this.transiciones = transiciones;	
	}
	
	public boolean disparar (int[] disparo) {
		int sum = 0;
		int[] multiplicacion = new int[plazas];
		
		for(int i=0; i<plazas; i++) {
			for(int k=0; k<transiciones; k++) {
				sum = sum + matrizIncidencia[i][k] * disparo[k];
			}
			multiplicacion[i] = sum;
			sum = 0;
		}
		
		for(int i=0; i<plazas; i++) { //verificar si es posible el disparo
			if(marcado[i] + multiplicacion[i] == -1) {
				posibleDisparar = false;
				System.out.println("El disparo no es posible.");
			}
		}
		
		if(posibleDisparar) {
			for(int i=0; i<plazas; i++) {
				marcado[i] += multiplicacion[i];
			}
			System.out.println("El disparo es posible.");
			return true;
		}
		else {
			posibleDisparar = true;
			return false;
		}
	}
	
	public int[] sensibilizadas() {
		int[] transicionesSensibilizadas = new int[transiciones];
		for(int k=0; k<transiciones; k++) {
			for(int i=0; i<plazas; i++) {
				if(matrizIncidencia[i][k] == -1) {
					if(marcado[i] == 0) {
						transicionesSensibilizadas[k] = 0;
						break;
					}
				}
				if(i == plazas-1) {
					transicionesSensibilizadas[k] = 1;
				}
			}
		}
		return transicionesSensibilizadas;
	}
	
	public int[] automaticas() {
		int[] transicionesAutomaticas = new int[transiciones];
		for(int k=0; k<transiciones; k++) {
			int contador = 0;
			for(int i=0; i<plazas; i++) {
				if(matrizIncidencia[i][k] == -1) {
					contador++;
					if(contador>1) {
						transicionesAutomaticas[k] = 0;
						break;
					}
				}
				if(i == plazas-1) {
					transicionesAutomaticas[k] = 1;
				}
			}
		}
		return transicionesAutomaticas;
	}
	
	public int transiciones() {
		return transiciones;
	}
}