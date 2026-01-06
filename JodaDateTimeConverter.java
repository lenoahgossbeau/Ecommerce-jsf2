/**
 * 
 */
package cm.itac.formation.ecommerce.Tools;


import java.sql.Timestamp;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.DateTime;
/**
 * @author erico
 *
 */
public class JodaDateTimeConverter implements Converter {

    private static final long serialVersionUID = 1L;

    @Override
    public Object convertDataValueToObjectValue( Object dataValue, Session session ) {
    	if(!(dataValue instanceof DateTime))
        return dataValue == null ? null : new DateTime( (Timestamp) dataValue );
    	return dataValue;
    }

    @Override
    public Object convertObjectValueToDataValue( Object objectValue, Session session ) {
        return objectValue == null ? null : new Timestamp( ( (DateTime) objectValue ).getMillis() );
    }

    @Override
    public void initialize( DatabaseMapping mapping, Session session ) {
    }

    @Override
    public boolean isMutable() {
        return false;
    }

}
