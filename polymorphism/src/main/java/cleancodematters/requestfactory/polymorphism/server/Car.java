package cleancodematters.requestfactory.polymorphism.server;

public class Car extends Vehicle {
  boolean isConvertible;

  public boolean isConvertible() {
    return isConvertible;
  }

  public void setConvertible( boolean isConvertible ) {
    this.isConvertible = isConvertible;
  }
}
