package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestyPrihlasovaniNaKurzy {

    WebDriver browser;

    public void fillLogInForm() {
        WebElement emailForm = browser.findElement(By.id("email"));
        WebElement passwordForm = browser.findElement(By.id("password"));
        WebElement submit = browser.findElement(By.xpath("//button[@type='submit']"));

        emailForm.sendKeys("misalangelo@gmail.com");
        passwordForm.sendKeys("Automatizace1");
        submit.click();
    }

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void logInForm() {
        browser.navigate().to("https://cz-test-dva.herokuapp.com/prihlaseni");

        fillLogInForm();

        WebElement succesfullyLogedIn = browser.findElement(By.xpath("//span[text() = 'Přihlášen']"));
        Assertions.assertNotNull("succesfullyLogedIn");
    }

    @Test
    public void registrationWithExistingAccountFirstChooseThenLogIn() {
        browser.navigate().to("https://cz-test-dva.herokuapp.com/31-trimesicni-kurzy-programova");

        WebElement createApplication = browser.findElement(By.xpath("//a[@href = 'https://cz-test-dva.herokuapp.com/zaci/pridat/71-java-1']"));
        createApplication.click();

        fillLogInForm();

        WebElement chooseDate = browser.findElement(By.xpath("//div[contains(text(),'Vyberte termín...')]"));
        chooseDate.click();
        WebElement specificDate = browser.findElement(By.className("text"));
        specificDate.click();
        WebElement fillInForename = browser.findElement(By.id("forename"));
        fillInForename.click();
        fillInForename.sendKeys("Míša");
        WebElement fillInSurname = browser.findElement(By.id("surname"));
        fillInSurname.click();
        fillInSurname.sendKeys("Langová");
        WebElement fillInBirthday = browser.findElement(By.id("birthday"));
        fillInBirthday.click();
        fillInBirthday.sendKeys("6.6.2006");
        WebElement choosePayForm = browser.findElement(By.xpath("//span/label[@for = 'payment_cash']"));
        choosePayForm.click();
        WebElement termsConditionsAprove = browser.findElement(By.xpath("//span/label[@for = 'terms_conditions']"));
        termsConditionsAprove.click();
        WebElement submitRegistrationOfChild = browser.findElement(By.xpath("//input[@type='submit']"));
        submitRegistrationOfChild.click();

        WebElement printRegistration = browser.findElement(By.xpath("//a[@title = 'Stáhnout potvrzení o přihlášení']"));
        Assertions.assertNotNull(printRegistration);

        WebElement buttonPrihlasky = browser.findElement(By.xpath("//a[@href = 'https://cz-test-dva.herokuapp.com/zaci']"));
        buttonPrihlasky.click();

        WebElement buttonDetail = browser.findElement(By.xpath("//a[@title = 'Zobrazit']"));
        Assertions.assertNotNull(buttonDetail);
    }


    @Test
    public void registrationWithExistingAccountLogInFirst() {
        browser.navigate().to("https://cz-test-dva.herokuapp.com/prihlaseni");

        fillLogInForm();

        WebElement buttonVytvoritPrihlasku = browser.findElement(By.xpath("//div/a/i[contains(@class, 'fa-plus-circle')]"));
        buttonVytvoritPrihlasku.click();

        List<WebElement> listOfButtonsCourses = browser.findElements(By.xpath("//div[@class = 'card']//a[text() = 'Více informací']"));
        WebElement thirdButtonViceInformaci = listOfButtonsCourses.get(2);
        thirdButtonViceInformaci.click();

        WebElement createApplication = browser.findElement(By.xpath("//a[@href = 'https://cz-test-dva.herokuapp.com/zaci/pridat/71-java-1']"));
        createApplication.click();
        WebElement chooseDate = browser.findElement(By.xpath("//div[contains(text(),'Vyberte termín...')]"));
        chooseDate.click();
        WebElement specificDate = browser.findElement(By.xpath("//span[contains(text(), '                                            28.06. - 02.07.2021')]"));
        specificDate.click();
        WebElement fillInForename = browser.findElement(By.id("forename"));
        fillInForename.click();
        fillInForename.sendKeys("Míša");
        WebElement fillInSurname = browser.findElement(By.id("surname"));
        fillInSurname.click();
        fillInSurname.sendKeys("Langová");
        WebElement fillInBirthday = browser.findElement(By.id("birthday"));
        fillInBirthday.click();
        fillInBirthday.sendKeys("6.6.2006");
        WebElement choosePayForm = browser.findElement(By.xpath("//span/label[@for = 'payment_cash']"));
        choosePayForm.click();
        WebElement termsConditionsAprove = browser.findElement(By.xpath("//span/label[@for = 'terms_conditions']"));
        termsConditionsAprove.click();
        WebElement submitRegistrationOfChild = browser.findElement(By.xpath("//input[@type='submit']"));
        submitRegistrationOfChild.click();

        WebElement printRegistration = browser.findElement(By.xpath("//a[@title = 'Stáhnout potvrzení o přihlášení']"));
        Assertions.assertNotNull(printRegistration);
    }

    @Test
    //parent should be able to quit registration in a list of application on existing account, logged in first
    //pre.:single application form created first, cleared list of applications
    public void quittingExistingApplicationLogedInFirst() {
        browser.navigate().to("https://cz-test-dva.herokuapp.com/prihlaseni");
        //Logg In
        fillLogInForm();

        //Create application
        WebElement buttonVytvoritPrihlasku = browser.findElement(By.xpath("//div/a/i[contains(@class, 'fa-plus-circle')]"));
        buttonVytvoritPrihlasku.click();

        List<WebElement> listOfButtonsCourses = browser.findElements(By.xpath("//div[@class = 'card']//a[text() = 'Více informací']"));
        WebElement thirdButtonViceInformaci = listOfButtonsCourses.get(2);
        thirdButtonViceInformaci.click();

        WebElement createApplication = browser.findElement(By.xpath("//a[@href = 'https://cz-test-dva.herokuapp.com/zaci/pridat/71-java-1']"));
        createApplication.click();
        WebElement chooseDate = browser.findElement(By.xpath("//div[contains(text(),'Vyberte termín...')]"));
        chooseDate.click();
        WebElement specificDate = browser.findElement(By.xpath("//span[contains(text(), '                                            28.06. - 02.07.2021')]"));
        specificDate.click();
        WebElement fillInForename = browser.findElement(By.id("forename"));
        fillInForename.click();
        fillInForename.sendKeys("Míša");
        WebElement fillInSurname = browser.findElement(By.id("surname"));
        fillInSurname.click();
        fillInSurname.sendKeys("Langová");
        WebElement fillInBirthday = browser.findElement(By.id("birthday"));
        fillInBirthday.click();
        fillInBirthday.sendKeys("6.6.2006");
        WebElement choosePayForm = browser.findElement(By.xpath("//span/label[@for = 'payment_cash']"));
        choosePayForm.click();
        WebElement termsConditionsAprove = browser.findElement(By.xpath("//span/label[@for = 'terms_conditions']"));
        termsConditionsAprove.click();
        WebElement submitRegistrationOfChild = browser.findElement(By.xpath("//input[@type='submit']"));
        submitRegistrationOfChild.click();

        //Quit application
        WebElement buttonOdhlaseniUcasti = browser.findElement(By.xpath("//a[contains(text(), 'Odhlášení účasti')]"));
        buttonOdhlaseniUcasti.click();

        WebElement chooseNemoc = browser.findElement(By.xpath("//label[contains(text(), 'Nemoc')]"));
        chooseNemoc.click();

        WebElement buttonOdhlasitZaka = browser.findElement(By.xpath("//input[@value = 'Odhlásit žáka']"));
        buttonOdhlasitZaka.click();
    }


    @AfterEach
    public void tearDown() {
        browser.close();
    }
}
