package Audio;

import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public final class TableAudio {
	
	private ArrayList<double[]> audioData;
	private int sampleRate;
	private int sampleSizeInBits;
	private int channels;
	
	public TableAudio(ArrayList<double[]> audioData, int sampleRate, int sampleSizeInBits, int channels){
		this.audioData = audioData;
		this.channels = channels;
		this.sampleRate = sampleRate;
		this.sampleSizeInBits = sampleSizeInBits;
	}
	
	
	public ArrayList<double[]> getAudioData() {
		return audioData;
	}
	public int getSampleRate() {
		return sampleRate;
	}
	public int getSampleSizeInBits() {
		return sampleSizeInBits;
	}
	public int getChannels() {
		return channels;
	}
	
	public void playAudio(){
		int i=0, n=0;
		boolean signed = (sampleSizeInBits == 8 ) ? false : true;
		AudioFormat audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, false);
		
		
		SourceDataLine line = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat); // format is an AudioFormat object
		if (!AudioSystem.isLineSupported(info)) {
		    System.out.println("La ligne n'est pas supportée");
		    return;
		    }
		    // Obtain and open the line.
		try {
		    line = (SourceDataLine) AudioSystem.getLine(info);
		    line.open(audioFormat);
		} catch (LineUnavailableException ex) {
		        ex.getStackTrace();
		}
		
		System.out.println("Ouverture réussie");
		
		n=line.available();
		while(this.getNextFrame(i, n) != null){
			
			line.write(getNextFrame(i, n), 0, n);
			i += line.available();
			n = line.available();
		}
		System.out.println(line.available());
		
		
	}


	public byte[] getNextFrame(int i, int available) {
		int n = (audioData.get(0).length - i > available) ? available : audioData.get(0).length - i;
		
		if (n <= 0)
			return null;
		else{
			byte[] frame = new byte[n];
			for(int k=0; k<n; k++){
				frame[k] =(byte) (audioData.get(0)[i+k]*128);
				
			}
			return frame;
		}
		
	}
	
	

}
