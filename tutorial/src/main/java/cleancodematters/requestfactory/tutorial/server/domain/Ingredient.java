package cleancodematters.requestfactory.tutorial.server.domain;

public class Ingredient {
  private String name;
  private boolean vegan;
  
  /* Getters and Setters */

  public String getName() {
    return name;
  }
  public void setName( String name ) {
    this.name = name;
  }
  public boolean isVegan() {
    return vegan;
  }
  public void setVegan( boolean vegan ) {
    this.vegan = vegan;
  }
}
