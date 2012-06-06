package cleancodematters.datagriddemo.client;

import java.util.List;

import cleancodematters.datagriddemo.client.PizzaRequestFactory.PizzaRequestContext;
import cleancodematters.datagriddemo.shared.ColumnIdentifier;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;


public class PizzaDataProvider extends AsyncDataProvider<PizzaProxy> {

  private final PizzaRequestFactory factory;
  private boolean sortIsAscending;
  private ColumnIdentifier sortKey;
  
  public PizzaDataProvider( PizzaRequestFactory factory ) {
    this.factory = factory;
    this.sortIsAscending = true;
    this.sortKey = ColumnIdentifier.ID;
  }
  

  @Override
  protected void onRangeChanged( final HasData<PizzaProxy> display ) {
    final Range range = display.getVisibleRange();
    PizzaRequestContext context = factory.context();
    PageProxy page = createPage( range, context );
    Request<List<PizzaProxy>> request = context.findAll( page, sortKey, sortIsAscending );
    request.with( "ingredients" ).fire( new Receiver<List<PizzaProxy>>() {

      @Override
      public void onSuccess( List<PizzaProxy> response ) {
        // TODO implement row count fetching
        display.setRowCount( range.getStart() + range.getLength() * 2, false );
        display.setRowData( range.getStart(), response );
      }
    } );
  }

  private static PageProxy createPage( final Range range, PizzaRequestContext context ) {
    PageProxy page = context.create( PageProxy.class );
    page.setFirst( range.getStart() );
    page.setCount( range.getLength() );
    return page;
  }
  
  public void sortingChanged( HasData<PizzaProxy> display, ColumnIdentifier sortKey, boolean ascending ) {
    this.sortKey = sortKey;
    this.sortIsAscending = ascending;
    onRangeChanged( display );
  }

}
