package org.example.tests;

import org.example.pages.*;
import org.example.util.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    HomePageHelper homePage;
    @BeforeMethod
      public void initTests(){
        log4j.info("LoginTest:@BeforeMethod initTests()");
        loginPage= PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage= PageFactory.initElements(driver,BoardsPageHelper.class);
        homePage=PageFactory.initElements(driver,HomePageHelper.class);

        homePage.waitUntilHomePageLoaded().openLoginPage();
        loginPage.waitUntilLoginPageIsLoaded();
    }
    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderLoginEmailIncorrect")
    public void loginNegativeLoginIncorrect(String login,String password,String errorMessage)  {
        log4j.startTestCase("loginNegativeLoginIncorrect()");
        log4j.info("Parameter login - " + login);
        log4j.info("Parameter password - " + password);
        log4j.info("Parameter errorMessage - " + errorMessage);
        loginPage.loginNotAtlassian(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),errorMessage,
                        "The error message is not correct");
        log4j.info("-----Test case was finished------");

    }
    @Test
    public void loginNegativeLoginEmpty()  {
        log4j.startTestCase("loginNegativeLoginEmpty()");
        log4j.info("Parameter login - " + "");
        log4j.info("Parameter password - " + PASSWORD);
        loginPage.loginNotAtlassian("",PASSWORD)
                .pressLoginButton();
        log4j.info("-----Test case was finished------");
        Assert.assertEquals(loginPage.getErrorMessage(),"Missing email",
                "The text of the error message is not correct");
    }
    @Test
    public void passwordIncorrectNegativeTest() {
        log4j.startTestCase("passwordIncorrectNegativeTest()");
        loginPage.loginAsAtlassian(LOGIN,PASSWORD+"1");
        log4j.info("Parameter login - " + LOGIN);
        log4j.info("Parameter password - " + PASSWORD+"1");
        log4j.info("Parameter errorMessage - " + "Incorrect email address and");
        Assert.assertTrue(loginPage.getAtlassianErrorMessage().contains("Incorrect email address and"),
                          "The error message is not contains the text 'Incorrect email address and'");
        log4j.info("-----Test case was finished------");
    }
    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderFirst")
    public void LoginPositive (String login,String password){
        log4j.startTestCase("LoginPositive");
        log4j.info("Parameter login - " + login);
        log4j.info("Parameter password - " + password);
        loginPage.loginAsAtlassian(login,password);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertTrue(loginPage.getBoardsIconName().equals("Boards"),"The text on the button is not 'Boards'");
        log4j.info("-----Test case was finished------");
      }
}
