package GUI.General;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.JFrame;

import GUI.Accueil.Accueil;
import GUI.Outils.Info;
import GUI.Utilisateurs.UtilisateurObj;
import GUI.Utilisateurs.UtilisateursObj;

public class Window extends JFrame{
	// A Card Layout to manage fenetres
	private CardLayout layout = new CardLayout();
	private Accueil accueil;
	// A list on the panels
	private Hashtable<String, Fenetre> panels = new Hashtable<String, Fenetre>();
	// An object of type utilisateurObj, which represents the current user
	public static UtilisateurObj USER;
	public void RefreshData(){
		this.accueil.refreshData();
	}
	public void setAccueil(Accueil accueil){
		this.accueil = accueil;
	}
	public Accueil getAccueil(){
		return this.accueil;
	}
	
	public Window(){
		// Sets an user
		UtilisateursObj utsObj = new UtilisateursObj();
		utsObj.readFromFile();
		Window.USER = utsObj.getUtilisateurs().get(0);
		// Title of the main Window
		this.setTitle("Coach Immersif");
		// Displays the main Window
		this.setVisible(true);
		// Minimal height and width
		this.setSize(new Dimension(1070,500));
		this.setResizable(false);
		// Moves it to the center of the screen
		this.setLocationRelativeTo(null);
		// Exit command
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(this.layout);
	}
	
	public void addFenetre(Fenetre fenetre, String name){
		this.add(fenetre, name);
		this.panels.put(name, fenetre);
	}
	
	public void setFenetre(String name){
		this.layout.show(this.getContentPane(), name);
	}
	
	public Fenetre getFenetre(String name){
		return this.panels.get(name);
	}
}
