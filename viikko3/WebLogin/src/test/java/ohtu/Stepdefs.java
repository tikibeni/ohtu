package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    // Chromedriveri on debuggausta varten. HTMLdriveri on nopea suorittamaan testit.
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    // USER LOGIN TESTING
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    

    @When("incorrect username {string} and password {string} are given")
    public void incorrectUsernameIsGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }  
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    // USER LOGIN TESTING



    // USER REGISTERATION TESTING
    @Given("command new user is selected")
    public void registerIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @Given("user with username {string} and password {string} is successfully created")
    public void userCreatedWithUsernameAndPassword(String username, String password) throws Throwable {
        registerIsSelected();
        registerWith(username, password, password);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userCreationNonSuccessfulLoginAttempt(String username, String password) throws Throwable {
        registerIsSelected();
        registerWith(username, password, password);
    }

    @When("a valid username {string} and password {string} and matching password confirmation {string} are entered")
    public void correctRegisterInfoAreGiven(String username, String password, String passwordConfirmation) {
        registerWith(username, password, passwordConfirmation);
    }

    @When("an invalid username {string} and valid password {string} are entered")
    public void invalidUsernameValidPasswordAreGiven(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }

    @When("a valid username {string} and invalid password {string} are entered")
    public void validUsernameInvalidPasswordAreGiven(String username, String password) throws Throwable {
        registerWith(username, password, password);
    }

    @When("a valid username {string}, valid password {string} and non-matching confirmation {string} are given")
    public void validUsernameValidPasswordInvalidConfirmation(String username, String password, String confirmation) throws Throwable {
        registerWith(username, password, confirmation);
    }

    @Then("a new user is created") 
    public void userCreatedAndCorrectPageDisplayed() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("user is not created and error {string} is reported")
    public void userCreationFailAndReportedError(String error) {
        pageHasContent(error);
    }
    // USER REGISTERATION TESTING
    
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 

    private void registerWith(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element.submit();
    }
}