package assessment;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class CheckTable 
{
	@FindBy (xpath = "//*[@id=\"yui-gen2-button\"]")
	private WebElement table
	;
	public static ExtentReports report;
	public static ExtentTest test;
	
	public boolean CheckTable(String username, WebDriver driver)
	{
		try
		{
			driver.findElement(By.partialLinkText("http://localhost:8080/securityRealm/user/admin/"));
		}
		catch(Exception e)
		{
			System.out.println("cannot find element");
		}
		
		return false;
	}
}
