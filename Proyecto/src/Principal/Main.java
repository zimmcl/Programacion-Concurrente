package Principal;

import Monitor.Monitor;
import Monitor.RedDePetri;

public class Main {

	public static void main(String[] args) {
		int[][] incidencia = {{-1,1,0,0},
							  {1,-1,0,0},
							  {0,0,-1,1},
							  {0,0,1,-1},
							  {-1,1,-1,1},
							 };
		int[] marcado = {2,0,1,0,1};
		int plazas = 5;
		int transiciones = 4;	
		int[] prioridades = {10,1,5,1};

		RedDePetri red = new RedDePetri(incidencia,marcado,plazas,transiciones);
		Monitor monitor = new Monitor(red, prioridades);

		int[][] disparos1 = {{1,0,0,0},
							 {0,1,0,0}
						    };
		int[][] disparos2 = {{0,0,1,0},
							 {0,0,0,1}
						    };
		
		/*Reemplazar por Piezas
		Hilo hilo1 = new Hilo(monitor,disparos1,2);
		Hilo hilo2 = new Hilo(monitor,disparos2,2);
		*/
		Thread t1= new Thread(hilo1);
		Thread t2= new Thread(hilo2);
		t1.start();
		t2.start();
		
	}

}