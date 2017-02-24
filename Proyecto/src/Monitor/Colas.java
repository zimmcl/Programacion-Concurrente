package Monitor;

import java.util.concurrent.Semaphore;

public class Colas {

	private Semaphore[] semaforosTransiciones;
	
	public Colas(int transiciones) {
		semaforosTransiciones = new Semaphore[transiciones];
		for (int i=0; i<transiciones; i++) {
			semaforosTransiciones[i] = new Semaphore(1,true);
		}
	}
	
	public int[] quienesEstan() {
		int[] enCola = new int[semaforosTransiciones.length];
		for(int i=0; i<semaforosTransiciones.length; i++) {
			if(semaforosTransiciones[i].hasQueuedThreads()) {
				enCola[i] = 1;
			}
			else {
				enCola[i] = 0;
			}
		}
		return enCola;
	}
	
	public void acquire(int[] semaforo) {
		for(int i=0; i<semaforosTransiciones.length; i++) {
			if(semaforo[i] == 1) {
				try {
					semaforosTransiciones[i].acquire();
				}
				catch (InterruptedException e) {}
			}
		}
	}
	
	public void release(int[] semaforo) {
		for(int i=0; i<semaforosTransiciones.length; i++) {
			if(semaforo[i] == 1) {
				semaforosTransiciones[i].release();
			}
		}
	}
}