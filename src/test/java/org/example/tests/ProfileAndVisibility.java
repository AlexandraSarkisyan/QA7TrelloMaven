package org.example.tests;
import org.example.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileAndVisibility extends TestBase{
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7currentBoard;
    MenuPageHelper menuPage;
    ProfileAndVisibilityPageHelper profileAndVisibilityPage;
    @BeforeMethod
    public void initTest() {
        loginPage= PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage=PageFactory.initElements(driver,BoardsPageHelper.class);
        qaHaifa7currentBoard = new CurrentBoardPageHelper(driver,"QAHaifa7");
        homePage =PageFactory.initElements(driver,HomePageHelper.class);
        profileAndVisibilityPage=PageFactory.initElements(driver,ProfileAndVisibilityPageHelper.class);
        menuPage= PageFactory.initElements(driver,MenuPageHelper.class);

        homePage.waitUntilHomePageLoaded().openLoginPage();
        loginPage.waitUntilLoginPageIsLoaded().loginAsAtlassian(LOGIN,PASSWORD);
        //Open QA7Haifa board
        boardsPage.waitUntilPageIsLoaded().openCurrentBoardPage("QAHaifa7");
        qaHaifa7currentBoard.waitUntilPageIsLoaded();
        //open member menu
        qaHaifa7currentBoard.openMemberMenu();
        menuPage.waitUntilPageIsLoaded();
        //Open Profile&Visibility Page
        menuPage.openProfileVisibility();
        profileAndVisibilityPage.waitUntilPageIsLoaded();
    }
    @Test
    public void isProfileVisibilityPage(){
        Assert.assertEquals(profileAndVisibilityPage.getProfileVisibilityTabName(), "Profile and Visibility");
    }

    @Test
    public void userNameVerification(){
        String titleMenu = profileAndVisibilityPage.getTitleMenuIcon();
        String userNameInTitle = titleMenu.substring(titleMenu.indexOf("(")+1,titleMenu.length()-1);
        String userName = profileAndVisibilityPage.getUserName();
        Assert.assertEquals(userNameInTitle, userName);
    }
}
