import java.util.List;

import org.sql2o.*;

public class Cuisine {
  private int id;
  private String type;

  public Cuisine (String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object otherCuisine) {
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType());
    }
  }

  public static List<Cuisine> all() {
    String sql = "SELECT id, type FROM cuisines";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  public static Cuisine find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cuisines WHERE id=:id";
      Cuisine cuisine = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cuisine.class);
      return cuisine;
    }
  }

  public String getType() {
    return type;
  }

  public int getId() {
    return id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cuisines(type) VALUES (:type)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("type", this.type)
        .executeUpdate()
        .getKey();
    }
  }
}
