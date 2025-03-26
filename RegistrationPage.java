package com.demo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "gender-male")
    private WebElement genderMale;

    @FindBy(id = "gender-female")
    private WebElement genderFemale;

    @FindBy(id = "FirstName")
    private WebElement firstName;

    @FindBy(id = "LastName")
    private WebElement lastName;

    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement password;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(css = ".result")
    private WebElement successMessage;

    @FindBy(css = ".message-error")
    private WebElement errorMessage;

   

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(email));
    }

    public void openRegistrationPage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ico-register")));
    	registerButton.click();
    }

    public void entersCredentials(String gender, String FirstName, String LastName, String Email, String Password, String ConfirmPassword) {
        if (gender.equalsIgnoreCase("Male")) {
            genderMale.click();
        } else {
            genderFemale.click();
        }

        firstName.clear();
        firstName.sendKeys(FirstName);

        lastName.clear();
        lastName.sendKeys(LastName);

        email.clear();
        email.sendKeys(Email);

        password.clear();
        password.sendKeys(Password);

        confirmPassword.clear();
        confirmPassword.sendKeys(ConfirmPassword);
    }

    public void clickOnRegister() {
        registerButton.click();
    }

    public Boolean isRegistrationSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isRegistrationFailed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
