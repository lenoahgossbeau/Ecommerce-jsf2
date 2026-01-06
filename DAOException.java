/**
 * 
 */
package cm.itac.formation.ecommerce.dao;

/**
 * @author dongmo
 *
 */
public class DAOException extends RuntimeException {
    /*
     * Constructeurs
     */
    public DAOException( String message ) {
        super( message );
    }

    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOException( Throwable cause ) {
        super( cause );
    }
}