import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KongaTest2 {

    private WebDriver driver;

    @BeforeTest
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        //1. create an instance of a chromedriver to run the script
        driver = new ChromeDriver();
        //2. navigate to the Konga Website
        driver.get("https://www.konga.com");
        //To maximize the Webpage
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }
    @Test(priority = 0)
    public void login() throws InterruptedException {
        //Input the login/signup field
        driver.findElement(By.xpath("//div[1]/div/div/div[4]/a")).click();
        Thread.sleep(5000);
        //Input the username
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Temitopeojinni@mallinator.com");
        //Input the password
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Topeojinni");
        Thread.sleep(5000);
        // Click on the login button
        driver.findElement(By.xpath("//div[3]/button[@type='submit']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void clickComputerAndAccessories() throws InterruptedException {
        //Click on computer and accessories
        driver.findElement(By.xpath("//div/a[@href='/category/accessories-computing-5227']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void clickLaptops() throws InterruptedException {
        //click on laptops
        driver.findElement(By.xpath("//li/a[@href='/category/accessories-computing-5227?menu=Computers and Accessories > Laptops']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 3)
    public void clickAppleMacBooks() throws InterruptedException {
        //click on Apple and MacBooks
        driver.findElement(By.xpath("//li/a[@href='/category/accessories-computing-5227?menu=Computers and Accessories > Laptops > Apple MacBooks']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 4)
    public void clickAddToCart() throws InterruptedException {
        //Click add to cart button
        driver.findElement(By.xpath("//li[2]/div/div/div[2]/form[@action='/cart/overview']/div[3]")).click();
        Thread.sleep(5000);
        //click on the cart button to see the added item
        driver.findElement(By.xpath("//div[@id='nav-bar-fix']/div/div/div/a[@href='/cart/overview']")).click();
        Thread.sleep(5000);
        //click on the checkout button to proceed to payment page
        driver.findElement(By.xpath("//aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(10000);
    }

    @Test(priority = 5)
    public void proceedToPayment() throws InterruptedException {
        //proceed to click on the card payment
        driver.findElement(By.xpath("//input[@data-payment-method='kpaygateway']")).click();
        Thread.sleep(10000);
        //click on the pay now button
        driver.findElement(By.xpath("//button[@name='placeOrder']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 6)
    public void insertPaymentDetails() throws InterruptedException {
        //switched to payment modal iframe
        driver.switchTo().frame("kpg-frame-component");
        //click the card option for the payment
        driver.findElement(By.xpath("//button[@class='dashboard-card__button Card']")).click();
        Thread.sleep(10000);
        //Input invalid card number
        driver.findElement(By.xpath("//input[@id='card-number']")).sendKeys("5176898754432231");
        Thread.sleep(10000);
        //Input an invalid expiry date
        driver.findElement(By.xpath("//input[@id='expiry']")).sendKeys("0624");
        Thread.sleep(5000);
        //Input an invalid CVV number on the CVV field
        driver.findElement(By.xpath("//*[@id=\"cvv\"]")).sendKeys("087");
        Thread.sleep(5000);
    }

    @Test(priority = 7)
    public void printError() throws InterruptedException {
        //identify the element that displayed the error message
        WebElement errorMessage = driver.findElement(By.xpath("//label[@id='card-number_unhappy']"));
        //print the error to console by getting the text attribute
        System.out.println("The Error Message is: "+errorMessage.getText());
        Thread.sleep(5000);
    }

    @Test(priority = 8)
    public void closePaymentDetailsFrame() {
        //closed the payment modal iframe
        driver.switchTo().defaultContent();
    }

    @AfterTest
    public void quitBrowser() {
        //Quit the browser
        driver.quit();
    }
}