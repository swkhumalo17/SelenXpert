package KeywordDrivenTestFramework.Testing.TestClasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

/**
 * @author SKHUMALO on 2024/07/12.
 * @project DAE-Automation-Framework
 */
public class ShoppingCartSteps {
    private WebDriver driver;

    @Before
    public void setup() {
        // Initialize WebDriver
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        // Close WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the shopping cart page")
    public void iAmOnTheShoppingCartPage() {
        // Navigate to shopping cart page
        driver.get("https://demowebshop.tricentis.com/");
    }

    @When("I log in to the shopping cart")
    public void iLogInToTheShoppingCart() {
        // Find login button and click it
        driver.findElement(By.linkText("Log in")).click();

        // Enter credentials (replace with actual values)
        driver.findElement(By.id("Email")).sendKeys("your_email");
        driver.findElement(By.id("Password")).sendKeys("your_password");

        // Click login button
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
    }

    @When("I go to the Computers menu and select Desktops")
    public void iGoToTheComputersMenuAndSelectDesktops() {
        // Click on Computers menu
        driver.findElement(By.linkText("Computers")).click();

        // Click on Desktops link
        driver.findElement(By.linkText("Desktops")).click();
    }

    @When("I choose {string}")
    public void iChooseProduct(String product) {
        // Click on the product
        driver.findElement(By.xpath("//h2[text()='" + product + "']//following-sibling::div//input[@value='Add to cart']")).click();
    }

    @When("I add it to cart")
    public void iAddItToCart() {
        // Add explicit wait if necessary for the product to be added to cart
    }

    @When("I accept Ts & Cs and checkout")
    public void iAcceptTsCsAndCheckout() {
        // Click on Terms of Service checkbox
        driver.findElement(By.id("termsofservice")).click();

        // Click on Checkout button
        driver.findElement(By.xpath("//button[text()='Checkout']")).click();
    }

    @When("I complete billing and shipping details")
    public void iCompleteBillingAndShippingDetails() {
        // Enter billing and shipping details
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("YourFirstName");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("YourLastName");
        // ... (Add other details as necessary)
    }

    @When("I choose CoD as payment method")
    public void iChooseCoDAsPaymentMethod() {
        // Select Cash on Delivery payment method
        driver.findElement(By.id("paymentmethod_1")).click();
    }

    @When("I confirm the order")
    public void iConfirmTheOrder() {
        // Click on Confirm button
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();
    }

    @Then("the order is confirmed")
    public void theOrderIsConfirmed() {
        // Assert that order confirmation message is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']"))
                .getText().contains("Your order has been successfully processed!"));
    }

    @When("I capture the order number")
    public void iCaptureTheOrderNumber() {
        // Capture and store order number (replace with appropriate locator)
        String orderNumber = driver.findElement(By.xpath("//div[@class='order-number']"))
                .getText();
        // Store order number in context or variable
    }

    @Then("the order number is stored")
    public void theOrderNumberIsStored() {
        // Verify order number is stored in context or variable
    }
}
