import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StudentRegistrationForm {

    WebDriver driver;
    String demoSiteUrl = "https://demoqa.com/automation-practice-form";

    By adsbelow= By.xpath("//div[contains(@id,'fixedban')]");

    By adsright= By.xpath("//section[contains(@id,'Right')]");
    By adsYoutube=By.xpath("//div[contains(@id,'Ad.Plus-728x90_0')]");
    By userFirstName= By.id("firstName");
    By userLastName= By.cssSelector("#lastName");

    By userEmailAddress= By.xpath("//input[@id='userEmail']");

    By userGender = By.xpath("//label[contains(@for,'gender')]");

    By userPhoneNumber= By.cssSelector("#userNumber");

    By userDob= By.id("dateOfBirthInput");

    By calendarday=By.xpath("//div[contains(@class,'day--')]");

    By calendarmonth= By.xpath("//select[contains(@class,'month')]/option");

    By calendaryear= By.xpath("//select[contains(@class,'year')]/option");

    By subjectcontrol= By.id("subjectsContainer");


    By subjectmenu= By.cssSelector("div[class*='auto-complete__menu']");

    By hobbies=By.xpath("//label[contains(@for,'hobbies-checkbox')]");

    By selectFile=By.id("uploadPicture");

    By userAddress= By.xpath("//textarea[@id='currentAddress']");

    By selectState=By.xpath("//div[contains(text(),'State')]");

    By selectCity=By.xpath("//div[contains(text(),'City')]");

    By submit=By.id("submit");



    WebElement txtBoxUserFirstName;

    WebElement txtBoxUserLastName;

    WebElement txtBoxEmailAddress;

    List<WebElement> radiooptionsUserGender;

    WebElement txtPhoneNumber;

    WebElement dtpDateOfBirth;

    List<WebElement> tdSelectDays;

    List<WebElement> tdSelectMonth;

    List<WebElement> tdSelectYear;

    WebElement txtSubject;

    List<WebElement> checkboxHobbies;

    WebElement btnFileUpload;

    WebElement txtAreaAddress;

    List<WebElement> dropdownlistState;

    List<WebElement> dropdownlistCity;

    WebElement btnSubmit;

    @BeforeTest
    void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.navigate().to(demoSiteUrl);
        driver.manage().window().maximize();



        //hide ads present in website
        hideElement(adsbelow);
        hideElement(adsright);
        hideElement(adsYoutube);


    }

    @Test (enabled = true,priority = 1)
    void InputStudentName()
    {
     txtBoxUserFirstName= driver.findElement(userFirstName);
     txtBoxUserLastName= driver.findElement(userLastName);
        txtBoxUserFirstName.sendKeys("TESTNAME");
        txtBoxUserLastName.sendKeys("TESTLASTNAME");


    }


    @Test (enabled = true,priority = 2)

    void InputStudentEmail()
    {
        txtBoxEmailAddress=driver.findElement(userEmailAddress);
        txtBoxEmailAddress.sendKeys("testmas@test.com");
    }


    @Test (enabled = true,priority = 3)

    void SelectStudentGender()
    {



//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
        radiooptionsUserGender= driver.findElements(userGender);

//        System.out.println(radiooptionsUserGender.size());
//        System.out.println(radiooptionsUserGender.get(0).getText());

        radiooptionsUserGender.stream().filter(i -> i.getText().equals("Other")).findFirst().get().click();


    }

    @Test (enabled = true,priority = 3)

    void InputPhoneNumber()
    {

        txtPhoneNumber=driver.findElement(userPhoneNumber);
        txtPhoneNumber.sendKeys("8117658901");

    }

    @Test (enabled = true,priority = 4)
    void DateofBirthpicker()

    {
        String selectMonth="May";
        String selectDay="16";
        String selectYear="1993";

               JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", txtPhoneNumber);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        dtpDateOfBirth=driver.findElement(userDob);

            dtpDateOfBirth.click();

//    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        tdSelectYear= driver.findElements(calendaryear);

        ReturnDate(tdSelectYear,selectYear);

        tdSelectMonth= driver.findElements(calendarmonth);

        ReturnDate(tdSelectMonth,selectMonth);

        //        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        tdSelectDays= driver.findElements(calendarday);
        ReturnDate(tdSelectDays,selectDay);
        dtpDateOfBirth.sendKeys(Keys.TAB);

        }


    @Test (enabled = true,priority = 5)
    void InputSubjects()
    {
        String testsubject="com";



        String subjectxpath="//div[contains(text(),'"+testsubject+"')]";


//
//
//
//
          txtSubject= driver.findElement(subjectcontrol);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", txtSubject);

//        WebDriverWait waitsubject = new WebDriverWait(driver, Duration.ofSeconds(20));
//        waitsubject.until(ExpectedConditions.elementToBeClickable(subjectcontrol)).click();




         txtSubject.click();
        Actions builder = new Actions(driver);
        builder.moveToElement(txtSubject).sendKeys(testsubject).build().perform();


        WebElement subjectSelect = driver.findElement(subjectmenu);

        List<WebElement> subjectOptions = subjectSelect.findElements(By.xpath(subjectxpath));

        System.out.println(subjectOptions.size());

        subjectOptions.stream()
                .forEach(subj-> System.out.println("Selected "+
                        subj.getText()
                ));

        subjectSelect.click();
//        builder.moveToElement(txtSubject).sendKeys(Keys.ENTER).build().perform();


//        clickLastFoundElement(txtSubject,testsubject);

    }


    @Test (enabled = true,priority = 6)
    void SelectHobby()

    {

        checkboxHobbies=driver.findElements(hobbies) ;

        checkboxHobbies.stream().filter(i -> i.getText().equals("Music")).findFirst().get().click();

    }


    @Test (enabled = true,priority = 7)
    void SelectPicture()

    {
      String File ="Testing-3-2.jpg";

      String filetoUpload=System.getProperty("user.dir")+"//imgs//"+File;
//        System.out.println(filetoUpload);

        btnFileUpload=driver.findElement(selectFile);

        btnFileUpload.sendKeys(filetoUpload);


    }

    @Test (enabled = true,priority = 8)
    void InputUserAddress()
    {
        txtAreaAddress=driver.findElement(userAddress);

        txtAreaAddress.sendKeys("Address Test");

    }

    @Test (enabled = true,priority = 9)
    void SelectStateandCity()
    {

        String teststate="Utt";
        String testcity="Merr";

        String statexpath="//*[contains(text(),'"
                +  teststate+"')]";

        String cityxpath="//*[contains(text(),'"
                +  testcity+"')]";
         WebElement State=driver.findElement(selectState);
        State.click();
        dropdownlistState= State.findElements(By.xpath(statexpath));

        dropdownlistState.stream()
                .forEach(state-> System.out.println("State "+
                        state.getText()
                ));


        dropdownlistState.get(0).click();


        WebElement City =driver.findElement(selectCity);
        City.click();

        dropdownlistCity=City.findElements(By.xpath(cityxpath));

        dropdownlistCity.stream()
                .forEach(state-> System.out.println("city "+
                        state.getText()
                ));

        dropdownlistCity.get(0).click();



    }


    @Test (enabled = true,priority = 10)

    void findHiddensubmitbtn()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.transform='scale(0.5)';");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        btnSubmit=driver.findElement(submit);
        btnSubmit.click();



    }

    @Test (enabled = true,priority = 11)

    void Closeform()
    {


        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("$('#closeLargeModal').trigger('click');");


    }




        void ReturnDate( List<WebElement> selection, String dateselect)

    {
        for ( WebElement userpick : selection) {

//               System.out.println(month.getText());

            if (userpick.getText().equals(dateselect)) {
                userpick.click();
                break;
            }
        }

    }


    public void hideElement(By ads)
    {
       WebElement element = driver.findElement(ads);

        ((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility='hidden'", element);
    }


    public void clickLastFoundElement(WebElement lastFoundElement, String key) {
        try {
            Actions builder = new Actions(driver);
            builder.moveToElement(lastFoundElement).sendKeys(key).build().perform();
            builder.moveToElement(lastFoundElement).sendKeys(Keys.ENTER).build().perform();

//            builder.moveToElement(lastFoundElement).click().build().perform();
        } catch (ElementNotInteractableException x1) {
            try {
                lastFoundElement.click();
            }
            catch (Exception x2) {
                hardClickElement(lastFoundElement,key);
            }
        }
    }


    private void hardClickElement(WebElement lastFoundElement,String value) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].setAttribute('value', `arguments[1]');", lastFoundElement, value);
        executor.executeScript("arguments[0].sendKeys(Keys.ENTER);", lastFoundElement);
    }



}
