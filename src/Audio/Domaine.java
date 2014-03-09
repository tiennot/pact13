package Audio;


public class Domaine {
	
	private int i; //fixe le début de la copie
	private double[] trame; // reçoit les points corespondant à 20ms
	private final int N; // constant quelquesoit la trame
	private double seuil=0.7; // seuil constant
	private int fech; // frequence d'echantillonage
	
	public Domaine(int f, double [] dataDonne, int fech){
		
		this.fech =fech; 
		N = (int) fech/50;
		trame = new double[N];
		
		this.i=f;
		for(int k=i; k<N+i; k++ ){
			trame[k-i] = dataDonne [k];
		}
		
	}
	
	//Calcul des produits scalaires
	
	public double fonctionDAutocorrelation(int j){ 
		double S=0;
		double H=0;
		for(int k=0; k<N-1-j;k++){
			H=S;
			S= H + trame[k]*trame[k+j];
		}
		return S;
	}
	
	//fonction minimun pour la prochaine methode
	
	public int min(int x, int y){
		if (x<y){
			return x;
		}
		return y;
	}
	
	//fonction créant un seuil variable ( non intégré dans le code encore à cause d'un probleme non résolu)
	
	public double seuil(int k){
		double s;
		s=(double) (N-k) / (double) N;
		return s;
	}
	
	
	public int frequenceFondamentale(){
		
		
		
		double a=this.fonctionDAutocorrelation(0);
		final int dec = (int) (fech/500);
		int indicedumax;
		int i =dec;
		int C=0;
		
		
		
		//assure que l etude commence quand la fonction atteint un minimun
		//System.out.println(dec);
		
		while (C<dec-10||i>N){
			for (int k=i-dec; k<i-10; k++){
			
				if((this.fonctionDAutocorrelation(k))/seuil(k)<(seuil*a)){
					C=C+1;
					
				}
				else{
					C=0;
					i=i+1;
				}
				if(i>N){
					indicedumax=-1;
					return indicedumax;
				}
			}
			
			
		}
		
		//detecte le maximum
		
		while (i<N){
			
			
			if (this.fonctionDAutocorrelation(i) > seuil*a*seuil(i)){
				
				while (this.fonctionDAutocorrelation(i)*seuil(i)<this.fonctionDAutocorrelation(i+1)*seuil(i+1)){
					i=i+1;
				}
				
				indicedumax=i;	
				double max=this.fonctionDAutocorrelation(i);
				
				while (i<min(N,indicedumax+dec)){
					if(max*seuil(indicedumax)<fonctionDAutocorrelation(i)*seuil(i)){
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

