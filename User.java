package cm.itac.formation.ecommerce.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class User implements Serializable{
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue( strategy = GenerationType.IDENTITY)
	    private Long id;
	    @NotNull
	    private String email;
	    @Column(name = "mot_de_passe")
	    @NotNull
	    private String motDePasse;
	    @NotNull
	    private String nom;
	    @ManyToOne
		@JoinColumn(name = "idProfile")
	    private Profile profile;
	    @ManyToOne
		@JoinColumn(name = "idUserAction")
	    private User_Action user_Action;
	    @Column(name = "date_inscription")
	    private Timestamp dateInscription;
	    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	    @JoinColumn(name = "idUser")
	    private List<Buy> buys; 

	public User() {
		// TODO Auto-generated constructor stub
		profile = new Profile();
		user_Action = new User_Action();
		
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public User_Action getUser_Action() {
		return user_Action;
	}

	public void setUser_Action(User_Action user_Action) {
		this.user_Action = user_Action;
	}

	/**
	 * @return the dateInscription
	 */
	public Timestamp getDateInscription() {
		return dateInscription;
	}

	/**
	 * @param dateInscription the dateInscription to set
	 */
	public void setDateInscription(Timestamp dateInscription) {
		this.dateInscription = dateInscription;
	}

}
