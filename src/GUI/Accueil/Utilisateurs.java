package GUI.Accueil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import GUI.General.Window;
import GUI.Outils.Const;
import GUI.Outils.Spacer;
import GUI.Outils.Text;
import GUI.Outils.Title;
import GUI.Outils.UtilisateurButton;
import GUI.Utilisateurs.UtilisateurObj;
import GUI.Utilisateurs.UtilisateursObj;

public class Utilisateurs extends JPanel{
	private Window window;
	private UtilisateursObj utilisateursObj = new UtilisateursObj();
	private JPanel utilisateursContainer = new JPanel();
	private Title title = new Title("Utilisateurs");
	private JPanel titlePanel = new JPanel();
	private ArrayList<UtilisateurButton> utButtons = new ArrayList<UtilisateurButton>();
	
	public Utilisateurs(Window window){
		this.window = window;
		this.setBackground(Const.BLUE);
		// Layouts
		this.setLayout(new BorderLayout());
		this.utilisateursContainer.setLayout(new BoxLayout(this.utilisateursContainer,BoxLayout.PAGE_AXIS));
		
		// Adds & creates title
		this.titlePanel.add(this.title);
		this.titlePanel.setOpaque(false);
		this.utilisateursContainer.add(this.titlePanel);
		
		// Adds some users. Should be done with the database
		this.utilisateursObj.readFromFile();
		
		// Display users
		int nbeUtilisateurs = this.utilisateursObj.getUtilisateurs().size();
		for(int i=0; i<nbeUtilisateurs; ++i){
			UtilisateurObj utObj = this.utilisateursObj.getUtilisateurs().get(i);
			UtilisateurButton button = new UtilisateurButton(utObj, this.window);
			this.utButtons.add(button);
			this.utilisateursContainer.add(button);
		}
		this.add(this.utilisateursContainer, BorderLayout.NORTH);
		// Style
		this.utilisateursContainer.setOpaque(false);
		this.setBackground(Const.BLUE_ALPHA);
	}
	
	//This method resets all utilisateurButtons
	public void resetUtilisateursButtons(){
		for(int i=0; i<this.utButtons.size(); i++){
			this.utButtons.get(i).setActive(false);
		}
	}
	
}
