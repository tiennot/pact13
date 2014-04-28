package GUI.Outils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class KinectLauncher {
	// This class writes into a file to tell the Kinect program it has to record
	private String filename ;
	
	public KinectLauncher(String filename){
		this.filename = filename;
	}
	
	public void startRecording(){
		PrintWriter writer;
		try {
			writer = new PrintWriter(this.filename, "UTF-8");
			writer.println("1");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopRecording(){
		PrintWriter writer;
		try {
			writer = new PrintWriter(this.filename, "UTF-8");
			writer.println("0");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
