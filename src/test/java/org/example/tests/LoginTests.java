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
        loginPage= PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage= PageFactory.initElements(driver,BoardsPageHelper.class);
        homePage=PageFactory.initElements(driver,HomePageHelper.class);

        homePage.waitUntilHomePageLoaded().openLoginPage();
        loginPage.waitUntilLoginPageIsLoaded();
    }
    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderLoginEmailIncorrect")
    public void loginNegativeLoginIncorrect(String login,String password,String errorMessage)  {
        loginPage.loginNotAtlassian(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),errorMessage,
                        "The error message is not correct");
    }
    @Test
    public void passwordIncorrectNegativeTest() {
        loginPage.loginAsAtlassian(LOGIN,PASSWORD+"1");
        Assert.assertTrue(loginPage.getAtlassianErrorMessage().contains("Incorrect email address and"),
                          "The error message is not contains the text 'Incorrect email address and'");
    }
    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderFirst")
    public void LoginPositive (String login,String password){
        loginPage.loginAsAtlassian(login,password);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertTrue(loginPage.getBoardsIconName().equals("Boards"),"The text on the button is not 'Boards'");

      }
}
