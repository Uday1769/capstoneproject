package com.demo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "small-searchterms")
    private WebElement searchBox;

    @FindBy(css = ".button-1.search-box-button")
    private WebElement searchButton;

    @FindBy(css = ".product-title > a")
    private WebElement firstProduct;
    
    @FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[3]/div[1]/div/div/div[2]/h2/a")
    private WebElement secondProduct;

    @FindBy(id = "add-to-cart-button-31")
    private WebElement firstAddToCart;

    @FindBy(id = "add-to-cart-button-18")
    private WebElement secondAddToCart;

    @FindBy(css = ".cart-label")
    private WebElement cartLink;

    @FindBy(css = ".cart-qty")
    private WebElement cartQuantity;

    @FindBy(css = ".order-total")
    private WebElement totalPrice;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get("https://demowebshop.tricentis.com/");
    }

    public void searchProduct(String productName) throws InterruptedException {
        searchBox.clear();
        searchBox.sendKeys(productName);
        try {
            WebElement closePopup = driver.findElement(By.cssSelector("popup-close-button")); 
            closePopup.click();
        } catch (Exception e) {
            System.out.println("No popup found.");
        }

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void addFirstProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
        wait.until(ExpectedConditions.elementToBeClickable(firstAddToCart)).click();
    }

    public void addSecondProductToCart() {
    	wait.until(ExpectedConditions.elementToBeClickable(secondProduct)).click();
        wait.until(ExpectedConditions.elementToBeClickable(secondAddToCart)).click();
    }

    public void goToCartPage() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public int getCartProductCount() {
        String text = wait.until(ExpectedConditions.visibilityOf(cartQuantity)).getText().replaceAll("[^0-9]", ""); 
        return Integer.parseInt(text);
    }

    public String getTotalPrice() {
        return wait.until(ExpectedConditions.visibilityOf(totalPrice)).getText();
    }
    
}
