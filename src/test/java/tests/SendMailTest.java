package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.MailBoxPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class SendMailTest {

    private WebDriver driver;

    @BeforeMethod
    @Parameters("url")
    public void seUP(String url) {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/seleniumgrid/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    @Parameters({"login", "mailbox", "password", "recipient", "subject", "text"})
    public void checkSendLetter(String login, String mailbox, String password, String recipient, String subject, String text) {
        MainPage mainPage = new MainPage(driver);
        mainPage.login(login, mailbox, password);
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        mailBoxPage.sendLetter(recipient, subject, text);
        Assert.assertTrue(mailBoxPage.getResultOfDeparture().contains("Ваше письмо отправлено. Перейти во Входящие"));
    }
}
