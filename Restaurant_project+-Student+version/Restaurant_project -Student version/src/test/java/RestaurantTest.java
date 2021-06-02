import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //Refactoring repetitive code
    @BeforeEach
    public void restaurantAdd() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        addToMenu();
    }
    public void addToMenu(){
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        LocalTime openingTime = LocalTime.now().minusHours(1);
        LocalTime closingTime = LocalTime.now().plusHours(1);
        restaurant = new Restaurant("cafe Day", "Chennai", openingTime, closingTime);
        addToMenu();
        boolean restaurantState = restaurant.isRestaurantOpen();
        assertThat(restaurantState, equalTo(true));
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        LocalTime openingTime = LocalTime.now().plusHours(1);
        LocalTime closingTime = LocalTime.now().minusHours(1);
        restaurant = new Restaurant("Spice World", "Delhi", openingTime, closingTime);
        addToMenu();
        boolean restaurantState = restaurant.isRestaurantOpen();
        assertThat(restaurantState, equalTo(false));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
    //failing test cases to get total order value

    @Test
    public void return_total_order_value_with_selected_menu_items(){
        String[] selectedItems={"Sweet corn soup","Vegetable lasagne"};
        assertEquals(restaurant.getOrderValue(selectedItems),388);
    }

    @Test
    public void return_price_as_zero_when_no_selection(){
        String[] selectedItems={};
        assertEquals(restaurant.getOrderValue(selectedItems),0);
    }
}


