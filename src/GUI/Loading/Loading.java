package GUI.Loading;

import java.awt.BorderLayout;

import GUI.General.Fenetre;
import GUI.General.Window;
import GUI.Outils.Text;

public class Loading extends Fenetre{
	public Loading(Window window){
		super(window);
		this.setLayout(new BorderLayout());
		this.add(new Text("Loading..."), BorderLayout.CENTER);
	}
}
