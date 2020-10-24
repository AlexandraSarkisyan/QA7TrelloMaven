package org.example.tests;
import org.example.pages.*;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7currentBoard;
    MenuPageHelper menuPage;

    @BeforeMethod
    public void initTest() {
        log4j.info("LoginTest:@BeforeMethod initTests()");
        loginPage= PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage=PageFactory.initElements(driver,BoardsPageHelper.class);
        qaHaifa7currentBoard = new CurrentBoardPageHelper(driver,"QAHaifa7");
        homePage =PageFactory.initElements(driver,HomePageHelper.class);
        menuPage= PageFactory.initElements(driver, MenuPageHelper.class);

        homePage.waitUntilHomePageLoaded().openLoginPage();
        loginPage.waitUntilLoginPageIsLoaded().loginAsAtlassian(LOGIN,PASSWORD);
        //Open QA7Haifa board
        boardsPage.waitUntilPageIsLoaded().openCurrentBoardPage("QAHaifa7");
        qaHaifa7currentBoard.waitUntilPageIsLoaded();
    }

    @Test
    public void isCorrectCurrentBoard(){
        Assert.assertEquals(qaHaifa7currentBoard.getCurrentBoardHeader(),"QAHaifa7",
                "The header of the screen is not 'QAHaifa7'");
    }

    @Test
    public void isCorrectCurrentBoard2(){
        Assert.assertTrue(qaHaifa7currentBoard.isCorrectCurrentBoard(),
                "The header of the screen is not 'QA Haifa7'");
    }
    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderCreateList")
    public void addNewListPositiveTest(String title) {
        //Add new list to the board
        int quantityListsInTheBeginning = qaHaifa7currentBoard.getQuantityLists();
        qaHaifa7currentBoard.addNewListToCurrentBoard(title);
        int quantityListsAtTheEnd= qaHaifa7currentBoard.getQuantityLists();
        Assert.assertEquals(quantityListsAtTheEnd, quantityListsInTheBeginning +1, "The quantityListsAtTheEnd is not quantityListsInTheBeginning-1 ");
    }
    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderCreateListRandom")
    public void addNewListPositiveTest2(String title) {
        log4j.startTestCase("addNewListPositiveTest2");
        log4j.info("Parameter title - " + title);
        int quantityListsInTheBeginning = qaHaifa7currentBoard.getQuantityLists();
        log4j.info("Num of Pages: "+quantityListsInTheBeginning);
        qaHaifa7currentBoard.addNewListToCurrentBoard(title);
        int quantityListsAtTheEnd= qaHaifa7currentBoard.getQuantityLists();
        log4j.info("Num of Pages: "+quantityListsAtTheEnd);
        Assert.assertEquals(quantityListsAtTheEnd, quantityListsInTheBeginning +1, "The quantityListsAtTheEnd is not quantityListsInTheBeginning-1 ");
        log4j.info("-----Test case was finished------");
    }


    @Test
    public void putAnyListToArchive() {
        //If there are no lists create the new list
        if (qaHaifa7currentBoard.getTextAddListButton().equals("Add a list")) {
            qaHaifa7currentBoard.addNewListToCurrentBoard("test");
        }
        int quantityListsInTheBeginning = qaHaifa7currentBoard.getQuantityLists();
        System.out.println("Text on the button: " + qaHaifa7currentBoard.getTextAddListButton());
        qaHaifa7currentBoard.openListMenu().putListToTheArchive();
        int quantityListsAtTheEnd = qaHaifa7currentBoard.getQuantityLists();
        Assert.assertEquals(quantityListsAtTheEnd, quantityListsInTheBeginning - 1, "The quantityListsAtTheEnd is not quantityListsInTheBeginning-1 ");
    }
}