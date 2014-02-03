package GUI.General;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.General.Window;
import GUI.Outils.Text;

// This class doesn't represents a window, but what we called a fenetre : accueil, etc.s
public class Fenetre extends JPanel{	
	// Parent
	Window window;
	// Background Image
	private Image img;
	
	public Fenetre(Window window){
		this.window = window;
		this.setVisible(false);
		this.img = new ImageIcon("data/GUI/background.jpg").getImage();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
}
