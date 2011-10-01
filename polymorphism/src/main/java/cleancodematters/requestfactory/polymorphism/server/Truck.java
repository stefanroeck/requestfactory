package cleancodematters.requestfactory.polymorphism.server;

public class Truck extends Vehicle {
  private int axles;

  public int getAxles() {
    return axles;
  }

  public void setAxles( int axles ) {
    this.axles = axles;
  }
}
