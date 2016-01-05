package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;

import BD.*;


/**
 * <b>vueDepart est la frame qui permettra d'entrer dans l'application et de cr�er une connexion avec la base de donn�es</b>
 * <p>
 * Une vueD�part est caract�ris�s par les informations suivantes :
 * <ul>
 * <li>Un panelPrincipal</li>
 * <li>Un bouton de connexion</li>
 * </ul>
 * </p>
 * <p>
 * On peut initialier une vue de d�part � partir de la classe lui m�me
 * On peut cr�er une vueInitial en appuyant sur le bouton connexion
 * </p> 
 * @author BOULME KUI ET DOLSA
 * @version 1.0
 */
public class vueDepart extends JFrame implements ActionListener{
	
	//______________LES VARIABLES DE LA CLASSE____________________
	
	/**
     *Le panelPrincipal.
     * @see vueDepart
     */
	private JPanel panelPrincipal;
	
	/**
     *Le bouton de connexion.
     * @see vueDepart
     */
	private JButton boutonConnexion;

	//_____________________LES METHODES________________________
	
	
	/**
    *Constructeur sans param�tre
    *On initialise le panel principal et le bouton de connexion
    *On initialise la frame de d�part
    */
	public vueDepart()
	{
		panelPrincipal = new JPanel();
		boutonConnexion = new JButton("Connexion");
		boutonConnexion.addActionListener(this);
		
		//Ajout dans la frame de d�part
		panelPrincipal.add(boutonConnexion);
		this.add(panelPrincipal);
		this.setTitle("Projet_BASE_DE_DONNEE");
		
		//Initialisation de la frame de d�part
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(200,100);
	}
	
	//ActionListener de la classe
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand()=="Connexion")
		{
			//Cr�ation d'une connexion avec la base de donn�es
			String url="jdbc:oracle:thin:skui/sS26121995@oracle.iut-orsay.fr:1521:etudom";
			Connection co=OutilsJDBC.openConnection(url);	
			System.out.println("connexion ouverte");
			System.out.println();
			
			//Cr�ation d'une nouvelle vue
			vueInitial vue;
			vue = new vueInitial();
			this.dispose();
		}
	}
	
}	
