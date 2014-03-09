package Audio;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class CaracList {
	
	
	
	private ArrayList<CaracteristiqueDiscours> caracList;
	
	public CaracList(){
		
		caracList = new ArrayList<CaracteristiqueDiscours>();
		
		try{
			FileInputStream fout = new FileInputStream("bdd/discours");
			ObjectInputStream ois = new ObjectInputStream(fout);
			while(true){
				try{
					caracList.add( (CaracteristiqueDiscours) ois.readObject());
				}
				catch(EOFException e){
					break;
				}
			}
			ois.close();
			System.out.println("Done");
		}
		catch(Exception e){
			e.printStackTrace();
	}
		
		//int s=caracList.size();
		//for( int i=0; i<5; i++){
		//	System.out.println(caracList.get(i).toString());
		//}
		
		}
	
	public CaracteristiqueDiscours discoursLePlusProche(TableAudio Audiofile, int identification){
		int s=caracList.size();
		CaracteristiqueDiscours min = null;
		CaracteristiqueDiscours discours= new CaracteristiqueDiscours(Audiofile, identification);
		double distancemin = 22; //discours.distance(caracList.get(0));
		
		for(int i=0; i<s; i++){
			
			if(caracList.get(i).getNumeroDiscours()==identification){
				
				if(caracList.get(i).distance(discours)<=distancemin){
					distancemin=caracList.get(i).distance(discours);
					min =caracList.get(i);
				}
			}
		}
		System.out.println(distancemin);
		return min;
	}
}
