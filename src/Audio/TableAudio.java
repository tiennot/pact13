package Audio;

/* La table audio est constituee de tous les echantillons du signal */

public final class TableAudio {
	
	private double[] audioData;
	private int sampleRate;
	private int sampleSizeInBits;
	
	public TableAudio(String fileName) {
		
		TableAudio tableAudio = ReadFile.getData(fileName);		
		
		this.audioData = tableAudio.audioData;
		this.sampleRate = tableAudio.sampleRate;
		this.sampleSizeInBits = tableAudio.sampleSizeInBits;
		
	}	
	
	public TableAudio(double[] audioData, int sampleRate, int sampleSizeInBits){
		this.audioData = audioData;
		this.sampleRate = sampleRate;
		this.sampleSizeInBits = sampleSizeInBits;
	}
	
	
	public double[] getAudioData() {
		return audioData;
	}
	public int getSampleRate() {
		return sampleRate;
	}
	public int getSampleSizeInBits() {
		return sampleSizeInBits;
	}	

}
