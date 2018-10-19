package assessment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InputDetails {
	@FindBy(name = "username")
	private WebElement userName;

	@FindBy(name = "password1")
	private WebElement password;

	@FindBy(name = "password2")
	private WebElement passwordConfirm;

	@FindBy(name = "fullname")
	private WebElement fullName;

	@FindBy(name = "email")
	private WebElement emailAddress;

	@FindBy(name = "Submit")
	private WebElement submitButton;

	public void SubmitUser(String user, String pass, String email, WebDriver driver) throws InterruptedException {

		userName.sendKeys(user);
		password.sendKeys(pass);
		passwordConfirm.sendKeys(pass);
		fullName.sendKeys(user);
		emailAddress.sendKeys(email);
	}
	
	public void SubmitUser(String user, String pass, String passConfirm, String email, String name, WebDriver driver) throws InterruptedException {

		userName.sendKeys(user);
		password.sendKeys(pass);
		passwordConfirm.sendKeys(passConfirm);
		fullName.sendKeys(name);
		emailAddress.sendKeys(email);
	}
	
	public void Submit(WebDriver driver)
	{
		submitButton = driver.findElement(By.xpath("//*[@id=\"yui-gen2-button\"]"));
		submitButton.click();
	}
	
	public void clickUserName(String user, WebDriver driver)
	{
		List<WebElement> userName = driver.findElements(By.linkText(user));
		
		userName.size();
		
		for(int i = 0; i < userName.size(); i++)

		{
			if(userName.get(i).getAttribute("href").equals("http://localhost:8080/securityRealm/user/" + user +"/"))
			{
				userName.get(i).click();
			}
		}
	}
}
