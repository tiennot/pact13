package GUI.Auditorium;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import GUI.Outils.Const;
import GUI.Outils.Info;
import GUI.Outils.Text;

public class EndButton extends JPanel implements MouseListener{
	private Text text = new Text("Terminer l'enregistrement");
	// Reference to its parent auditorium
	private Auditorium auditorium;
	
	public EndButton(Auditorium auditorium){
		// Adds the text label to display
		this.text.setFont(Const.font(20));
		this.add(this.text);
		// Makes it transparent
		this.setOpaque(false);
		// Listens to itself
		this.addMouseListener(this);
		// Adds the parent
		this.auditorium = auditorium;
	}

	public void mouseClicked(MouseEvent e) {
		this.auditorium.stopReccording();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {		
	}
}
