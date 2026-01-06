/**
 * 
 */
package cm.itac.formation.ecommerce.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import cm.itac.formation.ecommerce.dao.ArticleDao;
import cm.itac.formation.ecommerce.dao.FournisseurDao;
import cm.itac.formation.ecommerce.dao.Type_ArticleDao;
import cm.itac.formation.ecommerce.dao.entity.Article;
import cm.itac.formation.ecommerce.dao.entity.Fournisseur;
import cm.itac.formation.ecommerce.dao.entity.Type_Article;


/**
 * @author erico
 *
 */
@RequestScoped 
@ManagedBean
public class ArticlesBean implements Serializable{
	 private static final long serialVersionUID = 1L;

	    private Article        article;
	    private Fournisseur    fournisseur;
	    private Type_Article     type_Article;
	    
	    private boolean selection;
	    
	    private String codeType_Article;
	    private String codeFournisseur;
	   
	    private String idArticle;
	    private String code;
		private String nom;
		private  String description;
		private String prix;		
		private String disponible;		
		private String nom1Fournisseur;
		private String typeType_Article;
		private String prixAchat;
		private String quantity;
		
	    
	    
	    
	     
	    Map<String, Fournisseur> fournisseurs;
	    Map<String, Type_Article> type_Articles;
	   
	    List<Article> articles;
	    private List<Fournisseur>   list;
	    private List<Article> filteredArticleList;
	    private List<Type_Article>     list1;
	    
	    @PostConstruct
	    public void init() {
	    	
	        list = new ArrayList<Fournisseur>();
            
	        list1 = new ArrayList<Type_Article>();   
	    }	   
	    
	    public static final String SESSION_FOURNISSEURS   = "fournisseurs";
	    public static final String SESSION_TYPE_ARTICLES   = "type_Articles";
	   
	    public static final String SESSION_ARTICLES = "articles";
	    public static final String REQUEST_FOURNISSEUR = "fournisseurBean";
	    public static final String REQUEST_TYPE_ARTICLE = "type_ArticleBean";
	    public static final String REQUEST_ARTICLE = "articlesBean";
	    public static final String REQUEST_ARTICLE_LIST = "articleListBean";
		  
	    // Injection de notre EJB (Session Bean Stateless)
	    @EJB
	    private ArticleDao    articleDao;
	 
       @EJB
	    private FournisseurDao     fournisseurDao;
       
       @EJB
	    private Type_ArticleDao     type_ArticleDao;
	    // Initialisation de l'entité article     
	public ArticlesBean() {
		 article = new Article();
		 
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		 fournisseur = (Fournisseur) request.getSession().getAttribute(SESSION_FOURNISSEURS);
//		 type_Article = (Type_Article) request.getSession().getAttribute(SESSION_TYPE_ARTICLES);
		if( fournisseur == null)
				 fournisseur = new Fournisseur();
		ArticleListBean articleListBean = (ArticleListBean) request.getAttribute(REQUEST_ARTICLE_LIST);
		if(articleListBean != null){
			 article  = articleListBean.getArticle();
			 request.getSession().setAttribute("article", article);
			 }
		
		
		
		
		nom = article.getNom();
		description = article.getDescription();
		disponible = String.valueOf(article.getDisponible());
		idArticle = String.valueOf(article.getId());
		code = article.getCode();
		prix = String.valueOf(article.getPrix());
		prixAchat = String.valueOf(article.getPrixAchat());
		quantity = String.valueOf(article.getQuantite());
		
		nom1Fournisseur = article.getFournisseur().getNom1();
		//nom1Fournisseur = article.getNom1Fournisseur();
		System.out.println("nom1 "+ nom1Fournisseur);
		typeType_Article = article.getType_Article().getType();
		
		System.out.println("type "+ typeType_Article);
		
				
		
	}
		
	
	/**
	 * @return the fournisseurs
	 */
	
	public Map<String, Fournisseur> getFournisseurs() {
		return (Map<String, Fournisseur>) fournisseurs;
	}
	public Map<String, Type_Article> getTypeArticles() {
		return (Map<String, Type_Article>) type_Articles;
	}
	
