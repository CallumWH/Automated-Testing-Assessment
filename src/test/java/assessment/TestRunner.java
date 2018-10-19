	package assessment;

	import static org.junit.Assert.*;

	import java.io.IOException;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import assessment.InputDetails;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

	public class TestRunner 
	{
		public WebDriver driver = null;
		public static ExtentReports report;
		public static ExtentTest test;
		static int runs = 0;
		
		String userParameter;
		String passwordParameter;
		String passwordConfirmParameter;
		String nameParameter;
		
		@Before
		public void setup()
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
			driver = new ChromeDriver();
			if(runs < 1)
			{
				report = new ExtentReports("C:\\Users\\Admin\\Desktop\\AssessmentReport.html", true);
			}
			runs++;
		}
		
		@After
		public void tearDown()
		{
			driver.quit();
		}
		
		@Given("^that you are on the create UserScreen$")
		public void that_you_are_on_the_create_UserScreen() throws Throwable {
			//login
			driver.get("http://localhost:8080/login?from=%2F");
			
			test = report.startTest("Logging in to website");
			
			Login login = PageFactory.initElements(driver, Login.class);
			login.LogIntoWebsite("Admin", "password", driver);
			
			test.log(LogStatus.INFO, "Accessing Manageing");		
			login.GoToManage(driver);
			
			test.log(LogStatus.INFO, "Accessing UserManaging");	
			login.GoToManageUsers(driver);
			
			test.log(LogStatus.INFO, "Accessing user creatioN");
			login.GoToCreateUsers(driver);
			
			if(driver.getCurrentUrl().equals("http://localhost:8080/securityRealm/addUser"))
			{
				test.log(LogStatus.PASS, "User Creation Reached");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Navigation Failed");
			}
			
			assertEquals("http://localhost:8080/securityRealm/addUser", driver.getCurrentUrl());
			
			
			report.endTest(test);
			report.flush();
		}

		@When("^the User details are entered on the Create UserScreen$")
		public void the_User_details_are_entered_on_the_Create_UserScreen() throws Throwable 
		{
			test = report.startTest("input user details");
			InputDetails userInfoPage = PageFactory.initElements(driver, InputDetails.class);
			
			
			userInfoPage.SubmitUser("User", "Password", "email@email.com", driver);
			
			test.log(LogStatus.INFO, "Info Input Complete");
			report.flush();
			report.endTest(test);
			
			
		}

		@When("^the details are submitted on the Create UserScreen$")
		public void the_details_are_submitted_on_the_Create_UserScreen() throws Throwable 
		{
			test = report.startTest("Submiting details");
			
			test.log(LogStatus.INFO, "Begining submission of details");	
			InputDetails userInfoPage = PageFactory.initElements(driver, InputDetails.class);
			userInfoPage.Submit(driver);	
			
			if(driver.getCurrentUrl().equals("http://localhost:8080/securityRealm/"))
			{
				test.log(LogStatus.PASS, "Details Submited");
			}
			else
			{
				test.log(LogStatus.FAIL, "Details Failed to Submited");
			}
			
			report.flush();
			report.endTest(test);
		}

		@Then("^the Username should be visible on the UsersScreen$")
		public void the_Username_should_be_visible_on_the_UsersScreen() throws Throwable 
		{
			String user = new String("User");
			driver.get("http://localhost:8080/securityRealm/");
			Checker check = PageFactory.initElements(driver, Checker.class);
			
			test = report.startTest("Check User DB for new entry");
			test.log(LogStatus.INFO, "Searching for user");
			
			if(check.FindUserName(user, driver).equals(user))
			{
				test.log(LogStatus.PASS, "User : " + user + " Found");
			}
			else
			{
				test.log(LogStatus.FAIL, "User : " + user + " not found");
			}
			
			report.flush();
			report.endTest(test);
			
			assertEquals(user, check.FindUserName(user, driver));
		}

		@When("^the User details \"([^\"]*)\" username, \"([^\"]*)\" password, \"([^\"]*)\" confirm Password, and \"([^\"]*)\" Fullname are entered on the Create UserScreen$")
		public void the_User_details_username_password_confirm_Password_and_Fullname_are_entered_on_the_Create_UserScreen(String arg1, String arg2, String arg3, String arg4) throws Throwable 
		{	
		    userParameter = arg1;
		    passwordParameter = arg2;
		    passwordConfirmParameter = arg3;
		    nameParameter = arg4;
		    
		    test = report.startTest("Parameterized inputs for : " + userParameter);
		    
		    InputDetails input = PageFactory.initElements(driver, InputDetails.class);   
		    
		    input.SubmitUser(userParameter, passwordParameter, passwordConfirmParameter, "email@email.com", nameParameter, driver);
		    test.log(LogStatus.INFO, "Details Submited");
		    input.Submit(driver);
		    
		    report.flush();
		    report.endTest(test);
		    
		}

		@Then("^the \"([^\"]*)\" username should be visible on the UsersScreen$")
		public void the_username_should_be_visible_on_the_UsersScreen(String arg1) throws Throwable 
		{
			driver.get("http://localhost:8080/securityRealm/");
			Checker check = PageFactory.initElements(driver, Checker.class);
			
			test = report.startTest("Check Parameterized User DB for new entry for" + arg1);
			test.log(LogStatus.INFO, "Searching for user");
			
			if(check.FindUserName(arg1, driver).equals(arg1))
			{
				test.log(LogStatus.PASS, "User : " + arg1 + " Found");
			}
			else
			{
				test.log(LogStatus.FAIL, "User : " + arg1 + " not found");
			}
			
			report.flush();
			report.endTest(test);
			
			assertEquals(arg1, check.FindUserName(arg1, driver));
		}

		@Given("^the \"([^\"]*)\" username is visible on the UsersScreen$")
		public void the_username_is_visible_on_the_UsersScreen(String arg1) throws Throwable 
		{
			//login
			driver.get("http://localhost:8080/login?from=%2F");
			
			test = report.startTest("Logging in to website");
			
			Login login = PageFactory.initElements(driver, Login.class);
			login.LogIntoWebsite("Admin", "password", driver);
			driver.get("http://localhost:8080/securityRealm/");
			report.flush();
			report.endTest(test);
		}

		@When("^the \"([^\"]*)\" username is clicked on the UserScreen$")
		public void the_username_is_clicked_on_the_UserScreen(String arg1) throws Throwable 
		{
			InputDetails input = PageFactory.initElements(driver, InputDetails.class);  
			input.clickUserName(arg1, driver);
		}

		@Then("^the User Profile should display the \"([^\"]*)\" username on the ProfileScreen$")
		public void the_User_Profile_should_display_the_username_on_the_ProfileScreen(String arg1) throws Throwable 
		{

			Checker check = PageFactory.initElements(driver, Checker.class);
			test = report.startTest("Selecting " + arg1);
			
			if(check.CheckUserPage(arg1, driver).equals("Jenkins User ID: " + arg1))
			{
				test.log(LogStatus.PASS, arg1 + " was found!");
			}
			else
			{
				test.log(LogStatus.FAIL, arg1 + " was not found, " + check.CheckUserPage(arg1, driver) + " was found instead");
			}
			
			report.flush();
			report.endTest(test);
			
			assertEquals("Jenkins User ID: " + arg1, check.CheckUserPage(arg1, driver));
		    
		}

		@Given("^the \"([^\"]*)\" Username's profile page has been loaded$")
		public void the_Username_s_profile_page_has_been_loaded(String arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@Given("^the configure button has been clicked on the profile page$")
		public void the_configure_button_has_been_clicked_on_the_profile_page() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@When("^I change the old FullName on the Configure Page to a new FullName \"([^\"]*)\"$")
		public void i_change_the_old_FullName_on_the_Configure_Page_to_a_new_FullName(String arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@When("^I save the changes to the Configure Page$")
		public void i_save_the_changes_to_the_Configure_Page() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@Then("^the Configure Page should show the NewFullName \"([^\"]*)\"$")
		public void the_Configure_Page_should_show_the_NewFullName(String arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}
		
	}


