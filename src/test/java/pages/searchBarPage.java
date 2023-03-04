package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.commonMethods;

public class searchBarPage extends commonMethods {

@FindBy(xpath = "//*[@id=\"twotabsearchtextbox\"]")
    public WebElement searchBarBtn;

@FindBy(xpath = "//*[@id=\"nav-search-submit-button\"]")
    public WebElement searchBtn;

@FindBy(xpath = "//*[@id=\"nav-subnav\"]/a[1]")
public WebElement bookTab;
    public searchBarPage(){
        PageFactory.initElements(driver, this);
    }
}
