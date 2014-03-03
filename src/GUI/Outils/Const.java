package GUI.Outils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


// This class contains several static values that are used across the whole program
public class Const {
	// Color shades
	public static Color BLUE_LIGHT = new Color(154,96,32);
	public static Color BLUE = new Color(124,66,2);
	public static Color BLUE_DARK = new Color(94,36,0);
	public static Color BLUE_ALPHA = new Color(124,66,2,200);
	public static Color TRANSPARENT = new Color(0,0,0,0);
	// Font	
	public static Font font(int size){
		return new Font("Sans Serif", 0, size);
	}
}
