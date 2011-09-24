package cleancodematters.requestfactory.tutorial.server.domain;

import java.util.List;

public class Pizza implements Identifiable, Versionable{

  private Long id;
  private Long version;
  
  private String name;
  private List<Ingredient> ingredients;

  /* Getters and Setters */
  
  public Long getId() {
    return id;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients( List<Ingredient> ingredients ) {
    this.ingredients = ingredients;
  }

  public String getName() {
    return name;
  }

  public Long getVersion() {
    return version;
  }

  public void setId( Long id ) {
    this.id = id;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public void setVersion( Long version ) {
    this.version = version;
  }

}
