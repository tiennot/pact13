package GUI.General;
import java.awt.Font;
import java.io.InputStream;

import GUI.Loading.Loading;
import GUI.Outils.Info;
import GUI.Utilisateurs.UtilisateursObj;
import GUI.Accueil.Accueil;
import GUI.Auditorium.Auditorium;
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
		
		
		// Creates all the fenetres
		Accueil accueil = new Accueil(window);
		Fenetre auditorium = new Auditorium(window);
		Fenetre replay = new Fenetre(window);
		
		// Adds the fenetres to the cardLayout which handles fenetres
		window.addFenetre(accueil, "accueil"); window.setAccueil(accueil);
		window.addFenetre(auditorium, "auditorium");
		window.addFenetre(replay,  "replay");
		
		// Sets default fenetre : accueil
		window.setFenetre("accueil");
		
		UtilisateursObj ut = new UtilisateursObj();
		ut.readFromFile();
		
	}

}
