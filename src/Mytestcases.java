import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Mytestcases {

	WebDriver Driver = new ChromeDriver();
	//abohani

	String MyWebsite = "https://automationteststore.com/";

	String LoginOrRegister = "https://automationteststore.com/index.php?rt=account/login";

	String[] Firstnames = { "Mohammad", "Yazan", "Majdi" };
	String[] LastNames = { "Ahmad", "Amal", "Suha", "joj" };
	String GlobalUserName = "";
	Random rand = new Random();
	String GlobalPassword = "Asly";
	String GlobalUserNameForLogin = "";

	@BeforeTest
	public void Mysetup() {
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Driver.manage().window().maximize();
		Driver.navigate().to(MyWebsite);
		Driver.navigate().to(LoginOrRegister);

	}

	@Test(priority = 1)
	public void Mytest() throws InterruptedException {

		int RandomIndexForThefirstname = rand.nextInt(Firstnames.length);
		int RandomIndexForTheLastNames = rand.nextInt(Firstnames.length);
		int RandomNumberForEmail = rand.nextInt(4987);

		String domainName = "@gmail.com";

		String UserFirstName = Firstnames[RandomIndexForThefirstname];
		GlobalUserName = UserFirstName;
		String UserLastname = LastNames[RandomIndexForThefirstname];
		String Email = UserFirstName + UserLastname + RandomNumberForEmail + domainName;

		WebElement SignUpButton = Driver.findElement(By.xpath("//*[@id='accountFrm']/fieldset/button"));
		SignUpButton.click();

		WebElement UserName = Driver.findElement(By.id("AccountFrm_firstname"));
		UserName.sendKeys(UserFirstName);

		WebElement LastnName = Driver.findElement(By.id("AccountFrm_lastname"));
		LastnName.sendKeys(UserLastname);

		WebElement EmailInput = Driver.findElement(By.id("AccountFrm_email"));

		EmailInput.sendKeys(Email);

		WebElement Address = Driver.findElement(By.id("AccountFrm_address_1"));

		Address.sendKeys(" Amman-Marka");

		WebElement City = Driver.findElement(By.id("AccountFrm_city"));

		City.sendKeys("Capital cITY");

		WebElement Country = Driver.findElement(By.id("AccountFrm_country_id"));

		Select Selector2 = new Select(Country);

		int randomCountry = rand.nextInt(1, 240);

		Selector2.selectByIndex(randomCountry);
		Thread.sleep(2000);

		WebElement Region = Driver.findElement(By.id("AccountFrm_zone_id"));

		Select Selector = new Select(Region);

		int randomRegion = rand.nextInt(1, 10);

		Selector.selectByIndex(randomRegion);

		WebElement ZipCode = Driver.findElement(By.id("AccountFrm_postcode"));
		ZipCode.sendKeys("13310");

		WebElement LoginName = Driver.findElement(By.id("AccountFrm_loginname"));

		String LocalUserName = UserFirstName + UserLastname + RandomNumberForEmail;

		LoginName.sendKeys(LocalUserName);

		GlobalUserNameForLogin = LocalUserName;

		WebElement passwordField = Driver.findElement(By.id("AccountFrm_password"));
		passwordField.sendKeys(GlobalPassword);
		WebElement ConfirmPassword = Driver.findElement(By.id("AccountFrm_confirm"));
		ConfirmPassword.sendKeys(GlobalPassword);

		WebElement PrivacyPolicy = Driver.findElement(By.id("AccountFrm_agree"));

		PrivacyPolicy.click();

		WebElement ContinueButton = Driver.findElement(By.xpath("//*[@id=\"AccountFrm\"]/div[5]/div/div/button"));

		ContinueButton.click();

		Thread.sleep(2000);

	}

	@Test(priority = 2)
	public void Logout() throws InterruptedException {
		WebElement NavButton = Driver.findElement(By.id("customernav"));
		Actions action = new Actions(Driver);
		action.moveToElement(NavButton).perform();

		Thread.sleep(2000);

		Driver.findElement(By.linkText("Not " + GlobalUserName + "? Logoff")).click();

		Thread.sleep(5000);
	}

	@Test(priority = 3)

	public void LogIn() throws InterruptedException {
		Driver.navigate().to(LoginOrRegister);

		Driver.findElement(By.linkText("Login or register")).click();

		WebElement LogInInput2 = Driver.findElement(By.id("loginFrm_loginname"));
		LogInInput2.sendKeys(GlobalUserNameForLogin);
		WebElement PasswordInput = Driver.findElement(By.id("loginFrm_password"));

		PasswordInput.sendKeys(GlobalPassword);
		Thread.sleep(2000);

		WebElement LoginButton = Driver.findElement(By.xpath("//*[@id=\"loginFrm\"]/fieldset/button"));
		LoginButton.click();
	}

	@Test(priority = 4)
	public void AddItemToTheCart() throws InterruptedException {

		Thread.sleep(3000);

		String[] WebSiteForItem = { "https://automationteststore.com/index.php?rt=product/category&path=68",

				"https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=65" };

		int RandomIndex = rand.nextInt(WebSiteForItem.length);
		Driver.get(WebSiteForItem[RandomIndex]);

		WebElement ListOfItem = Driver.findElement(By.cssSelector(".thumbnails.row"));

		int TotalNumberOfItems = ListOfItem.findElements(By.tagName("li")).size();

		int RandmIndexForTheItem = rand.nextInt(TotalNumberOfItems);
		Thread.sleep(2000);

		ListOfItem.findElements(By.tagName("li")).get(RandmIndexForTheItem).click();

		WebElement Container = Driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));

		int numberofproduct = Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
		int randonindexfortheproduct = rand.nextInt(numberofproduct);
		Thread.sleep(2000);

		Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(randonindexfortheproduct).click();

		Thread.sleep(3000);
		
		WebElement UlList = Driver.findElement(By.className("productpagecart"));
		
		int ListItem = UlList.findElements(By.tagName("li")).get(0).findElements(By.tagName("a")).size();
				
				
				
				if (ListItem>0)
				{Driver.get(MyWebsite);
				System.out.println("The item is not exist");

				
				String ExpectedResult = "https://automationteststore.com/";
				String ActualResult  = Driver.getCurrentUrl();
				Assert.assertEquals(ActualResult, ExpectedResult);
				 
				
				
				
					
				}else 	{Driver.findElement(By.className("cart")).click();

				Thread.sleep(2000);
				String ActualResult = Driver.findElement(By.className("heading1")).getText();
				String ExpectedResult = "shoping cart"; 
				Assert.assertEquals(ActualResult, ExpectedResult.toUpperCase());
				
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
	
			
			
			
			
			
		}
		
		
		
		
		
	}


