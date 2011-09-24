package cleancodematters.requestfactory.tutorial.server.internal;

import cleancodematters.requestfactory.tutorial.server.PizzaDao;
import cleancodematters.requestfactory.tutorial.server.domain.Pizza;

import com.google.web.bindery.requestfactory.shared.Locator;

public class PizzaLocator extends Locator<Pizza, Long>{

  @Override
  public Pizza create( Class<? extends Pizza> clazz ) {
    return new Pizza();
  }

  @Override
  public Pizza find( Class<? extends Pizza> clazz, Long id ) {
    PizzaDao dao = new PizzaDaoImpl();
    return dao.findById( id );
  }


  @Override
  public Class<Pizza> getDomainType() {
    return Pizza.class;
  }

  @Override
  public Long getId( Pizza domainObject ) {
    return domainObject.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }

  @Override
  public Object getVersion( Pizza domainObject ) {
    return domainObject.getVersion();
  }
}
