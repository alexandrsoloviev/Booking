package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.AssertJUnit.assertEquals;

public class SearchSteps {


    WebDriver driver;
    String hotelName;
    public static final String BASE_URL = "https://www.booking.com/searchresults.en-gb.html";
    public static final By SEARCH_INPUT = By.xpath("//input[@id='ss']");
    public static final By SEARCH_BUTTON = By.xpath("//button[@type='submit']");
    public static final By TITLE_SEARCH_RESULT = By.xpath("(//div[@data-testid='title'])[1]");
    public static final By RATING_SEARCH_RESULT = By.xpath("(//div[@data-testid='review-score'])[1]/div[@aria-label]");



    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("ввести название города {string}")
    public void ввестиНазваниеГорода(String hotelName) {
        this.hotelName = hotelName;
    }

    @When("нажать кнопку search")
    public void нажатьКнопкуSearch() {
        driver.get(BASE_URL);
        driver.findElement(SEARCH_INPUT).click();
        driver.findElement(SEARCH_INPUT).sendKeys(hotelName);
        driver.findElement(SEARCH_BUTTON).click();
    }

    @Then("{string} певрый отель в списке")
    public void певрыйОтельВСписке(String hotelTitle) {
        assertEquals(driver.findElement(TITLE_SEARCH_RESULT).getText(), hotelTitle);
    }

    @And("рейтинг должен составлять {string}")
    public void рейтингДолженСоставлять(String rating) {
        assertEquals(driver.findElement(RATING_SEARCH_RESULT).getText(), rating);
    }

    @After()
    public void tearDown() {
        driver.quit();
    }
}
