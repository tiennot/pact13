package Audio;

import java.io.File;

public class RecordThread extends Thread {
	
	private RecordAudio recorder;
	private String fileName;
	
	public RecordThread(String fileName){
		this.recorder = new RecordAudio();
		this.fileName = fileName;
	}
	
	public void run() {
		RecordAudio.wavFile = new File(fileName);
		recorder.start();
    }
	
	public void stopRecord(){
		recorder.finish();
	}
}
