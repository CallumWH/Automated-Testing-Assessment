package assessment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Checker 
{
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

	public String CheckUser() throws InterruptedException 
	{
		
		String returnValue = userName.getText();
		return returnValue;
	}
	
	public String CheckPassword()
	{
		return password.getText();
	}
	
	public String CheckPasswordConfirm()
	{
		return passwordConfirm.getText();
	}
	
	public String CheckName()
	{
		return fullName.getText();
	}
	
	public String CheckEmail()
	{
		return emailAddress.getText();
	}
	
	public String FindUserName(String user, WebDriver driver)
	{
		
		WebElement userName = driver.findElement(By.linkText(user));
		
		return userName.getText();
	}
	
	public String CheckUserPage(String user, WebDriver driver)
	{
		WebElement userName = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]"));
		return userName.getText();
	}
	
}
