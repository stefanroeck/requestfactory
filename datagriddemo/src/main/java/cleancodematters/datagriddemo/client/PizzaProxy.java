package cleancodematters.datagriddemo.client;

import java.util.List;

import cleancodematters.datagriddemo.server.domain.Pizza;
import cleancodematters.datagriddemo.server.internal.PizzaLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;


@ProxyFor(value = Pizza.class, locator = PizzaLocator.class)
public interface PizzaProxy extends EntityProxy {
  public Long getId();
  
  public String getName();
  public void setName( String name );
  
  public List<IngredientProxy> getIngredients();
  public void setIngredients( List<IngredientProxy> ingredients );
}
