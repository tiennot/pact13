package GUI.General;
import java.awt.Font;
import java.io.InputStream;

import Classification.Classif;
import GUI.Loading.Loading;
import GUI.Outils.Info;
import GUI.Utilisateurs.UtilisateursObj;
import GUI.Accueil.Accueil;
import GUI.Auditorium.Auditorium;
import GUI.Auditorium.LearnText;
import GUI.Auditorium.LevelChoice;
import GUI.General.Window;

public class Main {

	public static void main(String[] args) {
		Info.echo("Lancement...");
		// Creates the main window
		Window window = new Window();
		
		// Loading screen while charging data
		Loading loading = new Loading(window);
		window.addFenetre(loading,  "loading");
		window.setFenetre("loading");
		
		// Font
		
		// Initializes the Kinect stuff
		Classif.Init();
		
		// Creates all the fenetres
		Accueil accueil = new Accueil(window);
		Fenetre auditorium = new Auditorium(window);
		Fenetre replay = new Fenetre(window);
		LearnText learnText = new LearnText(window, (Auditorium) auditorium);
		LevelChoice levelChoice = new LevelChoice(window, learnText);
		
		// Adds the fenetres to the cardLayout which handles fenetres
		window.addFenetre(accueil, "accueil"); window.setAccueil(accueil);
		window.addFenetre(auditorium, "auditorium");
		window.addFenetre(replay,  "replay");
		window.addFenetre(learnText,  "learnText");
		window.addFenetre(levelChoice,  "levelChoice");
		
		// Sets default fenetre : accueil
		window.setFenetre("accueil");
		
		UtilisateursObj ut = new UtilisateursObj();
		ut.readFromFile();
		
	}

}
