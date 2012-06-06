package cleancodematters.datagriddemo.client;

import cleancodematters.datagriddemo.server.domain.Ingredient;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;


@ProxyFor(value = Ingredient.class)
public interface IngredientProxy extends ValueProxy {
  public String getName();
  public void setName( String name );
  
  public boolean isVegan();
  public void setVegan( boolean vegan );
}
