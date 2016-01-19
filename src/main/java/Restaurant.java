import java.util.List;
import org.sql2o.*;

public class Restaurant {
  private int id;
  private String name;
  private int cuisine_id;

  public Restaurant (String name, int cuisine_id) {
    this.name = name;
    this.cuisine_id = cuisine_id;
  }

  @Override
  public boolean equals(Object otherRestaurant) {
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName());
    }
  }

  public static List<Restaurant> all() {
    String sql = "SELECT id, name FROM restaurants";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants WHERE id=:id";
      Restaurant restaurant = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurant.class);
      return restaurant;
    }
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public int getCuisineId() {
    return cuisine_id;
  }

  public String getCuisine() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cuisines WHERE id = :cuisine_id";
      Cuisine cuisine = con.createQuery(sql)
        .addParameter("cuisine_id", this.cuisine_id)
        .executeAndFetchFirst(Cuisine.class);
      return cuisine.getType();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants(name, cuisine_id) VALUES (:name, :cuisine_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("cuisine_id", this.cuisine_id)
        .executeUpdate()
        .getKey();
    }
  }
}
