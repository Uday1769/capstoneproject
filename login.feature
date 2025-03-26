Feature: Login,Register,Logout Functionalities

Scenario: Registering using valid & invalid credentials
Given Opens the login Page
And navigate to Registration Page
When Giving valid and invalid credentials to register
|gender  |FirstName  |LastName  |Email                 |Password    |ConfirmPassword|
| Male   | udaykiran | nalamada | n.uday1769@gmail.com | Ts05@ee0279| Ts05@ee0279     |
| Female | James     | Bond     | sample00007@gmail.com| james007   | james007        |
And clicks "Register" 
Then checks whether user is registered or not

Scenario: Logging in using valid & invalid credentials
Given Opening the login page
When Giving valid & invalid credentials
|email                 |password     |
| n.uday1769@gmail.com | Ts05@ee0279 |
| sample007@gmail.com  | james007    |

And clicks "Login" button
Then the user should be logged in

Scenario: searching for a product and adding it to the cart
    Given Searching for "laptop" 
    When Selecting the first product in the search results
    And Clicks "Add to Cart"
    Then The product should be added to the cart successfully
    And Checks whether the product added to cart or not

Scenario: Adding Multiple Products to Cart & Validating Cart Summary
    Given User is on the homepage
    When User searches for laptop and adds it to the cart
    And User searches for Camera and adds it to the cart
    And User navigates to the cart page
    Then Cart should display 2 products and the total price
    
Scenario: Applying a Coupon Code & Checking Discount Calculation
   Given User is in the homepage
   When User adds a product to the cart
   And User navigates to the checkout page
   And User applies the coupon code "TESTCOUPON"
   Then Discount should be applied correctly
   
   Scenario: login , adding a product to cart and proceed to checkout
Given Login using credentials email and password
And Add a product to the cart
When proceed to checkout
And giving the invalid and valid details
|Email              |Country       |City         |       Address                   |ZipCode   |Phonenumber       |
|jamessarh.com      |Argentina     |Rosaria      |1/34, Veracruz 2156, Buenos Aires|45678     |345678905         |
|jamessarah@mail.com|Argentina     |Rosaria      |1/34, Veracruz 2156, Buenos Aires|34562     |35467890345       |
Then confirm the order
 
        
Scenario: Checking the Logout Functionality
Given Get the login page
And Giving valid login credentials
|email                 |password     |
| n.uday1769@gmail.com | Ts05@ee0279 |
When click "login"
And clicking "logout" 
Then checks whether the user is loggged out or not 


