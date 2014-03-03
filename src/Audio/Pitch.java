package Audio;

import org.jfree.data.xy.XYSeriesCollection;


public class Pitch {
	
	private int N; //correspond � la constante de longeur des trames
	private double[] F0;
	private int K; //K corespond au nombre de trame dans le discour
	private int fech;//frequence d'echantillonage
	private double jitter;
	
	public void setJitter(double d)
	{
		this.jitter = d;
	}
	
	public Pitch( TableAudio audioFile){
		
		
		
		fech = audioFile.getSampleRate();
		XYSeriesCollection datadonne0 = audioFile.getAudioData(); 
		
		double[] datadonne = new double[datadonne0.getSeries(0).getItemCount()];
		
		for(int index=0, end = datadonne0.getSeries(0).getItemCount(); index<end; index++){
		
			datadonne[index] = datadonne0.getSeries(0).getY(index).doubleValue();
		
		}
		
		N = fech/50;
		
		K=datadonne.length/N;
		F0 = new double[K];
		
		for(int i=0; i< K-1 ;i++){
			 int j=i*N;
			Domaine S= new Domaine(j, datadonne, fech);
			double H= fech/(S.frequenceFondamentale());
			F0[i]=H;
		
		}
		
		double d = 0;
		for (int i =1; i< F0.length-1; i=i+1)
		{
			d = d + Math.abs(1/F0[i]-1/F0[i+1]);
		}
		
		this.setJitter( d/(F0.length-1) );
	}
	
	public double[] getF0(){
		return this.F0;
	}
	
	public void Jitter()
	{
		
	}
	
}


