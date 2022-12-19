package mk.finki.ukim.mk.lab.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AddOrEditPage extends AbstractPage {

    public AddOrEditPage(WebDriver driver) {
        super(driver);
    }

    private WebElement name;
    private WebElement description;
    private WebElement manufacturer;
    private WebElement submit;

    public static BalloonsPage addBalloon(WebDriver driver, String name, String description, String manufacturer) {
        get(driver, "/balloons/add-form");
        AddOrEditPage addOrEditPage = PageFactory.initElements(driver, AddOrEditPage.class);
        addOrEditPage.name.sendKeys(name);
        addOrEditPage.description.sendKeys(description);
        addOrEditPage.manufacturer.click();
        addOrEditPage.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();
        addOrEditPage.submit.click();
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public static BalloonsPage editBalloon(WebDriver driver, WebElement editButton, String name, String description, String manufacturer) {
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditPage addOrEditProduct = PageFactory.initElements(driver, AddOrEditPage.class);
        addOrEditProduct.name.sendKeys(name);
        addOrEditProduct.description.sendKeys(description);
        addOrEditProduct.manufacturer.click();
        addOrEditProduct.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();
        addOrEditProduct.submit.click();
        return PageFactory.initElements(driver,BalloonsPage.class);
    }


}
