package Monitor;
import java.util.concurrent.Semaphore;

public class Monitor {

	private Semaphore mutex;
	private RedDePetri red;
	private int[] automaticas;
	private Colas colas;
	private Politicas politicas;
	
	public Monitor(RedDePetri red, int[] prioridades) {
		mutex = new Semaphore(1,true);
		this.red = red;
		automaticas = red.automaticas();
		colas = new Colas(red.transiciones());
		politicas = new Politicas(prioridades);
	}
	
	public void disparar(int[] disparo) {
		boolean k = false;
		try {
			mutex.acquire();
			k = true;
		}
		catch (InterruptedException e) {}
		
		while(k) {
			k = red.disparar(disparo);
			
			if(k) {
				int[] sensibilizadas = red.sensibilizadas();
				int[] enColas = colas.quienesEstan();
				
				int[] or = new int[red.transiciones()];
				for(int i=0; i<red.transiciones(); i++) {
					if(automaticas[i] == 1 || enColas[i] == 1) {
						or[i] = 1;
					}
					else {
						or[i] = 0;
					}
				}
				
				boolean hayDisparos = false;
				int[] and = new int[red.transiciones()];
				for(int i=0; i<red.transiciones(); i++) {
					if(or[i] == 1 && sensibilizadas[i] == 1) {
						and[i] = 1;
						hayDisparos = true;
					}
					else {
						and[i] = 0;
					}
				}
				
				if(hayDisparos) {
					int[] proximoDisparo = politicas.cual(and);
					boolean esAutomatica = false;
					
					for(int i=0; i<red.transiciones(); i++) {
						if(proximoDisparo[i] == 1 && automaticas[i] == 1) {
							esAutomatica = true;
							break;
						}
					}				
					if(esAutomatica) {
						disparo = proximoDisparo;
					}
					else {
						colas.release(proximoDisparo);
						break;
					}	
				}
				else {
					k = false;
					mutex.release();
				}
			}
			else {
				mutex.release();
				colas.acquire(disparo); 
			}
		}
	}
	
	public RedDePetri red() {
		return red;
	}
}
