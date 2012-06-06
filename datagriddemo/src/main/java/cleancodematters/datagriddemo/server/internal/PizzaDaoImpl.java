package cleancodematters.datagriddemo.server.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import cleancodematters.datagriddemo.server.Page;
import cleancodematters.datagriddemo.server.PizzaDao;
import cleancodematters.datagriddemo.server.domain.Ingredient;
import cleancodematters.datagriddemo.server.domain.Pizza;
import cleancodematters.datagriddemo.shared.ColumnIdentifier;


public class PizzaDaoImpl implements PizzaDao {

  private static final Random RANDOM = new Random();
  private static final String[] NAMES = { "Funghi", "Hawai", "Margharita", "Tonno", "4 staggioni",
      "Vegetarian" };
  private static final String[] INGREDIENTS = { "cheese", "onion", "extra cheese", "tomatoe",
      "fish", "ananas", "banana" };
  
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

  @Override
  public List<Pizza> findAll( Page page, ColumnIdentifier sortKey, boolean sortIsAscending ) {
    List<Pizza> result = generateSampleData( page );
    
    return sort( result, sortKey, sortIsAscending );
  }

  private List<Pizza> generateSampleData( Page page ) {
    List<Pizza> result = new ArrayList<Pizza>();
    for( int i = page.getFirst(); i < page.getFirst() + page.getCount(); i++) {
      String name = NAMES[ i % NAMES.length ];
      result.add( createPizza( i, name ) );

    }
    return result;
  }

  private Pizza createPizza( int id, String name ) {
    Pizza result = new Pizza();
    result.setId( Long.valueOf( id ) );
    result.setName( name );
    addIngredients( result );
    return result;
  }

  private void addIngredients( Pizza result ) {
    int maxCount = INGREDIENTS.length;
    int ingredientCount = RANDOM.nextInt( maxCount - 1 ) + 1;
    for (int i=0; i<ingredientCount; i++) {
      Ingredient ingredient = new Ingredient();
      ingredient.setName( INGREDIENTS[RANDOM.nextInt( maxCount )] );
      result.getIngredients().add( ingredient );
    }
  }

  private List<Pizza> sort( List<Pizza> result, final ColumnIdentifier sortKey, final boolean sortIsAscending ) {
    Collections.sort( result, new Comparator<Pizza>() {
      @Override
      public int compare( Pizza arg0, Pizza arg1 ) {
        int compareResult = 0;
        if( ColumnIdentifier.ID.equals( sortKey ) ) {
          compareResult = arg0.getId().compareTo( arg1.getId() );
        } else if( ColumnIdentifier.NAME.equals( sortKey ) ) {
          compareResult = arg0.getName().compareTo( arg1.getName() );
        }
        if( !sortIsAscending ) {
          compareResult *= -1;
        }
        return compareResult;
      }
    } );
    return result;
  }

}
