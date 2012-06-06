package cleancodematters.datagriddemo.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cleancodematters.datagriddemo.shared.ColumnIdentifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.Handler;
import com.google.gwt.user.cellview.client.ColumnSortList.ColumnSortInfo;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DatagridEntryPoint implements EntryPoint {
  
  private Map<Column<?, ?>, ColumnIdentifier> columnToSortKeyMapping = new HashMap<Column<?,?>, ColumnIdentifier>();

  public void onModuleLoad() {
    Label errorLabel = new Label( "Ladies and Gentlemen, please choose your pizza" );
    RootPanel container = RootPanel.get("content");
    container.add( errorLabel );
    
    final PizzaDataProvider dataProvider = new PizzaDataProvider( createRequestFactory() );
    final DataGrid<PizzaProxy> dataGrid = createAndAddDataCell( container );
    dataProvider.addDataDisplay( dataGrid );
    dataGrid.addColumnSortHandler( new Handler() {
      
      @Override
      public void onColumnSort( ColumnSortEvent event ) {
        if( event.getColumnSortList().size() >= 1 ) {
          ColumnSortInfo columnSortInfo = event.getColumnSortList().get( 0 );
          ColumnIdentifier sortKey = columnToSortKeyMapping.get( columnSortInfo.getColumn() );
          dataProvider.sortingChanged( dataGrid, sortKey, columnSortInfo.isAscending() );
        }
      }
    } );
  }

  private DataGrid<PizzaProxy> createAndAddDataCell( RootPanel container ) {
    DataGrid<PizzaProxy> dataGrid = new DataGrid<PizzaProxy>();
    dataGrid.setHeight( "400px" );
    dataGrid.setEmptyTableWidget( new Label( "No data" ) );
    
    TextColumn<PizzaProxy> idColumn = new TextColumn<PizzaProxy>() {
      @Override
      public String getValue( PizzaProxy object ) {
        return String.valueOf( object.getId() );
      }
    };
    idColumn.setSortable( true );
    
    TextColumn<PizzaProxy> nameColumn = new TextColumn<PizzaProxy>() {
      @Override
      public String getValue( PizzaProxy object ) {
        return object.getName();
      }
    };
    nameColumn.setSortable( true );
    
    TextColumn<PizzaProxy> ingredientColumn = new TextColumn<PizzaProxy>() {
      @Override
      public String getValue( PizzaProxy object ) {
        return concatNames( object.getIngredients() );
      }

      private String concatNames( List<IngredientProxy> ingredients ) {
        String result = "";
        if( ingredients != null ) {
          for (IngredientProxy ingredientProxy : ingredients) {
            if( !result.isEmpty() ) {
              result += ", ";
            }
            result += ingredientProxy.getName();
          }
        }
        return result;
      }
    };
    
    dataGrid.addColumn( idColumn, "Id"  );
    dataGrid.addColumn( nameColumn, "Pizza name" );
    dataGrid.addColumn( ingredientColumn, "# ingredients" );
    dataGrid.setColumnWidth( idColumn, "60px" );
    dataGrid.setColumnWidth( nameColumn, "200px" );
    
    columnToSortKeyMapping.put( idColumn, ColumnIdentifier.ID );
    columnToSortKeyMapping.put( nameColumn, ColumnIdentifier.NAME );
    
    dataGrid.getColumnSortList().push( idColumn );
//    dataGrid.addColumnSortHandler( new ColumnSortEvent.AsyncHandler( dataGrid ) );
    
    // Create a Pager to control the table.
    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
    SimplePager pager = new SimplePager( TextLocation.CENTER, pagerResources, false, 0, true );
    pager.setDisplay( dataGrid );  
    container.add( pager );
    
    container.add( dataGrid );
    return dataGrid;
  }
  
  private PizzaRequestFactory createRequestFactory() {
    PizzaRequestFactory result = GWT.create( PizzaRequestFactory.class ); 
    result.initialize( new SimpleEventBus() );
    return result;
  }
}
