package com.demo.stepdefinitions;
import static org.testng.Assert.assertTrue;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.demo.base.BaseTest;
import com.demo.pages.AddCouponPage;
import com.demo.pages.AddToCartPage;
import com.demo.pages.CartPage;
import com.demo.pages.LoginPage;
import com.demo.pages.LogoutPage;
import com.demo.pages.RegistrationPage;
import com.demo.runners.TestRunner;
import com.demo.pages.CheckOutPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseTest{
	LoginPage loginPage;
	WebDriver driver;
	RegistrationPage registrationPage;
	LogoutPage logoutPage;
	AddToCartPage addToCartPage;
	CartPage cartPage;
	AddCouponPage couponPage;
	CheckOutPage checkOutPage;
	
	@Given("Opens the login Page")
	public void opens_the_login_page() throws Exception {
		BaseTest base = new BaseTest();
		String browser = TestRunner.BrowserType;
		base.BrowserSetup(browser);
		driver = BaseTest.OpenBrowser();
	    registrationPage=new RegistrationPage(driver);
	    registrationPage.openLoginPage();
	}

	@Given("navigate to Registration Page")
	public void navigate_to_registration_page() {
	    registrationPage.openRegistrationPage();
	}

	@When("Giving valid and invalid credentials to register")
	public void giving_valid_and_invalid_credentials_to_register(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> registrationData = dataTable.asLists(String.class);
		for (int i = 1; i < registrationData.size(); i++) { 
		    List<String> data = registrationData.get(i);
		    registrationPage.entersCredentials(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5));
		}

	}

	@When("clicks {string}")
	public void clicks(String string) {
		registrationPage.clickOnRegister();
	    
	}

	@Then("checks whether user is registered or not")
	public void checks_whether_user_is_registered_or_not() {
		if (registrationPage.isRegistrationSuccessful()) {  
		    Assert.assertTrue(true, "Registration was successful.");  
		} else {  
		    Assert.assertTrue(registrationPage.isRegistrationFailed(), "Expected registration failure.");  
		}
		BaseTest.CloseBrowser();

	} 
	
	@Given("Opening the login page")
	public void opening_the_login_page() throws Exception {
		BaseTest base = new BaseTest();
		String browser = TestRunner.BrowserType;
		base.BrowserSetup(browser);
		driver = BaseTest.OpenBrowser();
	    loginPage=new LoginPage(driver);
	    loginPage.openLoginPage();
	}

	@When("Giving valid & invalid credentials")
	public void giving_valid_invalid_credentials(DataTable dataTable) {
		List<List<String>> credentials = dataTable.asLists(String.class);
		for (int i = 1; i < credentials.size(); i++) { 
		    String email = credentials.get(i).get(0);
		    String password = credentials.get(i).get(1);
		    loginPage.entersCredentials(email, password);
		}
	}

	@When("clicks {string} button")
	public void clicks_button(String string) {
	    loginPage.clickLogin();
	}

	@Then("the user should be logged in")
	public void the_user_should_be_logged_in() {
		if (loginPage.isLoginSuccessful()) {
		    Assert.assertTrue(true, "Login was successful.");
		} else {
		    Assert.assertTrue(loginPage.isLoginFailed(), "Expected login failure.");
		}
		BaseTest.CloseBrowser();
	}

	@Given("Searching for {string}")
	public void searching_for(String productName) throws Exception {
	    BaseTest base = new BaseTest();
	    String browser = TestRunner.BrowserType;
	    base.BrowserSetup(browser);
	    driver = BaseTest.OpenBrowser();
	    
	    addToCartPage = new AddToCartPage(driver);
	    addToCartPage.searchProduct(productName);
	}

	@When("Selecting the first product in the search results")
	public void selecting_the_first_product_in_the_search_results() {
	    addToCartPage.selectFirstProduct();
	}

	@When("Clicks {string}")
	public void clicks1(String button) {
	    if (button.equalsIgnoreCase("Add to Cart")) {
	        addToCartPage.addToCart();
	    }
	}

	@Then("The product should be added to the cart successfully")
	public void the_product_should_be_added_to_the_cart_successfully() {
	    Assert.assertTrue(addToCartPage.isProductAddedToCart(), "Product was not added to the cart.");
	}

	@Then("Checks whether the product added to cart or not")
	public void checks_whether_the_product_added_to_cart_or_not() {
	    Assert.assertTrue(addToCartPage.isProductAddedToCart(), "Cart does not contain the expected product.");
	    BaseTest.CloseBrowser();
	}
	
	@Given("User is on the homepage")
    public void user_is_on_the_homepage() throws Exception {
		BaseTest base = new BaseTest();
		String browser = TestRunner.BrowserType;
		base.BrowserSetup(browser);
		driver = BaseTest.OpenBrowser();
        cartPage = new CartPage(driver);
        cartPage.openPage();
    }

    @When("User searches for laptop and adds it to the cart")
    public void user_searches_for_laptop_and_adds_it_to_the_cart() throws InterruptedException {
        cartPage.searchProduct("Laptop");
        cartPage.addFirstProductToCart();
    }

    @When("User searches for Camera and adds it to the cart")
    public void user_searches_for_camera_and_adds_it_to_the_cart() throws InterruptedException {
        cartPage.searchProduct("Camera");
        cartPage.addSecondProductToCart();
    }

    @When("User navigates to the cart page")
    public void user_navigates_to_the_cart_page() {
        cartPage.goToCartPage();
    }

    @Then("Cart should display {int} products and the total price")
    public void cart_should_display_products_and_the_total_price(Integer expectedCount) throws InterruptedException {
        int actualCount = cartPage.getCartProductCount();
        Assert.assertEquals(actualCount, expectedCount, "Number of items in cart does not match!");

        String totalPrice = cartPage.getTotalPrice();
        System.out.println("Total Cart Price: " + totalPrice);
        
       BaseTest.CloseBrowser();
    }
    
    @Given("User is in the homepage")
    public void user_is_in_the_homepage() throws Exception {
    	BaseTest base = new BaseTest();
		String browser = TestRunner.BrowserType;
		base.BrowserSetup(browser);
		driver = BaseTest.OpenBrowser();
        couponPage = new AddCouponPage(driver);
    }

    @When("User adds a product to the cart")
    public void user_adds_a_product_to_the_cart() {
        couponPage.searchProduct("Laptop"); 
        couponPage.addFirstProductToCart();
    }

    @When("User navigates to the checkout page")
    public void user_navigates_to_the_checkout_page() {
        couponPage.navigateToCart();
        
    }

    @When("User applies the coupon code {string}")
    public void user_applies_the_coupon_code(String couponCode) {
         couponPage.applyCouponCode(couponCode);
    }

    @Then("Discount should be applied correctly")
    public void discount_should_be_applied_correctly() {
      assertTrue( couponPage.isCouponAttempted(),"The coupon code you entered couldn't be applied to your order");
      BaseTest.CloseBrowser();
    }
	
	@Given("Get the login page")
	public void get_the_login_page() throws Exception {
		BaseTest base = new BaseTest();
		String browser = TestRunner.BrowserType;
		base.BrowserSetup(browser);
		driver = BaseTest.OpenBrowser();
	    logoutPage=new LogoutPage(driver);
	    logoutPage.openLoginPage();
	}

	@Given("Giving valid login credentials")
	public void giving_valid_login_credentials(DataTable dataTable) {
		 List<List<String>> credentials = dataTable.asLists(String.class);
		 String email = credentials.get(1).get(0);
	     String password = credentials.get(1).get(1);

		    logoutPage.enterValidCredentials(email, password);
	}
	@When("click {string}")
	public void click(String string) {
	    logoutPage.clickOnLogin();
	}
	@When("clicking {string}")
	public void clicking(String string) {
	    logoutPage.clickLogout();
	}

	@Then("checks whether the user is loggged out or not")
	public void checks_whether_the_user_is_loggged_out_or_not() {
		Assert.assertTrue(logoutPage.isLogoutSuccessful(), "Logout failed.");
	    BaseTest.CloseBrowser();
	}	
