package com.demo.pages;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CheckOutPage {
	WebDriver driver;
	WebDriverWait wait;

    @FindBy(id="Email")
    WebElement email;
    
    @FindBy(id="Password")
    WebElement password;
    
    @FindBy(css = ".button-1.login-button") WebElement loginSubmit;

    @FindBy(id = "small-searchterms") WebElement searchBar;
    @FindBy(css = ".button-1.search-box-button") WebElement searchButton;
    @FindBy(xpath = "//h2[@class='product-title']/a") WebElement productLink;
    @FindBy(css = ".button-1.add-to-cart-button") WebElement addToCartButton;
    @FindBy(css = ".cart-label") WebElement cartIcon;
    @FindBy(id = "termsofservice") WebElement termsofservice;

    @FindBy(id = "checkout") WebElement checkoutButton;
    @FindBy(id = "billing-address-select") WebElement billingDropdown;

    @FindBy(name = "BillingNewAddress.Email")
    WebElement emailField;

    @FindBy(id = "BillingNewAddress_CountryId")
    WebElement countryDropdown;

    @FindBy(id = "BillingNewAddress_City")
    WebElement cityField;

    @FindBy(id = "BillingNewAddress_Address1")
    WebElement addressField;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    WebElement zipCodeField;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    WebElement phoneNumberField;

    @FindBy(css = ".button-1.new-address-next-step-button")
    WebElement addressContinue;

    @FindBy(xpath = ".//*[@id='shipping-buttons-container']/input")
    WebElement shippingContinue;

    @FindBy(css = ".button-1.shipping-method-next-step-button")
    WebElement shippingMethodContinue;

    @FindBy(xpath = "//*[@id=\"payment-method-buttons-container\"]/input")
    WebElement paymentMethodContinue;

    @FindBy(css = ".button-1.payment-info-next-step-button")
    WebElement paymentInfoContinue;

    @FindBy(css = ".button-1.confirm-order-next-step-button")
    WebElement confirmOrderButton;

    @FindBy(className = "field-validation-error") // Adjust locator based on actual error message
    WebElement errorMessage;

    @FindBy(css = ".section.order-completed")
    WebElement orderConfirmationMessage;

    public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
        PageFactory.initElements(driver, this);
    }
    
    public void OpenPage() {
    	driver.get("https://demowebshop.tricentis.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        }
    
    
    public void Login() {
    	email.sendKeys("jamessarah@mail.com");
    	password.sendKeys("@sarah123");
    	loginSubmit.click();
    	
    }
    
    public void addProductToCart() {
    	searchBar.sendKeys("Build your own cheap computer");
    	searchButton.click();
    	productLink.click();
    	addToCartButton.click();
    	
    	
    }
    public void gotocartandCheckout() {
    	cartIcon.click();
    	termsofservice.click();
    	checkoutButton.click();
    	
    }

    public void enterAddressDetails(String Email, String Country, String City, String Address, String ZipCode, String PhoneNumber) throws InterruptedException {
    	
    	Select billingSelect = new Select(billingDropdown);
        billingSelect.selectByVisibleText("New Address");
        Thread.sleep(1000);
        
        emailField.clear();
        emailField.sendKeys(Email);

        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(Country);

        cityField.clear();
        cityField.sendKeys(City);

        addressField.clear();
        addressField.sendKeys(Address);

        zipCodeField.clear();
        zipCodeField.sendKeys(ZipCode);

        phoneNumberField.clear();
        phoneNumberField.sendKeys(PhoneNumber);

        addressContinue.click();
    }
	public void Continue() {	 
        shippingContinue.click();
        shippingMethodContinue.click();
        paymentMethodContinue.click();
        paymentInfoContinue.click();
        confirmOrderButton.click();
    }

    public String getConfirmationMessage() {
        try {
            return orderConfirmationMessage.getText();
        } catch (NoSuchElementException e) {
            return errorMessage.getText();
        }
    }
}