package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoQADroppablePage {
    private final WebDriver driver;

    @FindBy(id = "draggable")
    private WebElement draggable;

    @FindBy(id = "droppable")
    private WebElement droppable;

    public DemoQADroppablePage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://demoqa.com/droppable");
        PageFactory.initElements(driver, this);
    }

    public WebElement getDraggable() { return draggable; }
    public WebElement getDroppable() { return droppable; }
    public String droppableText() { return droppable.getText().trim(); }
}
