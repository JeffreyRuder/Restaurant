import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void restaurantInstantiatesCorrectly_true() {
    Restaurant newRestaurant = new Restaurant("Happy's", 3);
    assertTrue(newRestaurant instanceof Restaurant);
  }

  @Test
  public void restaurantInstantiatesWithName() {
    Restaurant newRestaurant = new Restaurant("Happy's", 3);
    assertEquals("Happy's", newRestaurant.getName());
  }

  @Test
  public void equals_returnsTrueIfSameName() {
    Restaurant firstRestaurant = new Restaurant("Happy's", 3);
    Restaurant secondRestaurant = new Restaurant("Happy's", 3);
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Restaurant.all().size());
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Restaurant newRestaurant = new Restaurant("Momo", 7);
    newRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(newRestaurant));
  }

  @Test
  public void save_savesCuisineWithRestaurant() {
    Restaurant restaurant = new Restaurant("La Sirenita", 1);
    restaurant.save();
    Restaurant savedRestaurant = Restaurant.find(restaurant.getId());
    assertEquals("Mexican", savedRestaurant.getCuisine());
  }

  @Test
  public void find_findRestaurantInDatabase_true() {
    Restaurant myRestaurant = new Restaurant("Burgerville #4428", 3);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertTrue(myRestaurant.equals(savedRestaurant));
  }

  // @Test
  // public void getRestaurants_retrievesAllRestaurantsFromDatabase_restaurantsList() {
  //   Restaurant firstRestaurant = new Restaurant
  // }

  // @Test
  // public void getTasks_retrievesAllTasksFromDatabase_tasksList() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task firstTask = new Task("Mow the lawn", myCategory.getId());
  //   firstTask.save();
  //   Task secondTask = new Task("Do the dishes", myCategory.getId());
  //   secondTask.save();
  //   Task[] tasks = new Task[] { firstTask, secondTask };
  //   assertTrue(myCategory.getTasks().containsAll(Arrays.asList(tasks)));
  // }
}
