import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/restaurant_list_test", null, null);
    try (Connection con = DB.sql2o.open()) {
      String createCuisines = "CREATE TABLE cuisines(id serial PRIMARY KEY, type varchar);";
      String addCuisines = "INSERT INTO cuisines(type) VALUES ('Mexican'), ('Vietnamese'), ('New American'), ('Italian'), ('Thai'), ('Steak'), ('Seafood');";
      String createRestaurants = "CREATE TABLE restaurants(id serial PRIMARY KEY, name varchar, cuisine_id int references cuisines(id));";
      con.createQuery(createCuisines).executeUpdate();
      con.createQuery(addCuisines).executeUpdate();      
      con.createQuery(createRestaurants).executeUpdate();
    }
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String dropCuisines = "DROP TABLE cuisines CASCADE;";
      String dropRestaurants = "DROP TABLE restaurants CASCADE;";
      con.createQuery(dropCuisines).executeUpdate();
      con.createQuery(dropRestaurants).executeUpdate();
    }
  }
}
