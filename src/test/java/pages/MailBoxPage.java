package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailBoxPage {

    private WebDriver driver;
    private Wait<WebDriver> wait;
    @FindBy(xpath = "//span[contains(@class, 'b-toolbar__btn__text') and contains(text(), 'Написать')]")
    private WebElement writeLetterButton;

    @FindBy(xpath = "//textarea[@data-original-name='To']")
    private WebElement recipientInput;

    @FindBy(xpath = "//div[contains(@class, 'compose-head__field')]/input[contains(@class, 'b-input')]")
    private WebElement subjectInput;

    @FindBy(xpath = "//body[@id='tinymce']")
    private WebElement bodyInput;

    @FindBy(xpath = "//span[contains(@class, 'b-toolbar__btn__text') and contains(text(), 'Отправить')]")
    private WebElement sendButton;

    @FindBy(xpath = "//div[contains(@class, 'message-sent__title')]")
    private WebElement resultOfDepartureLabel;

    @FindBy(tagName = "iframe")
    private List<WebElement> frames;


    public MailBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getResultOfDeparture() {
        return resultOfDepartureLabel.getText();
    }

    public void sendLetter(String recipient, String subject, String text) {
        wait = new WebDriverWait(driver, 10).withMessage("Can't find element");
        wait.until(ExpectedConditions.visibilityOf(writeLetterButton));
        writeLetterButton.click();
        recipientInput.sendKeys(recipient);
        subjectInput.sendKeys(subject);
        driver.switchTo().frame(frames.get(frames.size() - 1)); // количество фреймов постоянно меняется, поэтому такой костыль для перехода в последний
        bodyInput.sendKeys(text);
        driver.switchTo().defaultContent();
        sendButton.click();
        wait.until(ExpectedConditions.visibilityOf(resultOfDepartureLabel));
    }
}
