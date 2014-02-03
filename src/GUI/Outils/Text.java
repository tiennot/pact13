package GUI.Outils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Text extends JLabel{
	public Text(String string){
		// Font & Color
		this.setForeground(Color.white);
		this.setFont(new Font("Calibri Light", 0, 28));
		// Text-Align : center
		this.setHorizontalAlignment(JLabel.CENTER);
		// Creates the text
		this.setText(string);
		// Padding = transparent borders
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
}
