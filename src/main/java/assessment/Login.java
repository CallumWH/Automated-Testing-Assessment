package assessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Login 
{
	@FindBy(name = "j_username")
	private WebElement userName;

	@FindBy(name = "j_password")
	private WebElement password;

	@FindBy(name = "Submit")
	private WebElement submitButton;

	public void LogIntoWebsite(String user, String pass, WebDriver driver) throws InterruptedException {

		userName.sendKeys(user);
		password.sendKeys(pass);

		Actions action = new Actions(driver);
		action.click(submitButton);
		action.perform();

	}
	
	public void GoToManage(WebDriver driver)
	{
		WebElement manageJenkings = driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[4]/a[2]"));
		Actions action = new Actions(driver);
		action.click(manageJenkings);
		action.perform();
	}
	
	public void GoToManageUsers(WebDriver driver)
	{
		WebElement manageUsers = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[16]"));
		Actions action = new Actions(driver);
		action.click(manageUsers);
		action.perform();
	}
	
	public void GoToCreateUsers(WebDriver driver)
	{
		WebElement createUsers = driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]/a[2]"));
		Actions action = new Actions(driver);
		action.click(createUsers);
		action.perform();
	}
}
