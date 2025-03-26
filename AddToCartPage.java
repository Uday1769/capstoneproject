package com.demo.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCartPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "small-searchterms")
    private WebElement searchBox;

    @FindBy(css = ".search-box input[type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/h2[1]/a[1]")
    WebElement firstProductLink;

    @FindBy(id = "add-to-cart-button-31")
    WebElement addToCartButton;

    @FindBy(css = ".cart-qty")
    private WebElement cartQuantity;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        searchBox.sendKeys(productName);
        searchButton.click();
    }

    public void selectFirstProduct() {
        wait.until(ExpectedConditions.visibilityOf(firstProductLink)).click();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public boolean isProductAddedToCart() {
        return wait.until(ExpectedConditions.textToBePresentInElement(cartQuantity, "(1)"));
    }
}
