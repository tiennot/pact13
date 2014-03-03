package Audio;


public class Domaine {
	
	private int i;
	private double[] trame;
	private int N; // constant quelquesoit la trame
	private double seuil=0.6;
	private int fech;
	
	public Domaine(int f, double [] dataDonne, int fech){
		
		this.fech =fech;
		N = (int) fech/50;
		trame = new double[N];
		
		this.i=f;
		for(int k=i; k<N+i; k++ ){
			trame[k-i] = dataDonne [k];
		}
		
	}
	
	public double fonctionDAutocorrelation(int j){
		double S=0;
		double H=0;
		for(int k=0; k<N-1-j;k++){
			H=S;
			S= H + trame[k]*trame[k+j];
		}
		return S;
	}
	
	public int min(int x, int y){
		if (x<y){
			return x;
		}
		return y;
	}
	
	
	public int frequenceFondamentale(){
		// fr�quence d'�chantillonage 44100Hz
		// une trame 88
		// 400 Hz est la plus grande fr�quence on ne doit pas comparer deux max entre eux 
		// un max significatif doit donc �tre plus grand que ses 9 voisin de gauche et de droite.
		
		
		double a=this.fonctionDAutocorrelation(0);
		int dec = (int) (fech/400);
		int indicedumax;
		int i =dec;
		
		while(this.fonctionDAutocorrelation(i)/a >seuil){
				i=i+1;
		}	
		
		while (i<N){
			
			
			if (this.fonctionDAutocorrelation(i)/a > seuil){
				
				while (this.fonctionDAutocorrelation(i)<this.fonctionDAutocorrelation(i+1)){
					i=i+1;
				}
				
				indicedumax=i;	
				double max=this.fonctionDAutocorrelation(i);
				
				while (i<min(N,indicedumax+dec)){
					if(max<fonctionDAutocorrelation(i)){
						indicedumax=i;
						max=this.fonctionDAutocorrelation(i);
					}
					i=i+1;
				}
				return indicedumax;
			}
				i=i+1;
		}
			//System.out.println("pas de fondamentale");
			indicedumax=-1;
			return indicedumax;
		
	}
	
	
		}

