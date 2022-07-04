package Graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import code_source.Grille_de_jeu;

public class InterfacesDeJeu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Launch the application.
	 */
/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					InterfacesDeJeu window = new InterfacesDeJeu(5);
					window.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 */
	public InterfacesDeJeu(int taille) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		this.vect = new Vector<JLabel>();
		this.btn = new JButton();
		this.canvas = new JPanel();
		this.jeu = new Grille_de_jeu(taille);
		this.gl = new GridLayout(jeu.getTaille(),jeu.getTaille(),10,10);
		this.panel = new JPanel();
		this.f = new BorderLayout();
		this.score = new JLabel();
		this.time= new JLabel();
		this.valide = true;
		this.gagne = false;
		this.initialize();
		

		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setFocusable(true);
		this.addKeyListener(new keyDemoKeyListener(this));
		this.setSize(new Dimension(jeu.getTaille() *120,jeu.getTaille()*110));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		//this.setLayout(new FlowLayout());
		
		
		panel.setLayout(new GridLayout(1,3));
		panel.setPreferredSize(new Dimension(500, 80) );
		
		
		
		this.add(panel,BorderLayout.NORTH);
		
		panel.add(score);
		jeu.ajouter_Celulles();
		score.setBackground(Color.lightGray);
		score.setOpaque(true);
		score.setSize(60, 40);
	    score.setHorizontalAlignment(JLabel.CENTER);
	    score.setVerticalAlignment(JLabel.CENTER);
		score.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.black));
		score.setFont(new java.awt.Font(Font.MONOSPACED,Font.BOLD,20));
		
		panel.add(btn);
		btn.setText("Leave the game");
		btn.setBackground(Color.lightGray);
		btn.setOpaque(true);
		btn.setFont(new java.awt.Font(Font.MONOSPACED,Font.BOLD,15));
		btn.addActionListener(new ActionListener() {

         
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();		
				Menu frame;
				try {
					frame = new Menu();
					frame.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}

        });
		
		panel.add(time);
		time.setBackground(Color.lightGray);
		time.setOpaque(true);
		time.setSize(60, 40);
	    time.setHorizontalAlignment(JLabel.CENTER);
	    time.setVerticalAlignment(JLabel.CENTER);
		time.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.black));
		time.setFont(new java.awt.Font(Font.MONOSPACED,Font.BOLD,20));
		
		canvas.setLayout(gl);
		canvas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		canvas.setBackground(Color.darkGray);
		this.add(canvas , BorderLayout.CENTER);
		

		for(int i = 0 ; i < Math.pow(jeu.getTaille(),2);++i)
		{
			JLabel label = new JLabel();
			if(jeu.getcontenu(i%jeu.getTaille(),i/jeu.getTaille()) != 0)
			{
			    label.setHorizontalAlignment(JLabel.CENTER);
			    label.setVerticalAlignment(JLabel.CENTER);
				label .setText(Integer.toString(jeu.getcontenu(i%jeu.getTaille() ,i/jeu.getTaille())));
			}
			
			label.setOpaque(true);
			label.setBackground(getColor(jeu.getcontenu(i%jeu.getTaille() ,i/jeu.getTaille() )));
			
			label.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
			vect.addElement(label);
			canvas.add(label);	
		}
		score.setText("Score : " + Integer.toString(0));
		
		secondes = 0;
		
		/* Le coeur de la détermination du temps écoulé */
		timer = new Timer();
		timer.schedule(new TimerTask(){

			public void run() {
				if (valide) {
					secondes += 1;
					time.setText(timeToText(secondes));
				}
			}
			
		},0,1000);
	}
	Vector<JLabel> vect;
	JButton btn;
	JPanel canvas ;
	Grille_de_jeu jeu ;
	GridLayout gl ;
	JPanel panel ;
	BorderLayout f ;
	JLabel score ;
	JLabel time;
	Timer timer;
	long secondes;
	boolean gagne ;
	
	boolean valide = true;

		

	Color getColor(int val )
	{
		Color c = new Color(0);
		switch (val)
		{
		case 0: 
			c = new Color(255,195,0);
			break;
		case 2: 
			c = new Color(255,87,51);
			break;
		case 4: 
			c = new Color(199,0,57);
			break;
		case 8: 
			c = new Color(214,137,16);
			break;	
		case 16:
			c = new Color(218,247,166);
			break;
		case 32:
			c = new Color(31,97,141);
			break;
		case 64: 
			c = new Color(25,111,61);
			break;
		case 128: 
			c = new Color(236,112,99);
			break;
		case 256: 
			c = new Color(88,214,141);
			break;
		case 512: 
			c = new Color(93,109,126);
			break;
		case 1024: 
			c = new Color(165,105,189);
			break;
		case 2048: 
			c = new Color(171,178,185);
			break;
		default :
			c =Color.black;
		}
		
		return c;

	}
	
    public void movelabel(){ 	
    	
    	
    	

    	score.setText("Score : " + Integer.toString(jeu.getScore()));
    	
       	 
        for(int i = 0 ; i < jeu.getTaille()*jeu.getTaille();++i)
		{
        	vect.elementAt(i).setHorizontalAlignment(JLabel.CENTER);
        	vect.elementAt(i).setVerticalAlignment(JLabel.CENTER);
			if(jeu.getcontenu(i%jeu.getTaille(),i/jeu.getTaille()) != 0)
			{	
				vect.elementAt(i).setText(Integer.toString(jeu.getcontenu(i%jeu.getTaille() ,i/jeu.getTaille() )));
			}else {
				vect.elementAt(i) .setText("");
			}
			vect.elementAt(i).setBackground(getColor(jeu.getcontenu(i%jeu.getTaille(),i/jeu.getTaille() )));
		}
        
        
    	if(jeu.perdu())
    		finPartie();   
    	
    	
    	if(jeu.getMax() == 2048 && !this.gagne)
    	{
    		ImageIcon  icon = new  ImageIcon("ressources/gagne.png"); 
        	JOptionPane.showMessageDialog(null,"Congratulation you win 2048 , continue","You are the best !!",JOptionPane.OK_OPTION,icon);
    		this.gagne = true;
    		
    	}
     
    }
    public void moveDroite()
    {
    	if(this.jeu.clic_droit())
    	{
    		this.jeu.ajouter_Celulles();
    		this.movelabel();
    	}
    	
    }
    public void moveHaut()
    {
 	   if(this.jeu.clic_bas())
 	   {
 			this.jeu.ajouter_Celulles();
    		this.movelabel();
 	   }
 	 
    }
    public void moveBas()
    {
    	 if(this.jeu.clic_haut())
    	 {
  			this.jeu.ajouter_Celulles();
     		this.movelabel();
    	 }
    	
    }
    public void movegauche()
    {
    	if(this.jeu.clic_gauche())
    	 {
 			this.jeu.ajouter_Celulles();
    		this.movelabel();
    	 }
    }
		
   public void finPartie()
   {	valide = false;
	   
	   int i = JOptionPane.showConfirmDialog(null,"HAHA!! YOU LOSE :)","LOSER",JOptionPane.YES_NO_OPTION);
	  
	   if(i == 1)
	   {
		   this.dispose(); 
	   }else {
		   jeu = new Grille_de_jeu(4);
		   jeu.ajouter_Celulles();
		   movelabel();
		   secondes = 0;
		   valide = true;
		   
	   }
	   
	  
	   
		   
	   
   }



   private String timeToText(long secondes){
		/* Détermination des 3 composantes int h, mn, s 

		 
Remarque, on aura toujours les nombres qui tiennent
		 
sur deux chiffres, grâce aux modulos*/

		long heure, min, sec;
		sec = secondes % 60;
		min = (secondes / 60) % 60;
		heure = (secondes / (60*60)) % 24;
		
		/* Détermination des 3 composantes String h, mn, s */
		String sHeure, sMin, sSec;
		sHeure = timeFormat(heure);
		sMin = timeFormat(min);
		sSec = timeFormat(sec);
		
		/* Retourne la chaîne à afficher */
		return sHeure + ":" + sMin + ":" + sSec;
		
	}
   @SuppressWarnings("deprecation")
private String timeFormat(long timeComposant){
		if(timeComposant<10)
			return "0"+new Long(timeComposant).toString();
		else{
			return new Long(timeComposant).toString();
		}
	}
	
	



}
