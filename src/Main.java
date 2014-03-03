import java.util.Scanner;

import Audio.*;
import Display.GraphicDisplay;

public class Main {

	public static void main(String[] args) {
		

		while(true){
			
			Scanner sc = new Scanner(System.in);
			int choice;
			String fileName;
			System.out.println("Choisissez votre mode :");
			System.out.println("1.Lecture + Affichage");
			System.out.println("2.Capture Audio");
			choice = sc.nextInt();
			sc.nextLine();
			System.out.println("Nom du fichier");
			fileName = sc.nextLine();
			switch(choice){
			case 1:
				
				Analyse analyse = new Analyse(new TableAudio(fileName));
				analyse.extinctionVoix();
				analyse.detectionVoix();
				System.out.println(analyse.getDebut());
				System.out.println(analyse.getFin());
				//GraphicDisplay.showAudio(analyse.getTableAudio());
				GraphicDisplay.showGraph("Energie","Temps","Amplitude",analyse.getEnergy());

				break;
			case 2:
				System.out.println("Durée d'enregistrement (en s) ?");
				int time = sc.nextInt(); 
				RecordAudio.recordAudio(fileName, time);
				break;
			default:
				System.out.println("Mauvais choix.");
				break;
			}
			
		}

	}

}
