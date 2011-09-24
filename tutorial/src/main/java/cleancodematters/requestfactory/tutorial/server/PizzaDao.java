package cleancodematters.requestfactory.tutorial.server;

import cleancodematters.requestfactory.tutorial.server.domain.Pizza;

public interface PizzaDao {
  Pizza findById( Long id );
  void save( Pizza pizza );
}
