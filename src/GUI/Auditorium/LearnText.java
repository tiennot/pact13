package GUI.Auditorium;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;

import GUI.General.Fenetre;
import GUI.General.Window;
import GUI.Outils.Text;

public class LearnText extends Fenetre implements MouseListener{
	// Parent window
	private Window window;
	private Auditorium auditorium;
	public static String textToLearn = "";
	public Text text;
			
	public LearnText(Window window, Auditorium auditorium){
		super(window);
		this.window = window;
		this.auditorium = auditorium;
		this.setLayout(new BorderLayout());
		this.text = new Text(LearnText.textToLearn);
		this.add(this.text,BorderLayout.CENTER);
		
		// Listens to itself
		this.addMouseListener(this);
	}
	
	public void setText(String textToLearn){
		this.textToLearn = textToLearn;
		this.text.setText(textToLearn);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.window.setFenetre("auditorium");
		this.auditorium.startReccording();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	
}
