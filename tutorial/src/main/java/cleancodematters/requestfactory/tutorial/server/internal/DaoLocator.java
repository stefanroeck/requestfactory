package cleancodematters.requestfactory.tutorial.server.internal;


import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class DaoLocator implements ServiceLocator {

  @Override
  public Object getInstance( Class<?> clazz ) {
    // Do service look-up here, e.g. using Spring WebApplication Context
    return new PizzaDaoImpl();
  }

}
