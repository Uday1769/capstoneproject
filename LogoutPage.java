package com.demo.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "Email")
    private WebElement emailElement;

    @FindBy(id = "Password")
    private WebElement passwordElement;

    @FindBy(xpath = "//input[@value='Log in']")
    private WebElement loginButton;

    @FindBy(css = ".ico-logout")
    private WebElement logoutButton;

    @FindBy(css = ".ico-login")
    private WebElement loginLink;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(emailElement));
    }

    public void enterValidCredentials(String email, String password) {
        emailElement.sendKeys(email);
        passwordElement.sendKeys(password);
    }

    public void clickOnLogin() {
        loginButton.click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.visibilityOf(logoutButton)).click();
    }

    public Boolean isLogoutSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginLink));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
