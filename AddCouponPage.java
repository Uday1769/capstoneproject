package com.demo.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCouponPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "small-searchterms")
    WebElement searchBox;

    @FindBy(css = ".button-1.search-box-button")
    WebElement searchButton;

    @FindBy(css = ".product-title > a")
    WebElement firstProduct;

    @FindBy(id = "add-to-cart-button-31")
    WebElement addToCartButton;

    @FindBy(css = ".cart-label")
    WebElement cartLink;

    @FindBy(css = ".checkout-button")
    WebElement checkoutButton;

    @FindBy(name = "discountcouponcode")
    WebElement couponCodeInput;

    @FindBy(name = "applydiscountcouponcode")
    WebElement applyCouponButton;

    @FindBy(css = ".message-success")
    WebElement successMessage;

    @FindBy(css = ".discount-amount")
    WebElement discountAmount;
    
    @FindBy(css = ".message") 
    WebElement errorMessage;

    public AddCouponPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        searchBox.sendKeys(productName);
        searchButton.click();
    }

    public void addFirstProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public void navigateToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void applyCouponCode(String couponCode) {
        wait.until(ExpectedConditions.visibilityOf(couponCodeInput)).sendKeys(couponCode);
        applyCouponButton.click();
    }

    public boolean isCouponAttempted() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
    }
}
    