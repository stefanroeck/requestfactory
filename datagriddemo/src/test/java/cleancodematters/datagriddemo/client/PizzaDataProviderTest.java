package cleancodematters.datagriddemo.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import cleancodematters.datagriddemo.client.PizzaDataProvider;
import cleancodematters.datagriddemo.client.PizzaProxy;
import cleancodematters.datagriddemo.client.PizzaRequestFactory;
import cleancodematters.datagriddemo.server.Page;
import cleancodematters.datagriddemo.server.PizzaDao;
import cleancodematters.datagriddemo.server.domain.Pizza;
import cleancodematters.datagriddemo.shared.ColumnIdentifier;
import cleancodematters.requestfactory.testutils.RequestFactoryHelper;

import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class PizzaDataProviderTest {

  private PizzaDataProvider provider;
  private PizzaDao daoMock;

  @Before
  public void setup() {
    PizzaRequestFactory factory = RequestFactoryHelper.create( PizzaRequestFactory.class );
    daoMock = RequestFactoryHelper.getService( PizzaDao.class );
    provider = new PizzaDataProvider( factory );
  }
  
  @Test
  public void testRangeChanged() {
    String expectedName = "Funghi";
    int start = 0;
    int length = 50;
    Pizza expectedPizza = createPizza( expectedName );
    when( daoMock.findAll( new Page( start, length ), ColumnIdentifier.ID, true ) ).thenReturn( Arrays.asList( expectedPizza ) );
    HasData<PizzaProxy> display = createDisplayWithRange( start, length );
    
    provider.onRangeChanged( display );
    
    // TODO: ask Angelika Langer why this cast is needed.
    ArgumentCaptor<List<PizzaProxy>> captor = (ArgumentCaptor)ArgumentCaptor.forClass( List.class );
    verify( display ).setRowData( eq( start ), captor.capture() );
    List<PizzaProxy> returnedList = captor.getValue();
    assertEquals( 1, returnedList.size() );
    assertEquals( expectedName, returnedList.get( 0 ).getName() );
    assertNotNull( returnedList.get( 0 ).getIngredients() );
  }

  private static HasData<PizzaProxy> createDisplayWithRange( int start, int length ) {
    HasData<PizzaProxy> display = mock( HasData.class );
    when( display.getVisibleRange() ).thenReturn( new Range( start, length ) );
    return display;
  }
  
  @Test
  public void testChangeSorting() {
    int start = 0;
    int length = 10;
    ColumnIdentifier columnIdentifier = ColumnIdentifier.NAME;
    boolean ascending = false;
    HasData<PizzaProxy> display = createDisplayWithRange( start, length );
    
    provider.sortingChanged( display, columnIdentifier, ascending );
    
    verify( daoMock ).findAll( new Page( start, length ), columnIdentifier, ascending );
  }

  private Pizza createPizza( String expectedName ) {
    Pizza expectedPizza = new Pizza();
    expectedPizza.setName( expectedName );
    return expectedPizza;
  }

}
