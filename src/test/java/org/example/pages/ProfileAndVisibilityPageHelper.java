package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileAndVisibilityPageHelper extends PageBase{
    @FindBy(xpath = "//button[@aria-label = 'Open Member Menu']")
    WebElement memberMenuButton;
    @FindBy(xpath = "//input[@name='username']")
    WebElement usernameField;
    @FindBy(xpath = "//a[@data-tab='profile']")
    WebElement profileTab;
    public ProfileAndVisibilityPageHelper(WebDriver driver) {

        super(driver);
    }

    public String getTitleMenuIcon() {
            return memberMenuButton.getAttribute("title");
    }

    public String getUserName() {
            return usernameField.getAttribute("value");
        }
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(usernameField,10);
        waitUntilElementIsVisible(profileTab,10);
    }

    public String getProfileVisibilityTabName(){
        return profileTab.getText();
    }
}
