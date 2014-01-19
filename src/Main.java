import java.util.Scanner;

import Audio.*;

public class Main {

	public static void main(String[] args) {
		

		while(true){
			
			Scanner sc = new Scanner(System.in);
			int choice;
			String fileName;
			System.out.println("Choisissez votre mode :");
			System.out.println("1.Lecture + Affichage");
			System.out.println("2.Capture Audio");
			System.out.println("3.Affichage Spectre");
			choice = sc.nextInt();
			sc.nextLine();
			System.out.println("Nom du fichier");
			fileName = sc.nextLine();
			switch(choice){
			case 1:
				PlayAudio.playSound(fileName);
				ReadFile.showGraph("Forme d'onde", ReadFile.getData(fileName).getAudioData());
				break;
			case 2:
				System.out.println("Durée d'enregistrement (en s) ?");
				int time = sc.nextInt(); 
				RecordAudio.recordAudio(fileName, time);
				break;
			case 3:
				TableAudio tableAudio = ReadFile.getData(fileName);
				tableAudio.showSpectrum();
				break;
			default:
				System.out.println("Mauvais choix.");
				break;
			}
			
		}

	}

}
