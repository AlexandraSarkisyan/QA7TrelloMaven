package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityPageHelper extends PageBase{
    @FindBy(xpath = "//a[@data-tab='activity']")
    WebElement profileTab;
    @FindBy(xpath = "//button[@data-test-id='header-boards-menu-button']")
    WebElement boardsButton;
    @FindBy (xpath = "//div[@class = 'phenom-desc']")
    List<WebElement> recordsList;
    public ActivityPageHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementsAreVisible(recordsList,15);
    }
    public String getTitleCardFromEvent(){
        return driver.findElement(By.xpath("//a[@class='action-card']")).getText();}

    public String getFirstRecordText() {
        return  recordsList.get(0).getText();
    }

    public String getActivityTabName() {
        return profileTab.getAttribute("profileTab");
    }
}
