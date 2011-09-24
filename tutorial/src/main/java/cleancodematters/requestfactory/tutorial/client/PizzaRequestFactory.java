package cleancodematters.requestfactory.tutorial.client;

import cleancodematters.requestfactory.tutorial.server.PizzaDao;
import cleancodematters.requestfactory.tutorial.server.internal.DaoLocator;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

public interface PizzaRequestFactory extends RequestFactory {

  @Service(value = PizzaDao.class, locator = DaoLocator.class)
  public interface PizzaRequestContext extends RequestContext {
    Request<PizzaProxy> findById( Long id );
    Request<Void> save( PizzaProxy pizza );
  }
  
  PizzaRequestContext context();
}
