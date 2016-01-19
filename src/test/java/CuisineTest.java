import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_containsOnlyInitialSevenCuisines() {
    assertEquals(7, Cuisine.all().size());
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Cuisine firstCuisine = new Cuisine("New American");
    Cuisine secondCuisine = new Cuisine("New American");
    assertTrue(firstCuisine.equals(secondCuisine));
  }

  @Test
  public void save_savesCuisineInDatabase() {
    Cuisine myCuisine = new Cuisine("Thai");
    myCuisine.save();
    assertTrue(Cuisine.all().get(7).equals(myCuisine));
  }

  @Test
  public void save_assignsIdToObject() {
    Cuisine myCuisine = new Cuisine("Thai");
    myCuisine.save();
    Cuisine savedCuisine = Cuisine.all().get(7);
    assertEquals(myCuisine.getId(), savedCuisine.getId());
  }

  @Test
  public void find_findsCuisineInDatabase_true() {
    Cuisine myCuisine = new Cuisine("Seafood");
    myCuisine.save();
    Cuisine savedCuisine = Cuisine.find(myCuisine.getId());
    assertTrue(myCuisine.equals(savedCuisine));
  }

  // @Test
  // public void save_savesCategoryIdIntoDB_true() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn", myCategory.getId());
  //   myTask.save();
  //   Task savedTask = Task.find(myTask.getId());
  //   assertEquals(savedTask.getCategoryId(), myCategory.getId());
  // }

}
