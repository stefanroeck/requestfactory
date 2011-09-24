package cleancodematters.requestfactory.tutorial.server.internal;

import java.util.List;

import cleancodematters.requestfactory.tutorial.server.PizzaDao;
import cleancodematters.requestfactory.tutorial.server.domain.Ingredient;
import cleancodematters.requestfactory.tutorial.server.domain.Pizza;

public class PizzaDaoImpl implements PizzaDao {

  @Override
  public Pizza findById( Long id ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void save( Pizza pizza ) {
    checkPizzaAttributes( pizza );
    checkIngredientAttributes( pizza.getIngredients() );
  }

  private static void checkPizzaAttributes( Pizza pizza ) {
    if( isEmpty( pizza.getName() ) ) {
      throw new IllegalArgumentException( "pizza name must not be null" );
    }
  }

  private static void checkIngredientAttributes( List<Ingredient> ingredients ) {
    if (ingredients.size() == 0) {
      throw new IllegalArgumentException( "pizza cannot be empty" );
    }
    for (Ingredient ingredient : ingredients) {
      if (isEmpty( ingredient.getName() )) {
        throw new IllegalArgumentException( "ingredients should have a name, too" );
      }
    }
  }

  private static boolean isEmpty( String name ) {
    return name == null || name.length() == 0;
  }

}
