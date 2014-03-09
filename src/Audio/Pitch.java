package Audio;



public class Pitch {
	
	private int N; //correspond a la constante de longeur des trames
	private double[] F0;// stocke les valeurs de pitch
	private int K; //K corespond au nombre de trame dans le discour
	private int fech;//frequence d'echantillonage
	private int offset=400;// (utilit� pratique mais non essentiel)
	
	public Pitch( TableAudio audioFile){
		
		
		
		fech = audioFile.getSampleRate();
		double[] datadonne = audioFile.getAudioData(); 
		 // constuction de F0 a partir de la classe domaine
		N = fech/50;
		
		K=datadonne.length/N;
		F0 = new double[K];
		
		for(int i=0; i< K-1 ;i++){
			 int j=i*N;
			Domaine S= new Domaine(j, datadonne, fech);
			double H= fech/(S.frequenceFondamentale());
			F0[i]=H;
		
		}
	}
	
	public double[] getF0(){
		return this.F0;
	}
	
	//etudie les variation du pitch 
	
	public double[] valeurpositive(){
		int C=0;
		int h=0;
		for( int j=0; j<K; j++){
			if(F0[j]>0){
				C=C+1;
			}
		}
		
		double [] S;
		S= new double[C];
		
		for(int i=0; i<K;i++){
			if(F0[i]>0){
				S[h]=F0[i];
				h=h+1;
			}
		}
		return S;
	}
	
	public double[] variationDePitch(){
	
		double[] S=this.valeurpositive();
		int C=S.length;
		double[] T;
		T= new double[C-1];
		
		for(int k=0; k<C-1; k++){
			T[k]=S[k]-S[k+1]+offset;
		}
		
		return T;
	}
	
	public double[] histogrammeDePitch(){
		double[] S;
		
		S = new double[8];
		for(int i = 0; i<K; i++){
			if(F0[i]>0){
				if(F0[i]<50){
					S[0]=S[0]+1;
				}
				else if(F0[i]<100){
					S[1]=S[1]+1;
				}
				else if(F0[i]<150){
					S[2]=S[2]+1;
				}
				else if(F0[i]<200){
					S[3]=S[3]+1;
				}
				else if(F0[i]<250){
					S[4]=S[4]+1;
				}
				else if(F0[i]<300){
					S[5]=S[5]+1;
				}
				else if(F0[i]<350){
					S[6]=S[6]+1;
				}
				else if(F0[i]<400){
					S[7]=S[7]+1;
				}
			}
		}
		return S;
	}
	
	public double moyenne(){
		double [] h=this.valeurpositive();
		int C=h.length;
		double S=0;
		for(int i=0; i<C; i++){
			S=S+h[i];
		}
		S= (double) S/C;
		return S;
		
	}
	public double ecarttype(){
		double S=0;
		
		double [] g;
		g = this.valeurpositive();
		int C= g.length;
		
		double[] h;
		h=new double [C];
		
		for(int i=0; i<C; i++){
			h[i]=(this.moyenne()-g[i])*(this.moyenne()-g[i]);
		}
		for ( int i=0; i<C; i++){
			S=S+h[i];
		}
		S= (double) S/C;
		//System.out.println(S);
		return Math.sqrt(S);
	}
}

