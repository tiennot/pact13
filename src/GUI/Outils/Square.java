package GUI.Outils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Auditorium.Auditorium;
import GUI.General.Fenetre;
import GUI.General.Window;

public class Square extends JPanel implements MouseListener{
	private JLabel text = new JLabel();
	// Parent window
	private Window window;
	// Target fenetre
	private String fenetreName;
	public Square(String fileName, Window window, String fenetreName){
		this.text.setIcon(new ImageIcon("./data/GUI/"+fileName));
		this.setLayout(new BorderLayout());
		this.add(this.text, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(100,100));
		this.setBackground(Const.BLUE);
		this.fenetreName = fenetreName;
		this.window = window;
		// Listens to itself
		this.addMouseListener(this);
		
	}
	
	public void mouseClicked(MouseEvent event) {
		this.window.setFenetre(this.fenetreName);
		// If we are in the auditorium mode, we launch the reccording
		if(this.fenetreName=="auditorium"){
			Auditorium auditorium = (Auditorium) this.window.getFenetre(this.fenetreName);
			auditorium.startReccording();
		}
	}

	public void mouseEntered(MouseEvent event) {
		this.setBackground(Const.BLUE_DARK);
	}

	public void mouseExited(MouseEvent event) {
		this.setBackground(Const.BLUE);
		
	}

	public void mousePressed(MouseEvent event) { }

	public void mouseReleased(MouseEvent event) { }       
	
}
