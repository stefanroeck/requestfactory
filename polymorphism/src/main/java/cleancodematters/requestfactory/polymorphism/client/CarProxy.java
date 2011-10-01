package cleancodematters.requestfactory.polymorphism.client;

import cleancodematters.requestfactory.polymorphism.server.Car;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Car.class)
public interface CarProxy extends VehicleProxy {
  boolean isConvertible();
}
