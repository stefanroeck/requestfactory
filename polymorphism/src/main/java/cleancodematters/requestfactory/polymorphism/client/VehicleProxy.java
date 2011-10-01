package cleancodematters.requestfactory.polymorphism.client;

import cleancodematters.requestfactory.polymorphism.server.Vehicle;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

@ProxyFor(Vehicle.class)
public interface VehicleProxy extends ValueProxy {
  int getSpeed();
}