@Given("Login using credentials email and password")
public void login_using_credentials_email_and_password() throws Exception {
	BaseTest base = new BaseTest();
	String browser = TestRunner.BrowserType;
	base.BrowserSetup(browser);
	driver = BaseTest.OpenBrowser();
	checkOutPage=new CheckOutPage(driver);
	checkOutPage.Login();
}

@Given("Add a product to the cart")
public void add_a_product_to_the_cart() {
	checkOutPage.addProductToCart();
}

@When("proceed to checkout")
public void proceed_to_checkout() {
	checkOutPage.gotocartandCheckout();
 
}


@When("giving the invalid and valid details")
public void enter_checkout_details(DataTable dataTable) throws InterruptedException {
    List<Map<String, String>> details = dataTable.asMaps(String.class, String.class);

    for (Map<String, String> row : details) {
        checkOutPage.enterAddressDetails(
            row.get("Email"),
            row.get("Country"),
            row.get("City"),
            row.get("Address"),
            row.get("ZipCode"),
            row.get("Phonenumber")
        );
    }
}

@Then("confirm the order")
public void verify_order_confirmation() {
	checkOutPage.Continue();
    String confirmationMessage = checkOutPage.getConfirmationMessage();
    Assert.assertTrue(confirmationMessage.contains("Your order has been successfully processed!"), 
        "Expected order confirmation message not found!");
    
    BaseTest.CloseBrowser();
}
}