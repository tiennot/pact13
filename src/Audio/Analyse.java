package Audio;


public class Analyse {
	
	private TableAudio audioRef;
	private double seuilDetection = 0.1;
	private double tBegin;
	private double tEnd;
	private double[] ampDerivative;
	private double[] energy;
		
	
	public Analyse(TableAudio audioRef){
		this.audioRef=audioRef;
		computeEnergy();
		detectionVoix();
		extinctionVoix();
	}
	
	
	public TableAudio getTableAudio(){
		return this.audioRef;
	}
	
	
	
	public double[] getVariations(){
		return this.ampDerivative;
	}
	
	public void setDebut(int i) {
		this.tBegin= (double) i/audioRef.getSampleRate();
	}
	public void setFin(int i) {
		this.tEnd= (double) i/audioRef.getSampleRate();
	}
	public void setVariationsAmplitude(int i, double x) {
		this.ampDerivative[i]= x;
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
	
	public void variationsAmplitude ()
	{
		this.ampDerivative = new double[this.audioRef.getAudioData().length];
		
		for(int i=0; i<audioRef.getAudioData().length-1; i=i+1)
		{
			setVariationsAmplitude(i, (audioRef.getAudioData()[i+1]-audioRef.getAudioData()[i])*audioRef.getSampleRate());
		}
		
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
