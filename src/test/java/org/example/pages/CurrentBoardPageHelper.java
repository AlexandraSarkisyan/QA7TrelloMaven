package org.example.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrentBoardPageHelper extends PageBase {
    @FindBy(css = "a.list-header-extras-menu")
    WebElement listMenuButton;

    @FindBy(id = "workspaces-preamble-board-header-button")
    WebElement boardsButton;

    @FindBy(tagName = "h1")
    WebElement header;

    @FindBy(xpath = "//div[@class = 'list js-list-content']")
    List<WebElement> listElementsList;

    @FindBy(css = "a.open-add-list")
    WebElement addListButton;

    @FindBy(xpath = "//input[@class='list-name-input']")
            WebElement listNameField;
    @FindBy(xpath = "//button[@aria-label = 'Open Member Menu']")
            WebElement memberMenu;
    @FindBy(xpath = "//input[@class='primary mod-list-add-button js-save-edit']")
            WebElement saveNewListButton;
    @FindBy(css = "a.icon-close.dark-hover")
            WebElement closeAddNewListButton;
    @FindBy(xpath = "//a[@class='js-close-list']")
            WebElement putListToArchiveButton;
    @FindBy(xpath = "//span[@class='icon-sm icon-add']")
            WebElement addCardButton;
    @FindBy(xpath = "//textarea[@placeholder='Enter a title for this card…']")
            WebElement cardTitleButton;
    @FindBy(xpath = "//input[@class='primary confirm mod-compact js-add-card']")
            WebElement submitNewCard;
    @FindBy(xpath = "//a[@class='icon-lg icon-close dark-hover js-cancel']")
            WebElement cancelButton;
    @FindBy(xpath = "//a[@class ='open-card-composer js-open-card-composer']")
    List<WebElement> addCardButtonList;
    String boardName;

    public CurrentBoardPageHelper(WebDriver driver,String boardName) {
        super(driver);
        this.boardName=boardName;
        PageFactory.initElements(driver,this);
    }
    public CurrentBoardPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(boardsButton, 10);
        waitUntilElementIsVisible(header, 10);
        return this;
    }
    public String getCurrentBoardHeader(){
        return header.getText();
    }
    public void initiateAddList() {
        waitUntilElementIsClickable(addListButton,10);
        addListButton.click();
    }
    public CurrentBoardPageHelper fillTheNameAndSubmit(String name) {
        waitUntilElementIsClickable(listNameField, 10);
        editField(listNameField,name);
        saveNewList();
        return this;
    }
    public boolean isCorrectCurrentBoard() {
        return driver.findElement(By.tagName("h1")).getText().equals(this.boardName);
    }
    public int getQuantityLists() {
        waitUntilElementsAreVisible(listElementsList, 15);
        return listElementsList.size();
    }
    public CurrentBoardPageHelper addNewListToCurrentBoard(String title) {
        this.initiateAddList();
        this.fillTheNameAndSubmit(title);
        this.closeAddingNewList();
        return this;
    }

    public CurrentBoardPageHelper saveNewList() {
        waitUntilElementIsClickable(saveNewListButton, 10);
        saveNewListButton.click();
        return this;
    }

    public CurrentBoardPageHelper closeAddingNewList() {
        //close x button
        waitUntilElementIsClickable(closeAddNewListButton, 5);
        closeAddNewListButton.click();
        waitUntilElementIsInVisible(closeAddNewListButton, 5);
        return this;
    }

    public CurrentBoardPageHelper putListToTheArchive() {
        waitUntilElementIsClickable(putListToArchiveButton, 10);
        putListToArchiveButton.click();
        waitUntilElementIsInVisible(putListToArchiveButton,10);
        return this;
    }

    public CurrentBoardPageHelper openListMenu() {
        waitUntilElementIsClickable(listMenuButton,10);
        listMenuButton.click();
        return this;
    }

    public String getTextAddListButton() {
        return addListButton.getText();
    }

    public CurrentBoardPageHelper openMemberMenu() {
        waitUntilElementIsClickable(memberMenu,10);
        memberMenu.click();
        return this;
    }
    public void addNewCardInFirstList(String title){
        waitUntilElementIsClickable(addCardButton,10);
        addCardButtonList.get(0).click();
        waitUntilElementIsClickable(cardTitleButton,10);
        editField(cardTitleButton,title);
        submitNewCard();
        closeAddingCardXButton();

    }
    public CurrentBoardPageHelper closeAddingCardXButton() {
        waitUntilElementIsClickable(cancelButton,10);
        cancelButton.click();
        waitUntilElementIsInVisible(cancelButton,10);
        return this;
    }

    public CurrentBoardPageHelper submitNewCard() {
        waitUntilElementIsClickable(submitNewCard,10);
        submitNewCard.click();
        return this;
    }

}
