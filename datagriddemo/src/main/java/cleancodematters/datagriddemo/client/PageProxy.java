package cleancodematters.datagriddemo.client;

import cleancodematters.datagriddemo.server.Page;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;


@ProxyFor(Page.class)
public interface PageProxy extends ValueProxy {
  int getFirst();
  void setFirst( int first );
  
  int getCount();
  void setCount( int count );
}
