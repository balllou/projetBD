package Application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BD.OutilsJDBC;


/**
 * <b>vueInitial est la frame qui permettra d'afficher les resultats des requêtes</b>
 * <p>
 * Une vueInitial est caractérisés par les informations suivantes :
 * <ul>
 * <li>Un panelAffichage avec les labels</li>
 * <li>Un panelBouton avec l'ensemble des boutons</li>
 * </ul>
 * </p>
 * <p>
 * On peut initialier une vue de départ à partir de la classe lui même
 * On peut créer une vueDépart en appuyant sur le bouton quitter et en même temps fermer la connexion
 * </p> 
 * @author BOULME KUI ET DOLSA
 * @version 1.0
 */
public class vueInitial extends JFrame implements ActionListener {

	//______________LES VARIABLES DE LA CLASSE____________________

	/**
     *Le panelAffichage, labelHaut, labelMilieu
     * @see vueInitial
     * @see changePanelAffichage
     */
	private JPanel panelAffichage;
	private JLabel labelhaut;
	private JLabel labelmilieu;

	
	
	/**
     *Le panelBouton, bouton1, bouton2, bouton3, bouton4, bouton5, bouton6, bouton7, bouton8
     * @see vueInitial
     * @see changePanelAffichage
     */
	private JPanel panelBouton;
	private JButton bouton1;
	private JButton bouton2;
	private JButton bouton3;
	private JButton bouton4;
	private JButton bouton5;
	private JButton bouton6;
	private JButton bouton7;
	private JButton bouton8;

	
	/**
     *Le boutonQuitter
     * @see vueInitial
     * @see changePanelAffichage
     */
	private JButton boutonQuitter;


	//Pour l'affichage des requetes, on a créer un tableau de String
	public String[] titreRequete ={"Requete 1 : récupérer le nombre d'étudiants avec stage cette année","Requete 2 : récupérer le nombre d'étudiants sans stage cette année","Requete 3 : récupérer le nombre d'étudiants sans stage à une certaine date pour une année précédente"+
			"choisie par l'utilisateur","Requete 4 : le nombre de stagiaires pris par chaque entreprise durant les n dernières années","Requete 5 : le nombre moyen de stagiaires encadrés par les entreprises dans les n dernières années","Requete 6 : le nombre de stages par zone géographique choisi par l'utilisateur (département, ville)","Requete 7 : le nombre de stages pour toutes les zones géographiques (département, ville)","Requete 8 : récupérer toutes les entreprises et leur contact ayant eu au moins un stage dans les n"
					+"dernières années"};

	//_____________________LES METHODES________________________
	
