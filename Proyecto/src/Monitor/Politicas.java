package Monitor;
import java.util.Random;


public class Politicas {
//hay que pulirlo pero funciona proba
	
	private int[] prioridades;
	private int[] vectorDisparo;
	private int[] vectorPrueba;
	private int j;
 	public Politicas(int[] prioridades) {
		this.prioridades = prioridades;
		j = 0;
	}
	
	public int[] cual(int[] prioridades) {
		int i=0;
		vectorPrueba = burbuja();
		Random r = new Random();
		while(i<prioridades.length){
		if(prioridades[i]==vectorPrueba[i])
			vectorDisparo[i] = 1;
		else
			vectorDisparo[i] = 0;
		i++;	
		}
		i = 0;
		while(i<vectorDisparo.length){
			if(vectorDisparo[i]==0)
				j++;
			i++;
		}
		i = r.nextInt(vectorPrueba.length);
		if(j==0)
		vectorDisparo[i] = 1;
		return vectorDisparo;
	}
	public  int[] burbuja(){
        int i, j, aux;
        int A[]= prioridades;
        for(i=0;i<A.length-1;i++)
             for(j=0;j<A.length-i-1;j++)
                  if(A[j+1]<A[j]){
                     aux=A[j+1];
                     A[j+1]=A[j];
                     A[j]=aux;
                  }
		return A;
}
}
