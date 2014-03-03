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

	
	public int getMaximum ()
	{
		int max = 0;
		
		for (int i=0; i < audioRef.getAudioData().length ; i++)
		{
			max = (Math.abs(audioRef.getAudioData()[i]) > audioRef.getAudioData()[max])? i : max;
		}
		return max;
	}
		
	public void detectionVoix ()
	{
		int i = 0;
		this.setDebut(-1);
		int imax = getMaximum();
		while (i < audioRef.getAudioData().length && this.getDebut()<0)
		{
			
			if (Math.abs(audioRef.getAudioData()[i]/audioRef.getAudioData()[imax])> seuilDetection)
			{
				this.setDebut(i);
				
			}
			
			i++;
				
		}
		
	}

	public void extinctionVoix()
	{
		int i = audioRef.getAudioData().length-1;
		this.setFin(-1);
		int imax = getMaximum();
		while (i > 0 && this.getFin()<0)
		{
			
			if (Math.abs(audioRef.getAudioData()[i]/audioRef.getAudioData()[imax])> seuilDetection)
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
	
	

}
