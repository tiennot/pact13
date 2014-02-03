package GUI.Outils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Square extends JPanel implements MouseListener{
	private JLabel text = new JLabel();
	public Square(String fileName){
		this.text.setIcon(new ImageIcon("./data/GUI/"+fileName));
		this.setLayout(new BorderLayout());
		this.add(this.text, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(100,100));
		this.setBackground(Const.BLUE);
		// Listens to itself
		this.addMouseListener(this);
		
	}
	
	public void mouseClicked(MouseEvent event) { }

	public void mouseEntered(MouseEvent event) {
		this.setBackground(Const.BLUE_DARK);
	}

	public void mouseExited(MouseEvent event) {
		this.setBackground(Const.BLUE);
		
	}

	public void mousePressed(MouseEvent event) { }

	public void mouseReleased(MouseEvent event) { }       
	
}