	/**
	 * @return the articles
	 */
	public List<Article> getArticles() {
		return articles;
	}
	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	/**
	 * @param fournisseurs the fournisseurs to set
	 */
	public void setFournisseurs(Map<String, Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}
	public void setType_Articles(Map<String, Type_Article> type_Article) {
		this.type_Article = (Type_Article) type_Article;
	}
	
	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}
	
	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		
		this.article = article;
	}
	// Méthode d'action appelée lors du clic sur le bouton du formulaire
    // d'inscription
    public String creerArticle() {
        initialiserDateArticle();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 FournisseurBean fournisseurBean = (FournisseurBean) request.getAttribute(REQUEST_FOURNISSEUR)   ;
		 Type_ArticleBean type_ArticleBean = (Type_ArticleBean) request.getAttribute(REQUEST_TYPE_ARTICLE)   ;
		 ArticlesBean articlesBean = ( ArticlesBean) request.getAttribute(REQUEST_ARTICLE)   ;

        
            
       String codeSelected = codeFournisseur;
       
       if (article.getPrixAchat() == null ||article.getPrixAchat().compareTo(article.getPrix())>0) {
    	   FacesMessage message = new FacesMessage( "Echec de la création d'une article, le prix de vente ne peut etre inferieur au prix d'achat !" );
    	     
           FacesContext.getCurrentInstance().addMessage( null, message );
    	   
    	   return "newArticle"; 
       }
       if (article.getDisponible() <= 0) {
    	   FacesMessage message = new FacesMessage( "Echec de la création d'une article, la disponibilité doit etre de au moins 1 article !" );
    	     
           FacesContext.getCurrentInstance().addMessage( null, message );
    	   
    	   return "newArticle"; 
       }
       
       Type_Article type_Article = new Type_Article();
       type_Article.setId(Long.valueOf(codeType_Article));
       article.setType_Article(type_Article);
       
       Fournisseur fournisseur = new Fournisseur();
  		fournisseur.setId(Long.valueOf(codeFournisseur));
  		article.setFournisseur(fournisseur);
  		
       		
       		article = articleDao.creer(articlesBean.getArticle());
            
        FacesMessage message = new FacesMessage( "Succès de la création d'une article !" );
     
       FacesContext.getCurrentInstance().addMessage( null, message );
       
       ArticleListBean articleListBean = (ArticleListBean) request.getSession().getAttribute(REQUEST_ARTICLE_LIST)   ; 
       if(articleListBean != null)
       articleListBean.setReload(true);;
       return "articleOverview" ;
    }  
   
    public String modifier() {
    	initialiserDateArticle();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ArticleListBean articleListBean = (ArticleListBean) request.getAttribute(REQUEST_ARTICLE_LIST);
        
        
        if(articleListBean != null){
            article  = articleListBean.getArticle();
            request.getSession().setAttribute("article", article);
           
            } else if(request.getSession().getAttribute("article") != null) {
            	Article article1 = 	(Article) request.getSession().getAttribute("article");
            	article.setId(article1.getId());
            	
            }
        FournisseurBean fournisseurBean = (FournisseurBean) request.getAttribute(REQUEST_FOURNISSEUR)   ;
        Type_ArticleBean type_ArticleBean = (Type_ArticleBean) request.getAttribute(REQUEST_TYPE_ARTICLE)   ;

        if (article.getPrixAchat().compareTo(article.getPrix())>0) {
     	   FacesMessage message = new FacesMessage( "Echec de la création de la modification !" );
     	     
            FacesContext.getCurrentInstance().addMessage( null, message );
     	   
     	   return "articleEdit"; 
        }

        articleDao.modifier( article );
        FacesMessage message = new FacesMessage( "Succès de la modification !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
		return "articleEdit";
    }
   
    


    public void setSelection(boolean valeur) {

    selection = valeur;

    }
    public boolean getSelection() {

    return selection;
    }
    private void initialiserDateArticle() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );   
       
    }
	/**
	 * @return the fournisseur
	 */
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	/**
	 * @param fournisseur the fournisseur to set
	 */
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	/**
	 * @return the typeArticle
	 */
	public Type_Article getType_Article() {
		return type_Article;
	}
	/**
	 * @param typeArticle the typeArticle to set
	 */
	public void setType_Article(Type_Article type_Article) {
		this.type_Article = type_Article;
	}
	
	/**
	 * @return the type_Articles
	 */
	public Map<String, Type_Article> getType_Articles() {
		return type_Articles;
	}
	
	
	
	/**
	 * @return the idArticle
	 */
	public String getIdArticle() {
		return idArticle;
	}

	/**
	 * @param idArticle the idArticle to set
	 */
	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the prix
	 */
	public String getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(String prix) {
		this.prix = prix;
	}

	/**
	 * @return the disponible
	 */
	public String getDisponible() {
		return disponible;
	}

	/**
	 * @param disponible the disponible to set
	 */
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	/**
	 * @return the nom1Fournisseur
	 */
	public String getNom1Fournisseur() {
		return nom1Fournisseur;
	}

	/**
	 * @param nom1Fournisseur the nom1Fournisseur to set
	 */
	public void setNom1Fournisseur(String nom1Fournisseur) {
		this.nom1Fournisseur = nom1Fournisseur;
	}

	/**
	 * @return the typeType_Article
	 */
	public String getTypeType_Article() {
		return typeType_Article;
	}

	/**
	 * @param typeType_Article the typeType_Article to set
	 */
	public void setTypeType_Article(String typeType_Article) {
		this.typeType_Article = typeType_Article;
	}

	/**
	 * @return the list
	 */
	public List<Fournisseur> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Fournisseur> list) {
		this.list = list;
	}
	/**
	 * @return the filteredArticleList
	 */
	public List<Article> getFilteredArticleList() {
		return filteredArticleList;
	}
	/**
	 * @param filteredArticleList the filteredArticleList to set
	 */
	public void setFilteredArticleList(List<Article> filteredArticleList) {
		this.filteredArticleList = filteredArticleList;
	}
	/**
	 * @return the list1
	 */
	public List<Type_Article> getList1() {
		return list1;
	}
	/**
	 * @param list1 the list1 to set
	 */
	public void setList1(List<Type_Article> list1) {
		this.list1 = list1;
	}

	/**
	 * @return the codeType_article
	 */
	public String getCodeType_Article() {
		return codeType_Article;
	}

	/**
	 * @param codeType_article the codeType_article to set
	 */
	public void setCodeType_Article(String codeType_Article) {
		this.codeType_Article = codeType_Article;
	}

	/**
	 * @return the codeFournisseur
	 */
	public String getCodeFournisseur() {
		return codeFournisseur;
	}

	/**
	 * @param codeFournisseur the codeFournisseur to set
	 */
	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
	}

	/**
	 * @return the prixAchat
	 */
	public String getPrixAchat() {
		return prixAchat;
	}

	/**
	 * @param prixAchat the prixAchat to set
	 */
	public void setPrixAchat(String prixAchat) {
		this.prixAchat = prixAchat;
	}


	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}


	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
	
    
    

}
