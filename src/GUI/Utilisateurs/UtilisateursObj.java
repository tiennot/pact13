package GUI.Utilisateurs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import GUI.General.Window;
import GUI.Outils.Info;

// This class handles users : read from file, write to file, etc.
public class UtilisateursObj {
	// A list of the users
	private ArrayList<UtilisateurObj> utilisateurs = new ArrayList<UtilisateurObj>();
	
	public ArrayList<UtilisateurObj> getUtilisateurs() {
		return utilisateurs;
	}




	
	@SuppressWarnings("deprecation")
	public void readFromFile(){
		File file = new File("data/GUI/utilisateurs.txt");
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    //Clear the ArrayList
	    this.utilisateurs.clear();
	    
	    // Gets the data
	    try {
			fis = new FileInputStream(file);
			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
		    dis = new DataInputStream(bis);
		    // dis.available() returns 0 if the file does not have more lines.
		     try {
				while (dis.available() != 0){
				    try {
				    	String line = dis.readLine();
				    	String[] parts = line.split(" ");
				    	this.utilisateurs.add(new UtilisateurObj(parts));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void writeToFile(){
		File file = new File("data/GUI/utilisateurs.txt");
		try {
			PrintWriter writer = new PrintWriter(file);
			for (UtilisateurObj utilisateur : this.utilisateurs) {
				Info.echo(""+utilisateur.getMeilleurScore());
				writer.println(
						// Prints a line with all about an user
						utilisateur.getId()+" "+
						utilisateur.getPrenom()+" "+
						utilisateur.getNom()+" "+
						utilisateur.getNiveau()+" "+
						utilisateur.getTempsJeu()+" "+
						utilisateur.getNbparties()+" "+
						utilisateur.getMeilleurScore());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
}
