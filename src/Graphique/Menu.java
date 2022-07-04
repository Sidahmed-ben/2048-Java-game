package Graphique;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.

	 */
	public Menu() throws IOException {
		setResizable(false);
		setTitle("2048");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,600,800);
		setLocationRelativeTo(null);
		this.setFocusable(true);
		this.addKeyListener(new KeyMenuListner(this));
		contentPane = new JPanel();
		
		
		this.add(contentPane);
		contentPane.setLayout(null);
		
		
	
		Icon icon = new ImageIcon("ressources/_plus.png");
		plus = new JButton(icon);
		contentPane.add(plus);
		plus.setBounds(400, 300, 100, 100);
		
		Icon icon2 = new ImageIcon("ressources/moins.png");
		moins = new JButton(icon2);
		moins.setBounds(100, 300, 100, 100);
		contentPane.add(moins);
		
		taille = new JLabel();
		taille.setBounds(200, 300, 200, 100);
		taille.setText("4");
		taille.setHorizontalAlignment(JLabel.CENTER);
	    taille.setVerticalAlignment(JLabel.CENTER);
	    taille.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,50));
	    taille.setBackground(Color.RED);
		contentPane.add(taille);
		
		play = new JButton(" PLAY ");
		play.setBounds(100,450,400, 75);
		play.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,35));
		play.setBackground(new Color(30, 127, 203));
		contentPane.add(play);
		
		option = new JButton("OPTIONS");
		option.setBounds(100,575,400,75);
		option.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,35));
		option.setBackground(new Color(30, 127, 203));
		contentPane.add(option)	;
		
		tailleLabel.setBounds(100,225,400,75);
		tailleLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,35));
		tailleLabel.setHorizontalAlignment(JLabel.CENTER);
	    tailleLabel.setVerticalAlignment(JLabel.CENTER);
		contentPane.add(tailleLabel);	
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				InterfacesDeJeu window;
				try {
					window = new InterfacesDeJeu(Integer.valueOf(taille.getText()));
					window.setVisible(true);
				} catch (NumberFormatException | UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		} );
				
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			int var = Integer.valueOf(taille.getText());
			if(var  < 10)
			{
				var ++;	
				taille.setText(Integer.toString(var));
			}
			}
		} );
		
		
		
		moins.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
					int var = Integer.valueOf(taille.getText());
					if(var > 4)
					{
						var --;	
						taille.setText(Integer.toString(var));
					}
					}
				} );
		logo.setBounds(50, 50,500,100);
		contentPane.add(logo);
		
		fon2.setBounds(50, 50, 500,670);
		contentPane.add(fon2);
		
		fond.setBounds(0,0,600,800);
		contentPane.add(fond);
		
		
	}
	JLabel fon2 = new JLabel(new ImageIcon("ressources/gris.png"));
	JLabel fond = new JLabel(new ImageIcon("ressources/blue.png"));
	JLabel logo = new JLabel(new ImageIcon("ressources/logo.png"));
	JLabel tailleLabel = new JLabel("Grid size");
	JButton plus;
	JButton moins;
	JButton play;
	JButton option;
	JLabel taille;
	
	public void add()
	{
		int var = Integer.valueOf(this.taille.getText());
		if(var  < 10)
		{
			var ++;	
			this.taille.setText(Integer.toString(var));
		}
	}
	
	public void sub()
	{
		int var = Integer.valueOf(this.taille.getText());
		if(var > 4)
		{
			var --;	
			this.taille.setText(Integer.toString(var));
		}
	}
	public void enter()
	{
		dispose();
		InterfacesDeJeu window;
		try {
			window = new InterfacesDeJeu(Integer.valueOf(taille.getText()));
			window.setVisible(true);
		} catch (NumberFormatException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

