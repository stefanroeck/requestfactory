package cleancodematters.requestfactory.polymorphism.server;

import java.util.Arrays;
import java.util.List;

public class VehicleProvider {

  public List<Vehicle> getListOfVehicles() {
    return Arrays.asList( new Vehicle(), new Car(), new Truck() );
  }
}
