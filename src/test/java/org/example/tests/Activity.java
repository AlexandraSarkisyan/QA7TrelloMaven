package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Activity extends TestBase{
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7currentBoard;
    MenuPageHelper menuPage;
    ActivityPageHelper activityPage;

    @BeforeMethod
    public void initTest(){
        loginPage= PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage=PageFactory.initElements(driver, BoardsPageHelper.class);
        qaHaifa7currentBoard = new CurrentBoardPageHelper(driver,"QAHaifa7");
        homePage =PageFactory.initElements(driver, HomePageHelper.class);
        menuPage= PageFactory.initElements(driver,MenuPageHelper.class);
        activityPage= PageFactory.initElements(driver,ActivityPageHelper.class);

        homePage.waitUntilHomePageLoaded().openLoginPage();
        loginPage.waitUntilLoginPageIsLoaded().loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded().openCurrentBoardPage("QAHaifa7");
        qaHaifa7currentBoard.waitUntilPageIsLoaded();
    }

    @Test
    public void activityEventIsCorrect(){
        qaHaifa7currentBoard.addNewCardInFirstList("NewCard");
        qaHaifa7currentBoard.openMemberMenu();
        menuPage.waitUntilPageIsLoaded();
        menuPage.openActivityPage();
        activityPage.waitUntilPageIsLoaded();
        Assert.assertEquals(activityPage.getTitleCardFromEvent(),"NewCard");
    }



    /* @Test
    public void isActivityPage(){
        Assert.assertEquals(activityPage.getActivityTabName(), "activity");
    }*/

}
