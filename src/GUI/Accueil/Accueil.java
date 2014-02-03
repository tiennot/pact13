package GUI.Accueil;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import GUI.General.Fenetre;
import GUI.General.Window;
import GUI.Outils.Const;
import GUI.Outils.Info;
import GUI.Outils.Text;
import GUI.Outils.UtilisateurButton;

public class Accueil extends Fenetre{
	// Parent
	private Window window;	
	// The areas of the accueil fenetre
	protected Utilisateurs utilisateurs;
	protected MainArea mainArea;
	
	public Accueil(Window window){
		super(window);
		this.window = window;
		this.utilisateurs = new Utilisateurs(window);
		this.mainArea = new MainArea(window);
		// Overrides classic layout for windows
		this.setLayout(new BorderLayout());
		
		// Adds title to the borderLayout
		this.add(this.mainArea, BorderLayout.CENTER);
		
		// Creates and adds the users side section
		this.add(this.utilisateurs, BorderLayout.WEST);
	}
	
	public void refreshData(){
		this.mainArea.refreshData();
	}
	
	public Utilisateurs getUtilisateurs(){
		return this.utilisateurs;
	}
}
