package cleancodematters.datagriddemo.client;

import java.util.List;

import cleancodematters.datagriddemo.server.PizzaDao;
import cleancodematters.datagriddemo.server.internal.DaoLocator;
import cleancodematters.datagriddemo.shared.ColumnIdentifier;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;


public interface PizzaRequestFactory extends RequestFactory {

  @Service(value = PizzaDao.class, locator = DaoLocator.class)
  public interface PizzaRequestContext extends RequestContext {
    Request<PizzaProxy> findById( Long id );
    Request<Void> save( PizzaProxy pizza );

    Request<List<PizzaProxy>> findAll( PageProxy page, ColumnIdentifier sortKey, boolean sortIsAscending );
  }
  
  PizzaRequestContext context();
}
