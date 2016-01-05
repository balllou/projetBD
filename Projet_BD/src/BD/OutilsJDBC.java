package BD;

import java.sql.*;

/**
 * <b>OutilsJDBC est la classe qui permet de cr�er la connexion avec la bd</b>
 * <p>
 * On peut cr�er une connexion
 * On peut �xecuter un requ�te
 * On peut fermer une connexion
 * </p> 
 * @author BOULME KUI ET DOLSA
 * @version 1.0
 */
public class OutilsJDBC {
	
	
	//_____________________LES METHODES________________________
	
	/**
	    *m�thode openConnection(String url) 
	    *@return Connection
	    *@param  String url
	    *	On prend le param�tre string pour pouvoir cr�er notre connexion
	    */
	public static Connection openConnection (String url) {
		Connection co=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			co= DriverManager.getConnection(url);
		}
		catch (ClassNotFoundException e){
			System.out.println("il manque le driver oracle");
			System.exit(1);
		}
		catch (SQLException e) {
			System.out.println("impossible de se connecter � l'url : "+url);
			System.exit(1);
		}
		return co;
		}
	
	
	/**
	    *m�thode exec1Requete(String requete, Connection co, int type)
	    *@return Resultset
	    *@param  String requete
	    *	On prend le param�tre string pour pouvoir cr�er le statement
	    *@param Connection co
	    *	Le param�tre connexion permet de cr�er le statement
	    *@param int type
	    *	Permet de choisir quel type de statement on cr�er
	    */
	public static ResultSet exec1Requete (String requete, Connection co, int type){
		ResultSet res=null;
		try {
			Statement st;
			if (type==0){
				st=co.createStatement();}
			else {
				st=co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					       	ResultSet.CONCUR_READ_ONLY);
				};
			res= st.executeQuery(requete);
		}
		catch (SQLException e){
			System.out.println("Probl�me lors de l'ex�cution de la requete : "+requete);
		};
		return res;
	}

	
	/**
	    *m�thode closeConnection(String url) 
	    *@return Connection
	    *@param  String url
	    *	On prend le param�tre string pour pouvoir cr�er notre connexion
	    */
	public static void closeConnection(Connection co){
		try {
			co.close();
			System.out.println("Connexion ferm�e!");
		}
		catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}	
}
	public static void main(String[] args){};
}