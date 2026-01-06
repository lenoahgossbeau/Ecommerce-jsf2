/**
 * 
 */
package cm.itac.formation.ecommerce.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.joda.time.DateTime;

import cm.itac.formation.ecommerce.Tools.MySqlConnection;
import cm.itac.formation.ecommerce.dao.entity.Company;
import cm.itac.formation.ecommerce.dao.entity.CompanyGroup;

/**
 * @author admin
 *
 */
public class ItacManager {

	/**
	 * 
	 */
	 private  static java.util.Properties p;

	 private static final  String DEFAULT_PROPERTIES_FILE = "C://home//application.properties";

	 
	 
	 
	public static void main (String [] argvs) throws IOException{
        
		
		 p = new java.util.Properties();
		 //ClassLoader loader = Thread.currentThread().getContextClassLoader();
		// InputStream inputStream = loader.getResourceAsStream(DEFAULT_PROPERTIES_FILE);
		 InputStream inputStream = new FileInputStream(DEFAULT_PROPERTIES_FILE);
		 p.load(inputStream);
		
		
		System.out.println("Aggiunta del nuovo provider IAIK");
			
		Security.addProvider(new BouncyCastleProvider());

		System.out.println("Aggiunta riuscita.");

		// -------- creazione generatore di chiavi------------------------

		System.out.println("Creazione del generatore di chiavi");

		KeyGenerator gen = null;
		try {
		  gen = KeyGenerator.getInstance("DES");
		}
		catch (NoSuchAlgorithmException e1) {
		  System.out.println("Algoritmo non supportato in KeyGenerator");
		  System.exit(1);
		}
		
		System.out.println("Generatore di chiavi creato.");

		// -------- generazione chiave ------------------------------------

		System.out.println("Generazione della chiave segreta");

		gen.init(new SecureRandom());
		Key k = gen.generateKey();

		System.out.println("Chiave generata: ");
		for (int i=0; i<k.getEncoded().length; i++)
			System.out.print("" + k.getEncoded()[i] + "\t");
		System.out.println("");

		// -------- cifratura  ------------------------------------

		byte[] data = {10,20,30,40,50,60,70,80}, 
		       xxx = null, newdata = null;

		System.out.println("Messaggio in chiaro: ");
		for (int i=0; i<data.length; i++)
			System.out.print("" + data[i] + "\t");
		System.out.println("cvvv");
		System.out.println("Chiave in chiaro dal file  " + get("appManager.key"));

		// -------- creazione Cifratore -----------------

		System.out.println("Generazione del Cipher per l'algoritmo prescelto");

		Cipher des =  null;
		try {

		  des = Cipher.getInstance("DES");

		  // ----- inizializzazione per cifratura --------

		  des.init(Cipher.ENCRYPT_MODE, k);
		  System.out.println("Cipher inizializzato per cifratura");

		  // -------- cifratura -------------------

		  xxx = des.doFinal(data);

		  System.out.println("Messaggio cifrato: ");
		  for (int i=0; i<xxx.length; i++)
			System.out.print("" + xxx[i] + "\t");
		  System.out.println("");

		  // ----- inizializzazione per decifratura --------
		  
		  
		  Scanner scanner = new Scanner(System.in);
		  System.out.println("Priere d'introduire votre clé de licence, Merci");
		  String chiave = scanner.nextLine();
		  xxx = chiave.getBytes();
		  

		  des.init(Cipher.DECRYPT_MODE, k);
		  System.out.println("Cipher inizializzato per decifratura");

		  // -------- decifratura ----------------

		  newdata = des.doFinal(xxx);

		  System.out.println("Messaggio decifrato: ");
		  for (int i=0; i<newdata.length; i++)
			System.out.print("" + newdata[i] + "\t");
		  System.out.println("");

		  // -------- verifica --------------------

		  boolean res = true;
		  if (data.length != newdata.length) res = false;
		  else
		  for (int j=0; j<data.length; j++)
			if (data[j] != newdata[j]) { res = false; break; }

		  System.out.println("Esito della verifica: " + res);

		}
		
		catch (NoSuchAlgorithmException e2) {
		  System.out.println("Algoritmo non supportato");
		  System.exit(2);
		}
		catch (NoSuchPaddingException e2) {
		  System.out.println("Tipo di padding non supportato");
		  System.exit(2);
		}
		catch (BadPaddingException e3) {
	          System.out.println("Bad Padding");
	          System.exit(3);
		}
		catch (InvalidKeyException e3) {
		  System.out.println("Chiave non valida");
		  System.exit(3);
		}
		catch (IllegalBlockSizeException ex) {
	          System.out.println("Dimensione del blocco illegale");
	          System.exit(3);
	    	}

		
		
		
		Connection connection = MySqlConnection.getInstance();
		Statement statement = null;
		ResultSet resultSet = null;
		// Preparation à l'initialisation du Statement
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MySqlConnection.affiche("probleme avec le Statement");
			MySqlConnection.arret(e.getMessage());
		}
		MySqlConnection.affiche("Statement OK");
		
		
		
		String sql = "SELECT * FROM company WHERE name = '"+ get("appManager.company")+"'";
		try {
			 resultSet = statement.executeQuery(sql);
			boolean encore = resultSet.next();
			MySqlConnection.affiche("Données RecuperéEs avec Succès: Listes des Amis");
			if (!encore) {
				MySqlConnection.affiche("Données RecuperéEs avec Succès: Liste vide");
			}


				// transfert des données de la table alla ligne isimale sur mon
				// model AMI
			Company company = new Company(); 
			if(encore){
				 company = new Company(resultSet.getLong(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
						(CompanyGroup) resultSet.getObject(6),new DateTime(resultSet.getDate(7)),new DateTime(resultSet.getDate(8)));
			
			
				MySqlConnection.affiche(company.toString());
				
				DateTime dateTime = company.getExpired_date();
				if(!dateTime.isAfterNow()){
				MySqlConnection.affiche("Licence Expirée");
				System.exit(0);
				} else{
					MySqlConnection.affiche("Licence Validée");	
				}
			 }
			resultSet.close();
			MySqlConnection.affiche("resultSet OK: Données recuperées");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			MySqlConnection.affiche("probleme avec le executeQuery");
			MySqlConnection.arret(e.getMessage());
		}
		
		finally{
			try {
				if(!resultSet.isClosed())
					resultSet.close();
				if(!connection.isClosed())
				 connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	   }
	
	    public ItacManager() throws Exception {
	        p = new java.util.Properties();
	        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE));
	    }

	    public ItacManager(String propertiesFile) throws Exception {
	        p = new java.util.Properties();
	        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile));
	    }

	    public static java.lang.String get(String key) {
	        String toret = "";
	        toret = p.getProperty(key);
	        return toret;
	   
}
}