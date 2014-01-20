package Audio;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public final class TableAudio {
	
	private XYSeriesCollection audioData;
	private int sampleRate;
	private int sampleSizeInBits;
	private int channels;
	
	public TableAudio(XYSeriesCollection audioData, int sampleRate, int sampleSizeInBits, int channels){
		this.audioData = audioData;
		this.channels = channels;
		this.sampleRate = sampleRate;
		this.sampleSizeInBits = sampleSizeInBits;
	}
	
	
	public XYSeriesCollection getAudioData() {
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
	

	
	public void showSpectrum(){
		FastFourierTransformer math = new FastFourierTransformer(DftNormalization.UNITARY);
		XYSeries serie = this.audioData.getSeries(0);
		XYSeries specSerie = new XYSeries("Spectrum");
		//double step = (double) sampleRate;
		//double x=0.;
		int k = (int) Math.ceil(Math.log(serie.getItemCount())/Math.log(2));
		
		double[] data = new double[(int) Math.pow(2, k)];
		for(int i=0; i<serie.getItemCount();i++){
		data[i] =serie.getY(i).doubleValue();
		}
		
		Complex[] cResult = math.transform(data, TransformType.FORWARD);
		for(int i=0; i<cResult.length; i++){
			specSerie.add(i,cResult[i].abs());
		}
				
		ReadFile.showGraph("Spectrum", new XYSeriesCollection(specSerie));
	}

	
	

}
