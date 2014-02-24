package Audio;


public class Analyse {
	
	private TableAudio audioRef;
	private double seuilDetection = 0.1;
	private double tBegin;
	private double tEnd;
	private double[] ampDerivative;
	
	
	public Analyse(TableAudio audioRef){
		this.audioRef=audioRef;
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
	
	public void variationsAmplitude ()
	{
		this.ampDerivative = new double[this.audioRef.getAudioData().length];
		
		for(int i=0; i<audioRef.getAudioData().length-1; i=i+1)
		{
			setVariationsAmplitude(i, (audioRef.getAudioData()[i+1]-audioRef.getAudioData()[i])*audioRef.getSampleRate());
		}
	}
	

}
