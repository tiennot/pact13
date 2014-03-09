package Audio;


public class Analyse {
	
	private TableAudio audioRef;
	private double seuilDetection = 0.1;
	private double tBegin;
	private double tEnd;
	
	
	public Analyse(TableAudio audioRef){
		this.audioRef=audioRef;
	}
	
	
	public TableAudio getTableAudio(){
		return this.audioRef;
	}
	
	
	
	
	public void setDebut(int i) {
		this.tBegin= (double) i/audioRef.getSampleRate();
	}
	public void setFin(int i) {
		this.tEnd= (double) i/audioRef.getSampleRate();
	}
	public double getDebut() {
		return this.tBegin;
	}	
	public double getFin() {
		return this.tEnd;
	}

	
	public int getMaximum () //renvoie l'indice ou l'intensite est la plus forte
	{
		int max = 0;
		
		for (int i=0; i < audioRef.getAudioData().length ; i++)
		{
			max = (Math.abs(audioRef.getAudioData()[i]) > audioRef.getAudioData()[max])? i : max;
		}
		return max;
	}
	
	public int getMinimum(){
		int min = 0;
		for(int i =0; i<audioRef.getAudioData().length; i++){
			min = (Math.abs(audioRef.getAudioData()[i]) < audioRef.getAudioData()[min])? i : min;
		}
		return min;
	}
		
	public void detectionVoix ()
	{
		int i = 0;
		this.setDebut(-1);
		int imax = getMaximum();
		while (i < energy.length && this.getDebut()<0)
		{
			
			if (Math.abs(energy[i]/energy[imax])> seuilDetection)
			{
				this.setDebut(i);
				
			}
			
			i++;
				
		}
		
	}

	public void extinctionVoix()
	{
		int i = energy.length-1;
		this.setFin(-1);
		int imax = getMaximum();
		while (i > 0 && this.getFin()<0)
		{
			
			if (Math.abs(energy[i]/energy[imax])> seuilDetection)
			{
				this.setFin(i);
			}
			
			i--;
		}
	}
	
	public double[] variationsAmplitude ()
	{
		double[] ampDerivative = new double[this.audioRef.getAudioData().length];
		
		ampDerivative = new double[this.audioRef.getAudioData().length];
		
		for(int i=0; i<audioRef.getAudioData().length-1; i=i+1)
		{
			ampDerivative[i] = (audioRef.getAudioData()[i+1]-audioRef.getAudioData()[i])*audioRef.getSampleRate();
		}
		
		return ampDerivative;
	}
	
	public double[] getEnergy() {
		
		double[] energy = new double[this.audioRef.getAudioData().length];
		double moy=0;
		
		double[] amp = this.audioRef.getAudioData();
		
		for(int i =0; i<amp.length; i++){
			amp[i] = amp[i]*amp[i];
		}
		
		
		
		int n = 175; //nbre d'échantillon/2 sur lesquels est réalisée la moyenne
		//double sup,inf;
		
		/*for(int i=0; i<energy.length; i++){
			
			moy = amp[i];
			
			for(int k=0; k<n; k++){
				
				sup = (i+n>=energy.length)? 0 : amp[i+n];
				inf = (i-n>0)? amp[i-1-n] : 0;
				
				moy = moy + sup + inf;
			}
			
			energy[i] = (double) moy/(2*n);
		}*/
		
		
		for(int i=0; i<=n; i++){
			moy = moy + energy[i];
		}
		energy[0] = (double) moy/n;
		
		for(int i=1; i<energy.length-1; i++){
			
			double sup,inf;
			
			sup = (i+n>=amp.length)? 0 : amp[i+n];
			inf = (i-n>0)? amp[i-1-n] : 0;
			
			moy = energy[i-1]*2*n + sup - inf;
			energy[i] = (double) moy/(2*n);		
			
			
		}
		
		return energy;
		
		
		
	}
	
	public void computeEnergy(){
		
		int n = 150; // 2*n = nombre de points sur lesquels on realise la moyenne
		double[] data = this.audioRef.getAudioData().clone();
		this.energy = new double[data.length];
		double sup=0;
		double inf=0;
		double x=0;
		
		for(int i =0; i<data.length; i++){ //mise au carré des points
			data[i] = data[i] * data[i];
		}
		
		for(int i=0; i<n; i++){  //mise en place de la moyenne pour le premier point
			x = x + data[i];
		}
		energy[0] = x/(double) (2*n-1);
		
		for(int i=1; i<data.length; i++){ // on "decale" la moyenne d'un rang a chaque tour
			sup = (i+n-1 >= data.length) ? 0 : data[i+n-1];
			inf = (i-n >=0) ? data[i-n] : 0;
			energy[i] = ((double) (2*n-1)*energy[i-1] + sup - inf) /(double) (2*n-1);
		}
		
		return;
		
	}
	
	public double[] getEnergy(){
		return this.energy;
	}
	
	

}
