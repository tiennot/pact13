import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Audio.*;
import Display.GraphicDisplay;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<String> fileNames = new ArrayList<String>();
		ArrayList<Integer> fileIds = new ArrayList<Integer>();

		while(true){
			
			Scanner sc = new Scanner(System.in);
			int choice;
			String fileName;
			System.out.println("Choisissez votre mode :");
			System.out.println("1.Lecture + Affichage");
			System.out.println("2.Construction BDD");
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice){
			case 1:
				System.out.println("Nom du fichier");
				fileName = sc.nextLine();
				TableAudio tA = new TableAudio(fileName);
				Analyse a = new Analyse(tA);
				GraphicDisplay.showGraph("Energie", "Temps", "Log E", a.getEnergy());
				
				CaracteristiqueDiscours carac = new CaracteristiqueDiscours(new TableAudio(fileName),1); 
				System.out.println(carac.toString());
				
				
				//GraphicDisplay.showGraph("Variations","Temps","Amplitude",analyse.getEnergy());

				break;
			case 2:
				Integer n = new Integer(0);
				while(true){
					System.out.println("Nom du fichier");
					fileName = sc.nextLine();
					if(fileName.equals("0"))
						break;
					System.out.println("Identification");
					n = sc.nextInt();
					sc.nextLine();
					fileNames.add(fileName);
					fileIds.add(n);
				}
				
				try{
					FileOutputStream fout = new FileOutputStream("bdd/discours");
					ObjectOutputStream oos = new ObjectOutputStream(fout);
					for(int i=0; i<fileNames.size(); i++){
					oos.writeObject(new CaracteristiqueDiscours(new TableAudio(fileNames.get(i)),fileIds.get(i)));
					}
					oos.close();
					System.out.println("Done");
				}
				catch(Exception e){
					e.printStackTrace();
				}
				break;
			case 3:
				try{
					FileInputStream fout = new FileInputStream("bdd/discours");
					ObjectInputStream ois = new ObjectInputStream(fout);
					CaracteristiqueDiscours c = (CaracteristiqueDiscours) ois.readObject();
					while(true){
						System.out.println(c.toString());
						System.out.println("----------------");
						try{
							c = (CaracteristiqueDiscours) ois.readObject();
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
				break;
			default:
				System.out.println("Mauvais choix.");
				break;
			}
			
		}

	}

}
