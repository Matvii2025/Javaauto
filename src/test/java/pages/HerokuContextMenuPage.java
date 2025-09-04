package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HerokuContextMenuPage {
    private final WebDriver driver;

    @FindBy(id = "hot-spot")
    private WebElement hotSpot;

    public HerokuContextMenuPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://the-internet.herokuapp.com/context_menu");
        PageFactory.initElements(driver, this);
    }

    public WebElement box() { return hotSpot; }
}
