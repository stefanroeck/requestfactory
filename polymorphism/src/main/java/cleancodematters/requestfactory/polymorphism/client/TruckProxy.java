package cleancodematters.requestfactory.polymorphism.client;

import cleancodematters.requestfactory.polymorphism.server.Truck;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Truck.class)
public interface TruckProxy extends VehicleProxy{
  int getAxles();
}