	/**
	    *Constructeur sans paramètre
	    *On initialise les panel
	    *On initialise la frame de départ
	    */
	public vueInitial() {
		//Initialisation des boutons
		bouton1 = new JButton("Requete 1");
		bouton1.addActionListener(this);
		bouton2 = new JButton("Requete 2");
		bouton2.addActionListener(this);
		bouton3 = new JButton("Requete 3");
		bouton3.addActionListener(this);
		bouton4 = new JButton("Requete 4");
		bouton4.addActionListener(this);
		bouton5 = new JButton("Requete 5");
		bouton5.addActionListener(this);
		bouton6 = new JButton("Requete 6");
		bouton6.addActionListener(this);
		bouton7 = new JButton("Requete 7");
		bouton7.addActionListener(this);
		bouton8 = new JButton("Requete 8");
		bouton8.addActionListener(this);
		boutonQuitter = new JButton("Quitter");
		boutonQuitter.addActionListener(this);


		//Initialisation des boutons
		panelBouton = new JPanel(new GridLayout(9,1));
		panelBouton.add(bouton1);
		panelBouton.add(bouton2);
		panelBouton.add(bouton3);
		panelBouton.add(bouton4);
		panelBouton.add(bouton4);
		panelBouton.add(bouton5);
		panelBouton.add(bouton6);
		panelBouton.add(bouton7);
		panelBouton.add(bouton8);
		panelBouton.add(boutonQuitter);

		//panel affichage
		panelAffichage = new JPanel(new BorderLayout());
		labelhaut = new JLabel("Bienvenue dans l'application !");
		labelmilieu = new JLabel();
		panelAffichage.add(labelhaut,BorderLayout.NORTH);
		panelAffichage.add(labelmilieu,BorderLayout.CENTER);

		//Frame de départ
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(100,100));
		this.pack();
		this.setVisible(true);
		this.add(panelBouton, BorderLayout.CENTER);
		this.add(panelAffichage, BorderLayout.NORTH);
	}


	//méthode pour changer les panels
	private void changePanelAffichage(JPanel panel)
	{
		this.panelAffichage=panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String url="jdbc:oracle:thin:skui/sS26121995@oracle.iut-orsay.fr:1521:etudom";
		Connection co=OutilsJDBC.openConnection(url);
		//Cas du bouton quitter
		if (e.getActionCommand()=="Quitter")
		{
			vueDepart vue;
			vue = new vueDepart();
			//Fermeture de la connexion de la base de données
			try {
				co.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		}

		//_________________PROBLEME A CE NIVEAU___________________
		//Cas de la requete 1
		if (e.getActionCommand() =="Requete 1")
		{
			try {
				this.labelhaut.setText(titreRequete[0]);
				String sql = "{? = call nbEtudiantAvecStage}";
				System.out.println(sql);
				CallableStatement call = co.prepareCall(sql);
				call.registerOutParameter(1, java.sql.Types.INTEGER);
				call.execute();
				//récupération des ResultSet 
				ResultSet resultat1 = call.getResultSet(); 
				System.out.println(resultat1.getInt(1));
				}
			
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}

		//Cas de la requete 2
		if (e.getActionCommand() =="Requete 2")
		{
			this.labelhaut.setText(titreRequete[1]);
			
			String sql = "{?=call nbEtudiantSansStage()}";
			try {
				CallableStatement call = co.prepareCall(sql);
				call.registerOutParameter(1, java.sql.Types.INTEGER);
				if(call.execute()){ 
					//récupération des ResultSet 
					ResultSet resultat1 = call.getResultSet();
					while(resultat1.next()){ 
						for(int i=0;i<resultat1.getMetaData().getColumnCount();i++){ 
							System.out.print(resultat1.getObject(i+1)+", "); 
							System.out.println(resultat1.getInt(2));
						} 
					}
				}
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

		//Cas de la requete 3
		if (e.getActionCommand() =="Requete 3")
		{
			this.labelhaut.setText(titreRequete[2]);
			
			String sql = "{call nbEtudiantSansStageAnnee(?)}";
			try {
				CallableStatement call = co.prepareCall(sql);
				call.setObject(1,5);
				if(call.execute()){ 
					//récupération des ResultSet 
					ResultSet resultat1 = call.getResultSet();
					while(resultat1.next()){ 
						for(int i=0;i<resultat1.getMetaData().getColumnCount();i++){ 
							System.out.print(resultat1.getObject(i+1)+", "); 
						} 
					}
				}
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		//Cas de la requete 4
		if (e.getActionCommand() =="Requete 4")
		{
			this.labelhaut.setText(titreRequete[3]);
			
			String sql = "{call nbStagiaireParEntreprise(?,?)}";
			
			try {
				CallableStatement call = co.prepareCall(sql);
				call.setObject(1,5);
				if(call.execute()){ 
					//récupération des ResultSet 
					ResultSet resultat1 = call.getResultSet();
					while(resultat1.next()){ 
						for(int i=0;i<resultat1.getMetaData().getColumnCount();i++){ 
							System.out.print(resultat1.getObject(i+1)+", "); 
						} 
					}
				}
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		//Cas de la requete 5
		if (e.getActionCommand() =="Requete 5")
		{
			this.labelhaut.setText(titreRequete[4]);
			
			String sql = "{call nbMoyenEtudiantsDansEntreprise(?)}";
			try {
				CallableStatement call = co.prepareCall(sql);
				call.setObject(1,5);
				if(call.execute()){ 
					//récupération des ResultSet 
					ResultSet resultat1 = call.getResultSet();
					while(resultat1.next()){ 
						for(int i=0;i<resultat1.getMetaData().getColumnCount();i++){ 
							System.out.print(resultat1.getObject(i+1)+", "); 
						} 
					}
				}
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		//Cas de la requete 6
		if (e.getActionCommand() =="Requete 6")
		{
			this.labelhaut.setText(titreRequete[5]);
			
			String sql = "{call nbStagesParZone(?,?)}";
			try {
				CallableStatement call = co.prepareCall(sql);
				call.setObject(1,"Orsay");
				call.setObject(2,"91200");
				if(call.execute()){ 
					//récupération des ResultSet 
					ResultSet resultat1 = call.getResultSet();
					while(resultat1.next()){ 
						for(int i=0;i<resultat1.getMetaData().getColumnCount();i++){ 
							System.out.print(resultat1.getObject(i+1)+", "); 
						} 
					}
				}
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		//Cas de la requete 7
		if (e.getActionCommand() =="Requete 7")
		{
			this.labelhaut.setText(titreRequete[6]);
			
			String sql = "{call nbStagiaireZone()}";
			try {
				CallableStatement call = co.prepareCall(sql);
				call.registerOutParameter("nb", java.sql.Types.INTEGER);
				if(call.execute()){ 
					//récupération des ResultSet 
					ResultSet resultat1 = call.getResultSet();
					while(resultat1.next()){ 
						for(int i=0;i<resultat1.getMetaData().getColumnCount();i++){ 
							System.out.print(resultat1.getObject(i+1)+", "); 
						} 
					}
				}
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		//Cas de la requete 8
		if (e.getActionCommand() =="Requete 8")
		{
			this.labelhaut.setText(titreRequete[7]);
			
			String sql = "{call contactEntreprise(?)}";
			try {
				CallableStatement call = co.prepareCall(sql);
				call.setObject(1,5);
				if(call.execute()){ 
					//récupération des ResultSet 
					ResultSet resultat1 = call.getResultSet();
					while(resultat1.next()){ 
						for(int i=0;i<resultat1.getMetaData().getColumnCount();i++){ 
							System.out.print(resultat1.getObject(i+1)+", "); 
						} 
					}
				}
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}



}
