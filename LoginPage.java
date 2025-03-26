package com.demo.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "Email")
    private WebElement emailElement;

    @FindBy(id = "Password")
    private WebElement passwordElement;

    @FindBy(xpath = "//input[@value='Log in']")
    private WebElement loginButton;

    @FindBy(css = ".account")
    private WebElement accountElement;

    @FindBy(xpath = "//div[contains(@class, 'message-error')]")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(emailElement));
    }

    public void entersCredentials(String email, String password) {
        emailElement.clear();
        emailElement.sendKeys(email);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public Boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOf(accountElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isLoginFailed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
