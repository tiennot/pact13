package GUI.Accueil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.General.Window;
import GUI.Outils.Const;
import GUI.Outils.Spacer;
import GUI.Outils.Square;
import GUI.Outils.Text;
import GUI.Outils.Title;

public class MainArea extends JPanel{
	// Parent
	private Window window;
	// Main JPanel inside
	private JPanel container = new JPanel();
	// Title
	private Title title;
	private JPanel titlePanel = new JPanel();
	// Replay
	private JPanel replayPanel = new JPanel();
	private JLabel replayLabel = new JLabel();
	// Statistics section
	private JPanel statsPanel = new JPanel();
	private JPanel statsContainer = new JPanel();
	private Text statsNiveau = new Text(""); private Text statsTempsJeu = new Text("");
	private Text statsNbParties = new Text(""); private Text statsMeilleurScore = new Text("");
	// Buttons section
	private JPanel buttonsPanel = new JPanel();
	private JPanel buttonsContainer = new JPanel();	
	
	public MainArea(Window window){
		this.window = window;
		// Style
		this.setOpaque(false);
		// Sets Layout
		this.setLayout(new BorderLayout());
		// The Title
		this.title = new Title("Bienvenue "+Window.USER.getPrenom()+" !");
		this.add(this.titlePanel, BorderLayout.NORTH);
		this.titlePanel.setOpaque(false);
		this.titlePanel.add(this.title);
		//Container
		this.add(this.container, BorderLayout.CENTER);
		this.container.setOpaque(false);
		// Replay
		this.replayPanel.setPreferredSize(new Dimension(490,280));
		this.replayPanel.setOpaque(false);
		this.replayLabel = new JLabel();
		this.replayPanel.add(this.replayLabel);
		this.container.add(this.replayPanel);
		// Statistics
		this.add(this.statsPanel, BorderLayout.EAST);
		this.statsPanel.setLayout(new BorderLayout());
		this.statsPanel.add(this.statsContainer, BorderLayout.NORTH);
		this.statsContainer.setLayout(new BoxLayout(this.statsContainer, BoxLayout.PAGE_AXIS));
		this.refreshData();
		this.statsContainer.add(this.statsNiveau);
		this.statsContainer.add(this.statsNbParties);
		this.statsContainer.add(this.statsTempsJeu);
		this.statsContainer.add(this.statsMeilleurScore);
		this.statsContainer.setOpaque(false);;
		this.statsPanel.setOpaque(false);;
		// Buttons
		this.add(this.buttonsPanel, BorderLayout.SOUTH);
		this.buttonsPanel.setLayout(new BorderLayout());
		this.buttonsPanel.add(this.buttonsContainer, BorderLayout.WEST);
		this.buttonsContainer.setLayout(new BoxLayout(this.buttonsContainer, BoxLayout.LINE_AXIS));
		
		this.buttonsContainer.add(new Square("auditorium.png"));
		this.buttonsContainer.add(new Spacer());
		this.buttonsContainer.add(new Square("entretien.png"));
		this.buttonsContainer.add(new Spacer());
		this.buttonsContainer.add(new Square("aide.png"));
		this.buttonsContainer.add(new Spacer());
		this.buttonsContainer.add(new Square("trophees.png"));
		this.buttonsPanel.setOpaque(false);
		this.buttonsContainer.setOpaque(false);
		this.buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 36, 10, 10));
	}
	
	public void refreshData(){
		// Refreshes title
		this.getTitle().setText("Bienvenue "+Window.USER.getPrenom()+" !");
		// Refreshes statistics
		this.statsNiveau.setText("Niveau "+Window.USER.getNiveau());
		this.statsTempsJeu.setText("Temps de jeu : "+Window.USER.getTempsJeu());
		this.statsNbParties.setText("Parties jouées : "+Window.USER.getNbparties());
		this.statsMeilleurScore.setText("Meilleur score : "+Window.USER.getMeilleurScore());
		// Refreshes replay img
		this.replayLabel.setIcon(new ImageIcon("data/GUI/user-replay-"+Window.USER.getId()+".jpg"));
	}
	
	public Title getTitle(){
		return this.title;
	}
	 
}
