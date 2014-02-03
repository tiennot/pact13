package GUI.Outils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.General.Window;
import GUI.Utilisateurs.UtilisateurObj;

public class UtilisateurButton extends JPanel implements MouseListener{
	// Text of the user button
	private Text text;
	private UtilisateurObj utilisateurObj;
	// Parent window
	private Window window;
	// If active
	private boolean active = false;
	
	public UtilisateurButton(UtilisateurObj utilisateurObj, Window window){
		// Sets the user & the parent
		this.utilisateurObj = utilisateurObj;
		this.window = window;
		// Layout
		this.setLayout(new BorderLayout());
		// Creates and add a new text
		this.text = new Text(this.utilisateurObj.getPrenom());
		this.add(this.text, BorderLayout.WEST);
		// Style
		this.setBackground(Const.TRANSPARENT);
		this.text.setHorizontalAlignment(JLabel.LEFT);
		ImageIcon icon = new ImageIcon("./data/GUI/user-avatar-"+this.utilisateurObj.getId()+".jpg");
		this.text.setIconTextGap(5);
		this.text.setIcon(icon);
		// Size
		this.setPreferredSize(new Dimension(250,120));
		// Listens to itself
		this.addMouseListener(this);
	}
	
	public void setActive(boolean active){
		this.active = active;
		if(active) this.setBackground(Const.BLUE_DARK);
		else this.setBackground(Const.BLUE_LIGHT);
	}
	
	public void mouseClicked(MouseEvent event) {
		Window.USER = this.utilisateurObj;
		this.window.RefreshData();
		this.window.getAccueil().getUtilisateurs().resetUtilisateursButtons();
		if(!this.active){
			this.setActive(true);
		}
		else{
			this.setActive(false);
		}
	}

	public void mouseEntered(MouseEvent event) {
	}

	public void mouseExited(MouseEvent event) {
	}

	public void mousePressed(MouseEvent event) { }

	public void mouseReleased(MouseEvent event) { } 

}
