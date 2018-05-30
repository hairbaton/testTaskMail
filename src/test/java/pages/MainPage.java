package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MainPage {

    private WebDriver driver;
    @FindBy(xpath = "//input[@id='mailbox:login']")
    private WebElement loginInput;
    @FindBy(xpath = "//*[@id='mailbox:domain']")
    private WebElement mailboxInput;
    @FindBy(xpath = "//input[@id='mailbox:password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[contains(@class, 'o-control')]")
    private WebElement loginButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void login(String login, String mailbox, String password) {
        loginInput.sendKeys(login);
        Select dropdown = new Select(mailboxInput);
        dropdown.selectByVisibleText(mailbox);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
