/**
 * 
 */
package cm.itac.formation.ecommerce.Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author dongmo
 *
 */
public class MySqlConnection {


		// URL de connexion
		private String url = "jdbc:mysql://localhost:3306/bd_ecommerce";
		// Nom du user
		private String user = "root";
		// Mot de passe de l'utilisateur
		private String passwd = "root";
		// Objet Connection
		private static Connection connect;
		private static MySqlConnection instance = new MySqlConnection();

		// Constructeur privé
		private MySqlConnection() {

			//Preparation au chargement du Driver
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				affiche("probleme avec le Driver");
				arret(e1.getMessage());
			}
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Méthode d'accès au singleton
		public static Connection getInstance() {
			if (connect == null)
				instance = new MySqlConnection();

			return connect;
		}
			public static void affiche(String msg){
				System.out.println(msg);
			}
			
			public static void arret(String msg){
				System.err.println(msg);
				System.exit(0);
			}
}
