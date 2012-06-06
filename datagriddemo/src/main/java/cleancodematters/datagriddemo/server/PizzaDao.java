package cleancodematters.datagriddemo.server;

import java.util.List;

import cleancodematters.datagriddemo.server.domain.Pizza;
import cleancodematters.datagriddemo.shared.ColumnIdentifier;


public interface PizzaDao {
  List<Pizza> findAll( Page page, ColumnIdentifier sortKey, boolean sortIsAscending );
  Pizza findById( Long id );
  void save( Pizza pizza );
}
