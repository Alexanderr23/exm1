package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.commonMethods;

public class searchBar extends commonMethods {
    @Given("User navigates to Amazon page")
    public void user_navigates_to_amazon_page() {
     openBrowserAndLaunchApplication();
    }

    @Then("User enters books inside Search Bar")
    public void user_enters_books_inside_search_bar() {
  searchBP.searchBarBtn.sendKeys("books");
    }
    @Then("User clicks on Search")
    public void user_clicks_on_search() {
  searchBP.searchBtn.click();
    }
    @Then("book page should be visible")
    public void book_page_should_be_visible() {
 Boolean bookTab=searchBP.bookTab.isDisplayed();
 System.out.println(bookTab);
    }

}
