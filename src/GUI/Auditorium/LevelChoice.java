package GUI.Auditorium;

import javax.swing.BoxLayout;

import GUI.General.Fenetre;
import GUI.General.Window;
import GUI.Outils.Text;

public class LevelChoice extends Fenetre{
	// Parent window
	private Window window;
	
	public LevelChoice(Window window, LearnText learntext){
		super(window);
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		this.add(new LevelText("<html><p style='text-align:center'>Dominique de Villepin, ONU, 14 février 2003</p></html>",1, window, learntext));
		this.add(new LevelText("<html><p style='text-align:center'>Charles de Gaulle, Londres, 18 juin 1940</p></html>",2, window, learntext));
		this.add(new LevelText("<html><p style='text-align:center'>Robert Badinter, Paris, 17 septembre 1981</p></html>",3,window, learntext));
		this.add(new LevelText("<html><p style='text-align:center'>Jean Jaurès, Bruxelles, 29 juin 1914</p></html>",4,window, learntext));
	}
	
	
}
