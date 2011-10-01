package cleancodematters.requestfactory.polymorphism.client;

import java.util.List;

import cleancodematters.requestfactory.polymorphism.server.CreateNewInstanceLocator;
import cleancodematters.requestfactory.polymorphism.server.VehicleProvider;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

public interface VehicleRequestFactory extends RequestFactory {

  @Service(value=VehicleProvider.class, locator = CreateNewInstanceLocator.class)
  @ExtraTypes( {CarProxy.class, TruckProxy.class} )
  public interface VehicleRequestContext extends RequestContext {
    Request<List<VehicleProxy>> getListOfVehicles();
  }
  
  VehicleRequestContext context();
}
